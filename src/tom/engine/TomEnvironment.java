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
 * Pierre-Etienne Moreau  e-mail: Pierre-Etienne.Moreau@loria.fr
 *
 **/

package jtom;

import java.io.*;
import java.text.*;
import java.util.*;

import jtom.tools.*;
import jtom.exception.*;

import jtom.adt.tomsignature.*;
import jtom.adt.tomsignature.types.*;

import tom.platform.*;
import tom.platform.adt.platformoption.*;
import tom.platform.adt.platformoption.types.*;

import aterm.*;
import aterm.pure.*;

/**
 *
 */
public class TomEnvironment {

  /** */
  private ASTFactory astFactory;

  /** */
  private TomSignatureFactory tomSignatureFactory;

  /** */
  private PlatformOptionFactory platformOptionFactory;
  
  /** */
  private SymbolTable symbolTable;

  /** List of import paths. */
  private List userImportList;

  /** Absolute path where file are generated. */ 
  private File destDir;

  /** Absolute name of the input/output file (with extension). */
  private File inputFile;
  private File outputFile;

  /** Absolute name of the output file (given in command line). */
  private File userOutputFile;

  /** Relative path which corresponds to the package defined in the input file (empty by default) */
  private String packagePath;

  /** Eclipse mode for error management. */
  private boolean eclipseMode;

  /* in/out suffixes */
  private String inputSuffix;
  private String outputSuffix;

  /** */
  private Collection importsToDiscard;

  /**
   * Part of the Singleton pattern. The unique instance of the PluginPlatform.
   */
  private static TomEnvironment instance = null;
  
  /**
   * Part of the Singleton pattern. A protected constructor method, that exists to defeat instantiation.
   */
  private TomEnvironment(){}
  
  /**
   * Part of the Singleton pattern. Returns the instance of the PluginPlatform if it has been initialized before,
   * otherwise it throws a TomRuntimeException.
   * 
   * @return the instance of the PluginPlatform
   * @throws TomRuntimeException if the PluginPlatform hasn't been initialized before the call
   */
  public static TomEnvironment getInstance() {
    if(instance == null) {
      throw new TomRuntimeException(TomMessage.getString("GetInitializedPluginPlatformInstance"));
    }
    return instance;
  }

  /**
   * Part of the Singleton pattern. Initializes the TomEnvironment in case it hasn't been done before,
   * otherwise it reinitializes it.
   * 
   * @return the instance of the TomEnvironment
   */
  public static TomEnvironment create() {
    if(instance == null) {
      PureFactory pureFactory = SingletonFactory.getInstance();
      instance = new TomEnvironment();
      instance.tomSignatureFactory = TomSignatureFactory.getInstance(pureFactory);
      instance.astFactory = new ASTFactory(instance.tomSignatureFactory);
      instance.platformOptionFactory = PlatformOptionFactory.getInstance(pureFactory);
		
      instance.symbolTable = new SymbolTable(instance.astFactory);

      instance.inputSuffix = ".t";
      instance.outputSuffix = ".java";
      instance.userOutputFile = null;
      instance.eclipseMode = false;

      instance.importsToDiscard = new HashSet();
      instance.importsToDiscard.add("string.tom");
      instance.importsToDiscard.add("int.tom");
      instance.importsToDiscard.add("double.tom");
      instance.importsToDiscard.add("aterm.tom");
      instance.importsToDiscard.add("atermlist.tom");
  
      return instance;
    } else {
      TomEnvironment.clear();
      return instance;
    }
  }

  /**
   * Reinitializes the TomEnvironment instance.
   */
  public static void clear() {
    //instance.symbolTable.init();
    instance.destDir = null;
    instance.inputFile = null;
    instance.outputFile = null;
    instance.userOutputFile = null;
    instance.packagePath = null;
    instance.eclipseMode = false;
    instance.inputSuffix = ".t";
    instance.outputSuffix = ".java";
  }

  public void initializeFromOptionManager(OptionManager optionManager) {
    List localUserImportList = new ArrayList();
    String localDestDir = null;

    symbolTable.init(optionManager);
    // computes the input and output suffixes
    // well, it would be better in the future if we let the generator append the output suffix itself
    // so that's only temporary
    
    if ( ((Boolean)optionManager.getOptionValue("jCode")).booleanValue() ) {
      inputSuffix = ".t";
      outputSuffix = ".java";
    } else if ( ((Boolean)optionManager.getOptionValue("cCode")).booleanValue() ) {
      inputSuffix = ".t";
      outputSuffix = ".tom.c";
    } else if ( ((Boolean)optionManager.getOptionValue("camlCode")).booleanValue() ) {
      inputSuffix = ".t";
      outputSuffix = ".tom.ml";
    } else if ( ((Boolean)optionManager.getOptionValue("eCode")).booleanValue() ) {
      inputSuffix = ".t";
      outputSuffix = ".e";
    } else { // we should never ever be here normally...
      inputSuffix = ".t";
      outputSuffix = ".java";
    }

    // fills the local user import list
    String imports = (String)optionManager.getOptionValue("import");
    StringTokenizer st = new StringTokenizer(imports, ":"); // paths are separated by ':'
    while( st.hasMoreTokens() ) {
      String next = st.nextToken();
      localUserImportList.add(new File(next).getAbsoluteFile());
    }
    // Setting importList
    setUserImportList(localUserImportList);

    // for Eclipse...
    if ( ((Boolean)optionManager.getOptionValue("eclipse")).booleanValue() )
      setEclipseMode(true);

    // computes destdir
    localDestDir = (String)optionManager.getOptionValue("destdir");
    setDestDir(localDestDir);

    String commandLineUserOutputFile = (String)optionManager.getOptionValue("output");
    if ( commandLineUserOutputFile.length() > 0 ) {
      setUserOutputFile( commandLineUserOutputFile );
    }
  }
  
  public void prepareForInputFile(String localInputFileName) { // updateInputOutputFiles + init
    // compute inputFile:
    //  - add a suffix if necessary
    if(!localInputFileName.endsWith(getInputSuffix())) {
      localInputFileName += getInputSuffix();
    }
    setInputFile(localInputFileName);
    
    // compute outputFile:
    //  - either use the given UserOutputFileName
    //  - either concatenate
    //    the outputDir
    //    [the packagePath] will be updated by the parser
    //    and reuse the inputFileName with a good suffix
    if(isUserOutputFile()) {
      setOutputFile(getUserOutputFile().getPath());
    } else {
      String child = new File(getInputFileNameWithoutSuffix() + getOutputSuffix()).getName();
      File out = new File(getDestDir(),child).getAbsoluteFile();
      setOutputFile(out.getPath());
    }
  }
  

  /**
   * An accessor method.
   * 
   * @return an ASTFactory
   */
  public ASTFactory getASTFactory() { return astFactory; }
    
  /**
   * An accessor method.
   * 
   * @return a TomSignatureFactory
   */
  public TomSignatureFactory getTomSignatureFactory() { return tomSignatureFactory; }

  /**
   * An accessor method.
   * 
   * @return an PlatformOptionFactory
   */
  public PlatformOptionFactory getPlatformOptionFactory() { return platformOptionFactory; }

  /**
   * An accessor method.
   * 
   * @return an ASTFactory
   */
  public SymbolTable getSymbolTable() { return symbolTable; }

  public String getOutputSuffix() { return outputSuffix; }
  
  public void setOutputSuffix(String string) { outputSuffix = string; }

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

  /**
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
    try {
      
      importList.add(new File(getDestDir(),getPackagePath()).getCanonicalFile());
      
      importList.add(getInputFile().getParentFile().getCanonicalFile());
      String tom_home = System.getProperty("tom.home");
      if(tom_home != null) {
	File file = new File(new File(tom_home,"jtom"),"share");
	importList.add(file.getCanonicalFile());
	//System.out.println(" extend import list with: " + file.getPath());
      }
      //System.out.println("importList = " + importList);
    } catch (IOException e) {
      System.out.println("IO Exception when computing importList");
      e.printStackTrace();
    }
    return importList;
  }
 
  public String getInputSuffix() {
    //System.out.println("getInputSuffix : " +inputSuffix);
    return inputSuffix;
  }
  
  public void setInputSuffix(String inputSuffix) {
    this.inputSuffix = inputSuffix;
  }

  public void setPackagePath(String packagePath) {
    this.packagePath = packagePath.replace('.',File.separatorChar);
  }
  
  public String getPackagePath() {
    //System.out.println("getPackagePath : " +packagePath);
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
    return inputFile;
  }

  public String getInputFileNameWithoutSuffix() {
    String inputFileName = getInputFile().getPath();
    String res = inputFileName.substring(0, inputFileName.length() - getInputSuffix().length());
    //System.out.println("IFNWS : " +res);
    return res;
  }
  
  public String getOutputFileNameWithoutSuffix() {
    String outputFileName = getOutputFile().getPath();
    String res = outputFileName.substring(0, outputFileName.length() - getOutputSuffix().length());
    //System.out.println("OFNWS : " +res);
    return res;
  }

  public void setOutputFile(String sOutputFile) {
    try {
      this.outputFile = new File(sOutputFile).getCanonicalFile();
      this.outputFile.getParentFile().mkdirs();
    } catch (IOException e) {
      System.out.println("IO Exception using file `" + sOutputFile + "`");
      e.printStackTrace();
    }
  }
  
  public File getOutputFile() {
    return outputFile;
  }

  /**
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
    return userOutputFile != null;
  }

  public void setUserOutputFile(String sUserOutputFile) {
    try {
      this.userOutputFile = new File(sUserOutputFile).getCanonicalFile();
    } catch (IOException e) {
      System.out.println("IO Exception using file `" + sUserOutputFile + "`");
      e.printStackTrace();
    }
  }
 
  public File getUserOutputFile() {
    return userOutputFile;
  }
  
  public String getRawFileName() {
    String inputFileName = getInputFile().getName();
    String res = inputFileName.substring(0, inputFileName.length() - getInputSuffix().length());
    //System.out.println("Raw file name : " + res);
    return res;
  }

  public boolean isSilentDiscardImport(String fileName) {
    return importsToDiscard.contains(fileName);
  }

}
