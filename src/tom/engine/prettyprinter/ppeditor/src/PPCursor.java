package ppeditor;

import java.util.*;
import java.lang.*;
import java.io.*;

/**The PPCursor class is used to organize the file in columns and lines 
*/
public class PPCursor {

/**The PPTextPosition which represents the current position in the file
*/
  private PPTextPosition position;

/**The way of writing.
*If true the writeText method inserts the new text inside the existing text, else the writeText method replaces the original text.
*/
  private boolean insertion;

/**A list of StringBuffer used as a local copy of the file
*/
  private ArrayList<StringBuffer> fileBuffer;

/**Creates a PPCursor
*/
  public PPCursor(int l, int c) {
    
    this.position = new PPTextPosition(l,c);
    insertion=true;
    fileBuffer = new ArrayList<StringBuffer>();
    fileBuffer.add(new StringBuffer(""));
  }

/**Moves the PPCursor to an absolute position
*@param p the PPTextPosition to place the PPCursor
*/

  public void setPosition(PPTextPosition p) {

    this.position = p;
  }

/**Moves the PPCursor to a relative position from the current position
*@param delta the PPTextPosition to add to the current position
*/
  public void move(PPTextPosition delta) {

    position.add(delta);
  }

/**Changes the way of writing
*@param b the boolean to set the attribute insertion to
*/
  public void setInsertion(boolean b) {

    this.insertion = b;
  }

/**Gets the position attribute 
*@return value of the position attribute
*/
  public PPTextPosition getPosition() {

    return position;
  }

/**Gets the insertion attribute
*@return value of the insertion attribute
*/
  public boolean isInsertion() {

    return insertion;
  }

/**For debug purpose. Returns the fileBuffer of this PPCursor.
*@return the fileBuffer of this PPCursor
*/
  public ArrayList<StringBuffer> getFileBuffer() {

    return this.fileBuffer;
}

/**Reads a sequence of the fileBuffer attribute, from the current position to a given PPTextPosition
*@param p the PPTextPosition to stop to
*@return the string between the current position and the given PPTextPosition 
*/
//  public String readFileBuffer(PPTextPosition p) {}

/**Writes a string at the current position
*The way of writing (insertion or replacement) is given by the insertion attribute
*@param s the string to write
*/
  public void write(String s) {
    while(position.getLine()>=fileBuffer.size()){
      fileBuffer.add(new StringBuffer(""));
    }
    while(position.getColumn()>sizeOfCurrentLine()){
      lineContent(position.getLine()).append(" ");
    }
    for(int i=0;i<s.length();i++){
      if(s.charAt(i)!='\n'){
        this.lineContent(position.getLine()).insert(position.getColumn(),s.charAt(i));
/*Debug*///System.out.println(s.charAt(i));
        position.add(new PPTextPosition(0,1));
        if(!insertion && this.sizeOfCurrentLine()>position.getColumn()+1){
          this.erase();
        }
      }else{
        if(insertion && position.getColumn()<fileBuffer.get(position.getLine()).length()-1){
          fileBuffer.add(position.getLine()+1, new StringBuffer(fileBuffer.get(position.getLine()).subSequence(position.getColumn(), fileBuffer.get(position.getLine()).length()-1)));

        }else if(fileBuffer.size()-1 == position.getLine()){
          fileBuffer.add(new StringBuffer(""));
        }
        position.set(new PPTextPosition(position.getLine()+1,0));
      }
    }
  }

/**Erases one character
*/
  public void erase() {
		fileBuffer.get(position.getLine()).deleteCharAt(position.getColumn());
  }

/**Reinitializes the fileBuffer attribute to an empty List<StringBuffer>
*/
  public void eraseAll() {
		fileBuffer.clear();
  }

/**Creates a text file with the fileBuffer as a content.
*@param fileName the name of the created file
*@return a StringBuffer which represents the content of the created file
*/
  public StringBuffer dump(String fileName) {
    StringBuffer content = new StringBuffer("");
    for(int i=0;i<fileBuffer.size()-1;i++){
      content.append(fileBuffer.get(i)+"\n");
    }
    content.append(fileBuffer.get(fileBuffer.size()-1));
    try{
      File aFile = new File(fileName);
      aFile.createNewFile();
      FileWriter textFile = new FileWriter(fileName);
      textFile.write(content.toString());
      textFile.close();
    }catch(IOException ioe){
      ioe.printStackTrace();
      System.out.println("Writing failed.");
    }
    return content;
  }

  public int sizeOfCurrentLine(){
    if(fileBuffer.size()<position.getLine()+1){
      return -1;
    }else{
     return fileBuffer.get(position.getLine()).length();
    }
  }

  public StringBuffer lineContent(int l){
    return fileBuffer.get(l);
  }



}