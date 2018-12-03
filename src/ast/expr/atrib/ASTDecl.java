/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast.expr.atrib;

import ast.ASTCommand;
import ast.ASTNode;
import ast.ASTType;
import ast.expr.ASTExpr;
import java.io.PrintWriter;
import symbtab.SymbolTab;

/**
 *
 * @author juliocmalvares
 */
public class ASTDecl extends ASTCommand{
    private ASTExpr atrib;
    private ASTType type;
    String id;
    
    public ASTDecl(){}
    public ASTDecl(ASTExpr at, ASTType type, String id){
        this.atrib = at;
        this.type = type;
        this.id = id;
    }   

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ASTExpr getAtrib() {
        return atrib;
    }

    public void setAtrib(ASTExpr atrib) {
        this.atrib = atrib;
    }

    public ASTType getType() {
        return type;
    }

    public void setType(ASTType type) {
        this.type = type;
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
