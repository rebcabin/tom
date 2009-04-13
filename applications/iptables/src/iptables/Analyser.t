package iptables;

import iptables.ast.types.*;
import tom.library.sl.*; 
import java.util.*;

public class Analyser {
	%include {iptables/ast/Ast.tom}
	%include {sl.tom}

	public static final int NOT_COMPARABLE = -2;

	public static Rules checkIntegrity(Rules rs) {
		%match(rs) {
			rules(
				X*,
				r1@rule(action1,iface,proto,target,srcaddr1,dstaddr1,srcport,dstport),
				Y*,
				r2@rule(action2,iface,proto,target,srcaddr2,dstaddr2,srcport,dstport),
				Z*
			) -> {
				/* looking for equivalence in the rules */
				if (isEquiv(`srcaddr1,`srcaddr2) && isEquiv(`dstaddr1,`dstaddr2)) {
					if (`action1 == `action2) {
						System.out.println("doubloon: " + `r1);
						rs = `rules(X*,r1,Y*,Z*);
					} else
						System.err.println("conflicting rules:" + `r1 + "\t/\t" + `r2);
				}
			}
		}
		return rs;
	}

	public static Rules checkOptimization(Rules rs) {
		%match(rs) {
			rules(X*,r1,Y*,r2,Z*) -> {
				/* looking for inclusions optimizations */
				int i = compareTo(`r1,`r2);
				if (i == 1) {
					System.out.println("optimization: " + `r2);
					rs = `rules(X*,r1,Y*,Z*);
				} else if (i == -1) {
					System.out.println("optimization: " + `r1);
					rs = `rules(X*,Y*,r2,Z*);
				} else if (i == 0) {
					System.out.println("optimization-doubloon: " + `r1);
					rs = `rules(X*,r1,Y*,Z*);
				}
			}
		}
		return rs;
	}

	public static boolean isEquiv(Address a1, Address a2) {
		%match(a1,a2) {
			AddrAny(),AddrAny() -> { return true; }
			Addr4(ip1,smask1),Addr4(ip2,smask2) && (smask1 == smask2) -> {
				if ((`ip1 & `smask1) == (`ip2 & `smask2))
					return true;
			}
			Addr6(ipms1,ipls1,smaskms1,smaskls1),Addr6(ipms2,ipls2,smaskms2,smaskls2) 
			&& (smaskms1 == smaskms2) && (smaskls1 == smaskls2) -> {
				if (((`ipms1 & `smaskms1) == (`ipms2 & `smaskms2)) 
				&& ((`ipls1 & `smaskls1) == (`ipls2 & `smaskls2))) {
					return true;
				}
			}
		}
		return false;
	}

	/* 	returns:
			0 if Rules are equals, 
			1 if r2 is included in r1,
			-1 if r1 include in r2
			NOT_COMPARABLE if the 2 rules are not comparable
	*/
	public static int compareTo(Rule r1, Rule r2) {
		%match(r1,r2) {
			rule(action,iface,proto,target,srcaddr1,dstaddr1,srcport,dstport),
			rule(action,iface,proto,target,srcaddr2,dstaddr2,srcport,dstport) -> {
				int i1 = compareTo(`srcaddr1,`srcaddr2),
					i2 = compareTo(`dstaddr1,`dstaddr2);
				if ((i1 != NOT_COMPARABLE) && (i2 != NOT_COMPARABLE)) {
					if (i1 == 0) {
						return i2;
					} else if (i2 == 0) {
						return i1;
					} else {
						if ((i1 > 0) && (i2 > 0))
							return 1;
						else if ((i1 < 0) && (i2 < 0))
							return -1;
					}
				}
			}
		}
		return NOT_COMPARABLE;
	}

	/* 	returns:
			0 if Addresses are equals, 
			1 if a2 is included in a1,
			-1 if a1 include in a2
			NOT_COMPARABLE if the 2 addresses are not comparable
	*/
	public static int compareTo(Address a1, Address a2) {
		%match(a1,a2) {
			AddrAny(),AddrAny() -> { return 0; }
			Addr4(ip1,smask1),Addr4(ip2,smask2) -> {
				if (Math.abs(`smask1) < Math.abs(`smask2)) {
					if ((`ip2 & `smask1) == (`ip1 & `smask1))
						return 1;
				} else if (Math.abs(`smask1) > Math.abs(`smask2)) {
					if ((`ip1 & `smask2) == (`ip2 & `smask2))
						return -1;
				} else {
					if ((`ip1 & `smask1) == (`ip2 & `smask2))
						return 0;
				}
			}
			Addr6(ipms1,ipls1,smaskms1,smaskls1),Addr6(ipms2,ipls2,smaskms2,smaskls2) -> {
				if (`smaskms1 == `smaskms2) {
					/* if the most significant mask is filled with 1 bits,
					compare the less significant masks */
					if (`smaskms1 == (0x0L - 0x1L)) {
						if (Math.abs(`smaskls1) < Math.abs(`smaskls2)) {
							if ((`ipls2 & `smaskls1) == (`ipls1 & `smaskls1))
								return 1;
						} else if (Math.abs(`smaskls1) > Math.abs(`smaskls2)) {
							if ((`ipls1 & `smaskls2) == (`ipls2 & `smaskls2))
								return -1;
						} else {
							if ((`ipls1 & `smaskls1) == (`ipls2 & `smaskls1))
								return 0;
						}
					} else {
						if ((`ipms1 & `smaskms1) == (`ipms2 & `smaskms1))
							return 0;
					}
				} else if (Math.abs(`smaskms1) < Math.abs(`smaskms2)) {
					if ((`ipms2 & `smaskms1) == (`ipms1 & `smaskms1))
						return 1;
				} else if (Math.abs(`smaskms1) > Math.abs(`smaskms2)) {
					if ((`ipms1 & `smaskms2) == (`ipms2 & `smaskms2))
						return -1;
				}
			}
			/* >>>TODO: compare IPv4 and IPv6 (IPv4 can be written in IPv6) */
		}
		return NOT_COMPARABLE;
	}

	public static void main(String[] args) {
		Rule r1 = `rule(
			Accept(),
			Iface("eth0"),
			TCP(),
			In(),
			AddrAny(),
			Addr4((16+256+4096+65536),0xff000000),
			PortAny(),
			Port(80)
		);
		Rule r2 = `rule(
			Drop(),
			Iface("eth0"),
			TCP(),
			In(),
			AddrAny(),
			Addr4((16+256+4096+65536),0xff000000),
			PortAny(),
			Port(80)
		);
		Rule r3 = `rule(
			Accept(),
			Iface("eth0"),
			TCP(),
			In(),
			AddrAny(),
			Addr4((4096+65536),0xffff0000),
			PortAny(),
			Port(80)
		);

		Rules rs = 	`rules(r1,r2,r3),rsn;

		/* printing tests */
		System.out.println("\n#printing test: " +rs);

		/* isEquivAddress tests */
		Address a1,a2;
		a1 = `Addr4(256,0xffffff00);
		a2 = `Addr4(312,0xffffff00);
		System.out.println("\n# isEquivAddress test: isEquivaddr(" + `a1 + "," + `a2 + "):" + isEquiv(a1,a2));
		a1 = `Addr6(256,256,(0x0L - 0x1L),(0x0L - 0x100L));
		a2 = `Addr6(256,312,(0x0L - 0x1L),(0x0L - 0x100L));
		System.out.println("\n# isEquivAddress test: isEquivaddr(" + `a1 + "," + `a2 + "):" + isEquiv(a1,a2));

		/* checkIntegrity tests */
		System.out.println("\n# checkIntegrity test: doubloon");
		rsn = checkIntegrity(`rules(r1,r1));
		System.out.println("RSN: " + rsn);
		System.out.println("\n# checkIntegrity test: conflict");
		checkIntegrity(`rules(r1,r2));
		System.out.println("\n# checkIntegrity test: nothing wrong");
		checkIntegrity(`rules(r1,r3));
		System.out.println("\n# checkIntegrity test: doubloon & conflict");
		checkIntegrity(`rules(r1,r2,r3,r1));

		/* checkOptimization tests */
		System.out.println("\n# checkOptimization test");
		System.out.println("[[Rules: " + rs + "]]\n");
		rs = checkOptimization(rs);
		System.out.println("\n[[Rules: " + rs + "]]");
	}
}
