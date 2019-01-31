/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package misc;

import ast.expr.ASTAtrib;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.nio.CharBuffer;

/*
Integrantes do grupo: Júlio César Álvares
                      Marcus Vinicius
                      Marco Aurélio

    Alguns comandos não estão funcionando perfeitamente e apenas a geração de python está funcional.
    Abraços!



*/
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        Parser p = new Parser("src/test/teste.txt");
        p.yyparse();

        PrintWriter pw = new PrintWriter("out.py");
        pw.println("#/usr/bin/python3");
        pw.println("#encoding:utf-8");
        p.getRoot().generatePython(pw, p.getSymbolTab());
        pw.close();
    }
    
}
