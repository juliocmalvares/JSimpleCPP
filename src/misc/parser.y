%{
//yylval é a variavel do lexema que recebe do analisador lexico
package misc;
import java.io.*;
import ast.*;
import ast.expr.*;


%}

%left '<' '>'
%left '+' '-'
%token PRINT ID ATRIB IF WHILE ELSE NUM FOR INT

%%

lst_comandos:
    lst_comandos comando ';'    { ((ASTCommand)$1).setProx((ASTCommand)$2); $$ = $2; }
|   comando ';'                 { if(root == null) root = (ASTNode)$1; $$ = $1; }
;

comando:
    PRINT '<<' expr 
|   INT ID ATRIB expr {  }
|   IF '(' expr ')' '{' lst_comandos '}'                     { $$ = new ASTIf(  (ASTExpr)$3 , (ASTCommand)$6  );}
|   IF '(' expr ')' '{' lst_comandos '}'ELSE '{' lst_comandos '}'   { $$ = new ASTIf((ASTExpr)$3, (ASTCommand)$6, (ASTCommand)$8 ); }
|   WHILE '(' expr ')' '{' lst_comandos '}'
;

expr:
    expr '+' expr   { $$ = new ASTSoma( (ASTExpr)$1, (ASTExpr)$3 ); }
|   expr '-' expr
|   '(' expr ')'    { $$ = $2; }
|   expr '>' expr
|   expr '<' expr   
|   NUM             { $$ = new ASTNum((Integer)$1); }
;

%%
public Parser(String filename) throws Exception{
    super(new FileReader(filename));
}

public void yyerror(String s){
    System.out.println("Erro sintático: " + s);
}

ASTNode root;