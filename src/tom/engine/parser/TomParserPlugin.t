package jtom.parser;

import java.io.*;

import aterm.*;
import jtom.*;
import jtom.adt.tomsignature.types.*;
import jtom.adt.options.types.*;
import jtom.exception.*;
import jtom.tools.*;

import java.util.logging.*;

/**
 * The TomParser plugin.
 */
public class TomParserPlugin extends TomGenericPlugin {
  %include { ../adt/TomSignature.tom }
  %include{ ../adt/Options.tom }

  private TomParser parser;
  private String fileName;

  public static final String PARSED_SUFFIX = ".tfix.parsed";
  public static final String PARSED_TABLE_SUFFIX = ".tfix.parsed.table";
  public static final String DEBUG_TABLE_SUFFIX = ".tfix.debug.table";

  public TomParserPlugin() {
    super("TomParserPlugin");
  }

  public void setTerm(ATerm term) {
    fileName = ((AFun)term).getName();
  }

  public void run() {
    try {
      long startChrono = System.currentTimeMillis();
	
      boolean verbose      = getServer().getOptionBooleanValue("verbose");
      boolean intermediate = getServer().getOptionBooleanValue("intermediate");
      boolean java         = getServer().getOptionBooleanValue("jCode");
      boolean debug        = getServer().getOptionBooleanValue("debug");

      if(java) {
	TomJavaParser javaParser = TomJavaParser.createParser(fileName);
	String packageName = javaParser.JavaPackageDeclaration();
	// Update taskInput
	environment().setPackagePath(packageName);
	environment().updateOutputFile();
      }	else {
	environment().setPackagePath("");
      }
   
      //System.out.println(fileName);

      this.parser = TomParser.createParser(fileName);
      TomTerm parsedTerm = parser.startParsing();
      super.setTerm(parsedTerm);

      if(verbose) 
	System.out.println("TOM parsing phase (" + (System.currentTimeMillis()-startChrono)+ " ms)");
      // TODO: to be replaced by a log(Level.INFO)

      if(environment().isEclipseMode()) {
	String outputFileName = environment().getInputFile().getParent()+ File.separator + "."
	    + environment().getRawFileName()+ PARSED_TABLE_SUFFIX;

	Tools.generateOutput(outputFileName, symbolTable().toTerm());
      }

      if(intermediate) {
	Tools.generateOutput(environment().getOutputFileNameWithoutSuffix() + PARSED_SUFFIX, 
			     getTerm());
	Tools.generateOutput(environment().getOutputFileNameWithoutSuffix() + PARSED_TABLE_SUFFIX, 
			     symbolTable().toTerm());
      }
        
      if(debug)
	  Tools.generateOutput(environment().getOutputFileNameWithoutSuffix() + DEBUG_TABLE_SUFFIX, 
			       parser.getStructTable());
        
      environment().printAlertMessage("TomParserPlugin"); // TODO: will be useless soon

      if(!environment().isEclipseMode()) {
	  // remove all warning (in command line only)
	  environment().clearWarnings();
      } 
    } catch (TokenMgrError e) {
	getLogger().log( Level.SEVERE,
			 "TokenMgrError",
			 new Object[]{fileName, new Integer( getLineFromTomParser(parser) ), e.getMessage()} );
    } catch (TomIncludeException e) {
	getLogger().log( Level.SEVERE,
			 "SimpleMessage",
			 new Object[]{fileName, new Integer( getLineFromTomParser(parser) ), e.getMessage()} );
    } catch (TomException e) {
	getLogger().log( Level.SEVERE,
			 "SimpleMessage",
			 new Object[]{fileName, new Integer( getLineFromTomParser(parser) ), e.getMessage()} );
    } catch (FileNotFoundException e) {
	getLogger().log( Level.SEVERE,
			 "FileNotFound",
			 new Object[]{fileName, new Integer( getLineFromTomParser(parser) ), fileName} ); 
    } catch (ParseException e) {
	getLogger().log( Level.SEVERE,
			 "ParseException",
			 new Object[]{fileName, new Integer( getLineFromTomParser(parser) ), e.getMessage()} );
    } catch (Exception e) {
	e.printStackTrace();
	getLogger().log( Level.SEVERE,
			 "UnhandledException", 
			 new Object[]{fileName, e.getMessage()} );
    }
  }

  private int getLineFromTomParser(TomParser parser) {
    if(parser == null) {
      return TomMessage.DEFAULT_ERROR_LINE_NUMBER;
    } 
    return parser.getLine();
  }

  private int getLineFromJavaParser(TomJavaParser parser) {
    if(parser == null) {
      return TomMessage.DEFAULT_ERROR_LINE_NUMBER;
    } 
    return parser.getLine();
  }

  public TomOptionList declaredOptions() {
    return `concTomOption(OptionBoolean("parse","","",True()) // activation flag
			  );
  }
}
