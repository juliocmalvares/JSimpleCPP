/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast.expr.atrib;

import ast.ASTCommand;
import ast.ASTNode;
import ast.expr.ASTExpr;
import java.io.PrintWriter;
import symbtab.SymbolTab;

/**
 *
 * @author juliocmalvares
 */
public class ASTDecl extends ASTCommand{
    private ASTExpr expr;
    private String id;
 
    public ASTDecl(ASTExpr expr, String id){
        this.expr = expr;
        this.id = id;
    }

    public ASTExpr getExpr() {
        return expr;
    }

    public void setExpr(ASTExpr expr) {
        this.expr = expr;
    }
    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
