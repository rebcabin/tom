package lib.sl;

/**
 * Created with IntelliJ IDEA.
 * User: christophe
 * Date: 25/10/12
 * Time: 16:48
 * To change this template use File | Settings | File Templates.
 */

import lib.MOFException;
import lib.fun.*;
import lib.zip.*;

public class Fail<X,Y> extends Visitor<X,Y> {
   public <Ans> Ans visit(X x, Fun<Zip<X, Y>,Ans> k) throws MOFException { throw new MOFException(); }
}