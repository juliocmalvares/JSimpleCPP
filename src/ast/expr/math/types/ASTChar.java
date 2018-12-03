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
public class ASTChar extends ASTExpr{
    private Character lexeme;

    public Character getLexeme() {
        return lexeme;
    }

    public void setLexeme(Character lexeme) {
        this.lexeme = lexeme;
    }
    
    
    public ASTChar(Character lexeme){
        super(null, null);
        this.lexeme = lexeme;
        
    }
}
