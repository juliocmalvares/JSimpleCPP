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
public class ASTNum extends ASTExpr{
    private Integer lexeme;

    public ASTNum(Integer num) {
        super(null, null);
        this.lexeme = num;
    }

    public Integer getLexeme() {
        return lexeme;
    }

    public void setLexeme(Integer lexema) {
        this.lexeme = lexema;
    }
    
}
