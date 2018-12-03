package ast.expr.math.numerical;

import ast.expr.ASTExpr;

/**
 *
 * @author juliocmalvares
 */
public class ASTInteger extends ASTExpr{
    private Integer lexeme;

    public ASTInteger(Integer num) {
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
