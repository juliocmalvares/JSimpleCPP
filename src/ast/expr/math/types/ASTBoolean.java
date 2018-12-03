/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast.expr.math.types;

import ast.expr.ASTExpr;

/**
 *
 * @author juliocmalvares
 */

public class ASTBoolean extends ASTExpr{
    private Boolean lexeme;

    public Boolean getLexeme() {
        return lexeme;
    }

    public void setLexeme(Boolean lexeme) {
        this.lexeme = lexeme;
    }
    
    
    public ASTBoolean(Boolean lexeme){
        super(null, null);
        this.lexeme = lexeme;
    }
}
