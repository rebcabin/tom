
package tom.library.factory;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * represents a data model for a class that has been parsed by the Parser
 * 
 * @author Ahmad
 * 
 */
public class ParsedClass {

    /**
     * class simple name
     */
    private String simpleName;

    /**
     * class canonical name (fully qualified)
     */
    private String canonicalName;

    /**
     * fully qualified name of the package where this class is defined
     */
    private String packageName;

    /**
     * the no-arguments constructor of the class - if it exists -
     */
    private GeneratingConstructor noArgsConstructor;

    /**
     * list of class constructors that are annotated with @EnumerateGenerator
     * and have parameters
     */
    private List<GeneratingConstructor> constructors;

    /**
     * list of class methods that are annotated with @Enumerate
     */
    private List<GeneratingMethod> methods;

    /**
     * contained non-primitive classes references that need factories to be
     * generated for them the set contains the canonical names for these classes
     */
    Set<EnumerableType> dependencies;
    
    /**
     * enumerable type containing this class
     */
    EnumerableType enumerableType;
    
    /**
     * constant representing line separator
     */
    public static final String ENDL = System.getProperty("line.separator");

    public <T> ParsedClass(Class<T> classToParse, EnumerableType enumerableType) {
        this.constructors = new ArrayList<GeneratingConstructor>();
        this.methods = new ArrayList<GeneratingMethod>();
        this.canonicalName = classToParse.getCanonicalName();
        this.simpleName = classToParse.getSimpleName();
        this.packageName = classToParse.getPackage().getName();
        this.dependencies = new HashSet<EnumerableType>();
        this.enumerableType = enumerableType;
    }

    public String getSimpleName() {
        return simpleName;
    }

    public String getCanonicalName() {
        return canonicalName;
    }

    public String getPackageName() {
        return packageName;
    }

    public List<GeneratingConstructor> getConstructors() {
        return constructors;
    }

    public List<GeneratingMethod> getMethods() {
        return methods;
    }

    public GeneratingConstructor getConstructor(int index) {
        return constructors.get(index);
    }

    public GeneratingConstructor getNoArgsConstructor() {
        return noArgsConstructor;
    }

    public Set<EnumerableType> getDependencies() {
        return dependencies;
    }
    
    public EnumerableType getEnumerableType() {
        return enumerableType;
    }
    
    public String getFinalEnumName() {
        return "finalEnum"+simpleName;
    }
    
    public String getThisEnumName() {
        return "thisEnum"+simpleName;
    }
    
    /**
     * creates a wrapper for the no args constructor annotated with @EnumerateGenerator
     * 
     * @param noArgsConstructor
     *            constructor to wrap
     */
    public <T> void addNoArgsConstructor(Constructor<T> noArgsConstructor) {
        this.noArgsConstructor = new GeneratingConstructor(noArgsConstructor, -1, this);
    }

    /**
     * adds a constructor annotated with @EnumerateGenerator to the list after
     * wrapping it in ConstructorWrapper the size of the list is also passed to
     * the wrapper constructor to indicate the index of the constructor in the
     * list
     * 
     * @param cons
     *            Constructor to add
     */
    public <T> void addConstructor(Constructor<T> cons) {
        this.constructors.add(new GeneratingConstructor(cons, constructors.size(), this));
    }

    /**
     * adds a method annotated with @Enumerate to the list after wrapping it in
     * MethodWrapper the size of the list is also passed to the wrapper to
     * indicate the index of the method in the list
     * 
     * @param method
     *            Method to add
     */
    public void addMethod(Method method) {
        this.methods.add(new GeneratingMethod(method, methods.size(), this));
    }

    /**
     * Adds a class to the set of dependent classes. Factories will be generated
     * for all dependencies if not generated already
     * 
     * @param referencedClass
     *            the dependent class to add.
     */
    public void addDependency(EnumerableType referencedType) {
        this.dependencies.add(referencedType);
    }

    /**
     * returns the factory name
     * 
     * @return factory name of the factory class to be generated
     */
    public String getFactoryClassName() {
        return this.simpleName + "Factory";
    }

    /**
     * Whether this class has a no-args constructor or not
     */
    public boolean hasNoArgsConstructor() {
        return this.noArgsConstructor != null;
    }
    
    public String getAllConstructorsEnumName() {
        return "allConstructorsEnum"+simpleName;
    }
    
    public String getAllMethodsEnumName() {
        return "allMethodsEnum"+simpleName;
    }
    
    /**
     * Concatenates all enumerations generated by all constructors for this
     * class i.e. generates the string
     * noArgsConsEnum.plus(cons0Enum)....plus(consNEnum) It returns an empty
     * string if there were no generating constructors in this class
     * 
     * @return accumulated all-constructors enumeration
     */
    public String getAllContructorsEnum() {
        StringBuilder allConsEnum = new StringBuilder();
        // TODO: find a cleaner way to specify first additional constructor
        int firstAdditionalCons = 0;
        if (hasNoArgsConstructor()) {
            allConsEnum.append(this.getNoArgsConstructor().getEnumName());
        } else if (!constructors.isEmpty()) {
            allConsEnum.append(this.getConstructor(0).getEnumName());
            firstAdditionalCons = 1;
        }
        // for every additional constructor add the corresponding generated enum
        for (int i = firstAdditionalCons; i < this.constructors.size(); i++) {
            allConsEnum.append(".plus(");
            allConsEnum.append(this.getConstructor(i).getEnumName());
            allConsEnum.append(")");
        }
        return allConsEnum.toString();
    }

    /**
     * Concatenates all enumerations generated by all methods for this class
     * i.e. generates the string
     * _method0_XEnum.plus(_method1_XEnum)....plus(_methodN_XEnum) It returns an
     * empty string if there were no generating methods in this class
     * 
     * @return accumulated all-methods enumeration
     */
    public String getAllMethodsEnum() {
        StringBuilder allMethodsEnum = new StringBuilder();
        if (!this.methods.isEmpty()) {
            allMethodsEnum.append(methods.get(0).getEnumName());
        }
        for (int i = 1; i < methods.size(); i++) {
            allMethodsEnum.append(".plus(");
            allMethodsEnum.append(methods.get(i).getEnumName());
            allMethodsEnum.append(")");
        }
        return allMethodsEnum.toString();
    }

    /**
     * concatenates enumeration generated by all generating methods plus
     * enumeration generated by all generating constructors to build the final
     * enumeration
     * 
     * @return final concatenated enumeration from all generators
     */
    public String getFinalEnum() {
        if (this.methods.isEmpty()) {
            return getAllConstructorsEnumName();
        } else if (this.constructors.isEmpty() && !this.hasNoArgsConstructor()) {
            return getAllMethodsEnumName();
        }
        return  getAllConstructorsEnumName() +".plus("+ getAllMethodsEnumName() +")";
    }
}
