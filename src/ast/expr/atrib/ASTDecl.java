/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast.expr.atrib;

import ast.ASTNode;
import ast.ASTType;
import ast.expr.ASTExpr;

/**
 *
 * @author juliocmalvares
 */
public class ASTDecl extends ASTNode{
    private ASTAtrib atrib;
    private ASTType type;
    
    public ASTDecl(){}
    public ASTDecl(ASTAtrib at, ASTType type){
        this.atrib = at;
        this.type = type;
    }   

    public ASTAtrib getAtrib() {
        return atrib;
    }

    public void setAtrib(ASTAtrib atrib) {
        this.atrib = atrib;
    }

    public ASTType getType() {
        return type;
    }

    public void setType(ASTType type) {
        this.type = type;
    }    
}
