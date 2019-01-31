/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast;

import java.io.PrintWriter;
import symbtab.SymbolTab;

/**
 *
 * @author juliocmalvares
 */
public abstract class ASTNode {
    
    
    public abstract void semanticAnalysis(SymbolTab tab) throws Exception;
    public abstract void generateJasmin(PrintWriter out, SymbolTab symbolTab) throws Exception;
    public abstract void generatePython(PrintWriter out, SymbolTab symbolTab) throws Exception;
}
