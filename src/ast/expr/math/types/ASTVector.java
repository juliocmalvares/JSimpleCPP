/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast.expr.math.types;

import ast.expr.ASTExpr;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import symbtab.SymbolTab;

/**
 *
 * @author juliocmalvares
 */
public class ASTVector extends ASTExpr{
    
    public ASTVector(ASTExpr esq, ASTExpr dir) {
        super(esq, dir);
    }

    @Override
    public void semanticAnalysis(SymbolTab tab) throws Exception {
        if(this.getEsq() != null && this.getDir() != null){
            this.getEsq().semanticAnalysis(tab);
            this.getDir().semanticAnalysis(tab);

        }else
            throw new Exception("Error on line " + this.getLine());
    }

    @Override
    public void generateJasmin(PrintWriter out, SymbolTab symbolTab) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void generatePython(PrintWriter out, SymbolTab symbolTab) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
