/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast;

import ast.expr.ASTExpr;

/**
 *
 * @author juliocmalvares
 */
public class ASTPrint extends ASTCommand{
    private ASTExpr expr;

    public ASTExpr getExpr() {
        return expr;
    }

    public void setExpr(ASTExpr expr) {
        this.expr = expr;
    }


}
