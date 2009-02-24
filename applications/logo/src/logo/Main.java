package logo;

import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.ANTLRInputStream;
import org.antlr.runtime.tree.Tree;
import logo.ast.AstAdaptor;
import logo.ast.types.*;
import java.util.*;
import java.io.*;
import org.kohsuke.args4j.*;

public class Main {
  protected static Options options = new Options();

  public static void main(String[] args) {

    CmdLineParser optionParser = new CmdLineParser(options);
    optionParser.setUsageWidth(80);
    try {
      // parse the arguments.
      optionParser.parseArgument(args);
      //if( options.arguments.isEmpty() ) {
      if( options.help || options.h ) {
        throw new CmdLineException("Help");
      }
    } catch( CmdLineException e ) {
      // if there's a problem in the command line,
      // you'll get this exception. this will report
      // an error message.
      System.err.println(e.getMessage());
      System.err.println("java Main [options...] arguments ...");
      // print the list of available options
      optionParser.printUsage(System.err);
      System.err.println();
      return;
    }

    try {
      InputStream fileinput = System.in;
      if(options.in != null) {
        fileinput = new FileInputStream(options.in);
      }

      // Parse the input expression and build an AST
      LangageLexer lexer = new LangageLexer(new ANTLRInputStream(fileinput));
      CommonTokenStream tokens = new CommonTokenStream(lexer);
      LangageParser ruleParser = new LangageParser(tokens);
      Tree b = (Tree) ruleParser.program().getTree();
      InstructionList il = (InstructionList) AstAdaptor.getTerm(b);
      il = Compiler.optimize(il);
      Compiler.eval(il);

      PrintStream outputfile = System.out;
      if(options.out != null) {
        outputfile = new PrintStream(options.out);
      }
      PrintStream tomoutputfile = System.out;
    } catch (Exception e) {
      System.err.println("exception: " + e);
      e.printStackTrace();
      return;
    }
  }
}
