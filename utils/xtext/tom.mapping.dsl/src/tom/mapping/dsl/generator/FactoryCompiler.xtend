// Licence
package tom.mapping.dsl.generator

import com.google.inject.Inject
import java.util.List
import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EStructuralFeature
import org.eclipse.xtext.generator.IFileSystemAccess
import tom.mapping.dsl.generator.tom.OperatorsCompiler
import tom.mapping.dsl.generator.tom.TomFactoryCompiler
import tom.mapping.model.ClassOperator
import tom.mapping.model.FeatureParameter
import tom.mapping.model.Mapping
import tom.mapping.model.Operator
import tom.mapping.model.Parameter

class FactoryCompiler {
	
	extension TomMappingExtensions = new TomMappingExtensions()
	
	@Inject TomFactoryCompiler tfc
	@Inject ImportsCompiler imc
	@Inject Parameter injpa
	@Inject OperatorsCompiler injop
	
	String prefix=""
	
	def compile(Mapping m, IFileSystemAccess fsa){
		fsa.generateFile(packageToPath(prefix)+"/"+m.name.toFirstLower()+"/"+m.factoryName()+".java", m.main())
		}
	
	def main(Mapping map) {
		 tfc.main(map);
		 
		 '''
		 package �getPackagePrefix(prefix)��map.name.toFirstLower()�;
		 
		 import java.util.List;
		 
		 �/* PROTECTED REGION ID(map.name+"_user_factory_imports") ENABLED START */�
		 // protected imports, you should add here required imports
		 // that won't be removed after regeneration of the mapping code
		 
		 �imc.imports(map)�;
		 
		 �/* PROTECTED REGION END */�
		 
		 /**
		 * User factory for "name"
		 * -- Generated by TOM mapping EMF generator --
		 */
		 
		 public class �map.factoryName()� {'''
		 	
		 	/* PROTECTED REGION ID(map.name+"_user_factory_instances") ENABLED START */
		 	
		 	var packageList = map.operators.filter[e | e instanceof ClassOperator].reduce[f | f.class_.EPackage] // selectType, collect
		 	for(pack :packageList.intersect(packageList)) {
		 		'''public static �pack.name.toFirstUpper()�Factory �pack.name�Factory = �pack.name.toFirstUpper()�Factory.eINSTANCE;'''
		 	}
		 	
		 	var packageList2 = map.allDefaultOperators.filter[e | e.EPackage]
		 	for(pack: packageList2.filter[e | !packageList.filter(f | e.name == f.name && e!= f]) { // reject, select
		 		'''public static �pack.name.toFirstUpper()�Factory �pack.name�Factory = �pack.name.toFirstUpper()�Factory.eINSTANCE;'''
		 	}
		 
		 /* PROTECTED REGION END */
		 
		'''
		 // User operators �operators�
		 �for(module: map.modules)�{
		 	/** Module �module.name� **/
		 �for(op: module.operators){
		 	// Operator �op.name�;
		 	operator(map, op);
		 }� }
		 
		 /*
		 * Default TOM Operators for �name� mappin. Each class that has a terminal type has also a default factory function.
		 */
		 
		 �for(op: map.allDefaultsOperators)� {
		 	�javaFactoryCreateDefaultOperator(map,op.name, op)�;
		 }
		 
		 /* PROTECTED REGION ID(map.name+"_mapping_user_custom_methods"+map.name) ENABLED START */
		 
		 /*
		 * Custom factory functions that won't be removed at regeneration of mapping code
		 */
		 
		 /* PROTECTED REGION END */
		 '''	 
	}
	

	def operator(Mapping map, Operator op) {}
	
	def operator(Mapping map, ClassOperator clop) {
		if(clop.parameters.size>0) {
			val parameters = getCustomParameters(clop);
			javaFactoryCreateOperatorWithParameters(clop.parameters, clop); // Pourquoi le "parameters" ne passe-t-il pas ?
		} else {
			javaFactoryCreateDefaultOperator(map, clop.name, clop.class_)
		}
	}
	
	
	def javaFactoryCreateOperatorWithParameters(List<FeatureParameter> parameters, ClassOperator clop) {
		'''
		public static �clop.class_.name� �name.toFirstLower()�(
		�FOR p: parameters SEPARATOR ","�
		�injpa.javaFeatureParameters(p)�
		�ENDFOR�
		) 
		{
			�clop.class_.name� o = �clop.class_.EPackage.name�Factory.create�clop.class_.name.toFirstUpper()�();
			�for(p: parameters)� {
				�structureFeatureSetter(p.feature)�;
			}
			for(p: getSettedCustomParameters()){
				o.set�p.feature.name.toFirstUpper()�(�injop.settedValue(p.feature, p.value)�);
			}
			return o;
			}
		'''
	}
	
	
	def javaFactoryCreateDefaultOperator(Mapping mapping, String name, EClass ecl) {
		val parameters = getDefaultParameters(ecl,mapping);
		if(!ecl.abstract && !ecl.interface) {
			'''
			public static �ecl.name� �name.toFirstLower()�(injop.javaClassAttributes(mapping, ecl); 
			�FOR param: parameters SEPARATOR ","� 
			�injpa.defaultJavaFeatureParameter(param)�
			�ENDFOR�) {
				�ecl.name� o = �EPackage.name�Factory.create.�ecl.name.toFirstUpper()�();
				�for(attribute EAllAttributes)� {
					�structureFeatureSetter(attribute)�;
				}
				�for(param: parameters)� {
					�structureFeatureSetter(param)�;
				}
			return o;
			}
			'''
		}
	}
	
	
	def structureFeatureSetter(EStructuralFeature esf){
		if(esf.many){
			if(esf.unsettable){
				'''
				for(int i = 0 ; i < �esf.name�.size() ; ++i) {
					o.get�name.toFirstUpper()�().add(_�name�.get(i));
				}
				'''
				
				} else {
					'''
					�if(esf.unsettable)� {
						o.set�name.toFirstUpper()�(_�name�);
					}
				'''
			}
		}
	}

	
}