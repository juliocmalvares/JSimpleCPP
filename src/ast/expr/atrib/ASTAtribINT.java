/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast.expr.atrib;

import ast.expr.ASTExpr;
import ast.expr.ASTNum;

/**
 *
 * @author juliocmalvares
 */
public class ASTAtribINT extends ASTAtrib{
    private ASTNum value;
    
    //mexer
    public ASTAtribINT(ASTExpr esq, ASTExpr dir) {
        super(esq, dir);
    }
    
}
