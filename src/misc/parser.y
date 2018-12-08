%{
//yylval é a variavel do lexema que recebe do analisador lexico
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

//int a;
%}

%left '>' '<' "<=" ">=" "&&" "||" "==" "!="
%left '*' '/'
%left '+' '-' "++" "--"

%token PRINT ID ATRIB IF WHILE ELSE INT DOUBLE FOR INT DOUBLE BOOL CHAR STRING CIN BOOLEAN

%nonassoc PMORE PLESS

%%

lst_comandos:
    lst_comandos comando ';'                                            { ((ASTCommand)$1).setProx((ASTCommand)$2); $$ = $2;        }
|   comando ';'                                                         { if(root == null) root = (ASTNode)$1; $$ = $1;             }
|   variables ';'                                                       { $$ = $1; }
|   variables ',' variables                                             { $$ = new ASTListVariables(); ((ASTListVariables)$$).setProx((ASTDecl)$1); (ASTListVariables)$$).setProx((ASTDecl)$3); }
;

comando:
    PRINT "<<" expr                                                     { $$ = new ASTPrint((ASTExpr)$3); ((ASTPrint)$$).setLine(getYYline()+1);                                                }
|   CIN ">>" expr                                                       { $$ = new ASTCin((ASTExpr)$3); ((ASTCin)$$).setLine(getYYline()+1);                                                    }
|   IF '(' expr ')' '{' lst_comandos '}'                                { $$ = new ASTIf((ASTExpr)$3, (ASTCommand)$6); ((ASTIf)$$).setLine(getYYline()+1);                                      }
|   IF '(' expr ')' '{' lst_comandos '}' ELSE '{' lst_comandos '}'      { $$ = new ASTIf((ASTExpr)$3, (ASTCommand)$6, (ASTCommand)$8); ((ASTIf)$$).setLine(getYYline()+1);                      }
|   WHILE '(' expr ')' '{' lst_comandos '}'                             { $$ = new ASTWhile((ASTExpr)$3, (ASTCommand)$6); ((ASTWhile)$$).setLine(getYYline()+1);                                }
|   FOR '(' comando ';' expr ';' expr ')' '{' lst_comandos '}'          { $$ = new ASTFor((ASTCommand)$3, (ASTExpr)$5, (ASTExpr)$6, (ASTCommand)$7); ((ASTFor)$$).setLine(getYYline()+1);       }
;

variables:
    INT ID ATRIB expr ';'                                                  { symtab.set((String)$2); $$ = new ASTDecl((ASTInteger)$4, (String)$2); ((ASTDecl)$$).setLine(getYYline()+1);           }
|   DOUBLE ID ATRIB expr ';'                                               { symtab.set((String)$2); $$ = new ASTDecl((ASTDouble)$4, (String)$2); ((ASTDecl)$$).setLine(getYYline()+1);            }
|   BOOL ID ATRIB expr ';'                                                 { symtab.set((String)$2); $$ = new ASTDecl((ASTBoolean)$4, (String)$2); ((ASTDecl)$$).setLine(getYYline()+1);           }
|   CHAR ID ATRIB expr ';'                                                 { symtab.set((String)$2); $$ = new ASTDecl((ASTChar)$4, (String)$2); ((ASTDecl)$$).setLine(getYYline()+1);              }
|   STRING ID ATRIB expr ';'                                               { /* string*/                                                                                                           }
|   INT ID ';'                                                              { symtab.set((String)$2); $$ = new ASTDecl(new ASTInteger(0), (String)$2); ((ASTDecl)$$).setLine(getYYline()+1);        }
|   DOUBLE ID ';'                                                           { symtab.set((String)$2); $$ = new ASTDecl(new ASTDouble(0.0), (String)$2); ((ASTDecl)$$).setLine(getYYline()+1);         }
|   BOOL ID ';'                                                             { symtab.set((String)$2); $$ = new ASTDecl(new ASTBoolean(false), (String)$2); ((ASTDecl)$$).setLine(getYYline()+1);    }
|   CHAR ID ';'                                                             { symtab.set((String)$2); $$ = new ASTDecl(new ASTChar(' '), (String)$2); ((ASTDecl)$$).setLine(getYYline()+1);          }
|   STRING ID ';'                                                            { }
|   ID'['INT']' ';'                                                         { $$ = new ASTVector((ASTId)$1, (ASTInteger)$3); ((ASTVector)$$).setLine(getYYline()+1);                        }
|   ID'['ID']' ';'                                                          { $$ = new ASTVector((ASTId)$1, (ASTId)$3); ((ASTVector)$$).setLine(getYYline()+1);                             }
;

expr:
    expr '>' expr                                                       { $$ = new ASTBigger((ASTExpr)$1, (ASTExpr)$3); ((ASTBigger)$$).setLine(getYYline()+1);                         }
|   expr '<' expr                                                       { $$ = new ASTLess((ASTExpr)$1, (ASTExpr)$3); ((ASTLess)$$).setLine(getYYline()+1);                             }
|   expr '/' expr                                                       { $$ = new ASTDiv((ASTExpr)$1, (ASTExpr)$3); ((ASTDiv)$$).setLine(getYYline()+1);                               }
|   expr '*' expr                                                       { $$ = new ASTMult((ASTExpr)$1, (ASTExpr)$3); ((ASTMult)$$).setLine(getYYline()+1);                             }
|   expr '+' expr                                                       { $$ = new ASTSoma((ASTExpr)$1, (ASTExpr)$3); ((ASTSoma)$$).setLine(getYYline()+1);                             }
|   expr '-' expr                                                       { $$ = new ASTSub((ASTExpr)$1, (ASTExpr)$3); ((ASTSub)$$).setLine(getYYline()+1);                               }
|   expr"++"                                                            { $$ = new ASTSoma((ASTExpr)$1, new ASTInteger(1)); ((ASTSoma)$$).setLine(getYYline()+1);                       }
|   expr"--"                                                            { $$ = new ASTSub((ASTExpr)$1, new ASTInteger(1)); ((ASTSub)$$).setLine(getYYline()+1);                         }
|   '(' expr ')'                                                        { $$ = (ASTExpr)$2; ((ASTExpr)$$).setLine(getYYline()+1);                                                       }
|   '+'expr %prec PMORE                                                 { $$ = (ASTExpr)$2; ((ASTExpr)$$).setLine(getYYline()+1);                                                       }
|   '-'expr  %prec PLESS                                                { $$ = new ASTInteger((Integer)$2); ((ASTInteger)$$).setOperator("-"); ((ASTExpr)$$).setLine(getYYline()+1);    }
|   expr "&&" expr                                                      { $$ = new ASTAnd((ASTExpr)$1, (ASTExpr)$3); ((ASTAnd)$$).setLine(getYYline()+1);                               }
|   expr "||" expr                                                      { $$ = new ASTOr((ASTExpr)$1, (ASTExpr)$3); ((ASTOr)$$).setLine(getYYline()+1);                                 }
|   expr "!=" expr                                                      { $$ = new ASTNotEqual((ASTExpr)$1, (ASTExpr)$3); ((ASTNotEqual)$$).setLine(getYYline()+1);                     }
|   expr "==" expr                                                      { $$ = new ASTEqual((ASTExpr)$1, (ASTExpr)$3);  ((ASTEqual)$$).setLine(getYYline()+1);                          }
|   expr ">=" expr                                                      { $$ = new ASTBiggerEqual((ASTExpr)$1, (ASTExpr)$3); ((ASTBiggerEqual)$$).setLine(getYYline()+1);               }
|   expr "<=" expr                                                      { $$ = new ASTLessEqual((ASTExpr)$1, (ASTExpr)$3); ((ASTLessEqual)$$).setLine(getYYline()+1);                   }
|   INT                                                                 { $$ = new ASTInteger((Integer)$1); ((ASTInteger)$$).setLine(getYYline()+1);                                    }
|   DOUBLE                                                              { $$ = new ASTDouble((Double)$1); ((ASTDouble)$$).setLine(getYYline()+1);                                       }
|   ID                                                                  { $$ = new ASTId((String)$1); ((ASTId)$$).setLine(getYYline()+1);                                               }
|   BOOLEAN                                                             { $$ = new ASTBoolean((Boolean)$1); ((ASTBoolean)$$).setLine(getYYline()+1);                                    }
;

%%

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

