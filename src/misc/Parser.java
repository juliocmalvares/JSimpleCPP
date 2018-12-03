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



//#line 33 "Parser.java"




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
public final static short PRINT=257;
public final static short ID=258;
public final static short ATRIB=259;
public final static short IF=260;
public final static short WHILE=261;
public final static short ELSE=262;
public final static short NUM=263;
public final static short FOR=264;
public final static short INT=265;
public final static short DOUBLE=266;
public final static short BOOL=267;
public final static short CHAR=268;
public final static short STRING=269;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    0,    1,    1,    1,    1,    1,    1,    1,    1,
    1,    1,    2,    2,    2,    2,    2,    2,
};
final static short yylen[] = {                            2,
    3,    2,    3,    4,    4,    4,    4,    4,    7,   11,
    7,   11,    3,    3,    3,    3,    3,    1,
};
final static short yydefred[] = {                         0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    2,   18,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    1,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   15,    0,    0,
   13,   14,    0,    0,    0,    0,    0,    0,    0,   11,
    0,    0,    0,    0,    0,    0,    0,   10,   12,
};
final static short yydgoto[] = {                         10,
   11,   25,
};
final static short yysindex[] = {                      -166,
 -267,  -36,  -35,  -30, -246, -245, -242, -240, -239, -166,
  -38,  -40,  -40,  -40,  -40, -231, -222, -203, -199, -189,
   17,    0,    0,  -40,   23,    1,    2,   22,  -40,  -40,
  -40,  -40,  -40,    0,    9,  -40,  -40,  -40,  -40,  -46,
  -45,  -40,   23,   23,   23,   23,   23,    0,  -34,  -34,
    0,    0, -166, -166,   30, -124, -111,  -40, -183,    0,
   12,  -43,  -37, -166, -166,  -98,  -85,    0,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   28,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   29,   34,   45,   46,   47,    0,  -26,  -21,
    0,    0,    0,    0,    0,    0,    0,    0,   50,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,
};
final static short yygindex[] = {                        43,
   -8,   -7,
};
final static int YYTABLESIZE=223;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         24,
   59,   21,   12,   13,   14,   26,   27,   28,   38,   15,
   39,   16,   17,   60,   17,   18,   35,   19,   20,   16,
   22,   43,   44,   45,   46,   47,   68,   29,   49,   50,
   51,   52,   17,   17,   55,   17,   30,   16,   16,   69,
   16,   40,   41,   38,   38,   39,   39,   21,   21,   48,
   61,   38,   63,   39,   38,   31,   39,   21,   21,   32,
   36,   36,   37,   37,   38,   38,   39,   39,   36,   33,
   37,   36,   38,   37,   39,   34,   53,   54,   62,   64,
   42,   36,   36,   37,   37,   65,    3,    4,   58,   36,
    1,   37,    5,    2,    3,   56,   57,    4,    5,    6,
    7,    8,    9,    6,    7,    8,   66,   67,    9,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    1,    0,    0,    2,    3,    0,    0,    4,
    5,    6,    7,    8,    9,    1,    0,    0,    2,    3,
    0,    0,    4,    5,    6,    7,    8,    9,    1,    0,
    0,    2,    3,    0,    0,    4,    5,    6,    7,    8,
    9,    1,    0,    0,    2,    3,    0,    0,    4,    5,
    6,    7,    8,    9,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   23,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         40,
  125,   10,  270,   40,   40,   13,   14,   15,   43,   40,
   45,  258,  258,  125,   41,  258,   24,  258,  258,   41,
   59,   29,   30,   31,   32,   33,  125,  259,   36,   37,
   38,   39,   59,   60,   42,   62,  259,   59,   60,  125,
   62,   41,   41,   43,   43,   45,   45,   56,   57,   41,
   58,   43,   41,   45,   43,  259,   45,   66,   67,  259,
   60,   60,   62,   62,   43,   43,   45,   45,   60,  259,
   62,   60,   43,   62,   45,   59,  123,  123,  262,  123,
   59,   60,   60,   62,   62,  123,   59,   59,   59,   60,
  257,   62,   59,  260,  261,   53,   54,  264,  265,  266,
  267,  268,  269,   59,   59,   59,   64,   65,   59,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,  257,   -1,   -1,  260,  261,   -1,   -1,  264,
  265,  266,  267,  268,  269,  257,   -1,   -1,  260,  261,
   -1,   -1,  264,  265,  266,  267,  268,  269,  257,   -1,
   -1,  260,  261,   -1,   -1,  264,  265,  266,  267,  268,
  269,  257,   -1,   -1,  260,  261,   -1,   -1,  264,  265,
  266,  267,  268,  269,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,  263,
};
}
final static short YYFINAL=10;
final static short YYMAXTOKEN=270;
final static String yyname[] = {
"end-of-file",null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,"'('","')'",null,"'+'",null,
"'-'",null,null,null,null,null,null,null,null,null,null,null,null,null,"';'",
"'<'",null,"'>'",null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
"'{'",null,"'}'",null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,"PRINT","ID","ATRIB","IF","WHILE","ELSE",
"NUM","FOR","INT","DOUBLE","BOOL","CHAR","STRING","\"<<\"",
};
final static String yyrule[] = {
"$accept : lst_comandos",
"lst_comandos : lst_comandos comando ';'",
"lst_comandos : comando ';'",
"comando : PRINT \"<<\" expr",
"comando : INT ID ATRIB expr",
"comando : DOUBLE ID ATRIB expr",
"comando : BOOL ID ATRIB expr",
"comando : CHAR ID ATRIB expr",
"comando : STRING ID ATRIB expr",
"comando : IF '(' expr ')' '{' lst_comandos '}'",
"comando : IF '(' expr ')' '{' lst_comandos '}' ELSE '{' lst_comandos '}'",
"comando : WHILE '(' expr ')' '{' lst_comandos '}'",
"comando : FOR '(' expr ';' expr ';' expr ')' '{' lst_comandos '}'",
"expr : expr '+' expr",
"expr : expr '-' expr",
"expr : '(' expr ')'",
"expr : expr '>' expr",
"expr : expr '<' expr",
"expr : NUM",
};

//#line 56 "parser.y"

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

//#line 281 "Parser.java"
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
//#line 26 "parser.y"
{ ((ASTCommand)val_peek(2)).setProx((ASTCommand)val_peek(1)); yyval = val_peek(1); }
break;
case 2:
//#line 27 "parser.y"
{ if(root == null) root = (ASTNode)val_peek(1); yyval = val_peek(1); }
break;
case 3:
//#line 31 "parser.y"
{ /*Implementar print*/ }
break;
case 4:
//#line 33 "parser.y"
{ symtab.set((String)val_peek(2)); yyval = new ASTDecl((ASTInteger)val_peek(0), (String)val_peek(2)); }
break;
case 5:
//#line 34 "parser.y"
{ /*double*/}
break;
case 6:
//#line 35 "parser.y"
{ /*boolean*/}
break;
case 7:
//#line 36 "parser.y"
{ /*character*/}
break;
case 8:
//#line 37 "parser.y"
{ /* string*/}
break;
case 9:
//#line 39 "parser.y"
{ yyval = new ASTIf(  (ASTExpr)val_peek(4) , (ASTCommand)val_peek(1)  );}
break;
case 10:
//#line 40 "parser.y"
{ yyval = new ASTIf((ASTExpr)val_peek(8), (ASTCommand)val_peek(5), (ASTCommand)val_peek(3) ); }
break;
case 11:
//#line 41 "parser.y"
{ /*Implementar o while*/}
break;
case 12:
//#line 42 "parser.y"
{ /*Implementar o for*/}
break;
case 13:
//#line 46 "parser.y"
{ yyval = new ASTSoma( (ASTExpr)val_peek(2), (ASTExpr)val_peek(0) ); }
break;
case 14:
//#line 47 "parser.y"
{ yyval = new ASTSub( (ASTExpr)val_peek(2), (ASTExpr)val_peek(0)); }
break;
case 15:
//#line 48 "parser.y"
{ yyval = val_peek(1); }
break;
case 16:
//#line 49 "parser.y"
{ yyval = new ASTBigger((ASTExpr)val_peek(2), (ASTExpr)val_peek(0));}
break;
case 17:
//#line 50 "parser.y"
{ yyval = new ASTLess((ASTExpr)val_peek(2), (ASTExpr)val_peek(0));}
break;
case 18:
//#line 51 "parser.y"
{  if(val_peek(0) instanceof Integer){ yyval = new ASTInteger((Integer)val_peek(0)); System.out.println("Integer");}
                                                                           if(val_peek(0) instanceof Double) yyval = new ASTDouble((Double)val_peek(0)); }
break;
//#line 503 "Parser.java"
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
