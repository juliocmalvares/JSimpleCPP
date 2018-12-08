/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast.expr.math.logical;

import ast.expr.ASTExpr;

/**
 *
 * @author juliocmalvares
 */
public class ASTBiggerEqual extends ASTExpr{
    
    public ASTBiggerEqual(ASTExpr esq, ASTExpr dir) {
        super(esq, dir);
    }
    
}
