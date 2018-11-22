/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast.expr;

import ast.*;

/**
 *
 * @author juliocmalvares
 */
public abstract class ASTExpr extends ASTNode{
    private ASTExpr dir, esq;

    public ASTExpr(ASTExpr esq, ASTExpr dir) {
        this.dir = dir;
        this.esq = esq;
    }

    
    public ASTExpr getDir() {
        return dir;
    }

    public void setDir(ASTExpr dir) {
        this.dir = dir;
    }

    public ASTExpr getEsq() {
        return esq;
    }

    public void setEsq(ASTExpr esq) {
        this.esq = esq;
    }
    
}
