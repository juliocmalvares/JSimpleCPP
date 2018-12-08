//### This file created by BYACC 1.8(/Java extension  1.15)
//### Java capabilities added 7 Jan 97, Bob Jamison
//### Updated : 27 Nov 97  -- Bob Jamison, Joe Nieten
//###           01 Jan 98  -- Bob Jamison -- fixed generic semantic constructor
//###           01 Jun 99  -- Bob Jamison -- added Runnable support
//###           06 Aug 00  -- Bob Jamison -- made state variables class-global
//###           03 Jan 01  -- Bob Jamison -- improved flags, tracing
//###           16 May 01  -- Bob Jamison -- added custom stack sizing
//###           04 Mar 02  -- Yuval Oren  -- improved java performance, added options
//###           14 Mar 02  -- Tomas Hurka -- -d support, static initializer workaround
//### Please send bug reports to tom@hukatronic.cz
//### static char yysccsid[] = "@(#)yaccpar	1.8 (Berkeley) 01/20/90";






//#line 2 "parser.y"
/*yylval é a variavel do lexema que recebe do analisador lexico*/
package misc;
import java.io.*;
import ast.*;
import ast.expr.*;
import symbtab.SymbolTab;
import ast.expr.math.*;
import ast.expr.atrib.*;
import ast.expr.math.types.*;
import ast.expr.conditional.*;
import ast.expr.loop.*;
import ast.expr.math.logical.*;

/*int a;*/
//#line 32 "Parser.java"




public class Parser
             extends Scanner
{

boolean yydebug;        //do I want debug output?
int yynerrs;            //number of errors so far
int yyerrflag;          //was there an error?
int yychar;             //the current working character

//########## MESSAGES ##########
//###############################################################
// method: debug
//###############################################################
void debug(String msg)
{
  if (yydebug)
    System.out.println(msg);
}

//########## STATE STACK ##########
final static int YYSTACKSIZE = 500;  //maximum stack size
int statestk[] = new int[YYSTACKSIZE]; //state stack
int stateptr;
int stateptrmax;                     //highest index of stackptr
int statemax;                        //state when highest index reached
//###############################################################
// methods: state stack push,pop,drop,peek
//###############################################################
final void state_push(int state)
{
  try {
		stateptr++;
		statestk[stateptr]=state;
	 }
	 catch (ArrayIndexOutOfBoundsException e) {
     int oldsize = statestk.length;
     int newsize = oldsize * 2;
     int[] newstack = new int[newsize];
     System.arraycopy(statestk,0,newstack,0,oldsize);
     statestk = newstack;
     statestk[stateptr]=state;
  }
}
final int state_pop()
{
  return statestk[stateptr--];
}
final void state_drop(int cnt)
{
  stateptr -= cnt; 
}
final int state_peek(int relative)
{
  return statestk[stateptr-relative];
}
//###############################################################
// method: init_stacks : allocate and prepare stacks
//###############################################################
final boolean init_stacks()
{
  stateptr = -1;
  val_init();
  return true;
}
//###############################################################
// method: dump_stacks : show n levels of the stacks
//###############################################################
void dump_stacks(int count)
{
int i;
  System.out.println("=index==state====value=     s:"+stateptr+"  v:"+valptr);
  for (i=0;i<count;i++)
    System.out.println(" "+i+"    "+statestk[i]+"      "+valstk[i]);
  System.out.println("======================");
}


//########## SEMANTIC VALUES ##########
//## **user defined:Object
String   yytext;//user variable to return contextual strings
Object yyval; //used to return semantic vals from action routines
Object yylval;//the 'lval' (result) I got from yylex()
Object valstk[] = new Object[YYSTACKSIZE];
int valptr;
//###############################################################
// methods: value stack push,pop,drop,peek.
//###############################################################
final void val_init()
{
  yyval=new Object();
  yylval=new Object();
  valptr=-1;
}
final void val_push(Object val)
{
  try {
    valptr++;
    valstk[valptr]=val;
  }
  catch (ArrayIndexOutOfBoundsException e) {
    int oldsize = valstk.length;
    int newsize = oldsize*2;
    Object[] newstack = new Object[newsize];
    System.arraycopy(valstk,0,newstack,0,oldsize);
    valstk = newstack;
    valstk[valptr]=val;
  }
}
final Object val_pop()
{
  return valstk[valptr--];
}
final void val_drop(int cnt)
{
  valptr -= cnt;
}
final Object val_peek(int relative)
{
  return valstk[valptr-relative];
}
final Object dup_yyval(Object val)
{
  return val;
}
//#### end semantic value section ####
public final static short PRINT=265;
public final static short ID=266;
public final static short ATRIB=267;
public final static short IF=268;
public final static short WHILE=269;
public final static short ELSE=270;
public final static short INT=271;
public final static short DOUBLE=272;
public final static short FOR=273;
public final static short BOOL=274;
public final static short CHAR=275;
public final static short STRING=276;
public final static short CIN=277;
public final static short BOOLEAN=278;
public final static short PMORE=279;
public final static short PLESS=280;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    0,    0,    0,    1,    1,    1,    1,    1,    1,
    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,
    2,    2,    3,    3,    3,    3,    3,    3,    3,    3,
    3,    3,    3,    3,    3,    3,    3,    3,    3,    3,
    3,    3,    3,
};
final static short yylen[] = {                            2,
    3,    2,    2,    3,    3,    3,    7,   11,    7,   11,
    5,    5,    5,    5,    5,    3,    3,    3,    3,    3,
    5,    5,    3,    3,    3,    3,    3,    3,    2,    2,
    3,    2,    2,    3,    3,    3,    3,    3,    3,    1,
    1,    1,    1,
};
final static short yydefred[] = {                         0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    2,    3,    0,    0,
    0,   42,   40,   41,   43,    0,    0,    0,    0,    0,
    0,    0,   16,    0,   17,    0,    0,   18,    0,   19,
    0,   20,    0,    1,    4,   32,   33,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   29,   30,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   31,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   27,   28,   22,   21,    0,    0,   11,
   12,    0,   13,   14,   15,    0,    0,    0,    0,    9,
    0,    0,    0,    0,    0,    0,    0,    8,   10,
};
final static short yydgoto[] = {                         12,
   13,   14,   37,
};
final static short yysindex[] = {                       178,
 -261,  -61,   -6,   -4, -228, -220,   32, -204, -203, -191,
 -205, -161,   19,  -33,  -40, -257,  -40,  -40,  -43,  -42,
 -161,  -37,  -31,   20,  -40,   21,    0,    0,   23,  -40,
  -40,    0,    0,    0,    0,  -40,   80,   -1,    2,  -18,
  -10,  -40,    0,  -40,    0,   24,  -40,    0,  -40,    0,
  -40,    0,   80,    0,    0,    0,    0,   -2,  -40,  -40,
  -40,  -40,  -40,  -40,  -40,  -40,  -40,  -40,  -40,  -40,
    0,    0,   28,   30,  -32,  -26,  -41,   14,  -40,   22,
   43,   51,    0,  -35,  -35,  -35,  -35,  -35,  -35,  -35,
  -35,  -30,  -30,    0,    0,    0,    0,  178,  178,    0,
    0,   72,    0,    0,    0,  163,  165,  -40, -171,    0,
    6,  -23,  -22,  178,  178,  191,  193,    0,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   47,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   50,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,  103,  110,  116,  123,  130,  136,  143,
  150,   88,   96,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   59,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,
};
final static short yygindex[] = {                       -44,
  307,   91,  454,
};
final static int YYTABLESIZE=562;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         36,
   67,   69,   30,   70,   31,   68,   67,   69,   38,   70,
   29,   68,   69,   39,   70,   43,   45,  100,   60,   15,
   59,   48,   75,   67,   69,   28,   70,   50,   68,   16,
   76,   67,   69,   17,   70,   18,   68,   19,   83,   67,
   69,   60,   70,   59,   68,   20,  113,   67,   69,   60,
   70,   59,   68,  106,  107,   67,   69,   60,   70,   59,
   68,   22,   23,   67,   69,   60,   70,   59,   68,  116,
  117,   21,  101,   60,   24,   59,   25,   27,   52,   54,
  103,   60,   79,   59,   67,   69,   96,   70,   97,   68,
   98,   73,   67,   69,   74,   70,   99,   68,  112,  114,
  115,  104,   60,    1,   59,    5,    3,    4,    6,  105,
   60,    7,   59,   67,   69,   11,   70,    7,   68,   55,
    0,   67,   69,    0,   70,    0,   68,    0,   26,   26,
  108,   60,    0,   59,   26,    0,   25,   25,    0,   60,
    0,   59,   25,   23,    0,    0,   26,   26,    0,   26,
   24,    0,    0,    0,   25,   25,   39,   25,    0,    0,
    0,   23,   23,   38,   23,    0,    0,    0,   24,   24,
   34,   24,    0,    0,   39,   39,   35,   39,    0,    0,
    0,   38,   38,   37,   38,    0,    0,    0,   34,   34,
   36,   34,    0,    0,   35,   35,    0,   35,    0,    0,
    0,   37,   37,    0,   37,    0,    0,    0,   36,   36,
    0,   36,    0,    0,    0,   61,   62,   63,   64,   65,
   66,   71,   72,   42,   44,   32,    0,   71,   72,   47,
   33,   34,   71,   72,    0,   49,    0,   35,   61,   62,
   63,   64,   65,   66,   71,   72,   61,   62,   63,   64,
   65,   66,   71,   72,   61,   62,   63,   64,   65,   66,
   71,   72,   61,   62,   63,   64,   65,   66,   71,   72,
   61,   62,   63,   64,   65,   66,   71,   72,   61,   62,
   63,   64,   65,   66,   71,   72,   51,  109,    2,  110,
    0,    0,    0,    5,    6,    0,    8,    9,   10,   61,
   62,   63,   64,   65,   66,   71,   72,   61,   62,   63,
   64,   65,   66,   71,   72,  118,    0,  119,   26,    0,
    0,    0,    0,    0,    0,    0,    0,   46,   61,   62,
   63,   64,   65,   66,   71,   72,   61,   62,   63,   64,
   65,   66,   71,   72,   26,   26,   26,   26,   26,   26,
    0,    0,   25,   25,   25,   25,   25,   25,    0,   23,
   23,   23,   23,   23,   23,    0,   24,   24,   24,   24,
   24,   24,   39,   39,   39,   39,   39,   39,    0,   38,
   38,   38,   38,   38,   38,    0,   34,   34,   34,   34,
   34,   34,   35,   35,   35,   35,   35,   35,    0,   37,
   37,   37,   37,   37,   37,    0,   36,   36,   36,   36,
   36,   36,   26,   26,    0,    0,    0,    0,    0,    0,
    0,    0,   26,   26,    0,    0,    0,    1,    0,    1,
    3,    4,    3,    4,    0,    7,    0,    7,    0,   11,
    0,   11,    1,    2,    0,    3,    4,    0,    5,    6,
    7,    8,    9,   10,   11,    1,    0,    1,    3,    4,
    3,    4,    0,    7,    0,    7,    0,   11,    0,   11,
   40,   41,    0,    0,    0,    0,    0,    0,   53,    0,
    0,    0,    0,   56,   57,    0,    0,    0,    0,   58,
    0,    0,    0,    0,    0,   77,    0,   78,    0,    0,
   80,    0,   81,    0,   82,    0,    0,    0,    0,    0,
    0,    0,   84,   85,   86,   87,   88,   89,   90,   91,
   92,   93,   94,   95,    0,    0,    0,    0,    0,    0,
    0,    0,  102,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,  111,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         40,
   42,   43,   43,   45,   45,   47,   42,   43,  266,   45,
   44,   47,   43,  271,   45,   59,   59,   59,   60,  281,
   62,   59,   41,   42,   43,   59,   45,   59,   47,   91,
   41,   42,   43,   40,   45,   40,   47,  266,   41,   42,
   43,   60,   45,   62,   47,  266,   41,   42,   43,   60,
   45,   62,   47,   98,   99,   42,   43,   60,   45,   62,
   47,  266,  266,   42,   43,   60,   45,   62,   47,  114,
  115,   40,   59,   60,  266,   62,  282,   59,   59,   59,
   59,   60,   59,   62,   42,   43,   59,   45,   59,   47,
  123,   93,   42,   43,   93,   45,  123,   47,  270,  123,
  123,   59,   60,  265,   62,   59,  268,  269,   59,   59,
   60,  273,   62,   42,   43,  277,   45,   59,   47,   29,
   -1,   42,   43,   -1,   45,   -1,   47,   -1,   41,   42,
   59,   60,   -1,   62,   47,   -1,   41,   42,   -1,   60,
   -1,   62,   47,   41,   -1,   -1,   59,   60,   -1,   62,
   41,   -1,   -1,   -1,   59,   60,   41,   62,   -1,   -1,
   -1,   59,   60,   41,   62,   -1,   -1,   -1,   59,   60,
   41,   62,   -1,   -1,   59,   60,   41,   62,   -1,   -1,
   -1,   59,   60,   41,   62,   -1,   -1,   -1,   59,   60,
   41,   62,   -1,   -1,   59,   60,   -1,   62,   -1,   -1,
   -1,   59,   60,   -1,   62,   -1,   -1,   -1,   59,   60,
   -1,   62,   -1,   -1,   -1,  257,  258,  259,  260,  261,
  262,  263,  264,  267,  267,  266,   -1,  263,  264,  267,
  271,  272,  263,  264,   -1,  267,   -1,  278,  257,  258,
  259,  260,  261,  262,  263,  264,  257,  258,  259,  260,
  261,  262,  263,  264,  257,  258,  259,  260,  261,  262,
  263,  264,  257,  258,  259,  260,  261,  262,  263,  264,
  257,  258,  259,  260,  261,  262,  263,  264,  257,  258,
  259,  260,  261,  262,  263,  264,  267,  125,  266,  125,
   -1,   -1,   -1,  271,  272,   -1,  274,  275,  276,  257,
  258,  259,  260,  261,  262,  263,  264,  257,  258,  259,
  260,  261,  262,  263,  264,  125,   -1,  125,   12,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   21,  257,  258,
  259,  260,  261,  262,  263,  264,  257,  258,  259,  260,
  261,  262,  263,  264,  257,  258,  259,  260,  261,  262,
   -1,   -1,  257,  258,  259,  260,  261,  262,   -1,  257,
  258,  259,  260,  261,  262,   -1,  257,  258,  259,  260,
  261,  262,  257,  258,  259,  260,  261,  262,   -1,  257,
  258,  259,  260,  261,  262,   -1,  257,  258,  259,  260,
  261,  262,  257,  258,  259,  260,  261,  262,   -1,  257,
  258,  259,  260,  261,  262,   -1,  257,  258,  259,  260,
  261,  262,  106,  107,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,  116,  117,   -1,   -1,   -1,  265,   -1,  265,
  268,  269,  268,  269,   -1,  273,   -1,  273,   -1,  277,
   -1,  277,  265,  266,   -1,  268,  269,   -1,  271,  272,
  273,  274,  275,  276,  277,  265,   -1,  265,  268,  269,
  268,  269,   -1,  273,   -1,  273,   -1,  277,   -1,  277,
   17,   18,   -1,   -1,   -1,   -1,   -1,   -1,   25,   -1,
   -1,   -1,   -1,   30,   31,   -1,   -1,   -1,   -1,   36,
   -1,   -1,   -1,   -1,   -1,   42,   -1,   44,   -1,   -1,
   47,   -1,   49,   -1,   51,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   59,   60,   61,   62,   63,   64,   65,   66,
   67,   68,   69,   70,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   79,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,  108,
};
}
final static short YYFINAL=12;
final static short YYMAXTOKEN=282;
final static String yyname[] = {
"end-of-file",null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,"'('","')'","'*'","'+'","','",
"'-'",null,"'/'",null,null,null,null,null,null,null,null,null,null,null,"';'",
"'<'",null,"'>'",null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
"'['",null,"']'",null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,"'{'",null,"'}'",null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,"\"<=\"","\">=\"","\"&&\"","\"||\"",
"\"==\"","\"!=\"","\"++\"","\"--\"","PRINT","ID","ATRIB","IF","WHILE","ELSE",
"INT","DOUBLE","FOR","BOOL","CHAR","STRING","CIN","BOOLEAN","PMORE","PLESS",
"\"<<\"","\">>\"",
};
final static String yyrule[] = {
"$accept : lst_comandos",
"lst_comandos : lst_comandos comando ';'",
"lst_comandos : comando ';'",
"lst_comandos : variables ';'",
"lst_comandos : variables ',' variables",
"comando : PRINT \"<<\" expr",
"comando : CIN \">>\" expr",
"comando : IF '(' expr ')' '{' lst_comandos '}'",
"comando : IF '(' expr ')' '{' lst_comandos '}' ELSE '{' lst_comandos '}'",
"comando : WHILE '(' expr ')' '{' lst_comandos '}'",
"comando : FOR '(' comando ';' expr ';' expr ')' '{' lst_comandos '}'",
"variables : INT ID ATRIB expr ';'",
"variables : DOUBLE ID ATRIB expr ';'",
"variables : BOOL ID ATRIB expr ';'",
"variables : CHAR ID ATRIB expr ';'",
"variables : STRING ID ATRIB expr ';'",
"variables : INT ID ';'",
"variables : DOUBLE ID ';'",
"variables : BOOL ID ';'",
"variables : CHAR ID ';'",
"variables : STRING ID ';'",
"variables : ID '[' INT ']' ';'",
"variables : ID '[' ID ']' ';'",
"expr : expr '>' expr",
"expr : expr '<' expr",
"expr : expr '/' expr",
"expr : expr '*' expr",
"expr : expr '+' expr",
"expr : expr '-' expr",
"expr : expr \"++\"",
"expr : expr \"--\"",
"expr : '(' expr ')'",
"expr : '+' expr",
"expr : '-' expr",
"expr : expr \"&&\" expr",
"expr : expr \"||\" expr",
"expr : expr \"!=\" expr",
"expr : expr \"==\" expr",
"expr : expr \">=\" expr",
"expr : expr \"<=\" expr",
"expr : INT",
"expr : DOUBLE",
"expr : ID",
"expr : BOOLEAN",
};

//#line 84 "parser.y"

private Object root;

private SymbolTab symtab = new SymbolTab();

public SymbolTab getSymbolTab(){
    return symtab;
}

public ASTCommand getRoot(){
    return (ASTCommand) root;
}
public Parser(String filename) throws Exception{
    super(new FileReader(filename));
}

public void yyerror(String s){
    System.out.println("Erro sintático: " + s);
}

//#line 399 "Parser.java"
//###############################################################
// method: yylexdebug : check lexer state
//###############################################################
void yylexdebug(int state,int ch)
{
String s=null;
  if (ch < 0) ch=0;
  if (ch <= YYMAXTOKEN) //check index bounds
     s = yyname[ch];    //now get it
  if (s==null)
    s = "illegal-symbol";
  debug("state "+state+", reading "+ch+" ("+s+")");
}





//The following are now global, to aid in error reporting
int yyn;       //next next thing to do
int yym;       //
int yystate;   //current parsing state from state table
String yys;    //current token string


//###############################################################
// method: yyparse : parse input and execute indicated items
//###############################################################
int yyparse()
throws Exception
{
boolean doaction;
  init_stacks();
  yynerrs = 0;
  yyerrflag = 0;
  yychar = -1;          //impossible char forces a read
  yystate=0;            //initial state
  state_push(yystate);  //save it
  val_push(yylval);     //save empty value
  while (true) //until parsing is done, either correctly, or w/error
    {
    doaction=true;
    if (yydebug) debug("loop"); 
    //#### NEXT ACTION (from reduction table)
    for (yyn=yydefred[yystate];yyn==0;yyn=yydefred[yystate])
      {
      if (yydebug) debug("yyn:"+yyn+"  state:"+yystate+"  yychar:"+yychar);
      if (yychar < 0)      //we want a char?
        {
        yychar = yylex();  //get next token
        if (yydebug) debug(" next yychar:"+yychar);
        //#### ERROR CHECK ####
        if (yychar < 0)    //it it didn't work/error
          {
          yychar = 0;      //change it to default string (no -1!)
          if (yydebug)
            yylexdebug(yystate,yychar);
          }
        }//yychar<0
      yyn = yysindex[yystate];  //get amount to shift by (shift index)
      if ((yyn != 0) && (yyn += yychar) >= 0 &&
          yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
        {
        if (yydebug)
          debug("state "+yystate+", shifting to state "+yytable[yyn]);
        //#### NEXT STATE ####
        yystate = yytable[yyn];//we are in a new state
        state_push(yystate);   //save it
        val_push(yylval);      //push our lval as the input for next rule
        yychar = -1;           //since we have 'eaten' a token, say we need another
        if (yyerrflag > 0)     //have we recovered an error?
           --yyerrflag;        //give ourselves credit
        doaction=false;        //but don't process yet
        break;   //quit the yyn=0 loop
        }

    yyn = yyrindex[yystate];  //reduce
    if ((yyn !=0 ) && (yyn += yychar) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
      {   //we reduced!
      if (yydebug) debug("reduce");
      yyn = yytable[yyn];
      doaction=true; //get ready to execute
      break;         //drop down to actions
      }
    else //ERROR RECOVERY
      {
      if (yyerrflag==0)
        {
        yyerror("syntax error");
        yynerrs++;
        }
      if (yyerrflag < 3) //low error count?
        {
        yyerrflag = 3;
        while (true)   //do until break
          {
          if (stateptr<0)   //check for under & overflow here
            {
            yyerror("stack underflow. aborting...");  //note lower case 's'
            return 1;
            }
          yyn = yysindex[state_peek(0)];
          if ((yyn != 0) && (yyn += YYERRCODE) >= 0 &&
                    yyn <= YYTABLESIZE && yycheck[yyn] == YYERRCODE)
            {
            if (yydebug)
              debug("state "+state_peek(0)+", error recovery shifting to state "+yytable[yyn]+" ");
            yystate = yytable[yyn];
            state_push(yystate);
            val_push(yylval);
            doaction=false;
            break;
            }
          else
            {
            if (yydebug)
              debug("error recovery discarding state "+state_peek(0)+" ");
            if (stateptr<0)   //check for under & overflow here
              {
              yyerror("Stack underflow. aborting...");  //capital 'S'
              return 1;
              }
            state_pop();
            val_pop();
            }
          }
        }
      else            //discard this token
        {
        if (yychar == 0)
          return 1; //yyabort
        if (yydebug)
          {
          yys = null;
          if (yychar <= YYMAXTOKEN) yys = yyname[yychar];
          if (yys == null) yys = "illegal-symbol";
          debug("state "+yystate+", error recovery discards token "+yychar+" ("+yys+")");
          }
        yychar = -1;  //read another
        }
      }//end error recovery
    }//yyn=0 loop
    if (!doaction)   //any reason not to proceed?
      continue;      //skip action
    yym = yylen[yyn];          //get count of terminals on rhs
    if (yydebug)
      debug("state "+yystate+", reducing "+yym+" by rule "+yyn+" ("+yyrule[yyn]+")");
    if (yym>0)                 //if count of rhs not 'nil'
      yyval = val_peek(yym-1); //get current semantic value
    yyval = dup_yyval(yyval); //duplicate yyval if ParserVal is used as semantic value
    switch(yyn)
      {
//########## USER-SUPPLIED ACTIONS ##########
case 1:
//#line 29 "parser.y"
{ ((ASTCommand)val_peek(2)).setProx((ASTCommand)val_peek(1)); yyval = val_peek(1);        }
break;
case 2:
//#line 30 "parser.y"
{ if(root == null) root = (ASTNode)val_peek(1); yyval = val_peek(1);             }
break;
case 3:
//#line 31 "parser.y"
{ yyval = val_peek(1); }
break;
case 4:
//#line 32 "parser.y"
{ yyval = new ASTListVariables(); ((ASTListVariables)yyval).setProx((ASTDecl)val_peek(2)); (ASTListVariables)yyval).setProx((ASTDecl)val_peek(0)); }
break;
case 5:
//#line 36 "parser.y"
{ yyval = new ASTPrint((ASTExpr)val_peek(0)); ((ASTPrint)yyval).setLine(getYYline()+1);                                                }
break;
case 6:
//#line 37 "parser.y"
{ yyval = new ASTCin((ASTExpr)val_peek(0)); ((ASTCin)yyval).setLine(getYYline()+1);                                                    }
break;
case 7:
//#line 38 "parser.y"
{ yyval = new ASTIf((ASTExpr)val_peek(4), (ASTCommand)val_peek(1)); ((ASTIf)yyval).setLine(getYYline()+1);                                      }
break;
case 8:
//#line 39 "parser.y"
{ yyval = new ASTIf((ASTExpr)val_peek(8), (ASTCommand)val_peek(5), (ASTCommand)val_peek(3)); ((ASTIf)yyval).setLine(getYYline()+1);                      }
break;
case 9:
//#line 40 "parser.y"
{ yyval = new ASTWhile((ASTExpr)val_peek(4), (ASTCommand)val_peek(1)); ((ASTWhile)yyval).setLine(getYYline()+1);                                }
break;
case 10:
//#line 41 "parser.y"
{ yyval = new ASTFor((ASTCommand)val_peek(8), (ASTExpr)val_peek(6), (ASTExpr)val_peek(5), (ASTCommand)val_peek(4)); ((ASTFor)yyval).setLine(getYYline()+1);       }
break;
case 11:
//#line 45 "parser.y"
{ symtab.set((String)val_peek(3)); yyval = new ASTDecl((ASTInteger)val_peek(1), (String)val_peek(3)); ((ASTDecl)yyval).setLine(getYYline()+1);           }
break;
case 12:
//#line 46 "parser.y"
{ symtab.set((String)val_peek(3)); yyval = new ASTDecl((ASTDouble)val_peek(1), (String)val_peek(3)); ((ASTDecl)yyval).setLine(getYYline()+1);            }
break;
case 13:
//#line 47 "parser.y"
{ symtab.set((String)val_peek(3)); yyval = new ASTDecl((ASTBoolean)val_peek(1), (String)val_peek(3)); ((ASTDecl)yyval).setLine(getYYline()+1);           }
break;
case 14:
//#line 48 "parser.y"
{ symtab.set((String)val_peek(3)); yyval = new ASTDecl((ASTChar)val_peek(1), (String)val_peek(3)); ((ASTDecl)yyval).setLine(getYYline()+1);              }
break;
case 15:
//#line 49 "parser.y"
{ /* string*/                                                                                                           }
break;
case 16:
//#line 50 "parser.y"
{ symtab.set((String)val_peek(1)); yyval = new ASTDecl(new ASTInteger(0), (String)val_peek(1)); ((ASTDecl)yyval).setLine(getYYline()+1);        }
break;
case 17:
//#line 51 "parser.y"
{ symtab.set((String)val_peek(1)); yyval = new ASTDecl(new ASTDouble(0.0), (String)val_peek(1)); ((ASTDecl)yyval).setLine(getYYline()+1);         }
break;
case 18:
//#line 52 "parser.y"
{ symtab.set((String)val_peek(1)); yyval = new ASTDecl(new ASTBoolean(false), (String)val_peek(1)); ((ASTDecl)yyval).setLine(getYYline()+1);    }
break;
case 19:
//#line 53 "parser.y"
{ symtab.set((String)val_peek(1)); yyval = new ASTDecl(new ASTChar(' '), (String)val_peek(1)); ((ASTDecl)yyval).setLine(getYYline()+1);          }
break;
case 20:
//#line 54 "parser.y"
{ }
break;
case 21:
//#line 55 "parser.y"
{ yyval = new ASTVector((ASTId)val_peek(4), (ASTInteger)val_peek(2)); ((ASTVector)yyval).setLine(getYYline()+1);                        }
break;
case 22:
//#line 56 "parser.y"
{ yyval = new ASTVector((ASTId)val_peek(4), (ASTId)val_peek(2)); ((ASTVector)yyval).setLine(getYYline()+1);                             }
break;
case 23:
//#line 60 "parser.y"
{ yyval = new ASTBigger((ASTExpr)val_peek(2), (ASTExpr)val_peek(0)); ((ASTBigger)yyval).setLine(getYYline()+1);                         }
break;
case 24:
//#line 61 "parser.y"
{ yyval = new ASTLess((ASTExpr)val_peek(2), (ASTExpr)val_peek(0)); ((ASTLess)yyval).setLine(getYYline()+1);                             }
break;
case 25:
//#line 62 "parser.y"
{ yyval = new ASTDiv((ASTExpr)val_peek(2), (ASTExpr)val_peek(0)); ((ASTDiv)yyval).setLine(getYYline()+1);                               }
break;
case 26:
//#line 63 "parser.y"
{ yyval = new ASTMult((ASTExpr)val_peek(2), (ASTExpr)val_peek(0)); ((ASTMult)yyval).setLine(getYYline()+1);                             }
break;
case 27:
//#line 64 "parser.y"
{ yyval = new ASTSoma((ASTExpr)val_peek(2), (ASTExpr)val_peek(0)); ((ASTSoma)yyval).setLine(getYYline()+1);                             }
break;
case 28:
//#line 65 "parser.y"
{ yyval = new ASTSub((ASTExpr)val_peek(2), (ASTExpr)val_peek(0)); ((ASTSub)yyval).setLine(getYYline()+1);                               }
break;
case 29:
//#line 66 "parser.y"
{ yyval = new ASTSoma((ASTExpr)val_peek(1), new ASTInteger(1)); ((ASTSoma)yyval).setLine(getYYline()+1);                       }
break;
case 30:
//#line 67 "parser.y"
{ yyval = new ASTSub((ASTExpr)val_peek(1), new ASTInteger(1)); ((ASTSub)yyval).setLine(getYYline()+1);                         }
break;
case 31:
//#line 68 "parser.y"
{ yyval = (ASTExpr)val_peek(1); ((ASTExpr)yyval).setLine(getYYline()+1);                                                       }
break;
case 32:
//#line 69 "parser.y"
{ yyval = (ASTExpr)val_peek(0); ((ASTExpr)yyval).setLine(getYYline()+1);                                                       }
break;
case 33:
//#line 70 "parser.y"
{ yyval = new ASTInteger((Integer)val_peek(0)); ((ASTInteger)yyval).setOperator("-"); ((ASTExpr)yyval).setLine(getYYline()+1);    }
break;
case 34:
//#line 71 "parser.y"
{ yyval = new ASTAnd((ASTExpr)val_peek(2), (ASTExpr)val_peek(0)); ((ASTAnd)yyval).setLine(getYYline()+1);                               }
break;
case 35:
//#line 72 "parser.y"
{ yyval = new ASTOr((ASTExpr)val_peek(2), (ASTExpr)val_peek(0)); ((ASTOr)yyval).setLine(getYYline()+1);                                 }
break;
case 36:
//#line 73 "parser.y"
{ yyval = new ASTNotEqual((ASTExpr)val_peek(2), (ASTExpr)val_peek(0)); ((ASTNotEqual)yyval).setLine(getYYline()+1);                     }
break;
case 37:
//#line 74 "parser.y"
{ yyval = new ASTEqual((ASTExpr)val_peek(2), (ASTExpr)val_peek(0));  ((ASTEqual)yyval).setLine(getYYline()+1);                          }
break;
case 38:
//#line 75 "parser.y"
{ yyval = new ASTBiggerEqual((ASTExpr)val_peek(2), (ASTExpr)val_peek(0)); ((ASTBiggerEqual)yyval).setLine(getYYline()+1);               }
break;
case 39:
//#line 76 "parser.y"
{ yyval = new ASTLessEqual((ASTExpr)val_peek(2), (ASTExpr)val_peek(0)); ((ASTLessEqual)yyval).setLine(getYYline()+1);                   }
break;
case 40:
//#line 77 "parser.y"
{ yyval = new ASTInteger((Integer)val_peek(0)); ((ASTInteger)yyval).setLine(getYYline()+1);                                    }
break;
case 41:
//#line 78 "parser.y"
{ yyval = new ASTDouble((Double)val_peek(0)); ((ASTDouble)yyval).setLine(getYYline()+1);                                       }
break;
case 42:
//#line 79 "parser.y"
{ yyval = new ASTId((String)val_peek(0)); ((ASTId)yyval).setLine(getYYline()+1);                                               }
break;
case 43:
//#line 80 "parser.y"
{ yyval = new ASTBoolean((Boolean)val_peek(0)); ((ASTBoolean)yyval).setLine(getYYline()+1);                                    }
break;
//#line 720 "Parser.java"
//########## END OF USER-SUPPLIED ACTIONS ##########
    }//switch
    //#### Now let's reduce... ####
    if (yydebug) debug("reduce");
    state_drop(yym);             //we just reduced yylen states
    yystate = state_peek(0);     //get new state
    val_drop(yym);               //corresponding value drop
    yym = yylhs[yyn];            //select next TERMINAL(on lhs)
    if (yystate == 0 && yym == 0)//done? 'rest' state and at first TERMINAL
      {
      if (yydebug) debug("After reduction, shifting from state 0 to state "+YYFINAL+"");
      yystate = YYFINAL;         //explicitly say we're done
      state_push(YYFINAL);       //and save it
      val_push(yyval);           //also save the semantic value of parsing
      if (yychar < 0)            //we want another character?
        {
        yychar = yylex();        //get next character
        if (yychar<0) yychar=0;  //clean, if necessary
        if (yydebug)
          yylexdebug(yystate,yychar);
        }
      if (yychar == 0)          //Good exit (if lex returns 0 ;-)
         break;                 //quit the loop--all DONE
      }//if yystate
    else                        //else not done yet
      {                         //get next state and push, for next yydefred[]
      yyn = yygindex[yym];      //find out where to go
      if ((yyn != 0) && (yyn += yystate) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yystate)
        yystate = yytable[yyn]; //get new state
      else
        yystate = yydgoto[yym]; //else go to new defred
      if (yydebug) debug("after reduction, shifting from state "+state_peek(0)+" to state "+yystate+"");
      state_push(yystate);     //going again, so push state & val...
      val_push(yyval);         //for next action
      }
    }//main loop
  return 0;//yyaccept!!
}
//## end of method parse() ######################################



//## run() --- for Thread #######################################
//## The -Jnorun option was used ##
//## end of method run() ########################################



//## Constructors ###############################################
//## The -Jnoconstruct option was used ##
//###############################################################



}
//################### END OF CLASS ##############################
