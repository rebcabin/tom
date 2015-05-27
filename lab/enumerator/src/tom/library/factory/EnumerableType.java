package tom.library.factory;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

enum DependencyType {
    SIMPLE, SUPERTYPE, RECURSIVE
}

public class EnumerableType {
    
    private final Class<?> typeClass;
    private final Set<Class<?>> subTypes = new HashSet<Class<?>>();
    private DependencyType dependencyType;
    private ParsedClass parsedClass;
    private Set<ParsedClass> parsedClasses = new HashSet<ParsedClass>();
    
    public EnumerableType(Class enumertableClass) {
        this.dependencyType = DependencyType.SIMPLE;
        this.typeClass = enumertableClass;
    }

    public EnumerableType(Class enumertableClass, Class<?>[] concreteClasses) {
        this.dependencyType = DependencyType.SUPERTYPE;
        this.typeClass = enumertableClass;
        Collections.addAll(subTypes, concreteClasses);
    }

    public Class<?> getTypeClass() {
        return typeClass;
    }
    
    public ParsedClass getParsedClass() {
        return parsedClass;
    }
    
    public DependencyType getDependencyType() {
        return dependencyType;
    }

    public Set<Class<?>> getSubTypes() {
        return subTypes;
    }
    
    public Set<ParsedClass> getParsedClasses() {
        return parsedClasses;
    }
    
    
    public void parse() {
        this.parsedClass = parseClass(typeClass);
        for (Class<?> classToParse : subTypes) {
            ParsedClass parsedClass = parseClass(classToParse);
            this.parsedClasses.add(parsedClass);
        }
    }
    /**
     * parses a class and stores parsed information into the ParsedClass
     * 
     * @param classToParse
     *            the class to be parsed
     * @return parsedClass object holding the parsed information (constructors,
     *         annotations...)
     */
    public ParsedClass parseClass(Class<?> classToParse) {
        ParsedClass parsedClass = new ParsedClass(classToParse, this);
        // load all constructors having @Enumerate annotations
        for (Constructor<?> cons : classToParse.getDeclaredConstructors()) {
            if (cons.isAnnotationPresent(Enumerate.class)) {
                if (cons.getParameterTypes().length == 0) {
                    // no args cons
                    parsedClass.addNoArgsConstructor(cons);
                } else {
                    // cons with parameters
                    parsedClass.addConstructor(cons);
                }

            }
        }
        // load all methods annotated as @Enumerate
        for (Method method : classToParse.getDeclaredMethods()) {
            if (method.isAnnotationPresent(Enumerate.class)) {
                parsedClass.addMethod(method);

            }
        }
        return parsedClass;
    }

    public Set<EnumerableType> getDependencies() {
        Set<EnumerableType> dependencies = new HashSet<EnumerableType>();
        dependencies.addAll(parsedClass.getDependencies());
        for (ParsedClass parsedClass : parsedClasses) {
            dependencies.addAll(parsedClass.getDependencies());
        }
        return dependencies;
    }
    
    public String getPackageName() {
        return typeClass.getPackage().getName();
    }
    
    public String getFactoryName() {
        return typeClass.getSimpleName() + "Factory";
    }
    
    public String getSimpleName() {
        return typeClass.getSimpleName();
    }
    
    public String getCanonicalName() {
        return typeClass.getCanonicalName();
    }
    
    public String getFinalEnum() {
        StringBuilder finalEnum = new StringBuilder();
        if (dependencyType.equals(DependencyType.SIMPLE)) {
            return parsedClass.getFinalEnumName();
        } else if (dependencyType.equals(DependencyType.SUPERTYPE) && parsedClasses.size()>0) {
            Iterator<ParsedClass> it = parsedClasses.iterator();
            ParsedClass concreteClass = it.next();
            finalEnum.append(concreteClass.getFinalEnumName());
            while (it.hasNext()) {
                finalEnum.append(".plus(");
                finalEnum.append(it.next().getFinalEnumName());
                finalEnum.append(")");
            }
        }
        return finalEnum.toString();
    }
}
