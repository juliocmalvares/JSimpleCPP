/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast.expr;

/**
 *
 * @author juliocmalvares
 */
public class ASTSub extends ASTExpr{
    public ASTSub(ASTExpr esq, ASTExpr dir){
        super(esq, dir);
    }
}