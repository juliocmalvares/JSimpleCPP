package misc;

//java code
%%

%caseless
%byaccj
%class Scanner
%public

digit = [0-9]
white = [ \n\t\r]
id = [a-z_$][a-z_$0-9]

%%

{white}+ { }
{digit}+\.?{digit}+(e[+-]?{digit}+)? { ((Parser)this).yylval = new Integer(yytext()); return Parser.NUM; }
{id}* { ((Parser)this).yylval = yytext(); return Parser.ID; }
"=" {return Parser.ATRIB;}
"cout" {return Parser.PRINT;}
"if" {return Parser.IF;}
"else" {return Parser.ELSE;}
"while" {return Parser.WHILE;}
"for" { return Parser.FOR; }
"int" {return Parser.INT;}
\'~['\n]*\' {/*string*/}
. { return (int) yytext().charAt(0);}
