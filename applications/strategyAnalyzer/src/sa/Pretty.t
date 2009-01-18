package sa;

import sa.rule.types.*;
import tom.library.sl.*; 
import java.util.*;

public class Pretty {
  %include { rule/Rule.tom }
  %include { sl.tom }

  public static String toString(ExpressionList l) {
    StringBuffer sb = new StringBuffer();
    %match(l) {
      ExpressionList(_*,x,_*) -> {
        sb.append(Pretty.toString(`x)); 
        sb.append("\n");
      }
    }
    return sb.toString();
  }

  public static String toString(Expression e) {
    StringBuffer sb = new StringBuffer();
    %match(e) {
      Let(x,v,t) -> {
        sb.append("let ");
        sb.append(`x);
        sb.append(" = ");
        sb.append(Pretty.toString(`v));
        sb.append(" in ");
        sb.append(Pretty.toString(`t));
      }

      Set(rulelist) -> {
        sb.append("{ ");
        %match(rulelist) {
          RuleList(_*,x,end*) -> {
            sb.append(Pretty.toString(`x));
            if(!`end.isEmptyRuleList()) {
              sb.append(", ");
            }
          }
        }
        sb.append(" }");
      }

      Strat(s) -> {
        sb.append(`s);
      }
      
      Signature(symbollist) -> {
        sb.append("signature { ");
        %match(symbollist) {
          SymbolList(_*,Symbol(name,arity),end*) -> {
            sb.append(`name + ":" + `arity);
            if(!`end.isEmptySymbolList()) {
              sb.append(", ");
            }
          }
        }
        sb.append(" }");
      }


    }
    return sb.toString();
  }
  
  public static String toString(Rule r) {
    StringBuffer sb = new StringBuffer();
    %match(r) {
    Rule(lhs,rhs) -> {
      sb.append(Pretty.toString(`lhs));
      sb.append(" -> ");
      sb.append(Pretty.toString(`rhs));
    }
    }
    return sb.toString();
  }

  public static String toString(Term t) {
    StringBuffer sb = new StringBuffer();
    %match(t) {
      Var(n) -> { sb.append(`n); }

      BuiltinInt(n) -> { sb.append(`n); }

      Anti(p) -> { 
        sb.append("!"); 
        sb.append(Pretty.toString(`p));
      }

      Appl(symb,args) -> { 
        sb.append(`symb); 
        sb.append("(");
        %match(args) {
          TermList(_*,x,end*) -> {
            sb.append(Pretty.toString(`x));
            if(!`end.isEmptyTermList()) {
              sb.append(",");
            }
          }
        }
        sb.append(")");
      }
    }
    return sb.toString();
  }

  %typeterm HashSet {
          implement      { java.util.HashSet }
  }

  %strategy CollectVars(varSet:HashSet) extends Identity() {
    visit Term {
      Var(v)  -> {
        varSet.add(`v);
      }
    }
  }


  public static String generateAprove(Collection<Rule> bag, Map<String,Integer> sig) 
    throws VisitFailure{

    StringBuffer rulesb = new StringBuffer();

    HashSet<String> varSet = new HashSet<String>();
    Strategy cv = `CollectVars(varSet);

//     Set<String> symbols = sig.keySet();
//     sb.append("SYMBOLS(");
//     for(String name: symbols) {
//       sb.append(name + ",");
//     }
//     sb.deleteCharAt(sb.length()-1);
//     sb.append(")");

    rulesb.append("\nRULES(\n");
    for(Rule r:bag) {
      %match(r){
        Rule(lhs,rhs) -> {
          `BottomUp(cv).visit(`lhs);
        }
      }
      rulesb.append("        " + Pretty.toString(r) + "\n");
    }
    rulesb.append(")\n");

    StringBuffer varsb = new StringBuffer();
    varsb.append("VAR(");
    for(String name: varSet) {
      varsb.append(name + ",");
    }
    varsb.deleteCharAt(varsb.length()-1);
    varsb.append(")");

    return varsb.toString()+rulesb.toString();
  }  



  public static String generate(Collection<Rule> bag, Map<String,Integer> sig, String classname) {
    StringBuffer sb = new StringBuffer();
    String lowercaseClassname = classname.toLowerCase();
    sb.append(
        %[
import @lowercaseClassname@.m.types.*;
import java.io.*;
public class @classname@ {
  %gom {
    module m
      abstract syntax
      T = Bottom()
]%);
    for(String name: sig.keySet()) {
      int arity = sig.get(name);
      String args = "";
      for(int i=0 ; i<arity ; i++) {
        args += "kid"+i+":T";
        if(i+1<arity) { args += ","; }
      }
      sb.append("        | " + name + "(" + args + ")\n");
    }

    sb.append("      module m:rules() {\n");
    for(Rule r:bag) {
      sb.append("        " + Pretty.toString(r) + "\n");
    }
    sb.append(%[
    }
  }
  

  public static void main(String[] args) {
    try {
      BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
      T input = T.fromString(reader.readLine());
      T t = `@Compiler.getTopName()@(input);
      System.out.println(t);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
    
    ]%);

    return sb.toString();
  }

}

