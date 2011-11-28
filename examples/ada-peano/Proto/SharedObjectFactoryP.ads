with SharedObjectP; use SharedObjectP;


package SharedObjectFactoryP is 

	type SharedObjectPtr is access all SharedObject'Class;
	pragma Controlled (SharedObjectPtr) ;

	type SharedObjectEntry is  
	record
	Next : access SharedObjectEntry := null;
	Element : SharedObjectPtr := null ;
	end record;

	type SharedObjectEntryPtr is access all SharedObjectEntry;	
	pragma Controlled (SharedObjectEntryPtr) ;

	type HashTable is array (Integer range <>) of access SharedObjectEntry;

	type SharedObjectFactory is record
	Size : Natural := 0 ;
	LogSize : Natural := 10 ;
	table : HashTable ( 0 .. 99) ;
	end record;


	function projector(this: SharedObjectFactory; entree: Integer) return Integer;

	procedure build(this:in out SharedObjectFactory; prototype: in SharedObject'Class; foundObj: out SharedObjectPtr; foundObjPhy : out SharedObject'Class) ;

	procedure stats(this: SharedObjectFactory) ;

end SharedObjectFactoryP;

