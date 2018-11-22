/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast.expr.atrib;

import ast.ASTNode;
import ast.expr.ASTExpr;

/**
 *
 * @author juliocmalvares
 */
public class ASTDecl extends ASTNode{
    private String type;
    private ASTExpr atrib;
    
    public ASTDecl(String type, ASTExpr exp){
        this.type = type;
        this.atrib = exp;
    }
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ASTExpr getAtrib() {
        return atrib;
    }

    public void setAtrib(ASTAtrib atrib) {
        this.atrib = atrib;
    }
    
}
