package ast.expr.math.types;

import ast.expr.ASTExpr;
import java.io.PrintWriter;
import symbtab.SymbolTab;

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
    }
    
    public ASTInteger(Integer num, String operator){
        super(null, null);
        this.lexeme = num;
        this.operator = operator;
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

    @Override
    public void semanticAnalysis(SymbolTab tab) throws Exception {
    }

    @Override
    public void generateJasmin(PrintWriter out, SymbolTab symbolTab) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void generatePython(PrintWriter out, SymbolTab symbolTab) throws Exception {
        out.print(this.lexeme);
    }

    
}
