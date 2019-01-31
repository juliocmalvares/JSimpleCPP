/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast.expr.math.types;

import ast.expr.ASTExpr;
import java.io.PrintWriter;
import symbtab.SymbolTab;

/**
 *
 * @author juliocmalvares
 */
public class ASTDouble extends ASTExpr {
    private Double lexeme;

    public Double getLexeme() {
        return lexeme;
    }

    public void setLexeme(Double lexeme) {
        this.lexeme = lexeme;
    }
    
    
    public ASTDouble(Double num) {
        super(null, null);
        this.lexeme = num;
    }

    @Override
    public void semanticAnalysis(SymbolTab tab) throws Exception {
    }

    @Override
    public void generateJasmin(PrintWriter out, SymbolTab symbolTab) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void generatePython(PrintWriter out, SymbolTab symbolTab) throws Exception {
        out.print(this.lexeme);
    }

    
}
