package templates

class Imports {
	
//	«DEFINE imports FOR Mapping»«FOREACH getAllRootPackages() AS p»«EXPAND imports(prefix) FOR p»«ENDFOREACH»«ENDDEFINE»
//«DEFINE importsWithUtils FOR Mapping»«FOREACH getAllRootPackages() AS p»«EXPAND importsWithUtils(prefix) FOR p»«ENDFOREACH»«ENDDEFINE»
//
//
//«DEFINE imports(String prefix) FOR EPackage»
//«IF EClassifiers.size>0-»
//import «getPackagePrefix(prefix)»«name».*;
//«ENDIF»
//«FOREACH ESubpackages AS p»«EXPAND imports(getPackagePrefix(prefix)+name) FOR p»«ENDFOREACH»
//«ENDDEFINE»
//
//«DEFINE importsWithUtils(String prefix) FOR EPackage»
//«IF EClassifiers.size>0-»
//import «getPackagePrefix(prefix)»«name».*;
//import «getPackagePrefix(prefix)»«name».util.*;
//«ENDIF»
//«FOREACH ESubpackages AS p»«EXPAND importsWithUtils(getPackagePrefix(prefix)+name) FOR p»«ENDFOREACH»
//«ENDDEFINE»
	
}