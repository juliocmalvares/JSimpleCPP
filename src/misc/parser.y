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

%left '<' '>'
%left '+' '-'
%token PRINT ID ATRIB IF WHILE ELSE NUM FOR INT DOUBLE BOOL CHAR STRING

%%

lst_comandos:
    lst_comandos comando ';'                                            { ((ASTCommand)$1).setProx((ASTCommand)$2); $$ = $2; }
|   comando ';'                                                         { if(root == null) root = (ASTNode)$1; $$ = $1; }
;

comando:
    PRINT '<<' expr                                                     { /*Implementar print*/ } 

|   INT ID ATRIB expr                                                   { symtab.set((String)$2); $$ = new ASTDecl((ASTInteger)$4, (String)$2); }
|   DOUBLE ID ATRIB expr                                                { /*double*/}
|   BOOL ID ATRIB expr                                                  { /*boolean*/}
|   CHAR ID ATRIB expr                                                  { /*character*/}
|   STRING ID ATRIB expr                                                { /* string*/}

|   IF '(' expr ')' '{' lst_comandos '}'                                { $$ = new ASTIf(  (ASTExpr)$3 , (ASTCommand)$6  );}
|   IF '(' expr ')' '{' lst_comandos '}'ELSE '{' lst_comandos '}'       { $$ = new ASTIf((ASTExpr)$3, (ASTCommand)$6, (ASTCommand)$8 ); }
|   WHILE '(' expr ')' '{' lst_comandos '}'                             { /*Implementar o while*/}
|   FOR '(' expr ';' expr ';' expr ')' '{' lst_comandos '}'             { /*Implementar o for*/}
;

expr:
    expr '+' expr                                                       { $$ = new ASTSoma( (ASTExpr)$1, (ASTExpr)$3 ); }
|   expr '-' expr                                                       { $$ = new ASTSub( (ASTExpr)$1, (ASTExpr)$3); }
|   '(' expr ')'                                                        { $$ = $2; }
|   expr '>' expr                                                       { $$ = new ASTBigger((ASTExpr)$1, (ASTExpr)$3);}
|   expr '<' expr                                                       { $$ = new ASTLess((ASTExpr)$1, (ASTExpr)$3);}
|   NUM                                                                 {  if($1 instanceof Integer){ $$ = new ASTInteger((Integer)$1); System.out.println("Integer");}
                                                                           if($1 instanceof Double) $$ = new ASTDouble((Double)$1); }
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

