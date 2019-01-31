/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast.expr;

import ast.*;
import java.io.PrintWriter;
import symbtab.SymbolTab;

/**
 *
 * @author juliocmalvares
 */
public abstract class ASTExpr extends ASTNode{
    private ASTExpr dir, esq;
    private int line;

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }
    

    public ASTExpr(ASTExpr esq, ASTExpr dir) {
        this.dir = dir;
        this.esq = esq;
    }

    public ASTExpr getDir() {
        return dir;
    }

    public void setDir(ASTExpr dir) {
        this.dir = dir;
    }

    public ASTExpr getEsq() {
        return esq;
    }

    public void setEsq(ASTExpr esq) {
        this.esq = esq;
    }
    
    @Override
    public abstract void semanticAnalysis(SymbolTab tab) throws Exception;
    @Override
    public abstract void generateJasmin(PrintWriter out, SymbolTab symbolTab) throws Exception;
    @Override
    public abstract void generatePython(PrintWriter out, SymbolTab symbolTab) throws Exception;
    
}
