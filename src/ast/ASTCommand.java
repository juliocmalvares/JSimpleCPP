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
public abstract class ASTCommand extends ASTNode{
    private ASTCommand prox;
    private int line;

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public ASTCommand getProx() {
        return prox;
    }

    public void setProx(ASTCommand prox) throws Exception{
        this.prox = prox;
    }
    
    /**
     *
     * @param out
     * @param symbolTab
     */
    public abstract void generateJasmin(PrintWriter out, SymbolTab symbolTab) throws Exception;
    public abstract void generatePython(PrintWriter out, SymbolTab symbolTab) throws Exception;
}
