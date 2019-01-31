/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast;

import ast.expr.ASTExpr;
import java.io.PrintWriter;
import symbtab.SymbolTab;

/**
 *
 * @author juliocmalvares
 */
public class ASTPrint extends ASTCommand{
    private ASTExpr expr;

    public ASTPrint(ASTExpr expr) {
        this.expr = expr;
    }
    
    public ASTExpr getExpr() {
        return expr;
    }

    public void setExpr(ASTExpr expr) {
        this.expr = expr;
    }

    @Override
    public void generateJasmin(PrintWriter out, SymbolTab symbolTab) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void generatePython(PrintWriter out, SymbolTab symbolTab) throws Exception {
        for(int i = 0; i < symbolTab.getPadding(); i++) out.print('\t');
        out.print("print(");
        this.getExpr().generatePython(out, symbolTab);
        out.print(")\n");
        if(this.getProx() != null) this.getProx().generatePython(out, symbolTab);
    }

    @Override
    public void semanticAnalysis(SymbolTab tab) throws Exception {
        this.getExpr().semanticAnalysis(tab);
    }


}
