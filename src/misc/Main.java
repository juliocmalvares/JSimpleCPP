/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package misc;

import java.io.File;
import java.io.PrintWriter;

/**
 *
 * @author juliocmalvares
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        Parser p = new Parser("src/test/teste.txt");       
        
        
        p.yyparse();
        
    }
    
}
