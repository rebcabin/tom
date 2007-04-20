// $ANTLR 3.0b6 /home/tonio/workspace/jtom/src/tom/gom/expander/rule/Rule.g 2007-04-20 18:01:04

  package tom.gom.expander.rule;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class RuleLexer extends Lexer {
    public static final int NOTEQUALS=14;
    public static final int RPAR=18;
    public static final int LPAR=16;
    public static final int WS=22;
    public static final int ARROW=11;
    public static final int STRING=20;
    public static final int RULE=5;
    public static final int RULELIST=4;
    public static final int CONDEQUALS=9;
    public static final int ESC=21;
    public static final int EQUALS=13;
    public static final int CONDTERM=8;
    public static final int CONDNOTEQUALS=10;
    public static final int IF=12;
    public static final int INT=19;
    public static final int EOF=-1;
    public static final int Tokens=23;
    public static final int CONDRULE=6;
    public static final int APPL=7;
    public static final int COMA=17;
    public static final int ID=15;
    public RuleLexer() {;} 
    public RuleLexer(CharStream input) {
        super(input);
    }
    public String getGrammarFileName() { return "/home/tonio/workspace/jtom/src/tom/gom/expander/rule/Rule.g"; }

    // $ANTLR start ARROW
    public void mARROW() throws RecognitionException {
        try {
            ruleNestingLevel++;
            int _type = ARROW;
            int _start = getCharIndex();
            int _line = getLine();
            int _charPosition = getCharPositionInLine();
            int _channel = Token.DEFAULT_CHANNEL;
            // /home/tonio/workspace/jtom/src/tom/gom/expander/rule/Rule.g:48:9: ( '->' )
            // /home/tonio/workspace/jtom/src/tom/gom/expander/rule/Rule.g:48:9: '->'
            {
            match("->"); 


            }



                    if ( token==null && ruleNestingLevel==1 ) {
                        emit(_type,_line,_charPosition,_channel,_start,getCharIndex()-1);
                    }

                        }
        finally {
            ruleNestingLevel--;
        }
    }
    // $ANTLR end ARROW

    // $ANTLR start LPAR
    public void mLPAR() throws RecognitionException {
        try {
            ruleNestingLevel++;
            int _type = LPAR;
            int _start = getCharIndex();
            int _line = getLine();
            int _charPosition = getCharPositionInLine();
            int _channel = Token.DEFAULT_CHANNEL;
            // /home/tonio/workspace/jtom/src/tom/gom/expander/rule/Rule.g:49:8: ( '(' )
            // /home/tonio/workspace/jtom/src/tom/gom/expander/rule/Rule.g:49:8: '('
            {
            match('('); 

            }



                    if ( token==null && ruleNestingLevel==1 ) {
                        emit(_type,_line,_charPosition,_channel,_start,getCharIndex()-1);
                    }

                        }
        finally {
            ruleNestingLevel--;
        }
    }
    // $ANTLR end LPAR

    // $ANTLR start RPAR
    public void mRPAR() throws RecognitionException {
        try {
            ruleNestingLevel++;
            int _type = RPAR;
            int _start = getCharIndex();
            int _line = getLine();
            int _charPosition = getCharPositionInLine();
            int _channel = Token.DEFAULT_CHANNEL;
            // /home/tonio/workspace/jtom/src/tom/gom/expander/rule/Rule.g:50:8: ( ')' )
            // /home/tonio/workspace/jtom/src/tom/gom/expander/rule/Rule.g:50:8: ')'
            {
            match(')'); 

            }



                    if ( token==null && ruleNestingLevel==1 ) {
                        emit(_type,_line,_charPosition,_channel,_start,getCharIndex()-1);
                    }

                        }
        finally {
            ruleNestingLevel--;
        }
    }
    // $ANTLR end RPAR

    // $ANTLR start COMA
    public void mCOMA() throws RecognitionException {
        try {
            ruleNestingLevel++;
            int _type = COMA;
            int _start = getCharIndex();
            int _line = getLine();
            int _charPosition = getCharPositionInLine();
            int _channel = Token.DEFAULT_CHANNEL;
            // /home/tonio/workspace/jtom/src/tom/gom/expander/rule/Rule.g:51:8: ( ',' )
            // /home/tonio/workspace/jtom/src/tom/gom/expander/rule/Rule.g:51:8: ','
            {
            match(','); 

            }



                    if ( token==null && ruleNestingLevel==1 ) {
                        emit(_type,_line,_charPosition,_channel,_start,getCharIndex()-1);
                    }

                        }
        finally {
            ruleNestingLevel--;
        }
    }
    // $ANTLR end COMA

    // $ANTLR start EQUALS
    public void mEQUALS() throws RecognitionException {
        try {
            ruleNestingLevel++;
            int _type = EQUALS;
            int _start = getCharIndex();
            int _line = getLine();
            int _charPosition = getCharPositionInLine();
            int _channel = Token.DEFAULT_CHANNEL;
            // /home/tonio/workspace/jtom/src/tom/gom/expander/rule/Rule.g:52:10: ( '==' )
            // /home/tonio/workspace/jtom/src/tom/gom/expander/rule/Rule.g:52:10: '=='
            {
            match("=="); 


            }



                    if ( token==null && ruleNestingLevel==1 ) {
                        emit(_type,_line,_charPosition,_channel,_start,getCharIndex()-1);
                    }

                        }
        finally {
            ruleNestingLevel--;
        }
    }
    // $ANTLR end EQUALS

    // $ANTLR start NOTEQUALS
    public void mNOTEQUALS() throws RecognitionException {
        try {
            ruleNestingLevel++;
            int _type = NOTEQUALS;
            int _start = getCharIndex();
            int _line = getLine();
            int _charPosition = getCharPositionInLine();
            int _channel = Token.DEFAULT_CHANNEL;
            // /home/tonio/workspace/jtom/src/tom/gom/expander/rule/Rule.g:53:13: ( '!=' )
            // /home/tonio/workspace/jtom/src/tom/gom/expander/rule/Rule.g:53:13: '!='
            {
            match("!="); 


            }



                    if ( token==null && ruleNestingLevel==1 ) {
                        emit(_type,_line,_charPosition,_channel,_start,getCharIndex()-1);
                    }

                        }
        finally {
            ruleNestingLevel--;
        }
    }
    // $ANTLR end NOTEQUALS

    // $ANTLR start IF
    public void mIF() throws RecognitionException {
        try {
            ruleNestingLevel++;
            int _type = IF;
            int _start = getCharIndex();
            int _line = getLine();
            int _charPosition = getCharPositionInLine();
            int _channel = Token.DEFAULT_CHANNEL;
            // /home/tonio/workspace/jtom/src/tom/gom/expander/rule/Rule.g:54:6: ( 'if' )
            // /home/tonio/workspace/jtom/src/tom/gom/expander/rule/Rule.g:54:6: 'if'
            {
            match("if"); 


            }



                    if ( token==null && ruleNestingLevel==1 ) {
                        emit(_type,_line,_charPosition,_channel,_start,getCharIndex()-1);
                    }

                        }
        finally {
            ruleNestingLevel--;
        }
    }
    // $ANTLR end IF

    // $ANTLR start INT
    public void mINT() throws RecognitionException {
        try {
            ruleNestingLevel++;
            int _type = INT;
            int _start = getCharIndex();
            int _line = getLine();
            int _charPosition = getCharPositionInLine();
            int _channel = Token.DEFAULT_CHANNEL;
            // /home/tonio/workspace/jtom/src/tom/gom/expander/rule/Rule.g:55:7: ( ( '0' .. '9' )+ )
            // /home/tonio/workspace/jtom/src/tom/gom/expander/rule/Rule.g:55:7: ( '0' .. '9' )+
            {
            // /home/tonio/workspace/jtom/src/tom/gom/expander/rule/Rule.g:55:7: ( '0' .. '9' )+
            int cnt1=0;
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);
                if ( ((LA1_0>='0' && LA1_0<='9')) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // /home/tonio/workspace/jtom/src/tom/gom/expander/rule/Rule.g:55:8: '0' .. '9'
            	    {
            	    matchRange('0','9'); 

            	    }
            	    break;

            	default :
            	    if ( cnt1 >= 1 ) break loop1;
                        EarlyExitException eee =
                            new EarlyExitException(1, input);
                        throw eee;
                }
                cnt1++;
            } while (true);


            }



                    if ( token==null && ruleNestingLevel==1 ) {
                        emit(_type,_line,_charPosition,_channel,_start,getCharIndex()-1);
                    }

                        }
        finally {
            ruleNestingLevel--;
        }
    }
    // $ANTLR end INT

    // $ANTLR start ESC
    public void mESC() throws RecognitionException {
        try {
            ruleNestingLevel++;
            int _type = ESC;
            int _start = getCharIndex();
            int _line = getLine();
            int _charPosition = getCharPositionInLine();
            int _channel = Token.DEFAULT_CHANNEL;
            // /home/tonio/workspace/jtom/src/tom/gom/expander/rule/Rule.g:56:7: ( '\\\\' ('n'|'r'|'t'|'b'|'f'|'\"'|'\\''|'\\\\'))
            // /home/tonio/workspace/jtom/src/tom/gom/expander/rule/Rule.g:56:7: '\\\\' ('n'|'r'|'t'|'b'|'f'|'\"'|'\\''|'\\\\')
            {
            match('\\'); 
            if ( input.LA(1)=='\"'||input.LA(1)=='\''||input.LA(1)=='\\'||input.LA(1)=='b'||input.LA(1)=='f'||input.LA(1)=='n'||input.LA(1)=='r'||input.LA(1)=='t' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recover(mse);    throw mse;
            }


            }



                    if ( token==null && ruleNestingLevel==1 ) {
                        emit(_type,_line,_charPosition,_channel,_start,getCharIndex()-1);
                    }

                        }
        finally {
            ruleNestingLevel--;
        }
    }
    // $ANTLR end ESC

    // $ANTLR start STRING
    public void mSTRING() throws RecognitionException {
        try {
            ruleNestingLevel++;
            int _type = STRING;
            int _start = getCharIndex();
            int _line = getLine();
            int _charPosition = getCharPositionInLine();
            int _channel = Token.DEFAULT_CHANNEL;
            // /home/tonio/workspace/jtom/src/tom/gom/expander/rule/Rule.g:57:10: ( '\"' ( ESC | ~ ('\"'|'\\\\'|'\\n'|'\\r'))* '\"' )
            // /home/tonio/workspace/jtom/src/tom/gom/expander/rule/Rule.g:57:10: '\"' ( ESC | ~ ('\"'|'\\\\'|'\\n'|'\\r'))* '\"'
            {
            match('\"'); 
            // /home/tonio/workspace/jtom/src/tom/gom/expander/rule/Rule.g:57:14: ( ESC | ~ ('\"'|'\\\\'|'\\n'|'\\r'))*
            loop2:
            do {
                int alt2=3;
                int LA2_0 = input.LA(1);
                if ( (LA2_0=='\\') ) {
                    alt2=1;
                }
                else if ( ((LA2_0>='\u0000' && LA2_0<='\t')||(LA2_0>='\u000B' && LA2_0<='\f')||(LA2_0>='\u000E' && LA2_0<='!')||(LA2_0>='#' && LA2_0<='[')||(LA2_0>=']' && LA2_0<='\uFFFE')) ) {
                    alt2=2;
                }


                switch (alt2) {
            	case 1 :
            	    // /home/tonio/workspace/jtom/src/tom/gom/expander/rule/Rule.g:57:15: ESC
            	    {
            	    mESC(); 

            	    }
            	    break;
            	case 2 :
            	    // /home/tonio/workspace/jtom/src/tom/gom/expander/rule/Rule.g:57:19: ~ ('\"'|'\\\\'|'\\n'|'\\r')
            	    {
            	    if ( (input.LA(1)>='\u0000' && input.LA(1)<='\t')||(input.LA(1)>='\u000B' && input.LA(1)<='\f')||(input.LA(1)>='\u000E' && input.LA(1)<='!')||(input.LA(1)>='#' && input.LA(1)<='[')||(input.LA(1)>=']' && input.LA(1)<='\uFFFE') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse =
            	            new MismatchedSetException(null,input);
            	        recover(mse);    throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    break loop2;
                }
            } while (true);

            match('\"'); 

            }



                    if ( token==null && ruleNestingLevel==1 ) {
                        emit(_type,_line,_charPosition,_channel,_start,getCharIndex()-1);
                    }

                        }
        finally {
            ruleNestingLevel--;
        }
    }
    // $ANTLR end STRING

    // $ANTLR start ID
    public void mID() throws RecognitionException {
        try {
            ruleNestingLevel++;
            int _type = ID;
            int _start = getCharIndex();
            int _line = getLine();
            int _charPosition = getCharPositionInLine();
            int _channel = Token.DEFAULT_CHANNEL;
            // /home/tonio/workspace/jtom/src/tom/gom/expander/rule/Rule.g:58:6: ( ('a'..'z'|'A'..'Z'|'_') ( ('a'..'z'|'A'..'Z'|'_'|'0'..'9'))* )
            // /home/tonio/workspace/jtom/src/tom/gom/expander/rule/Rule.g:58:6: ('a'..'z'|'A'..'Z'|'_') ( ('a'..'z'|'A'..'Z'|'_'|'0'..'9'))*
            {
            if ( (input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z') ) {
                input.consume();

            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recover(mse);    throw mse;
            }

            // /home/tonio/workspace/jtom/src/tom/gom/expander/rule/Rule.g:58:30: ( ('a'..'z'|'A'..'Z'|'_'|'0'..'9'))*
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);
                if ( ((LA3_0>='0' && LA3_0<='9')||(LA3_0>='A' && LA3_0<='Z')||LA3_0=='_'||(LA3_0>='a' && LA3_0<='z')) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // /home/tonio/workspace/jtom/src/tom/gom/expander/rule/Rule.g:58:31: ('a'..'z'|'A'..'Z'|'_'|'0'..'9')
            	    {
            	    if ( (input.LA(1)>='0' && input.LA(1)<='9')||(input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse =
            	            new MismatchedSetException(null,input);
            	        recover(mse);    throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    break loop3;
                }
            } while (true);


            }



                    if ( token==null && ruleNestingLevel==1 ) {
                        emit(_type,_line,_charPosition,_channel,_start,getCharIndex()-1);
                    }

                        }
        finally {
            ruleNestingLevel--;
        }
    }
    // $ANTLR end ID

    // $ANTLR start WS
    public void mWS() throws RecognitionException {
        try {
            ruleNestingLevel++;
            int _type = WS;
            int _start = getCharIndex();
            int _line = getLine();
            int _charPosition = getCharPositionInLine();
            int _channel = Token.DEFAULT_CHANNEL;
            // /home/tonio/workspace/jtom/src/tom/gom/expander/rule/Rule.g:59:6: ( ( (' '|'\\t'|'\\n'))+ )
            // /home/tonio/workspace/jtom/src/tom/gom/expander/rule/Rule.g:59:6: ( (' '|'\\t'|'\\n'))+
            {
            // /home/tonio/workspace/jtom/src/tom/gom/expander/rule/Rule.g:59:6: ( (' '|'\\t'|'\\n'))+
            int cnt4=0;
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);
                if ( ((LA4_0>='\t' && LA4_0<='\n')||LA4_0==' ') ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // /home/tonio/workspace/jtom/src/tom/gom/expander/rule/Rule.g:59:7: (' '|'\\t'|'\\n')
            	    {
            	    if ( (input.LA(1)>='\t' && input.LA(1)<='\n')||input.LA(1)==' ' ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse =
            	            new MismatchedSetException(null,input);
            	        recover(mse);    throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt4 >= 1 ) break loop4;
                        EarlyExitException eee =
                            new EarlyExitException(4, input);
                        throw eee;
                }
                cnt4++;
            } while (true);

             _channel=HIDDEN; 

            }



                    if ( token==null && ruleNestingLevel==1 ) {
                        emit(_type,_line,_charPosition,_channel,_start,getCharIndex()-1);
                    }

                        }
        finally {
            ruleNestingLevel--;
        }
    }
    // $ANTLR end WS

    public void mTokens() throws RecognitionException {
        // /home/tonio/workspace/jtom/src/tom/gom/expander/rule/Rule.g:1:10: ( ARROW | LPAR | RPAR | COMA | EQUALS | NOTEQUALS | IF | INT | ESC | STRING | ID | WS )
        int alt5=12;
        switch ( input.LA(1) ) {
        case '-':
            alt5=1;
            break;
        case '(':
            alt5=2;
            break;
        case ')':
            alt5=3;
            break;
        case ',':
            alt5=4;
            break;
        case '=':
            alt5=5;
            break;
        case '!':
            alt5=6;
            break;
        case 'i':
            int LA5_7 = input.LA(2);
            if ( (LA5_7=='f') ) {
                int LA5_13 = input.LA(3);
                if ( ((LA5_13>='0' && LA5_13<='9')||(LA5_13>='A' && LA5_13<='Z')||LA5_13=='_'||(LA5_13>='a' && LA5_13<='z')) ) {
                    alt5=11;
                }
                else {
                    alt5=7;}
            }
            else {
                alt5=11;}
            break;
        case '0':
        case '1':
        case '2':
        case '3':
        case '4':
        case '5':
        case '6':
        case '7':
        case '8':
        case '9':
            alt5=8;
            break;
        case '\\':
            alt5=9;
            break;
        case '\"':
            alt5=10;
            break;
        case 'A':
        case 'B':
        case 'C':
        case 'D':
        case 'E':
        case 'F':
        case 'G':
        case 'H':
        case 'I':
        case 'J':
        case 'K':
        case 'L':
        case 'M':
        case 'N':
        case 'O':
        case 'P':
        case 'Q':
        case 'R':
        case 'S':
        case 'T':
        case 'U':
        case 'V':
        case 'W':
        case 'X':
        case 'Y':
        case 'Z':
        case '_':
        case 'a':
        case 'b':
        case 'c':
        case 'd':
        case 'e':
        case 'f':
        case 'g':
        case 'h':
        case 'j':
        case 'k':
        case 'l':
        case 'm':
        case 'n':
        case 'o':
        case 'p':
        case 'q':
        case 'r':
        case 's':
        case 't':
        case 'u':
        case 'v':
        case 'w':
        case 'x':
        case 'y':
        case 'z':
            alt5=11;
            break;
        case '\t':
        case '\n':
        case ' ':
            alt5=12;
            break;
        default:
            NoViableAltException nvae =
                new NoViableAltException("1:1: Tokens : ( ARROW | LPAR | RPAR | COMA | EQUALS | NOTEQUALS | IF | INT | ESC | STRING | ID | WS );", 5, 0, input);

            throw nvae;
        }

        switch (alt5) {
            case 1 :
                // /home/tonio/workspace/jtom/src/tom/gom/expander/rule/Rule.g:1:10: ARROW
                {
                mARROW(); 

                }
                break;
            case 2 :
                // /home/tonio/workspace/jtom/src/tom/gom/expander/rule/Rule.g:1:16: LPAR
                {
                mLPAR(); 

                }
                break;
            case 3 :
                // /home/tonio/workspace/jtom/src/tom/gom/expander/rule/Rule.g:1:21: RPAR
                {
                mRPAR(); 

                }
                break;
            case 4 :
                // /home/tonio/workspace/jtom/src/tom/gom/expander/rule/Rule.g:1:26: COMA
                {
                mCOMA(); 

                }
                break;
            case 5 :
                // /home/tonio/workspace/jtom/src/tom/gom/expander/rule/Rule.g:1:31: EQUALS
                {
                mEQUALS(); 

                }
                break;
            case 6 :
                // /home/tonio/workspace/jtom/src/tom/gom/expander/rule/Rule.g:1:38: NOTEQUALS
                {
                mNOTEQUALS(); 

                }
                break;
            case 7 :
                // /home/tonio/workspace/jtom/src/tom/gom/expander/rule/Rule.g:1:48: IF
                {
                mIF(); 

                }
                break;
            case 8 :
                // /home/tonio/workspace/jtom/src/tom/gom/expander/rule/Rule.g:1:51: INT
                {
                mINT(); 

                }
                break;
            case 9 :
                // /home/tonio/workspace/jtom/src/tom/gom/expander/rule/Rule.g:1:55: ESC
                {
                mESC(); 

                }
                break;
            case 10 :
                // /home/tonio/workspace/jtom/src/tom/gom/expander/rule/Rule.g:1:59: STRING
                {
                mSTRING(); 

                }
                break;
            case 11 :
                // /home/tonio/workspace/jtom/src/tom/gom/expander/rule/Rule.g:1:66: ID
                {
                mID(); 

                }
                break;
            case 12 :
                // /home/tonio/workspace/jtom/src/tom/gom/expander/rule/Rule.g:1:69: WS
                {
                mWS(); 

                }
                break;

        }

    }


 

}