package misc;

//java code
%%

%caseless
%byaccj
%class Scanner
%public
%debug

%{
    public int getYYline(){
        return yyline;
    }

    public void setYYline(int line){
        ((Scanner)this).yyline = line;
    }
%}

digit = [0-9]
white = [ \n\t\r\f]
id = [a-z_$][a-z_$0-9]*

InputCharacter = [^\r\n]
LineTerminator = \r|\n|\r\n
comments = {TraditionalComment} | {endlComment} | {DocumentationComment}
TraditionalComment   = "/*" [^*] ~"*/" | "/*" "*"+ "/"
endlComment     = "//" {InputCharacter}* {LineTerminator}?
DocumentationComment = "/**" {CommentContent} "*"+ "/"
CommentContent       = ( [^*] | \*+ [^/*] )*

%%
{comments}                                      { }
{white}+                                        { }
{digit}+                                        { ((Parser)this).yylval = new Integer((String)yytext()); return Parser.INT;          }
{digit}+\.{digit}+(e[+-]?{digit}+)?             { ((Parser)this).yylval = new Double((String)yytext()); return Parser.DOUBLE;       }
"="                                             { return Parser.ATRIB;                                                      }
"cout"                                          { return Parser.PRINT;                                                      }
"cin"                                           { return Parser.CIN;                                                        }
"if"                                            { return Parser.IF;                                                         }
"else"                                          { return Parser.ELSE;                                                       }
"while"                                         { return Parser.WHILE;                                                      }
"for"                                           { return Parser.FOR;                                                        }
"int"                                           { return Parser.INT;                                                        }
"double"                                        { return Parser.DOUBLE;                                                     }
"string"                                        { return Parser.STRING;                                                     }
"char"                                          { return Parser.CHAR;                                                       }
"bool"                                          { return Parser.BOOL;                                                       }
"false"                                         { return Parser.BOOLEAN;                                                    }
"true"                                          { return Parser.BOOLEAN;                                                    }
{id}                                            { ((Parser)this).yylval = yytext(); return Parser.ID;                       }
\"~[\"\n]*\"                                    { /* string */                                                              }
.                                               { return (int) yytext().charAt(0);                                          }
