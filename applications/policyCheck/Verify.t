import java.util.LinkedList;
import java.util.ArrayList;


import verify.example.*;
import verify.example.types.*;

public class Verify{
	%include { sl.tom }
	%gom{ 
    module Example 
      imports int
      abstract syntax
      
      //Security  levels : TS Top Secret, S Secret, C Confidential.
      SecurityLevel = TS() | S() | C()
      
      //Subject who has an ID for identification and a security level SL.
      Subject = subject(ID:int,SL:SecurityLevel)
      
      //Object who has an ID for identification and a security level SL.
      SecurityObject = securityObject(ID:int,SL:SecurityLevel)
      
      //Access mode, in this case could be read or write; others can be added
      AccessMode = read()|write()
      
      //Request type: 
      // add - to request the addition of an access,  
      // delete - to request the deletion of an access
      RequestType = add()|delete()
      
      // Sort of expression, explicit to specify that the access is explicit or conversely implicit
      // to specify that the access is implicit
      AccessType =explicit()|implicit()
      
      //An access is defined by the "subject" that accesses  the  "object" in "access mode" 
      //It can be explicitly requested by the subjects or an implicit consequence of other accesses
      Access = access(subject:Subject,securityObject:SecurityObject,A:AccessMode,E:AccessType)
      
      //Request, composed of a request type and an access
      Request = request(RT:RequestType, Ac:Access)
      
      //List of accesses
      ListOfAccesses =accesses(Access *)
      
      //State compose of a list of read accesses (Reads) and a list of write accesses(Write)
      // !!!!!!!!!!!!!! The AccessMode flag is not really necessary then !!!!!!!!!!!!!!
      State = state(Reads: ListOfAccesses,Writes: ListOfAccesses)
      
      //Sort representing a request upon a state
      RequestUponState = rus(R:Request,S:State)

      }

  

  int numberOfSubjects;
  int numberOfObjects;
  int numberOfSecurityLevels;
  int numberOfAccessModes;

//   verify.example.types.state.state stateToVerify;
  State stateToVerify;
  ArrayList<ArrayList<Integer>> subjectSets;
  ArrayList<ArrayList<Integer>> objectSets;
  ArrayList<ArrayList<Integer>> subjecSetsXobjectSetSets;
  ArrayList<ArrayList<Integer>> allRequests;
  ArrayList<verify.example.types.RequestUponState> implicitRequestsUponOriginalState;
  boolean LeakageDetected=false;
  RequestUponState CurrentRequestOfScenario;
  
  
  public Verify(int numberOfSubjects, int numberOfObjects, int numberOfSecurityLevels, int numberOfAccessModes){
    this.numberOfSubjects=numberOfSubjects;
    this.numberOfObjects=numberOfObjects;
    this.numberOfSecurityLevels=numberOfSecurityLevels;
    this.numberOfAccessModes=numberOfAccessModes;
  }




public Response transition(RequestUponState req){
%match (req){
			rus(request(add(),access(subject(i1,l1),securityObject(_,l2),read(),_)),s0@state(_,accesses(_*,access(subject(i1,l1),securityObject(i3,l3),write(),_),_*)))-> { if (!(compare(`l2,`l3))){return new Response(false,`s0);} }
			rus(request(add(),access(subject(_,l1),securityObject(_,l2),read(),_)),s1)-> { if (!(compare(`l2,`l1))){return new Response(false,`s1);} }
			rus(request(add(),a@access(_,_,read(),_)),state(e,i))-> { return new Response(true,`state(accesses(a,e),i)); }
			rus(request(add(),access(subject(i1,l1),securityObject(_,l2),write(),_)),
                s2@state(accesses(_*,access(subject(i1,l1),securityObject(_,l3),read(),_),_*),_))-> { if (!(compare(`l3,`l2))){return new Response(false,`s2);} }
			rus(request(add(),a@access(_,_,write(),_)),state(i,e))-> {return new Response(true,`state(i,accesses(a,e))); }
			rus(request(delete(),a),state(accesses(X*,a,Y*),i))-> { return new Response(true,`state(accesses(X*,Y*),i)); }
            rus(request(delete(),a),state(i,accesses(X*,a,Y*)))-> { return new Response(true,`state(i,accesses(X*,Y*))); }
}
throw new RuntimeException("should not be there");
}



public Response transitionold(RequestUponState req){
%match (req){
			rus(request(add(),access(subject(i1,l1),securityObject(_,l2),read(),_)),s0@state(_,accesses(_*,access(subject(i1,l1),securityObject(i3,l3),write(),_),_*)))-> { if (!(compare(`l2,`l3))){return new Response(false,`s0);} }
			rus(request(add(),access(subject(i1,l1),securityObject(_,l2),read(),_)),s1)-> { if (!(compare(`l2,`l1))){return new Response(false,`s1);} }
			rus(request(add(),a@access(subject(i1,l1),securityObject(_,l2),read(),_)),state(e,i))-> { return new Response(true,`state(accesses(a,e),i)); }
			rus(request(add(),access(subject(i1,l1),securityObject(_,l2),write(),_)),
                s2@state(accesses(_*,access(subject(i1,l1),securityObject(i3,l3),read(),_),_*),_))-> { if (!(compare(`l3,`l2))){return new Response(false,`s2);} }
			rus(request(add(),a@access(subject(i1,l1),securityObject(_,l2),write(),_)),state(i,e))-> {return new Response(true,`state(i,accesses(a,e))); }
			rus(request(delete(),access(subject(i1,l1),securityObject(_,l2),read(),_)),
                state(accesses(X*,access(subject(i1,l1),securityObject(i2,l2),read(),_),Y*),i))-> { return new Response(true,`state(accesses(X*,Y*),i)); }
            rus(request(delete(),access(subject(i1,l1),securityObject(_,l2),write(),_)),
                state(i,accesses(X*,access(subject(i1,l1),securityObject(i2,l2),write(),_),Y*)))-> { return new Response(true,`state(i,accesses(X*,Y*))); }
}
throw new RuntimeException("should not be there");
}


public static boolean compare(SecurityLevel l1,SecurityLevel l2){
%match (l1,l2){
			TS(),S() -> { return false; }
			TS(),C() -> { return false; }
			S(),C() -> { return false; }
			_,_ -> { return true; }
			
}
throw new RuntimeException("should not be there");
}




//public void generateSets(int numberOfSubjects, int numberOfObjects, int numberOfSecurityLevels, int numberOfAccessModes){
public void generateSets(){
Combinatory CSubjectSets=new Combinatory(numberOfSubjects,numberOfSecurityLevels-1);
CSubjectSets.start();
try {
	CSubjectSets.join();
} catch (Exception e) {
	System.out.println("join on combination");
}
subjectSets=CSubjectSets.combination;

Combinatory CObjectSets=new Combinatory(numberOfObjects,numberOfSecurityLevels-1);
CObjectSets.start();
try {
	CObjectSets.join();
} catch (Exception e) {
	System.out.println("join on combination");
}
objectSets=CObjectSets.combination;

LinkedList sizeOfsets=new LinkedList();
sizeOfsets.add(subjectSets.size());
sizeOfsets.add(objectSets.size());
DirectProduct DPsubjecSetsXobjectSetSets=new DirectProduct(sizeOfsets);
DPsubjecSetsXobjectSetSets.compute();
subjecSetsXobjectSetSets=DPsubjecSetsXobjectSetSets.ProductsList;

LinkedList sizeOfsets2=new LinkedList();
sizeOfsets2.add(numberOfSubjects);
sizeOfsets2.add(numberOfObjects);
sizeOfsets2.add(numberOfAccessModes);
DirectProduct DPsubjecXobjectXaccessMode=new DirectProduct(sizeOfsets2);
DPsubjecXobjectXaccessMode.compute();
allRequests=DPsubjecXobjectXaccessMode.ProductsList;
}


 %strategy makeExplicit() extends `Identity() {
    visit State {
    	state(reads@accesses(X1*,access(s1,o1,read(),_),X2*,access(s2,o2,read(),_),X3*),
           writes@accesses(X4*,access(s,o,write(),_),X5*))->{
           if (`((s==s1 && o==o2))){
        	   verify.example.types.ListOfAccesses l=`accesses(X1*,X2*,X3*);
        	   boolean contains=false;
        	   %match(l){
        		   accesses(X*,access(s3,o3,read(),_),Y*) ->{
        			   if (`s2==`s3 && `o1==`o3){
        				   contains=true;
        			   }
        		   }
        	   } 
        	   if (contains) return `state(reads,writes);
        	   else return `state(accesses(access(s2,o1,read(),implicit()),reads),writes);
           }else  if (`((s==s2 && o==o1))){
        	   verify.example.types.ListOfAccesses l=`accesses(X1*,X2*,X3*);
        	   boolean contains=false;
        	   %match(l){
        		   accesses(X*,access(s3,o3,read(),_),Y*) ->{
        			   if (`s1==`s3 && `o2==`o3){
        				   contains=true;
        			   }
        		   }
        	   } 
        	   if (contains) return `state(reads,writes);
        	   else return `state(accesses(access(s1,o2,read(),implicit()),reads),writes);
           }
 }  
  }
}

public boolean Verification(State setOfAccesses){
try {
	State res=(State)`RepeatId(makeExplicit()).visit(setOfAccesses);
	implicitRequestsUponOriginalState=new ArrayList<verify.example.types.RequestUponState>();
%match(res){
	state(e,accesses(_*,a@access(_,_,_,implicit()),_*))->{implicitRequestsUponOriginalState.add(`rus(request(add(),a),setOfAccesses));}
	state(accesses(_*,a@access(_,_,_,implicit()),_*),e)->{implicitRequestsUponOriginalState.add(`rus(request(add(),a),setOfAccesses));}
}
for(java.util.Iterator iterator=implicitRequestsUponOriginalState.iterator(); iterator.hasNext();){
	RequestUponState iruos=(RequestUponState)iterator.next();
	if (!(transition(iruos).getGranted())){
		CurrentRequestOfScenario=iruos;
		System.out.println("Scenario detected :"+CurrentRequestOfScenario);
		return false;
	}
}


} catch (Exception e) {
	System.out.println("A problem occured while applying strategy");
}

return true;

}

public boolean check(ArrayList<Subject> Subjects, ArrayList<SecurityObject> Objects, int[] permutationOfRequests){
State M=`state(accesses(),accesses());
for (int i = 0; i < permutationOfRequests.length; i++) {
//System.out.println("permutationOfRequests :"+(i+1)+"/"+permutationOfRequests.length);
ArrayList<Integer> requestIndexes=allRequests.get(permutationOfRequests[i]);
Access a=`access(Subjects.get(requestIndexes.get(0)),Objects.get(requestIndexes.get(1)),((requestIndexes.get(2)==0)?(read()):(write())),explicit());
Request r=`request(add(),a);
RequestUponState rus=`rus(r,M);
Response response=transition(rus);
if (response.getGranted())M=response.getState();
if (Verification(M)==false){
System.out.println("Information leakage detected");
return false;}
}
System.out.println("No information leakage detected");
return true;
}

public int[] checkAllPermutationsOfRequests(ArrayList<Subject> Subjects, ArrayList<SecurityObject> Objects, PermutationGenerator PG){
while (PG.hasMore ()) {
	int[] currentPermutation=PG.getNext();
	if(!check(Subjects,Objects,currentPermutation)){
		return currentPermutation;
	}
	System.out.println("for permutation :"+toStringArray(currentPermutation));
}
int[] rep={0};
return rep;
}

public void checkRandomSets(){
//generateSets(numberOfSubjects,numberOfObjects,numberOfSecurityLevels,numberOfAccessModes);
generateSets();
ArrayList<Subject> Subjects=new ArrayList<Subject>();
ArrayList<SecurityObject> Objects=new ArrayList<SecurityObject>();
int indexSubjectSet=(int)(Math.random()*subjectSets.size());
int indexObjectSet=(int)(Math.random()*objectSets.size());
int i=0;
for (java.util.Iterator iterator = (subjectSets.get(indexSubjectSet)).iterator(); iterator.hasNext();) {
	Integer securityLevel = (Integer) iterator.next();
	switch (securityLevel) {
	case 0:
		Subjects.add(`subject(i,C()));
		break;
	case 1:
		Subjects.add(`subject(i,S()));
		break;
	case 2:
		Subjects.add(`subject(i,TS()));
		break;
	default:
		System.out.println("An error occured while attributing security levels for subjects");
		break;
	}

i++; 
}
System.out.println("Generated Subjects :"+Subjects);
i=0;
for (java.util.Iterator iterator = (objectSets.get(indexObjectSet)).iterator(); iterator.hasNext();) {
	Integer securityLevel = (Integer) iterator.next();
	switch (securityLevel) {
	case 0:
		Objects.add(`securityObject(i,C()));
		break;
	case 1:
		Objects.add(`securityObject(i,S()));
		break;
	case 2:
		Objects.add(`securityObject(i,TS()));
		break;
	default:
		System.out.println("An error occured while attributing security levels for objects");
		break;
	} 
i++;
}
System.out.println("Generated Objects :"+Objects);
PermutationGenerator PG= new PermutationGenerator (allRequests.size());
int[] o=checkAllPermutationsOfRequests(Subjects,Objects,PG);
if (o.length==1){
	System.out.println("No leakage detected for all permutations");
}else{
	//System.out.println("subjectSet: ="+subjectSets.get(indexSubjectSet));
	//System.out.println("objectSet: "+objectSets.get(indexObjectSet));
}

}


//public void checkAllSets(int numberOfSubjects, int numberOfObjects, int numberOfSecurityLevels, int numberOfAccessModes){
public boolean checkSpecificSets(int iss,int ios){
//generateSets(numberOfSubjects,numberOfObjects,numberOfSecurityLevels,numberOfAccessModes);
generateSets();
int indexSubjectSet=iss;
int indexObjectSet=ios;
System.out.println("current Subject Set :"+indexSubjectSet);
System.out.println("current Object Set :"+indexObjectSet);
ArrayList<Subject> Subjects=new ArrayList<Subject>();
ArrayList<SecurityObject> Objects=new ArrayList<SecurityObject>();
int i=0;
for (java.util.Iterator iterator = (subjectSets.get(indexSubjectSet)).iterator(); iterator.hasNext();) {
	Integer securityLevel = (Integer) iterator.next();
	switch (securityLevel) {
	case 0:
		Subjects.add(`subject(i,C()));
		break;
	case 1:
		Subjects.add(`subject(i,S()));
		break;
	case 2:
		Subjects.add(`subject(i,TS()));
		break;
	default:
		System.out.println("An error occured while attributing security levels for subjects");
		break;
	} 
i++;
}
i=0;
for (java.util.Iterator iterator = (objectSets.get(indexObjectSet)).iterator(); iterator.hasNext();) {
	Integer securityLevel = (Integer) iterator.next();
	switch (securityLevel) {
	case 0:
		Objects.add(`securityObject(i,C()));
		break;
	case 1:
		Objects.add(`securityObject(i,S()));
		break;
	case 2:
		Objects.add(`securityObject(i,TS()));
		break;
	default:
		System.out.println("An error occured while attributing security levels for objects");
		break;
	} 
i++;
}

PermutationGenerator PG= new PermutationGenerator (allRequests.size());
int[] o=checkAllPermutationsOfRequests(Subjects,Objects,PG);
System.out.println("For subjects :\n"+Subjects);
System.out.println("and objects :\n"+Objects);
if (o.length==1){
	System.out.println("No leakage detected for all permutations");
}else{
	LeakageDetected=true;
	System.out.println("Leakage detected for permutations :\n"+toStringArray(o));
	return false;
}



return true;
}






public boolean checkAllSets(){
//generateSets(numberOfSubjects,numberOfObjects,numberOfSecurityLevels,numberOfAccessModes);
generateSets();
for(int indexSubjectSet=0; indexSubjectSet<subjectSets.size();indexSubjectSet++){
for(int indexObjectSet=0; indexObjectSet<objectSets.size();indexObjectSet++){
System.out.println("current Subject Set :"+indexSubjectSet);
System.out.println("current Object Set :"+indexObjectSet);
ArrayList<Subject> Subjects=new ArrayList<Subject>();
ArrayList<SecurityObject> Objects=new ArrayList<SecurityObject>();
int i=0;
for (java.util.Iterator iterator = (subjectSets.get(indexSubjectSet)).iterator(); iterator.hasNext();) {
	Integer securityLevel = (Integer) iterator.next();
	switch (securityLevel) {
	case 0:
		Subjects.add(`subject(i,C()));
		break;
	case 1:
		Subjects.add(`subject(i,S()));
		break;
	case 2:
		Subjects.add(`subject(i,TS()));
		break;
	default:
		System.out.println("An error occured while attributing security levels for subjects");
		break;
	} 
i++;
}
i=0;
for (java.util.Iterator iterator = (objectSets.get(indexObjectSet)).iterator(); iterator.hasNext();) {
	Integer securityLevel = (Integer) iterator.next();
	switch (securityLevel) {
	case 0:
		Objects.add(`securityObject(i,C()));
		break;
	case 1:
		Objects.add(`securityObject(i,S()));
		break;
	case 2:
		Objects.add(`securityObject(i,TS()));
		break;
	default:
		System.out.println("An error occured while attributing security levels for objects");
		break;
	} 
i++;
}

PermutationGenerator PG= new PermutationGenerator (allRequests.size());
int[] o=checkAllPermutationsOfRequests(Subjects,Objects,PG);
System.out.println("For subjects :\n"+Subjects);
System.out.println("and objects :\n"+Objects);
if (o.length==1){
	System.out.println("No leakage detected for all permutations");
}else{
	LeakageDetected=true;
	System.out.println("Leakage detected for permutations :\n"+toStringArray(o));
	return false;
}


}}
return true;
}

public static void main(String[] args) {
//Verify(int numberOfSubjects, int numberOfObjects, int numberOfSecurityLevels, int numberOfAccessModes);
Verify Verification=new Verify(2,2,2,2);
//Verification.checkSpecificSets(0,1);
Verification.checkAllSets();
System.out.println(((Verification.LeakageDetected)?"a leakage is detected":"no leakage is detected"));
}

public static String toStringArray(int[] t){
String result="[";
if (t.length>0){
	result+=t[0];
	for (int i = 1; i < t.length; i++) {
		result+=", "+t[i];
	}
}
result+="]";
return result;
}

}

