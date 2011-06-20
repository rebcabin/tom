package test;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;

import newparser.HostParser;

import org.antlr.runtime.ANTLRFileStream;
import org.antlr.runtime.CharStream;
import org.antlr.runtime.tree.Tree;

public class Test_ParseAndDrawTrees {

	public static void main(String args[]) throws IOException{
		
		final String TOM_HOME = System.getenv("TOM_HOME")+"/"; 
		if(TOM_HOME==null){
			throw new RuntimeException("$TOM_HOME is not set...");
		}
		final String RLT_PATH = "../../applications/islandgrammar2/";
		
		final String OUTPUT_DIR = TOM_HOME+RLT_PATH+"output/";
		final String INPUT_DIR = TOM_HOME+RLT_PATH+"input/";
		
		String testedFiles[];
		
		// === make a list of files to test 
		Process ls = Runtime.getRuntime().exec("ls "+INPUT_DIR);
		BufferedInputStream ls_stdout = new BufferedInputStream(ls.getInputStream());
		
		String ls_res = "";
		int ls_char;
		while((ls_char=ls_stdout.read())!=-1){
			ls_res+=(char)ls_char;
		}
		
		testedFiles = ls_res.split("\n");
		// ===
		
		// === parse and draw
		for(int i=0; i<testedFiles.length; i++){
		  if(!testedFiles[i].startsWith("_")){
		  
			  String inputFileName = INPUT_DIR+testedFiles[i];
		  String outputFileName = OUTPUT_DIR+testedFiles[i];
		
		  System.out.println("\n> Now parsing "+testedFiles[i]+" .");
		  
		  CharStream input = new ANTLRFileStream(inputFileName);
		  Tree tree = new HostParser().parse(input);
			
			futil.ANTLRTreeDrawer.draw(outputFileName, tree);
			
		  }
		}
		
		/*
		CharStream input = new ANTLRFileStream(testedFile);
		Tree tree = new HostParser().parse(input);
		
		futil.ANTLRTreeDrawer.draw(pngFile, tree);
		*/
	}
}
