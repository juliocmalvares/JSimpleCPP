package ast;

import ast.expr.ASTExpr;
import java.io.PrintWriter;
import symbtab.SymbolTab;

/**
 *
 * @author juliocmalvares
 */
public class ASTCin extends ASTCommand{
    private ASTExpr expr;

    public ASTCin(ASTExpr expr) {
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
        this.getExpr().generatePython(out, symbolTab);
        out.print(" = input()\n");
        if(this.getProx() != null) this.getProx().generatePython(out, symbolTab);
    }

    @Override
    public void semanticAnalysis(SymbolTab tab) throws Exception {
        this.getExpr().semanticAnalysis(tab);
        if(this.getProx() != null) this.getProx().semanticAnalysis(tab);
    }
    
}
