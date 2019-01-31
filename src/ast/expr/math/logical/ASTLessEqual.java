/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast.expr.math.logical;

import ast.expr.ASTExpr;
import ast.expr.math.types.ASTBoolean;
import java.io.PrintWriter;
import symbtab.SymbolTab;

/**
 *
 * @author juliocmalvares
 */
public class ASTLessEqual extends ASTExpr{
    
    public ASTLessEqual(ASTExpr esq, ASTExpr dir) {
        super(esq, dir);
    }


    @Override
    public void semanticAnalysis(SymbolTab tab) throws Exception {
        if(this.getEsq() instanceof ASTBoolean && this.getDir() instanceof ASTBoolean){
            this.getEsq().semanticAnalysis(tab);
            this.getDir().semanticAnalysis(tab);
        }else
            throw new Exception("Incompatible types for logical expression on line " + this.getLine());
    }

    @Override
    public void generateJasmin(PrintWriter out, SymbolTab symbolTab) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void generatePython(PrintWriter out, SymbolTab symbolTab) throws Exception {
        this.getEsq().generatePython(out, symbolTab);
        out.print(" <= ");
        this.getDir().generatePython(out, symbolTab);
    }
    
}
