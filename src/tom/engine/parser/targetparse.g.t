header{
    package jtom.parser;

    import java.util.*;

    import aterm.*;
    import aterm.pure.*;
    
    import jtom.*;
    import jtom.tools.*;
    import jtom.adt.tomsignature.*;
    import jtom.adt.tomsignature.types.*;

    import antlr.*;
}




class NewTargetParser extends Parser;


{
    //--------------------------
    %include{TomSignature.tom}
    //--------------------------

    

    // the file to be parsed
    private String currentFile(){
        return TomMainParser.currentFile;
    };
    
    // the parser for tom constructs
    NewTomParser tomparser; 

    // the lexer
    NewTargetLexer targetlexer = (NewTargetLexer) TomMainParser.selector.getStream("targetlexer");
    
    StringBuffer targetLanguage = new StringBuffer("");

    Stack lines = new Stack();
    Stack columns = new Stack();

    public void init(){
        tomparser = new NewTomParser(getInputState(),this);
    }
/*
    public static NewTargetParser createTargetParser(TokenStreamSelector selector){
        NewTargetParser targetparser = new NewTargetParser(selector);
//        this(selector);
        tomparser = NewTomParser.createNewTomParser(this.getInputState(),this);
    }*/
/*
    public NewTargetParser(TokenStreamSelector selector){
        super(selector);
//        this.filename = filename;
        symbolTable().init();
        tomparser = new NewTomParser(getInputState(),this);
    }*/

    public TomServer getServer(){
        return TomServer.getInstance();
    }

    private final TomSignatureFactory getTomSignatureFactory(){
        return tsf();
    }
    
    private TomEnvironment environment() {
        return getServer().getEnvironment();
    }
    
    private TomSignatureFactory tsf(){
        return environment().getTomSignatureFactory();
    }
    
    private jtom.tools.ASTFactory ast() {
        return environment().getASTFactory();
    }
    
    private SymbolTable symbolTable() {
        return environment().getSymbolTable();
    }
    /*
    private TomTaskInput getInput() {
        return TomTaskInput.getInstance();
    }*/

    public TomStructureTable getStructTable() {
        return null;
  }




    public int popLine(){
        return ((Integer) lines.pop()).intValue();
    }

    public int popColumn(){
        return ((Integer) columns.pop()).intValue();
    }

    public void pushLine(int line){
        lines.push(new Integer(line));
    }

    public void pushColumn(int column){
        columns.push(new Integer(column));
    }
    
    private String cleanCode(String code){
        return code.substring(code.indexOf('{')+1,code.lastIndexOf('}'));
    }
    
    private String removeLastBrace(String code){
        return code.substring(0,code.lastIndexOf("}"));
    }

    private String getCode(){
        String result = targetlexer.target.toString();
        targetlexer.clearTarget();
        return result;
    }

    public void addTargetCode(Token t){
        targetlexer.target.append(t.getText());
    }

    private String pureCode(String code){
        return code.replace('\t',' ');
    }

    private boolean isCorrect(String code){
        return (! code.equals("") && ! code.matches("\\s*"));
    }
/*
    private void printRes(ATerm result){
        try{
            TomMainParser.writer.write(result+"\n\n");
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
  */  
    public int getLine(){
        return 0;
    }

    void p(String s){
        System.out.println(s);
    }

    public TomTerm startParsing(){
        try{
            return input();
        }
        catch(RecognitionException e){
            e.printStackTrace();
        }
        catch(TokenStreamException e){
            e.printStackTrace();
        }
        return null;
    }
} 

    
input returns [TomTerm result]
{
    result = null;
    LinkedList list = new LinkedList();
    pushLine(1);
    pushColumn(1);
}   
	:
        blockList[list] t:EOF
        {
            list.add(`TargetLanguageToTomTerm(
                    TL(
                        getCode(),
                        TextPosition(popLine(),popColumn()),
                        TextPosition(t.getLine(),t.getColumn())
                    )
                )
            );
            String comment = 
            "Generated by TOM (version " + TomServer.VERSION + "): Do not edit this file";
            list.addFirst(`TargetLanguageToTomTerm(Comment(comment)));
            result = `Tom(ast().makeList(list));
//            printRes(term);
        }
    ;

blockList [LinkedList list]
    :
        (
            matchConstruct[list]
        |   ruleConstruct[list] 
        |   signature()
        |   backquoteTerm[list]
        |   localVariable()
        |   operator[list] 
        |   operatorList[list] 
        |   operatorArray[list] 
        |   includeConstruct()
        |   typeTerm[list] 
        |   typeList[list] 
        |   typeArray[list] 
        |   LBRACE blockList[list] RBRACE 
        |   TARGET 
        )*

    ;

ruleConstruct [LinkedList list]
{
    TargetLanguage code = null;
}
    :
        t:RULE
        {     
            String textCode = getCode();
            if(isCorrect(textCode)) {
                code = `TL(
                    textCode,
                    TextPosition(popLine(),popColumn()),
                    TextPosition(t.getLine(),t.getColumn())
                );
                list.add(code);
            }
            
            Option ot = `OriginTracking(
                Name("Rule"),
                t.getLine(),
                Name(currentFile())
            );    
            
            Instruction ruleSet = tomparser.ruleConstruct(ot);
            list.add(ruleSet);
        }
    ;

matchConstruct [LinkedList list]
{
    TargetLanguage code = null;
}
	:
        t:MATCH 
        {        
            String textCode = getCode();
            if(isCorrect(textCode)) {
                code = `TL(
                    textCode,
                    TextPosition(popLine(),popColumn()),
                    TextPosition(t.getLine(),t.getColumn())
                );
                list.add(code);
            } 

            Option ot = `OriginTracking(Name("Match"),t.getLine(), Name(currentFile()));
            
            Instruction match = tomparser.matchConstruct(ot);
            list.add(match);
        }
    ;

signature
    :
        {
            System.out.println("target language :"+targetlexer.target);
            targetlexer.clearTarget();
        }
        VAS 
        {   
            tomparser.signature();
        } 
    ;

backquoteTerm [LinkedList list]
{
    TargetLanguage code = null;
}
    :
        t:BACKQUOTE
        {
            String textCode = getCode();
            if(isCorrect(textCode)) {
                code = `TL(
                    textCode,
                    TextPosition(popLine(),popColumn()),
                    TextPosition(t.getLine(),t.getColumn())
                );
                list.add(code);
            } 
            
            Option ot = `OriginTracking(Name("Backquote"),t.getLine(), Name(currentFile()));
            TomTerm bqTerm = tomparser.bqTerm();
            list.add(bqTerm);
            p("END BQ");
        }
    ;

localVariable
    :
        {
            targetlexer.clearTarget();
        }
        VARIABLE
        {
            tomparser.variable();
        }
    ;

operator [LinkedList list]
{
    TargetLanguage code = null;
}
    :
        t:OPERATOR
        {
            String textCode = pureCode(getCode());
            if(isCorrect(textCode)) {
                code = `TL(
                    textCode,
                    TextPosition(popLine(),popColumn()),
                    TextPosition(t.getLine(),t.getColumn()));
                list.add(`TargetLanguageToTomTerm(code));
            }

            Declaration operatorDecl = tomparser.operator();
            list.add(operatorDecl);
        }
    ;

operatorList [LinkedList list]
{
    TargetLanguage code = null;
}
    :
        t:OPERATORLIST 
        {
            String textCode = pureCode(getCode());
            if(isCorrect(textCode)) {
                code = `TL(
                    textCode,
                    TextPosition(popLine(),popColumn()),
                    TextPosition(t.getLine(),t.getColumn()));
                list.add(`TargetLanguageToTomTerm(code));
            }

            Declaration operatorListDecl = tomparser.operatorList();
            list.add(operatorListDecl);
        }
    ;

operatorArray [LinkedList list]
{
    TargetLanguage code = null;
}
    :
        t:OPERATORARRAY
        {
            String textCode = pureCode(getCode());
            if(isCorrect(textCode)) {
                code = `TL(
                    textCode,
                    TextPosition(popLine(),popColumn()),
                    TextPosition(t.getLine(),t.getColumn()));
                list.add(`TargetLanguageToTomTerm(code));
            }

            Declaration operatorArrayDecl = tomparser.operatorArray();
            list.add(operatorArrayDecl);
        }
    ;

includeConstruct
    :
        INCLUDE
        {
            tomparser.include();
        }
        
    ;
typeTerm [LinkedList list]
{
    TargetLanguage code = null;
    int line, column;
}
    :
        (
            tt:TYPETERM 
            {
                line = tt.getLine();
                column = tt.getColumn();
            }
        |   t:TYPE
            {
                line = t.getLine();
                column = t.getColumn();
            }
        )
        {
            // addPreviousCode...
            String textCode = getCode();
            if(isCorrect(textCode)) {
                code = `TL(
                    textCode,
                    TextPosition(popLine(),popColumn()),
                    TextPosition(line,column));
                list.add(code);
            }
            Declaration termdecl = tomparser.typeTerm();

//            list.add(`TargetLanguageToTomTerm(code));
            list.add(termdecl);

            
        }

    ;

typeList [LinkedList list]
{
    TargetLanguage code = null;
    int line, column;
}
    :
        t:TYPELIST
        {
            String textCode = getCode();
            if(isCorrect(textCode)) {
                code = `TL(
                    textCode,
                    TextPosition(popLine(),popColumn()),
                    TextPosition(t.getLine(),t.getColumn()));
                list.add(code);
            }

            Declaration listdecl = tomparser.typeList();
            list.add(listdecl);
        }
    ;

typeArray [LinkedList list]
{
    TargetLanguage code = null;
    int line, column;
}
    :
        t:TYPEARRAY
        {
            String textCode = getCode();
            if(isCorrect(textCode)) {
                code = `TL(
                    textCode,
                    TextPosition(popLine(),popColumn()),
                    TextPosition(t.getLine(),t.getColumn()));
                list.add(code);
            }

            Declaration arraydecl = tomparser.typeArray();
            list.add(arraydecl);
        }
    ;

goalLanguage [LinkedList list] returns [TargetLanguage result]
{
    result =  null;
}
    :
        t1:LBRACE 
        {
            pushLine(t1.getLine());
            pushColumn(t1.getColumn());
        }
        blockList[list]
        t2:RBRACE 
        {
            //code = targetlexer.target.toString();
            result = `TL(cleanCode(getCode()),
                TextPosition(popLine(),popColumn()),
                TextPosition(t2.getLine(),t2.getColumn())
            );
            targetlexer.clearTarget();
        }
    ;

targetLanguage [LinkedList list] returns [TargetLanguage result]
{
    result = null;
p("begin target");
}
    :
        blockList[list] t:RBRACE
        {
            String code = removeLastBrace(getCode());

            result = `TL(
                code,
                TextPosition(popLine(),popColumn()),
                TextPosition(t.getLine(),t.getColumn())
            );
            
            targetlexer.clearTarget();
     
            p("end target "+t.getLine());
        }
    ;
/*
bqGoal returns [String bqCode]
{
    bqCode = getCode();
} 
    :
        blockList[new LinkedList()] RPAREN
    ;
*/

// here begins the lexer


class NewTargetLexer extends Lexer;
options {
	k=6;
    filter=TARGET;
    charVocabulary='\u0000'..'\uffff';
}

{
    private boolean appendBraces = false;

    public StringBuffer target = new StringBuffer("");
    
    public void setAppendBraces(boolean b){
        appendBraces = b;
    }

    public void clearTarget(){
        target.delete(0,target.length());
    }
}

BACKQUOTE
    :   "`" {TomMainParser.selector.push("tomlexer");}
    ;
RULE
    :   "%rule" {TomMainParser.selector.push("tomlexer");}
    ;
INCLUDE
    :   "%include" 
    ;
MATCH
	:	"%match" {TomMainParser.selector.push("tomlexer");}
    ;
VARIABLE
    :   "%variable" 
	;
VAS
    :   "%vas"  
    ;
OPERATOR
    :   "%op"   {TomMainParser.selector.push("tomlexer");}
    ;
TYPE
    :   "%type" {TomMainParser.selector.push("tomlexer");}
    ;
TYPETERM
    :   "%typeterm" {TomMainParser.selector.push("tomlexer");}
    ;
TYPELIST
    :   "%typelist" {TomMainParser.selector.push("tomlexer");}
    ;
TYPEARRAY
    :   "%typearray" {TomMainParser.selector.push("tomlexer");}
    ;   
OPERATORLIST
    :   "%oplist"   {TomMainParser.selector.push("tomlexer");}
    ;
OPERATORARRAY
    :   "%oparray"  {TomMainParser.selector.push("tomlexer");}
    ;
/*
LPAREN
    :   '(' 
        {
            target.append($getText);
        }  
    ;
RPAREN
    :   ')'
        {
            target.append($getText);
        }  
    ;*/
LBRACE  
    :   '{' 
        {
            target.append($getText);
        }  
    ;
RBRACE  
    :   '}' 
        {
            target.append($getText);
        }  
    ; 

WS	:	(	' '
		|	'\t'
		|	'\f'
		// handle newlines
		|	(	"\r\n"  // Evil DOS
			|	'\r'    // Macintosh
			|	'\n'    // Unix (the right way)
			)
			{ newline(); }
		){  target.append($getText);
            $setType(Token.SKIP);}
		
	;


protected
ESC
	:	'\\'
		(	'n'
		|	'r'
		|	't'
		|	'b'
		|	'f'
		|	'"'
		|	'\''
		|	'\\'
		|	('u')+ HEX_DIGIT HEX_DIGIT HEX_DIGIT HEX_DIGIT
		|	'0'..'3'
			(
				options {
					warnWhenFollowAmbig = false;
				}
			:	'0'..'7'
				(
					options {
						warnWhenFollowAmbig = false;
					}
				:	'0'..'7'
				)?
			)?
		|	'4'..'7'
			(
				options {
					warnWhenFollowAmbig = false;
				}
			:	'0'..'7'
			)?
		)
	;

protected
HEX_DIGIT
	:	('0'..'9'|'A'..'F'|'a'..'f')
	;

COMMENT 
    :
        ( SL_COMMENT | t:ML_COMMENT {$setType(t.getType());} )
        { $setType(Token.SKIP);}
	;

protected
SL_COMMENT 
    :
        "//"
        ( ~('\n'|'\r') )*
        (
			options {
				generateAmbigWarnings=false;
			}
		:	'\r' '\n'
		|	'\r'
		|	'\n'
        )
        {
            target.append($getText);
            newline(); 
        }
	;

protected
ML_COMMENT 
    :
        "/*"        
        (	{ LA(2)!='/' }? '*' 
        |
        )
        (
            options {
                greedy=false;  // make it exit upon "*/"
                generateAmbigWarnings=false; // shut off newline errors
            }
        :	'\r' '\n'	{newline();}
        |	'\r'		{newline();}
        |	'\n'		{newline();}
        |	~('\n'|'\r')
        )*
        "*/"
        {target.append($getText);}
	;

protected 
TARGET
    :
        (   
            . 
        )
        {target.append($getText);}
    ;
