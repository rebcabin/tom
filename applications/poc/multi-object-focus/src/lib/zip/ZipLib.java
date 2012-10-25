package lib.zip;

import lib.MOFException;
import lib.fun.*;

/**
 * Created with IntelliJ IDEA.
 * User: christophe
 * Date: 24/10/12
 * Time: 18:07
 * To change this template use File | Settings | File Templates.
 */
public class ZipLib {
    public static <T,S> Zip<T,S> mkZip(Fun<S,T> c, S f) { return new Zip<T,S>(c,f); }

    public static <T> Zip<T,T> unit(T t) { return new Zip<T,T>(FunLib.id(t) , t); }

    public static <T,S,R> Zip<T,R> join(final Zip<T,Zip<S,R>> z) {
        final Zip<S,R> zs = z.focus;
        return new Zip<T, R>( new Fun<R,T>() { public T apply(R r) throws MOFException { return z.context.apply(zs.replace(r)); } }
                            , zs.focus
                            ) ;
    }

    public static <X,Y> Fun<X,Zip<Y,Y>> lift(final Fun<X,Y> f) {
        return new Fun<X,Zip<Y,Y>>() {
            public Zip<Y,Y> apply(X x) throws MOFException { return unit(f.apply(x));}
        };
    }
}