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
public class ASTOr extends ASTExpr{
    
    public ASTOr(ASTExpr esq, ASTExpr dir) {
        super(esq, dir);
    }

    @Override
    public void semanticAnalysis(SymbolTab tab) throws Exception {
        if(this.getDir() instanceof ASTBoolean && this.getEsq() instanceof ASTBoolean){
            this.getDir().semanticAnalysis(tab);
            this.getEsq().semanticAnalysis(tab);
        }else{
            throw new Exception("Incompatible types for logical expression on line " + this.getLine());
        }
        //return false;
    }

    @Override
    public void generateJasmin(PrintWriter out, SymbolTab symbolTab) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void generatePython(PrintWriter out, SymbolTab symbolTab) throws Exception {
        this.getEsq().generatePython(out, symbolTab);
        out.print(" or ");
        this.getDir().generatePython(out, symbolTab);
    }


    
}
