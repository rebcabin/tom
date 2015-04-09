package tom.library.factory;

import java.lang.reflect.Constructor;
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
     * the no-arguments constructor of the class - if it exists -
     */
    private ConstructorWrapper noArgsConstructor;

    /**
     * list of class constructors that are annotated with @EnumerateGenerator
     * and have parameters and have parameters
     */
    private List<ConstructorWrapper> constructors = new ArrayList<ConstructorWrapper>();

    /**
     * contained non-primitive classes references that need factories to be
     * generated for them the set contains the canonical names for these classes
     */
    Set<Class> dependencies;

    /**
     * constant representing line separator
     */
    public static final String ENDL = System.getProperty("line.separator");

    public <T> ParsedClass(Class<T> classToParse) {
        this.canonicalName = classToParse.getCanonicalName();
        this.simpleName = classToParse.getSimpleName();
        this.dependencies = new HashSet<Class>();
    }

    public String getSimpleName() {
        return simpleName;
    }

    public String getCanonicalName() {
        return canonicalName;
    }

    public List<ConstructorWrapper> getConstructors() {
        return constructors;
    }

    public ConstructorWrapper getConstructor(int index) {
        return constructors.get(index);
    }

    public ConstructorWrapper getNoArgsConstructor() {
        return noArgsConstructor;
    }
    
    public Set<Class> getDependencies() {
        return dependencies;
    }

    /**
     * creates a wrapper for the no args constructor annotated with @EnumerateGenerator
     * 
     * @param noArgsConstructor
     *            constructor to wrap
     */
    public <T> void addNoArgsConstructor(Constructor<T> noArgsConstructor) {
        this.noArgsConstructor = new ConstructorWrapper(noArgsConstructor, -1, this);
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
        this.constructors
            .add(new ConstructorWrapper(cons, constructors.size(), this));
    }

    /**
     * adds the canonical name of a class to the set of dependent classes
     * factories will be generated for all dependencies if not generated already
     * 
     * @param referencedClassName
     *            name of the dependent class to add.
     */
//    public void addDependency(String referencedClassName) {
        public void addDependency(Class referencedClassName) {
        this.dependencies.add(referencedClassName);
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

    /**
     * Concatenates all enumerations generated by all constructors for this
     * class i.e. generates the string
     * noArgsConsEnum.plus(cons0Enum)....plus(consNEnum)
     * 
     * @return accumulated final enumeraion
     */
    public String getFinalEnum() {
        StringBuilder finalEnum = new StringBuilder();
        //TODO: find a cleaner way to specify first additional constructor
        int firstAdditionalCons = 0;
        if (hasNoArgsConstructor()) {
            finalEnum.append(this.getNoArgsConstructor().getEnumName());
        } else {
            finalEnum.append(this.getConstructor(0).getEnumName());
            firstAdditionalCons = 1;
        }
        // for every additional constructor add the corresponding generated enum
        for (int i = firstAdditionalCons; i < this.constructors.size(); i++) {
            finalEnum.append(".plus(");
            finalEnum.append(this.getConstructor(i).getEnumName());
            finalEnum.append(")");
        }
        return finalEnum.toString();
    }

}
