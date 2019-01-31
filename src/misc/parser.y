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

%}

%left '>' '<' LESSEQUAL BIGGEREQUAL AND OR EQUAL NOTEQUAL ATRIB
%left '*' '/'
%left '+' '-' INCR DECR

%token PRINT ID ATRIB IF WHILE ELSE INT DOUBLE FOR INT DOUBLE BOOL CHAR STRING CIN BOOLEAN IN OUT INCR DECR NOTEQUAL EQUAL BIGGEREQUAL LESSEQUAL OR AND VRG

%nonassoc PMORE PLESS

%%

lst_comandos:
    lst_comandos comando                                            { ((ASTCommand)$1).setProx((ASTCommand)$2); ((ASTCommand)$2).setAnt((ASTCommand)$1); $$ = $2;        }
|   comando                                                         { if(root == null) root = (ASTNode)$1; $$ = $1;             }
;

comando:
    PRINT OUT expr ';'                                                  { $$ = new ASTPrint((ASTExpr)$3); ((ASTPrint)$$).setLine(getYYline()+1);                                                }
|   CIN IN expr ';'                                                     { $$ = new ASTCin((ASTExpr)$3); ((ASTCin)$$).setLine(getYYline()+1);                                                    }
|   IF '(' expr ')' '{' lst_comandos '}'                                { $$ = new ASTIf((ASTExpr)$3, (ASTCommand)$6); ((ASTIf)$$).setLine(getYYline()+1);                                      }
|   IF '(' expr ')' '{' lst_comandos '}' ELSE '{' lst_comandos '}'      { $$ = new ASTIf((ASTExpr)$3, (ASTCommand)$6, (ASTCommand)$10); ((ASTIf)$$).setLine(getYYline()+1);                      }
|   WHILE '(' expr ')' '{' lst_comandos '}'                             { $$ = new ASTWhile((ASTExpr)$3, (ASTCommand)$6); ((ASTWhile)$$).setLine(getYYline()+1);                                }
|   FOR '(' comando expr ';' expr ')' '{' lst_comandos '}'              { $$ = new ASTFor((ASTCommand)$3, (ASTExpr)$4, (ASTExpr)$6, (ASTCommand)$9); ((ASTFor)$$).setLine(getYYline()+1);       }
|   INT ID ATRIB expr ';'                                               { symtab.set((String)$2); $$ = new ASTDecl((ASTExpr)$4, (String)$2, "int"); ((ASTDecl)$$).setLine(getYYline()+1);           }
|   DOUBLE ID ATRIB expr ';'                                            { symtab.set((String)$2); $$ = new ASTDecl((ASTExpr)$4, (String)$2, "double"); ((ASTDecl)$$).setLine(getYYline()+1);            }
|   BOOL ID ATRIB expr ';'                                              { symtab.set((String)$2); $$ = new ASTDecl((ASTExpr)$4, (String)$2, "boolean"); ((ASTDecl)$$).setLine(getYYline()+1);           }
|   CHAR ID ATRIB expr ';'                                              { symtab.set((String)$2); $$ = new ASTDecl((ASTExpr)$4, (String)$2, "char"); ((ASTDecl)$$).setLine(getYYline()+1);              }
|   STRING ID ATRIB expr ';'                                            { symtab.set((String)$2); $$ = new ASTDecl((ASTExpr)$4, (String)$2, "string"); ((ASTDecl)$$).setLine(getYYline()+1);              }
|   INT ID ';'                                                          { symtab.set((String)$2); $$ = new ASTDecl(new ASTInteger(0), (String)$2, "int"); ((ASTDecl)$$).setLine(getYYline()+1);        }
|   DOUBLE ID ';'                                                       { symtab.set((String)$2); $$ = new ASTDecl(new ASTDouble(0.0), (String)$2, "double"); ((ASTDecl)$$).setLine(getYYline()+1);       }
|   BOOL ID ';'                                                         { symtab.set((String)$2); $$ = new ASTDecl(new ASTBoolean(false), (String)$2, "boolean"); ((ASTDecl)$$).setLine(getYYline()+1);    }
|   CHAR ID ';'                                                         { symtab.set((String)$2); $$ = new ASTDecl(new ASTChar(' '), (String)$2, "char"); ((ASTDecl)$$).setLine(getYYline()+1);         }
|   STRING ID ';'                                                       { symtab.set((String)$2); $$ = new ASTDecl( new ASTString(" "), ((String)$2), "string");  ((ASTDecl)$$).setLine(getYYline()+1); }
|   INT ID'['expr']' ';'                                                { symtab.set((String)$2); $$ = new ASTDecl(new ASTInteger(0), (String)$2, true, (ASTExpr)$4, "int"); ((ASTDecl)$$).setLine(getYYline()+1);}
|   CHAR ID '['expr']' ';'                                              { symtab.set((String)$2); $$ = new ASTDecl(new ASTChar(' '), (String)$2, true, (ASTExpr)$4, "char"); ((ASTDecl)$$).setLine(getYYline()+1); }
|   BOOL ID'['expr']' ';'                                               { symtab.set((String)$2); $$ = new ASTDecl(new ASTBoolean(false), (String)$2, true, (ASTExpr)$4, "boolean"); ((ASTDecl)$$).setLine(getYYline()+1); }
|   DOUBLE ID'['expr']' ';'                                             { symtab.set((String)$2); $$ = new ASTDecl(new ASTDouble(0.0), (String)$2, true, (ASTExpr)$4, "double"); ((ASTDecl)$$).setLine(getYYline()+1); }
|   STRING ID'['expr']'  ';'                                            { symtab.set((String)$2); $$ = new ASTDecl(new ASTString(" "), (String)$2, true, (ASTExpr)$4, "string"); ((ASTDecl)$$).setLine(getYYline()+1); }
|   ID ATRIB expr                                                       { $$ = new ASTAtrib((ASTId)$1, (ASTExpr)$3); ((ASTAtrib)$$).setLine(getYYline()+1);                             }
|   ID'['INT']' ATRIB expr                                              { $$ = new ASTAtrib((ASTId)$1, (ASTExpr)$6, ((ASTInteger)$3)); ((ASTAtrib)$$).setLine(getYYline()+1);     }
|   ID'['ID']' ATRIB expr                                               { $$ = new ASTAtrib((ASTId)$1, (ASTExpr)$6, ((ASTId)$3)); ((ASTAtrib)$$).setLine(getYYline()+1);        }
;

expr:
    expr '>' expr                              { $$ = new ASTBigger((ASTExpr)$1, (ASTExpr)$3); ((ASTBigger)$$).setLine(getYYline()+1);                         }
|   expr '<' expr                              { $$ = new ASTLess((ASTExpr)$1, (ASTExpr)$3); ((ASTLess)$$).setLine(getYYline()+1);                             }
|   expr '/' expr                              { $$ = new ASTDiv((ASTExpr)$1, (ASTExpr)$3); ((ASTDiv)$$).setLine(getYYline()+1);                               }
|   expr '*' expr                              { $$ = new ASTMult((ASTExpr)$1, (ASTExpr)$3); ((ASTMult)$$).setLine(getYYline()+1);                             }
|   expr '+' expr                              { $$ = new ASTSoma((ASTExpr)$1, (ASTExpr)$3); ((ASTSoma)$$).setLine(getYYline()+1);                             }
|   expr '-' expr                              { $$ = new ASTSub((ASTExpr)$1, (ASTExpr)$3); ((ASTSub)$$).setLine(getYYline()+1);                               }
|   expr INCR                                  { $$ = new ASTSoma((ASTExpr)$1, new ASTInteger(1)); ((ASTSoma)$$).setLine(getYYline()+1);                       }
|   expr DECR                                  { $$ = new ASTSub((ASTExpr)$1, new ASTInteger(1)); ((ASTSub)$$).setLine(getYYline()+1);                         }
|   '(' expr ')'                               { $$ = (ASTExpr)$2; ((ASTExpr)$$).setLine(getYYline()+1);                                                       }
|   '+'expr %prec PMORE                        { $$ = (ASTExpr)$2; ((ASTExpr)$$).setLine(getYYline()+1);                                                       }
|   '-'expr  %prec PLESS                       { $$ = new ASTInteger((Integer)$2); ((ASTInteger)$$).setOperator("-"); ((ASTExpr)$$).setLine(getYYline()+1);    }
|   expr AND expr                              { $$ = new ASTAnd((ASTExpr)$1, (ASTExpr)$3); ((ASTAnd)$$).setLine(getYYline()+1);                               }
|   expr OR expr                               { $$ = new ASTOr((ASTExpr)$1, (ASTExpr)$3); ((ASTOr)$$).setLine(getYYline()+1);                                 }
|   expr NOTEQUAL expr                         { $$ = new ASTNotEqual((ASTExpr)$1, (ASTExpr)$3); ((ASTNotEqual)$$).setLine(getYYline()+1);                     }
|   expr EQUAL expr                            { $$ = new ASTEqual((ASTExpr)$1, (ASTExpr)$3);  ((ASTEqual)$$).setLine(getYYline()+1);                          }
|   expr BIGGEREQUAL expr                      { $$ = new ASTBiggerEqual((ASTExpr)$1, (ASTExpr)$3); ((ASTBiggerEqual)$$).setLine(getYYline()+1);               }
|   expr LESSEQUAL expr                        { $$ = new ASTLessEqual((ASTExpr)$1, (ASTExpr)$3); ((ASTLessEqual)$$).setLine(getYYline()+1);                   }
|   ID'['INT']'                                { $$ = new ASTVector((ASTId)$1, (ASTInteger)$3); ((ASTVector)$$).setLine(getYYline()+1);                        }
|   ID'['ID']'                                 { $$ = new ASTVector((ASTId)$1, (ASTId)$3); ((ASTVector)$$).setLine(getYYline()+1);                             }
|   INT                                        { $$ = new ASTInteger((Integer)$1); ((ASTInteger)$$).setLine(getYYline()+1);                                    }
|   DOUBLE                                     { $$ = new ASTDouble((Double)$1); ((ASTDouble)$$).setLine(getYYline()+1);                                       }
|   ID                                         { $$ = new ASTId((String)$1); ((ASTId)$$).setLine(getYYline()+1);                                               }
|   BOOLEAN                                    { $$ = new ASTBoolean((Boolean)$1); ((ASTBoolean)$$).setLine(getYYline()+1);                                    }
|   STRING                                     { $$ = new ASTString(((String)$1)); ((ASTString)$$).setLine(getYYline()+1);                                     }
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

/*
|   lst_decl                                                            { $$ = ((ASTListVariables)$1); }
;

lst_decl:
    lst_decl VRG decl                           { ((ASTListVariables)$1).setProx((ASTListVariables)$3); ((ASTListVariables)$3).setAnt((ASTListVariables)$1); $$ = $3; }
|   decl                                        { $$ = ((ASTListVariables)$1); }
;
*/

