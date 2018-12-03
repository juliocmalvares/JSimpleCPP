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
public class ASTDouble extends ASTExpr {
    private Double lexeme;

    public Double getLexeme() {
        return lexeme;
    }

    public void setLexeme(Double lexeme) {
        this.lexeme = lexeme;
    }
    
    
    public ASTDouble(Double num) {
        super(null, null);
        this.lexeme = num;
    }
    
}
