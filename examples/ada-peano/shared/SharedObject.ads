package SharedObject is 
	type SharedObject is interface;

	function duplicate(this: SharedObject'Class) return SharedObject'Class is abstract;
	function equivalent(this: SharedObject'Class, o: SharedObject'Class) is abstract;
	function hashCode(this: SharedObject'Class) return Integer is abstract;

end SharedObject;


