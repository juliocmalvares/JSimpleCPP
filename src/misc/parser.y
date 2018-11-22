%{
//yylval é a variavel do lexema que recebe do analisador lexico
import java.io.*;
import compiler.ast.*;
import compiler.ast.expr.*;
%}

%left '<' '>'
%left '+' '-'
%token PRINT ID ATRIB IF WHILE ELSE NUM

%%

lst_comandos:
    lst_comandos comando ';'    { ((ASTCommand)$1).setProximo($2); $$ = $2; }
|   comando ';'                 { if(root == null) root = $1; $$ = $1; }
;

comando:
    WRITELN '(' expr ')'
|   ID ATRIB expr
|   IF '(' expr ')' THEN lst_comandos ENDIF                     { $$ = new ASTIf($3, $6);}
|   IF '(' expr ')' THEN lst_comandos ELSE lst_comandos ENDIF   { $$ = new ASTIf($3, $6, $8); }
|   WHILE '(' expr ')' DO lst_comandos DONE
;

expr:
    expr '+' expr   { $$ = new ASTSoma($1, $3); }
|   expr '-' expr
|   '(' expr ')'    { $$ = $2; }
|   expr '>' expr
|   expr '<' expr   
|   NUM             { $$ = new ASTNum($1); }
;

%%
public Parser(String filename) throws Exception{
    super(new FileReader(filename));
}

public void yyerror(String s){
    System.out.println("Erro sintático: " + s);
}