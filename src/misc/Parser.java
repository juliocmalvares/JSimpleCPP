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

//#line 31 "Parser.java"




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
public final static short LESSEQUAL=257;
public final static short BIGGEREQUAL=258;
public final static short AND=259;
public final static short OR=260;
public final static short EQUAL=261;
public final static short NOTEQUAL=262;
public final static short ATRIB=263;
public final static short INCR=264;
public final static short DECR=265;
public final static short PRINT=266;
public final static short ID=267;
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
public final static short IN=279;
public final static short OUT=280;
public final static short VRG=281;
public final static short PMORE=282;
public final static short PLESS=283;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    0,    1,    1,    1,    1,    1,    1,    1,    1,
    1,    1,    1,    1,    1,    1,    1,    1,    1,    1,
    1,    1,    1,    1,    1,    1,    2,    2,    2,    2,
    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,
    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,
};
final static short yylen[] = {                            2,
    2,    1,    4,    4,    7,   11,    7,   10,    5,    5,
    5,    5,    5,    3,    3,    3,    3,    3,    6,    6,
    6,    6,    6,    3,    6,    6,    3,    3,    3,    3,
    3,    3,    2,    2,    3,    2,    2,    3,    3,    3,
    3,    3,    3,    4,    4,    1,    1,    1,    1,    1,
};
final static short yydefred[] = {                         0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    2,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    1,    0,    0,    0,   46,
   47,   50,   49,    0,    0,    0,    0,    0,    0,    0,
    0,   14,    0,    0,   15,    0,    0,    0,   16,    0,
    0,   17,    0,    0,   18,    0,    0,   36,   37,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   33,   34,    3,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    4,    0,    0,   35,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   31,   32,    0,    0,    0,
    0,    9,    0,   10,    0,    0,   11,    0,   12,    0,
   13,    0,   45,   44,    0,    0,    0,    0,   19,   22,
    0,   21,   20,   23,    0,    7,    0,    0,    0,    0,
    0,    0,    8,    6,
};
final static short yydgoto[] = {                         12,
   13,   35,
};
final static short yysindex[] = {                      -238,
 -263,  -81,  -22,  -21, -242, -235,   10, -216, -215, -210,
 -220, -238,    0,  -40,  -40, -255,  -40,  -40,  -51,  -46,
 -238,  -44,  -38,  -37,  -40,    0,  -40,  -40,  -24,    0,
    0,    0,    0,  -40,  556,  937,  -25,  -23,  510,  519,
  -40,    0,  -40,  -40,    0,  -40,  -40,  -40,    0,  -40,
  -40,    0,  -40,  -40,    0,  -40,  780,    0,    0, -209,
  533,  -40,  -40,  -40,  -40,  -40,  -40,  -40,  -40,  -40,
  -40,  -40,  -40,    0,    0,    0, -194, -190,  -49,  -48,
  789,  810,  819,  840,  849,  870,  879,  893,  902,  914,
  923,    0,  -17,  -16,    0,  -36,  -36,  -36,  -36,  -36,
  -36,  -36,  -36,  -41,  -41,    0,    0,  -40,  -40, -238,
 -238,    0,   19,    0,   20,  -40,    0,   21,    0,   22,
    0,   23,    0,    0,  937,  937, -105,  234,    0,    0,
  542,    0,    0,    0, -185,    0,  -35,  -31, -238, -238,
  272,  734,    0,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    1,    0,
    0,    0,    0,    0,    0,  358,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   72,   95,  118,  146,  194,
  388,  433,  457,   24,   49,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,  402,  488,    0,    0,    0,    0,
    0,    0,    0,    0,  475,    0,    0,    0,    0,    0,
    0,    0,    0,    0,
};
final static short yygindex[] = {                       -84,
    2, 1188,
};
final static int YYTABLESIZE=1304;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         34,
   48,   72,   27,   73,   28,   70,   72,   42,   73,   16,
   71,   37,   45,   26,   49,   38,   14,   17,   18,  135,
   52,   55,   47,   30,   19,  127,  128,    1,    2,    3,
    4,   20,    5,    6,    7,    8,    9,   10,   11,   43,
   48,   48,   48,   48,   46,   48,   50,   48,   29,   21,
   22,   23,   53,   56,  141,  142,   24,   93,   25,   48,
   48,   94,   48,   30,   30,   30,   60,   77,  108,   78,
   30,   27,  109,  110,  111,  123,  124,  129,  130,  132,
  133,  134,   30,   30,  138,   30,    0,  139,   29,   29,
   29,  140,    0,   48,   28,   29,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   29,   29,    0,
   29,   27,   27,    0,    0,    0,   30,   43,    0,    0,
    0,    0,    0,    0,    0,   48,    0,    0,   26,   26,
   27,   27,    0,   27,   28,   28,    0,    0,    0,    0,
    0,   29,   26,   26,    0,   42,    0,    0,   30,    0,
    0,    0,    0,   28,   28,    0,   28,   43,   43,    0,
    1,    2,    3,    4,   27,    5,    6,    7,    8,    9,
   10,   11,    0,   29,    0,    0,   43,   43,    0,   43,
    0,   15,    0,    0,    0,   42,   42,   28,    0,    0,
    0,    0,    0,   38,    0,    0,   27,    0,    0,    0,
    0,    0,    0,    0,   42,   42,    0,   42,    0,    0,
   43,   41,    0,    0,    0,    0,   44,    0,   48,   28,
    0,    0,   74,   75,   51,   54,   29,   74,   75,    0,
   30,   31,    0,   38,   38,   32,    0,   33,   42,    0,
    0,    0,   43,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   38,   38,    0,   38,    0,   48,   48,   48,
   48,   48,   48,    0,   48,   48,   48,   48,   48,   48,
   42,   48,   48,   48,   48,   48,   48,   48,   48,    0,
   30,   30,   30,   30,   30,   30,   38,    0,    0,   30,
   30,   30,   30,    0,   30,   30,   30,   30,   30,   30,
   30,   30,    0,    0,    0,   29,   29,   29,   29,   29,
   29,    0,    0,    0,   29,   29,   29,   29,   38,   29,
   29,   29,   29,   29,   29,   29,   29,    0,   27,   27,
   27,   27,   27,   27,    0,    0,    0,   27,   27,   27,
   27,    0,   27,   27,   27,   27,   27,   27,   27,   27,
    0,   28,   28,   28,   28,   28,   28,   24,  136,    0,
   28,   28,   28,   28,    0,   28,   28,   28,   28,   28,
   28,   28,   28,    0,   43,   43,   43,   43,   43,   43,
    0,    0,    0,   43,   43,   43,   43,   39,   43,   43,
   43,   43,   43,   43,   43,   43,  143,   24,    0,    0,
    0,   26,   42,   42,   42,   42,   42,   42,    0,    0,
    0,   42,   42,   42,   42,    0,   42,   42,   42,   42,
   42,   42,   42,   42,    0,    0,    0,   39,   39,    0,
    0,    0,   41,    0,    0,    0,    0,    0,    0,    0,
    0,   26,    0,    0,    0,    0,   39,   39,    0,   39,
   38,   38,   38,   38,   38,   38,   40,    0,    0,   38,
   38,   38,   38,    0,   38,   38,   38,   38,   38,   38,
   38,   38,   41,   41,    5,    0,    0,    0,    0,    0,
   39,    0,   24,    0,    0,    0,    0,   25,    0,    0,
    0,   41,   41,    0,   41,    0,   40,   40,    0,    1,
    2,    3,    4,    0,    5,    6,    7,    8,    9,   10,
   11,    0,   39,    0,    5,   40,   40,    5,   40,    5,
    0,    0,    0,    0,    0,   41,   26,   25,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    1,    2,    3,
    4,    0,    5,    6,    7,    8,    9,   10,   11,   40,
   79,   70,   72,    0,   73,    0,   71,   41,    0,   80,
   70,   72,    0,   73,    0,   71,    0,    0,    0,   63,
    0,   62,    0,   95,   70,   72,    0,   73,   63,   71,
   62,   40,  137,   70,   72,    0,   73,    0,   71,    0,
    0,    0,   63,    0,   62,    0,    0,   70,   72,    5,
   73,   63,   71,   62,    0,    0,    0,    0,    0,    0,
    0,    0,   25,    0,   76,   63,    0,   62,    0,    0,
    0,    0,    0,   24,   24,   24,   24,    0,   24,   24,
   24,   24,   24,   24,   24,   24,    0,    0,    0,    0,
    0,    0,    0,    0,   39,   39,   39,   39,   39,   39,
    0,    0,    0,   39,   39,   39,   39,    0,   39,   39,
   39,   39,   39,   39,   39,   39,    0,   26,   26,   26,
   26,    0,   26,   26,   26,   26,   26,   26,   26,   26,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   41,
   41,   41,   41,   41,   41,    0,    0,    0,   41,   41,
   41,   41,    0,   41,   41,   41,   41,   41,   41,   41,
   41,    0,    0,   40,   40,   40,   40,   40,   40,    0,
    0,    0,   40,   40,   40,   40,    0,   40,   40,   40,
   40,   40,   40,   40,   40,    0,    0,    0,    0,    0,
    5,    5,    5,    5,    0,    5,    5,    5,    5,    5,
    5,    5,    5,   25,   25,   25,   25,    0,   25,   25,
   25,   25,   25,   25,   25,   25,   64,   65,   66,   67,
   68,   69,    0,   74,   75,   64,   65,   66,   67,   68,
   69,    0,   74,   75,    0,    0,    0,    0,    0,   64,
   65,   66,   67,   68,   69,    0,   74,   75,   64,   65,
   66,   67,   68,   69,    0,   74,   75,    0,    0,    0,
    0,    0,   64,   65,   66,   67,   68,   69,    0,   74,
   75,   70,   72,    0,   73,    0,   71,    0,    0,    0,
   70,   72,    0,   73,    0,   71,    0,    0,   92,   63,
    0,   62,    0,    0,    0,    0,    0,  112,   63,    0,
   62,   70,   72,    0,   73,    0,   71,    0,  144,    0,
   70,   72,    0,   73,    0,   71,    0,    0,    0,   63,
    0,   62,    0,    0,    0,    0,    0,  114,   63,    0,
   62,   70,   72,    0,   73,    0,   71,    0,    0,    0,
   70,   72,    0,   73,    0,   71,    0,    0,    0,   63,
    0,   62,  113,    0,    0,    0,    0,  116,   63,    0,
   62,   70,   72,    0,   73,    0,   71,    0,    0,    0,
   70,   72,    0,   73,    0,   71,    0,    0,  117,   63,
    0,   62,  115,    0,   70,   72,    0,   73,   63,   71,
   62,    0,    0,   70,   72,    0,   73,    0,   71,    0,
    0,  119,   63,    0,   62,   70,   72,    0,   73,    0,
   71,   63,    0,   62,   70,   72,    0,   73,    0,   71,
    0,  118,  121,   63,    0,   62,    0,    0,   70,   72,
    0,   73,   63,   71,   62,    0,    0,    0,    0,    0,
    0,    0,    0,    0,  120,    0,   63,    0,   62,    1,
    2,    3,    4,    0,    5,    6,    7,    8,    9,   10,
   11,    0,    0,    0,    0,  122,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   64,   65,   66,   67,
   68,   69,    0,   74,   75,   64,   65,   66,   67,   68,
   69,    0,   74,   75,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   64,   65,   66,   67,
   68,   69,    0,   74,   75,   64,   65,   66,   67,   68,
   69,    0,   74,   75,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   64,   65,   66,   67,
   68,   69,    0,   74,   75,   64,   65,   66,   67,   68,
   69,    0,   74,   75,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   64,   65,   66,   67,
   68,   69,    0,   74,   75,   64,   65,   66,   67,   68,
   69,    0,   74,   75,    0,    0,    0,    0,    0,   64,
   65,   66,   67,   68,   69,    0,   74,   75,   64,   65,
   66,   67,   68,   69,    0,   74,   75,    0,    0,    0,
   64,   65,   66,   67,   68,   69,    0,   74,   75,   64,
   65,   66,   67,   68,   69,    0,   74,   75,    0,    0,
    0,    0,    0,   64,   65,   66,   67,   68,   69,    0,
   74,   75,   36,    0,   39,   40,    0,    0,    0,    0,
    0,    0,   57,    0,   58,   59,    0,    0,    0,    0,
    0,   61,    0,    0,    0,    0,    0,    0,   81,    0,
   82,   83,    0,   84,   85,   86,    0,   87,   88,    0,
   89,   90,    0,   91,    0,    0,    0,    0,    0,   96,
   97,   98,   99,  100,  101,  102,  103,  104,  105,  106,
  107,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,  125,  126,    0,    0,    0,
    0,    0,    0,  131,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         40,
    0,   43,   43,   45,   45,   42,   43,   59,   45,   91,
   47,  267,   59,   12,   59,  271,  280,   40,   40,  125,
   59,   59,   21,    0,  267,  110,  111,  266,  267,  268,
  269,  267,  271,  272,  273,  274,  275,  276,  277,   91,
   40,   41,   42,   43,   91,   45,   91,   47,    0,   40,
  267,  267,   91,   91,  139,  140,  267,  267,  279,   59,
   60,  271,   62,   40,   41,   42,   91,   93,  263,   93,
   47,    0,  263,  123,  123,   93,   93,   59,   59,   59,
   59,   59,   59,   60,  270,   62,   -1,  123,   40,   41,
   42,  123,   -1,   93,    0,   47,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   59,   60,   -1,
   62,   40,   41,   -1,   -1,   -1,   93,    0,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,  125,   -1,   -1,  127,  128,
   59,   60,   -1,   62,   40,   41,   -1,   -1,   -1,   -1,
   -1,   93,  141,  142,   -1,    0,   -1,   -1,  125,   -1,
   -1,   -1,   -1,   59,   60,   -1,   62,   40,   41,   -1,
  266,  267,  268,  269,   93,  271,  272,  273,  274,  275,
  276,  277,   -1,  125,   -1,   -1,   59,   60,   -1,   62,
   -1,  263,   -1,   -1,   -1,   40,   41,   93,   -1,   -1,
   -1,   -1,   -1,    0,   -1,   -1,  125,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   59,   60,   -1,   62,   -1,   -1,
   93,  263,   -1,   -1,   -1,   -1,  263,   -1,  263,  125,
   -1,   -1,  264,  265,  263,  263,  267,  264,  265,   -1,
  271,  272,   -1,   40,   41,  276,   -1,  278,   93,   -1,
   -1,   -1,  125,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   59,   60,   -1,   62,   -1,  257,  258,  259,
  260,  261,  262,   -1,  264,  265,  266,  267,  268,  269,
  125,  271,  272,  273,  274,  275,  276,  277,  278,   -1,
  257,  258,  259,  260,  261,  262,   93,   -1,   -1,  266,
  267,  268,  269,   -1,  271,  272,  273,  274,  275,  276,
  277,  278,   -1,   -1,   -1,  257,  258,  259,  260,  261,
  262,   -1,   -1,   -1,  266,  267,  268,  269,  125,  271,
  272,  273,  274,  275,  276,  277,  278,   -1,  257,  258,
  259,  260,  261,  262,   -1,   -1,   -1,  266,  267,  268,
  269,   -1,  271,  272,  273,  274,  275,  276,  277,  278,
   -1,  257,  258,  259,  260,  261,  262,    0,  125,   -1,
  266,  267,  268,  269,   -1,  271,  272,  273,  274,  275,
  276,  277,  278,   -1,  257,  258,  259,  260,  261,  262,
   -1,   -1,   -1,  266,  267,  268,  269,    0,  271,  272,
  273,  274,  275,  276,  277,  278,  125,   40,   -1,   -1,
   -1,    0,  257,  258,  259,  260,  261,  262,   -1,   -1,
   -1,  266,  267,  268,  269,   -1,  271,  272,  273,  274,
  275,  276,  277,  278,   -1,   -1,   -1,   40,   41,   -1,
   -1,   -1,    0,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   40,   -1,   -1,   -1,   -1,   59,   60,   -1,   62,
  257,  258,  259,  260,  261,  262,    0,   -1,   -1,  266,
  267,  268,  269,   -1,  271,  272,  273,  274,  275,  276,
  277,  278,   40,   41,    0,   -1,   -1,   -1,   -1,   -1,
   93,   -1,  125,   -1,   -1,   -1,   -1,    0,   -1,   -1,
   -1,   59,   60,   -1,   62,   -1,   40,   41,   -1,  266,
  267,  268,  269,   -1,  271,  272,  273,  274,  275,  276,
  277,   -1,  125,   -1,   40,   59,   60,   43,   62,   45,
   -1,   -1,   -1,   -1,   -1,   93,  125,   40,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  266,  267,  268,
  269,   -1,  271,  272,  273,  274,  275,  276,  277,   93,
   41,   42,   43,   -1,   45,   -1,   47,  125,   -1,   41,
   42,   43,   -1,   45,   -1,   47,   -1,   -1,   -1,   60,
   -1,   62,   -1,   41,   42,   43,   -1,   45,   60,   47,
   62,  125,   41,   42,   43,   -1,   45,   -1,   47,   -1,
   -1,   -1,   60,   -1,   62,   -1,   -1,   42,   43,  125,
   45,   60,   47,   62,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,  125,   -1,   59,   60,   -1,   62,   -1,   -1,
   -1,   -1,   -1,  266,  267,  268,  269,   -1,  271,  272,
  273,  274,  275,  276,  277,  278,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,  257,  258,  259,  260,  261,  262,
   -1,   -1,   -1,  266,  267,  268,  269,   -1,  271,  272,
  273,  274,  275,  276,  277,  278,   -1,  266,  267,  268,
  269,   -1,  271,  272,  273,  274,  275,  276,  277,  278,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  257,
  258,  259,  260,  261,  262,   -1,   -1,   -1,  266,  267,
  268,  269,   -1,  271,  272,  273,  274,  275,  276,  277,
  278,   -1,   -1,  257,  258,  259,  260,  261,  262,   -1,
   -1,   -1,  266,  267,  268,  269,   -1,  271,  272,  273,
  274,  275,  276,  277,  278,   -1,   -1,   -1,   -1,   -1,
  266,  267,  268,  269,   -1,  271,  272,  273,  274,  275,
  276,  277,  278,  266,  267,  268,  269,   -1,  271,  272,
  273,  274,  275,  276,  277,  278,  257,  258,  259,  260,
  261,  262,   -1,  264,  265,  257,  258,  259,  260,  261,
  262,   -1,  264,  265,   -1,   -1,   -1,   -1,   -1,  257,
  258,  259,  260,  261,  262,   -1,  264,  265,  257,  258,
  259,  260,  261,  262,   -1,  264,  265,   -1,   -1,   -1,
   -1,   -1,  257,  258,  259,  260,  261,  262,   -1,  264,
  265,   42,   43,   -1,   45,   -1,   47,   -1,   -1,   -1,
   42,   43,   -1,   45,   -1,   47,   -1,   -1,   59,   60,
   -1,   62,   -1,   -1,   -1,   -1,   -1,   59,   60,   -1,
   62,   42,   43,   -1,   45,   -1,   47,   -1,  125,   -1,
   42,   43,   -1,   45,   -1,   47,   -1,   -1,   -1,   60,
   -1,   62,   -1,   -1,   -1,   -1,   -1,   59,   60,   -1,
   62,   42,   43,   -1,   45,   -1,   47,   -1,   -1,   -1,
   42,   43,   -1,   45,   -1,   47,   -1,   -1,   -1,   60,
   -1,   62,   93,   -1,   -1,   -1,   -1,   59,   60,   -1,
   62,   42,   43,   -1,   45,   -1,   47,   -1,   -1,   -1,
   42,   43,   -1,   45,   -1,   47,   -1,   -1,   59,   60,
   -1,   62,   93,   -1,   42,   43,   -1,   45,   60,   47,
   62,   -1,   -1,   42,   43,   -1,   45,   -1,   47,   -1,
   -1,   59,   60,   -1,   62,   42,   43,   -1,   45,   -1,
   47,   60,   -1,   62,   42,   43,   -1,   45,   -1,   47,
   -1,   93,   59,   60,   -1,   62,   -1,   -1,   42,   43,
   -1,   45,   60,   47,   62,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   93,   -1,   60,   -1,   62,  266,
  267,  268,  269,   -1,  271,  272,  273,  274,  275,  276,
  277,   -1,   -1,   -1,   -1,   93,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,  257,  258,  259,  260,
  261,  262,   -1,  264,  265,  257,  258,  259,  260,  261,
  262,   -1,  264,  265,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,  257,  258,  259,  260,
  261,  262,   -1,  264,  265,  257,  258,  259,  260,  261,
  262,   -1,  264,  265,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,  257,  258,  259,  260,
  261,  262,   -1,  264,  265,  257,  258,  259,  260,  261,
  262,   -1,  264,  265,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,  257,  258,  259,  260,
  261,  262,   -1,  264,  265,  257,  258,  259,  260,  261,
  262,   -1,  264,  265,   -1,   -1,   -1,   -1,   -1,  257,
  258,  259,  260,  261,  262,   -1,  264,  265,  257,  258,
  259,  260,  261,  262,   -1,  264,  265,   -1,   -1,   -1,
  257,  258,  259,  260,  261,  262,   -1,  264,  265,  257,
  258,  259,  260,  261,  262,   -1,  264,  265,   -1,   -1,
   -1,   -1,   -1,  257,  258,  259,  260,  261,  262,   -1,
  264,  265,   15,   -1,   17,   18,   -1,   -1,   -1,   -1,
   -1,   -1,   25,   -1,   27,   28,   -1,   -1,   -1,   -1,
   -1,   34,   -1,   -1,   -1,   -1,   -1,   -1,   41,   -1,
   43,   44,   -1,   46,   47,   48,   -1,   50,   51,   -1,
   53,   54,   -1,   56,   -1,   -1,   -1,   -1,   -1,   62,
   63,   64,   65,   66,   67,   68,   69,   70,   71,   72,
   73,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,  108,  109,   -1,   -1,   -1,
   -1,   -1,   -1,  116,
};
}
final static short YYFINAL=12;
final static short YYMAXTOKEN=283;
final static String yyname[] = {
"end-of-file",null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,"'('","')'","'*'","'+'",null,
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
null,null,null,null,null,null,null,null,"LESSEQUAL","BIGGEREQUAL","AND","OR",
"EQUAL","NOTEQUAL","ATRIB","INCR","DECR","PRINT","ID","IF","WHILE","ELSE","INT",
"DOUBLE","FOR","BOOL","CHAR","STRING","CIN","BOOLEAN","IN","OUT","VRG","PMORE",
"PLESS",
};
final static String yyrule[] = {
"$accept : lst_comandos",
"lst_comandos : lst_comandos comando",
"lst_comandos : comando",
"comando : PRINT OUT expr ';'",
"comando : CIN IN expr ';'",
"comando : IF '(' expr ')' '{' lst_comandos '}'",
"comando : IF '(' expr ')' '{' lst_comandos '}' ELSE '{' lst_comandos '}'",
"comando : WHILE '(' expr ')' '{' lst_comandos '}'",
"comando : FOR '(' comando expr ';' expr ')' '{' lst_comandos '}'",
"comando : INT ID ATRIB expr ';'",
"comando : DOUBLE ID ATRIB expr ';'",
"comando : BOOL ID ATRIB expr ';'",
"comando : CHAR ID ATRIB expr ';'",
"comando : STRING ID ATRIB expr ';'",
"comando : INT ID ';'",
"comando : DOUBLE ID ';'",
"comando : BOOL ID ';'",
"comando : CHAR ID ';'",
"comando : STRING ID ';'",
"comando : INT ID '[' expr ']' ';'",
"comando : CHAR ID '[' expr ']' ';'",
"comando : BOOL ID '[' expr ']' ';'",
"comando : DOUBLE ID '[' expr ']' ';'",
"comando : STRING ID '[' expr ']' ';'",
"comando : ID ATRIB expr",
"comando : ID '[' INT ']' ATRIB expr",
"comando : ID '[' ID ']' ATRIB expr",
"expr : expr '>' expr",
"expr : expr '<' expr",
"expr : expr '/' expr",
"expr : expr '*' expr",
"expr : expr '+' expr",
"expr : expr '-' expr",
"expr : expr INCR",
"expr : expr DECR",
"expr : '(' expr ')'",
"expr : '+' expr",
"expr : '-' expr",
"expr : expr AND expr",
"expr : expr OR expr",
"expr : expr NOTEQUAL expr",
"expr : expr EQUAL expr",
"expr : expr BIGGEREQUAL expr",
"expr : expr LESSEQUAL expr",
"expr : ID '[' INT ']'",
"expr : ID '[' ID ']'",
"expr : INT",
"expr : DOUBLE",
"expr : ID",
"expr : BOOLEAN",
"expr : STRING",
};

//#line 87 "parser.y"

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

/*
|   lst_decl                                                            { $$ = ((ASTListVariables)$1); }
;

lst_decl:
    lst_decl VRG decl                           { ((ASTListVariables)$1).setProx((ASTListVariables)$3); ((ASTListVariables)$3).setAnt((ASTListVariables)$1); $$ = $3; }
|   decl                                        { $$ = ((ASTListVariables)$1); }
;
*/

//#line 583 "Parser.java"
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
//#line 28 "parser.y"
{ ((ASTCommand)val_peek(1)).setProx((ASTCommand)val_peek(0)); ((ASTCommand)val_peek(0)).setAnt((ASTCommand)val_peek(1)); yyval = val_peek(0);        }
break;
case 2:
//#line 29 "parser.y"
{ if(root == null) root = (ASTNode)val_peek(0); yyval = val_peek(0);             }
break;
case 3:
//#line 33 "parser.y"
{ yyval = new ASTPrint((ASTExpr)val_peek(1)); ((ASTPrint)yyval).setLine(getYYline()+1);                                                }
break;
case 4:
//#line 34 "parser.y"
{ yyval = new ASTCin((ASTExpr)val_peek(1)); ((ASTCin)yyval).setLine(getYYline()+1);                                                    }
break;
case 5:
//#line 35 "parser.y"
{ yyval = new ASTIf((ASTExpr)val_peek(4), (ASTCommand)val_peek(1)); ((ASTIf)yyval).setLine(getYYline()+1);                                      }
break;
case 6:
//#line 36 "parser.y"
{ yyval = new ASTIf((ASTExpr)val_peek(8), (ASTCommand)val_peek(5), (ASTCommand)val_peek(1)); ((ASTIf)yyval).setLine(getYYline()+1);                      }
break;
case 7:
//#line 37 "parser.y"
{ yyval = new ASTWhile((ASTExpr)val_peek(4), (ASTCommand)val_peek(1)); ((ASTWhile)yyval).setLine(getYYline()+1);                                }
break;
case 8:
//#line 38 "parser.y"
{ yyval = new ASTFor((ASTCommand)val_peek(7), (ASTExpr)val_peek(6), (ASTExpr)val_peek(4), (ASTCommand)val_peek(1)); ((ASTFor)yyval).setLine(getYYline()+1);       }
break;
case 9:
//#line 39 "parser.y"
{ symtab.set((String)val_peek(3)); yyval = new ASTDecl((ASTExpr)val_peek(1), (String)val_peek(3), "int"); ((ASTDecl)yyval).setLine(getYYline()+1);           }
break;
case 10:
//#line 40 "parser.y"
{ symtab.set((String)val_peek(3)); yyval = new ASTDecl((ASTExpr)val_peek(1), (String)val_peek(3), "double"); ((ASTDecl)yyval).setLine(getYYline()+1);            }
break;
case 11:
//#line 41 "parser.y"
{ symtab.set((String)val_peek(3)); yyval = new ASTDecl((ASTExpr)val_peek(1), (String)val_peek(3), "boolean"); ((ASTDecl)yyval).setLine(getYYline()+1);           }
break;
case 12:
//#line 42 "parser.y"
{ symtab.set((String)val_peek(3)); yyval = new ASTDecl((ASTExpr)val_peek(1), (String)val_peek(3), "char"); ((ASTDecl)yyval).setLine(getYYline()+1);              }
break;
case 13:
//#line 43 "parser.y"
{ symtab.set((String)val_peek(3)); yyval = new ASTDecl((ASTExpr)val_peek(1), (String)val_peek(3), "string"); ((ASTDecl)yyval).setLine(getYYline()+1);              }
break;
case 14:
//#line 44 "parser.y"
{ symtab.set((String)val_peek(1)); yyval = new ASTDecl(new ASTInteger(0), (String)val_peek(1), "int"); ((ASTDecl)yyval).setLine(getYYline()+1);        }
break;
case 15:
//#line 45 "parser.y"
{ symtab.set((String)val_peek(1)); yyval = new ASTDecl(new ASTDouble(0.0), (String)val_peek(1), "double"); ((ASTDecl)yyval).setLine(getYYline()+1);       }
break;
case 16:
//#line 46 "parser.y"
{ symtab.set((String)val_peek(1)); yyval = new ASTDecl(new ASTBoolean(false), (String)val_peek(1), "boolean"); ((ASTDecl)yyval).setLine(getYYline()+1);    }
break;
case 17:
//#line 47 "parser.y"
{ symtab.set((String)val_peek(1)); yyval = new ASTDecl(new ASTChar(' '), (String)val_peek(1), "char"); ((ASTDecl)yyval).setLine(getYYline()+1);         }
break;
case 18:
//#line 48 "parser.y"
{ symtab.set((String)val_peek(1)); yyval = new ASTDecl( new ASTString(" "), ((String)val_peek(1)), "string");  ((ASTDecl)yyval).setLine(getYYline()+1); }
break;
case 19:
//#line 49 "parser.y"
{ symtab.set((String)val_peek(4)); yyval = new ASTDecl(new ASTInteger(0), (String)val_peek(4), true, (ASTExpr)val_peek(2), "int"); ((ASTDecl)yyval).setLine(getYYline()+1);}
break;
case 20:
//#line 50 "parser.y"
{ symtab.set((String)val_peek(4)); yyval = new ASTDecl(new ASTChar(' '), (String)val_peek(4), true, (ASTExpr)val_peek(2), "char"); ((ASTDecl)yyval).setLine(getYYline()+1); }
break;
case 21:
//#line 51 "parser.y"
{ symtab.set((String)val_peek(4)); yyval = new ASTDecl(new ASTBoolean(false), (String)val_peek(4), true, (ASTExpr)val_peek(2), "boolean"); ((ASTDecl)yyval).setLine(getYYline()+1); }
break;
case 22:
//#line 52 "parser.y"
{ symtab.set((String)val_peek(4)); yyval = new ASTDecl(new ASTDouble(0.0), (String)val_peek(4), true, (ASTExpr)val_peek(2), "double"); ((ASTDecl)yyval).setLine(getYYline()+1); }
break;
case 23:
//#line 53 "parser.y"
{ symtab.set((String)val_peek(4)); yyval = new ASTDecl(new ASTString(" "), (String)val_peek(4), true, (ASTExpr)val_peek(2), "string"); ((ASTDecl)yyval).setLine(getYYline()+1); }
break;
case 24:
//#line 54 "parser.y"
{ yyval = new ASTAtrib((ASTId)val_peek(2), (ASTExpr)val_peek(0)); ((ASTAtrib)yyval).setLine(getYYline()+1);                             }
break;
case 25:
//#line 55 "parser.y"
{ yyval = new ASTAtrib((ASTId)val_peek(5), (ASTExpr)val_peek(0), ((ASTInteger)val_peek(3))); ((ASTAtrib)yyval).setLine(getYYline()+1);     }
break;
case 26:
//#line 56 "parser.y"
{ yyval = new ASTAtrib((ASTId)val_peek(5), (ASTExpr)val_peek(0), ((ASTId)val_peek(3))); ((ASTAtrib)yyval).setLine(getYYline()+1);        }
break;
case 27:
//#line 60 "parser.y"
{ yyval = new ASTBigger((ASTExpr)val_peek(2), (ASTExpr)val_peek(0)); ((ASTBigger)yyval).setLine(getYYline()+1);                         }
break;
case 28:
//#line 61 "parser.y"
{ yyval = new ASTLess((ASTExpr)val_peek(2), (ASTExpr)val_peek(0)); ((ASTLess)yyval).setLine(getYYline()+1);                             }
break;
case 29:
//#line 62 "parser.y"
{ yyval = new ASTDiv((ASTExpr)val_peek(2), (ASTExpr)val_peek(0)); ((ASTDiv)yyval).setLine(getYYline()+1);                               }
break;
case 30:
//#line 63 "parser.y"
{ yyval = new ASTMult((ASTExpr)val_peek(2), (ASTExpr)val_peek(0)); ((ASTMult)yyval).setLine(getYYline()+1);                             }
break;
case 31:
//#line 64 "parser.y"
{ yyval = new ASTSoma((ASTExpr)val_peek(2), (ASTExpr)val_peek(0)); ((ASTSoma)yyval).setLine(getYYline()+1);                             }
break;
case 32:
//#line 65 "parser.y"
{ yyval = new ASTSub((ASTExpr)val_peek(2), (ASTExpr)val_peek(0)); ((ASTSub)yyval).setLine(getYYline()+1);                               }
break;
case 33:
//#line 66 "parser.y"
{ yyval = new ASTSoma((ASTExpr)val_peek(1), new ASTInteger(1)); ((ASTSoma)yyval).setLine(getYYline()+1);                       }
break;
case 34:
//#line 67 "parser.y"
{ yyval = new ASTSub((ASTExpr)val_peek(1), new ASTInteger(1)); ((ASTSub)yyval).setLine(getYYline()+1);                         }
break;
case 35:
//#line 68 "parser.y"
{ yyval = (ASTExpr)val_peek(1); ((ASTExpr)yyval).setLine(getYYline()+1);                                                       }
break;
case 36:
//#line 69 "parser.y"
{ yyval = (ASTExpr)val_peek(0); ((ASTExpr)yyval).setLine(getYYline()+1);                                                       }
break;
case 37:
//#line 70 "parser.y"
{ yyval = new ASTInteger((Integer)val_peek(0)); ((ASTInteger)yyval).setOperator("-"); ((ASTExpr)yyval).setLine(getYYline()+1);    }
break;
case 38:
//#line 71 "parser.y"
{ yyval = new ASTAnd((ASTExpr)val_peek(2), (ASTExpr)val_peek(0)); ((ASTAnd)yyval).setLine(getYYline()+1);                               }
break;
case 39:
//#line 72 "parser.y"
{ yyval = new ASTOr((ASTExpr)val_peek(2), (ASTExpr)val_peek(0)); ((ASTOr)yyval).setLine(getYYline()+1);                                 }
break;
case 40:
//#line 73 "parser.y"
{ yyval = new ASTNotEqual((ASTExpr)val_peek(2), (ASTExpr)val_peek(0)); ((ASTNotEqual)yyval).setLine(getYYline()+1);                     }
break;
case 41:
//#line 74 "parser.y"
{ yyval = new ASTEqual((ASTExpr)val_peek(2), (ASTExpr)val_peek(0));  ((ASTEqual)yyval).setLine(getYYline()+1);                          }
break;
case 42:
//#line 75 "parser.y"
{ yyval = new ASTBiggerEqual((ASTExpr)val_peek(2), (ASTExpr)val_peek(0)); ((ASTBiggerEqual)yyval).setLine(getYYline()+1);               }
break;
case 43:
//#line 76 "parser.y"
{ yyval = new ASTLessEqual((ASTExpr)val_peek(2), (ASTExpr)val_peek(0)); ((ASTLessEqual)yyval).setLine(getYYline()+1);                   }
break;
case 44:
//#line 77 "parser.y"
{ yyval = new ASTVector((ASTId)val_peek(3), (ASTInteger)val_peek(1)); ((ASTVector)yyval).setLine(getYYline()+1);                        }
break;
case 45:
//#line 78 "parser.y"
{ yyval = new ASTVector((ASTId)val_peek(3), (ASTId)val_peek(1)); ((ASTVector)yyval).setLine(getYYline()+1);                             }
break;
case 46:
//#line 79 "parser.y"
{ yyval = new ASTInteger((Integer)val_peek(0)); ((ASTInteger)yyval).setLine(getYYline()+1);                                    }
break;
case 47:
//#line 80 "parser.y"
{ yyval = new ASTDouble((Double)val_peek(0)); ((ASTDouble)yyval).setLine(getYYline()+1);                                       }
break;
case 48:
//#line 81 "parser.y"
{ yyval = new ASTId((String)val_peek(0)); ((ASTId)yyval).setLine(getYYline()+1);                                               }
break;
case 49:
//#line 82 "parser.y"
{ yyval = new ASTBoolean((Boolean)val_peek(0)); ((ASTBoolean)yyval).setLine(getYYline()+1);                                    }
break;
case 50:
//#line 83 "parser.y"
{ yyval = new ASTString(((String)val_peek(0))); ((ASTString)yyval).setLine(getYYline()+1);                                     }
break;
//#line 932 "Parser.java"
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
