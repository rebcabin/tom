// Licence
package tom.mapping.dsl.generator

import com.google.inject.Inject
import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EStructuralFeature
import org.eclipse.xtext.generator.IFileSystemAccess
import tom.mapping.model.ClassOperator
import tom.mapping.model.FeatureParameter
import tom.mapping.model.Mapping
import tom.mapping.model.Operator
import java.util.ArrayList
import org.eclipse.emf.ecore.EPackage
import tom.mapping.dsl.generator.tom.ParametersCompiler
import tom.mapping.dsl.generator.tom.TomFactoryCompiler
import tom.mapping.dsl.generator.tom.OperatorsCompiler

class FactoryCompiler {
	
	extension TomMappingExtensions = new TomMappingExtensions()
	
	@Inject TomFactoryCompiler tfc
	@Inject ImportsCompiler imc
	@Inject ParametersCompiler injpa
	@Inject OperatorsCompiler injop
	
	String prefix=""
	
	def compile(Mapping m, IFileSystemAccess fsa){
		fsa.generateFile(packageToPath(prefix)+"/"+m.name.toFirstLower()+"/"+m.factoryName()+".java", m.main())
		}
		
		
	def ArrayList<EPackage> intersectName(ArrayList<EPackage> listBase) { // Defined because of the intersections of "packageList" needed in function "main"
		var listDestination = new ArrayList<EPackage>();
		
		for(eltB : listBase) {
			var sameName = false;
			for(eltD : listDestination) {
				sameName = sameName || eltB.name == eltD.name;
				} 
			if(!sameName) {
				listDestination.add(eltB);
				}
			}
		return listDestination;
		}
	
	def main(Mapping map) {
		 tfc.main(map);
		 
		 '''
		 package «prefix.getPackagePrefix()»«map.name.toFirstLower()»;
		 
		 import java.util.List;
		 
		 «/* PROTECTED REGION ID(map.name+"_user_factory_imports") ENABLED START */»
		 // protected imports, you should add here required imports
		 // that won't be removed after regeneration of the mapping code
		 
		 «imc.imports(map)»
		 
		 «/* PROTECTED REGION END */»
		 
		 /**
		 * User factory for "name"
		 * -- Generated by TOM mapping EMF generator --
		 */
		 
		 public class «map.factoryName()» {
		 	
		 	«/* PROTECTED REGION ID(map.name+"_user_factory_instances") ENABLED START */»
		 	
		 	«var packageList = new ArrayList<EPackage>()»
		 	«FOR elt : map.operators.filter(typeof(ClassOperator))»
		 	«packageList.add(elt.class_.EPackage)»
		 	«ENDFOR»
		 	
		 	«FOR pack : packageList.intersectName()»
		 		public static «pack.name.toFirstUpper()»Factory «pack.name»Factory = «pack.name.toFirstUpper()»Factory.eINSTANCE
		 	«ENDFOR»
		 	
		 	«var packageList2 = new ArrayList<EPackage>()»
		 	«FOR elt : map.allDefaultOperators»
		 	«packageList2.add(elt.EPackage)»
		 	«ENDFOR»
		 	
		 	«FOR pack: packageList2.intersectName()»
		 		public static «pack.name.toFirstUpper()»Factory «pack.name»Factory = «pack.name.toFirstUpper()»Factory.eINSTANCE
		 	«ENDFOR»
		 }
		 
		 «/* PROTECTED REGION END */»
		 
		 // User operators «map.operators»
		 «FOR module: map.modules»{
		 	/** Module «module.name» **/
		 «FOR op: module.operators»
		 	// Operator «op.name»
		 	«map.operator(op)»
		 «ENDFOR»
		 «ENDFOR»
		 
		 /*
		 * Default TOM Operators for «map.name» mapping. Each class that has a terminal type has also a default factory function.
		 */
		 
		 «FOR op: map.allDefaultOperators» 
		 	«map.javaFactoryCreateDefaultOperator(op.name, op)»
		 «ENDFOR»
		 
		 /* PROTECTED REGION ID(map.name+"_mapping_user_custom_methods"+map.name) ENABLED START */
		 
		 /*
		 * Custom factory functions that won't be removed at regeneration of mapping code
		 */
		 
		 /* PROTECTED REGION END */
		 '''	 
	}
	

	def dispatch operator(Mapping map, Operator op) {}
	
	def dispatch operator(Mapping map, ClassOperator clop) {
		if(clop.parameters.size>0) {
			val parameters = clop.getCustomParameters();
			javaFactoryCreateOperatorWithParameters(parameters, clop);
		} else {
			map.javaFactoryCreateDefaultOperator(clop.name, clop.class_)
		}
	}
	
	
	def javaFactoryCreateOperatorWithParameters(Iterable<FeatureParameter> parameters, ClassOperator clop) { // List[] = Iterable<>
		'''
		public static «clop.class_.name» «clop.name.toFirstLower()»(
		«FOR p: parameters SEPARATOR ","»
		«injpa.javaFeatureParameter(p)»
		«ENDFOR»
		) {
			«clop.class_.name» o = «clop.class_.EPackage.name»Factory.create«clop.class_.name.toFirstUpper()»();
			«FOR p: parameters»
				«p.feature.structureFeatureSetter()»
			«ENDFOR»
			«FOR p: clop.getSettedCustomParameters()»
				o.set«p.feature.name.toFirstUpper()»(«injop.settedValue(p.feature, p.value)»);
			«ENDFOR»
			return o;
			}
		'''
	}
	
	
	def javaFactoryCreateDefaultOperator(Mapping mapping, String name, EClass ecl) {
		val parameters = ecl.getDefaultParameters(mapping);
		'''
		«IF !ecl.abstract && !ecl.interface»
			public static «ecl.name» «name.toFirstLower()»(«injop.javaClassAttributes(mapping, ecl)»
			«FOR param: parameters SEPARATOR ","» 
			«injpa.defaultJavaFeatureParameter(param)»
			«ENDFOR») {
				«ecl.name» o = «ecl.EPackage.name»Factory.create.«ecl.name.toFirstUpper()»();
				«FOR attribute : ecl.EAllAttributes»
					«attribute.structureFeatureSetter()»
				«ENDFOR»
				«FOR param: parameters»
					«param.structureFeatureSetter()»;
				«ENDFOR»
			return o;
			}	
		«ENDIF»
		'''
	}
	
	
	def structureFeatureSetter(EStructuralFeature esf){
		
		if(esf.many){
		'''
		«IF !esf.unsettable»
			for(int i = 0 ; i < «esf.name».size() ; ++i) {
				o.get«esf.name.toFirstUpper()»().add(_«esf.name».get(i));
				}
		«ENDIF»
				'''
			} else {
				'''
				«IF !esf.unsettable»
					o.set«esf.name.toFirstUpper()»(_«esf.name»);
				«ENDIF»
				'''
			}
		}
	
}