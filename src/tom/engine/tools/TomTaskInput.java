/*
 *   
 * TOM - To One Matching Compiler
 * 
 * Copyright (C) 2000-2004 INRIA
 * Nancy, France.
 * 
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307 USA
 * 
 * Pierre-Etienne Moreau	e-mail: Pierre-Etienne.Moreau@loria.fr
 *
 **/

package jtom.tools;

import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;
import jtom.exception.TomRuntimeException;
import java.io.*;

import jtom.adt.tomsignature.types.*;

public class TomTaskInput {
  private TomTerm term;
  private TomErrorList errors;

  /*
   * Singleton pattern
   */
  private static TomTaskInput instance = null;
  protected TomTaskInput() {
    // exisits to defeat instantiation
  }

  public static TomTaskInput getInstance() {
    if(instance == null) {
      throw new TomRuntimeException(new Throwable("cannot get the instance of an unitialized TomTaskInput"));
    }
    return instance;
  }

  public static TomTaskInput create(TomErrorList errors) {
    if(instance == null) {
      instance = new TomTaskInput();
      instance.errors = errors;
      return instance;
    } else {
      throw new TomRuntimeException(new Throwable("cannot create two instances of TomTaskInput"));
    }
  }

  /*
   * list of import paths
   */
  private List userImportList;

    /* 
     * destDir 
     * absolute path where file are generated  
     */ 
  private File destDir;
 
    /*
     * inputFile
     * absolute name of the input file (with extension) 
     */
  private File inputFile;

    /*
     * outputFile
     * absolute name of the output file (with extension) 
     */
  private File outputFile;

    /*
     * packagePath
     * relative path which corresponds to the package defined in the input file (empty by default) 
     */
  private String packagePath = ""; 

  private String inputSuffix = ".t";
  private String outputSuffix = ".java";
  public final static String parsedSuffix    = ".tfix.parsed";
  public final static String expandedSuffix  = ".tfix.expanded";
  public final static String compiledSuffix  = ".tfix.compiled";
  public final static String optimizedSuffix  = ".tfix.optimized";
  public final static String verifExtractionSuffix = ".tfix.verif";
  public final static String parsedTableSuffix = ".tfix.parsed.table";
  public final static String expandedTableSuffix = ".tfix.expanded.table";
  public final static String debugTableSuffix = ".tfix.debug.table";
  
  private boolean needDebugExpansion = false, 
    verbose = false, // a verbose mode (Show duration for each phase)
    intermediate = false, // generate intermediate files
    debugMode = false, // generate debug primitives
    jCode = true,  // generate Java
    cCode = false, // generate C
    camlCode = false, // generate Caml
    eCode = false, // generate Eiffel
    doParse   = true, // parse a *.t file
    doExpand  = true, // expand the AST
    doCompile = true, // compile the AST
    printOutput = true, // print the output file using a TomGenerator instance
    doOnlyCompile = false, // try to start from an intermediate file
    doCheck = true, // verify after parsing (syntax checking) and expansion (type checking)
    strictType = true, // no universal type
    genDecl = true, // generate declarations
    doOptimization = false, // optimize generated code
    warningAll = false, // print warning error messages
    noWarning = false, // print only warning stopping the compilation
    staticFunction = false, // generate static functions
    debugMemory = false, // generate debug primitives
    pretty = false, // Synchronize TL code and source code
    atermStat = false, // Shows aterm statistics
    eclipseMode = false, // Eclipse mode for error management
    doVerify = false, // Compilation correctness verification
    help = false, // usage called
    version = false; //version called

  private boolean userOutputFile=false; //to know if the the output file is given by the user 

  private final static int JAVA   = 1;
  private final static int C      = 2;
  private final static int CAML   = 3;
  private final static int EIFFEL = 4;
  private int language = JAVA;
  
  public void setTerm(TomTerm term) {
    this.term = term;
  }
  public TomTerm getTerm() {
    return term;
  }
  
  public boolean getNeedDebugExpansion() {
    return needDebugExpansion;
  }
  
  public void setNeedDebugExpansion(boolean need) {
    needDebugExpansion = need;
  }
   
  public boolean isVerbose() {
    return verbose;
  }
  public void setVerbose(boolean b) {
    verbose = b;
  }

  public boolean isIntermediate() {
    return intermediate;
  }
  public void setIntermediate(boolean b) {
    intermediate = b;
  }

  public boolean isDebugMode() {
    return debugMode;
  }
  public void setDebugMode(boolean b) {
    debugMode = b;
  }

  public boolean isPrintOutput() {
    return printOutput;
  }
  public void setPrintOutput(boolean b) {
    printOutput = b;
  }

  public String getOutputSuffix() {
    return outputSuffix;
  }
  
  public void setOutputSuffix(String string) {
    outputSuffix = string;
  }

  public boolean isCCode() {
    return language == C;
  }
  public void setCCode() {
    language = C;
  }

  public boolean isCamlCode() {
    return language == CAML;
  }
  public void setCamlCode() {
    language = CAML;
  }
  
  public boolean isECode() {
    return language == EIFFEL;
  }
  public void setECode() {
    language = EIFFEL;
  }
  
  public boolean isJCode() {
    return language == JAVA;
  }
  public void setJCode() {
    language = JAVA;
  }

  public boolean isDoCheck() {
    return doCheck;
  }
  public void setDoCheck(boolean b) {
    doCheck = b;
  }
	
  public boolean isDoExpand() {
    return doExpand;
  }
  public void setDoExpand(boolean b) {
    doExpand = b;
  }

  public boolean isDoOnlyCompile() {
    return doOnlyCompile;
  }
  public void setDoOnlyCompile(boolean b) {
    doOnlyCompile = b;
  }
	
  public boolean isDoCompile() {
    return doCompile;
  }
  public void setDoCompile(boolean b) {
    doCompile = b;
  }

  public boolean isDoParse() {
    return doParse;
  }
  public void setDoParse(boolean b) {
    doParse = b;
  }

  public boolean isGenDecl() {
    return genDecl;
  }
  public void setGenDecl(boolean b) {
    genDecl = b;
  }

  public boolean isStrictType() {
    return strictType;
  }
  public void setStrictType(boolean b) {
    strictType = b;
  }

  public boolean isDoOptimization() {
    return doOptimization;
  }
  public void setDoOptimization(boolean b) {
    doOptimization = b;
  }

  public boolean isDebugMemory() {
    return debugMemory;
  }
  public void setDebugMemory(boolean b) {
    debugMemory = b;
  }

  public boolean isNoWarning() {
    return noWarning;
  }
  public void setNoWarning(boolean b) {
    noWarning = b;
  }

  public boolean isStaticFunction() {
    return staticFunction;
  }
  public void setStaticFunction(boolean b) {
    staticFunction = b;
  }

  public boolean isWarningAll() {
    return warningAll;
  }
  public void setWarningAll(boolean b) {
    warningAll = b;
  }

  public boolean isPretty() {
    return pretty;
  }
  public void setPretty(boolean b) {
    pretty = b;
  }

  public boolean isAtermStat() {
    return atermStat;
  }
  public void setAtermStat(boolean b) {
    atermStat = b;
  }

  public TomErrorList getErrors() {
    return errors;
  }
  public void setErrors(TomErrorList list) {
    errors = list;
  }

  public boolean isDoVerify() {
    return doVerify;
  }
  public void setDoVerify(boolean b) {
    doVerify = b;
  }

  public boolean isEclipseMode() {
    return eclipseMode;
  }
  public void setEclipseMode(boolean b) {
    eclipseMode = b;
  }


  public void setUserImportList(List list) {
    userImportList = list;
  }

  public List getUserImportList() {
    return userImportList;
  }

    /*
     * dynamically compute the list of imported files:
     *  - user defined imports
     *  - destDir/packagePath
     *  - inputFile.getParent
     *  - TOM_HOME/share/jtom
     */
  public List getImportList() {
    List importList = new ArrayList(getUserImportList().size()+3);
    for(Iterator it=getUserImportList().iterator() ; it.hasNext() ;) {
      importList.add(it.next());
    }
    importList.add(new File(getDestDir(),getPackagePath()).getAbsoluteFile());
    importList.add(getInputFile().getParentFile().getAbsoluteFile());
    String tom_home = System.getProperty("tom.home");
    if(tom_home != null) {
      File file = new File(new File(tom_home,"jtom"),"share");
      importList.add(file.getAbsoluteFile());
      //System.out.println(" extend import list with: " + file.getPath());
    }
    //System.out.println("importList = " + importList);
    return importList;
  }
 
  public boolean isVersion() {
    return version;
  }
  public void setVersion(boolean b) {
    version = b;
  }

  public boolean isHelp() {
    return help;
  }
  public void setHelp(boolean b) {
    help = b;
  }

	public String getInputSuffix() {
		return inputSuffix;
	}
  
	public void setInputSuffix(String inputSuffix) {
		this.inputSuffix = inputSuffix;
	}

	public void setPackagePath(String packagePath) {
		this.packagePath = packagePath.replace('.',File.separatorChar);
	}
	
	public String getPackagePath() {
    //System.out.println("getPackagePath = " + packagePath);
		return packagePath;
	}

	public void setDestDir(String destDir) {
    try {
      this.destDir = new File(destDir).getCanonicalFile();
    } catch (IOException e) {
      System.out.println("IO Exception using file `" + destDir + "`");
      e.printStackTrace();
    }
	}
	
	public File getDestDir() {
    //System.out.println("getDestDir = " + destDir);
		return destDir;
	}

  public void setInputFile(String sInputFile) {
    try {
      this.inputFile = new File(sInputFile).getCanonicalFile();
    } catch (IOException e) {
      System.out.println("IO Exception using file `" + sInputFile + "`");
      e.printStackTrace();
    }

	}
	
	public File getInputFile() {
    //System.out.println("getInputFile = " + inputFile);
		return inputFile;
	}

	public String getInputFileNameWithoutSuffix() {
    String inputFileName = getInputFile().getPath();
    String res = inputFileName.substring(0, inputFileName.length() - getInputSuffix().length());
    //System.out.println("getInputFileNameWithoutSuffix = " + res);
    return res;
	}

  public void setOutputFile(String sOutputFile) {
    try {
      this.outputFile = new File(sOutputFile).getCanonicalFile();
      //System.out.println("setOutputFile = " + this.outputFile);
    } catch (IOException e) {
      System.out.println("IO Exception using file `" + sOutputFile + "`");
      e.printStackTrace();
    }
	}
	
	public File getOutputFile() {
    //System.out.println("getOutputFile = " + outputFile);
		return outputFile;
	}

    /*
     * update the outputFile by inserting the packagePath
     * between the destDir and the fileName
     */
  public void updateOutputFile() {
    if(!isUserOutputFile()) {
      File out = new File(getOutputFile().getParentFile(),getPackagePath());
      setOutputFile(new File(out, getOutputFile().getName()).getPath());
    }
  }

  public boolean isUserOutputFile() {
    return userOutputFile;
  }
  public void setUserOutputFile(boolean b) {
    userOutputFile = b;
  }
 
 
} // class TomTaskInput
