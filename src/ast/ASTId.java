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
public class ASTId extends ASTExpr{
    private String lexeme;
    
    public ASTId(String lexeme) {
        super(null, null);
        this.lexeme = lexeme;
    }
    
}
