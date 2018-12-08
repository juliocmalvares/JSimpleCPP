package ast.expr.math.types;

import ast.expr.ASTExpr;

/**
 *
 * @author juliocmalvares
 */
public class ASTInteger extends ASTExpr{
    private Integer lexeme;
    private String operator;
    
    public ASTInteger(Integer num) {
        super(null, null);
        this.lexeme = num;
        this.operator = "";
        this.trateOperator();
    }
    
    public ASTInteger(Integer num, String operator){
        super(null, null);
        this.lexeme = num;
        this.operator = operator;
        this.trateOperator();
    }
    
    private void trateOperator(){
        this.lexeme = lexeme*-1;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Integer getLexeme() {
        return lexeme;
    }

    public void setLexeme(Integer lexema) {
        this.lexeme = lexema;
    }
    
}
