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
public class ASTMult extends ASTExpr{
    public ASTMult(ASTExpr esq, ASTExpr dir){
        super(esq, dir);
    }
}