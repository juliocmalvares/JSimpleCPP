package ast.expr.loop;

import ast.ASTCommand;
import ast.expr.ASTExpr;
import java.io.PrintWriter;
import symbtab.SymbolTab;

/**
 *
 * @author juliocmalvares
 */
public class ASTFor extends ASTCommand {
    private ASTExpr condition, iteration;
    private ASTCommand init, commands;

    public ASTFor(ASTCommand init, ASTExpr condition, ASTExpr iteration, ASTCommand commands) {
        this.init = init;
        this.condition = condition;
        this.iteration = iteration;
        this.commands = commands;
    }
    
    public ASTCommand getInit() {
        return init;
    }

    public void setInit(ASTCommand init) {
        this.init = init;
    }

    public ASTExpr getCondition() {
        return condition;
    }

    public void setCondition(ASTExpr condition) {
        this.condition = condition;
    }

    public ASTExpr getIteration() {
        return iteration;
    }

    public void setIteration(ASTExpr iteration) {
        this.iteration = iteration;
    }

    public ASTCommand getCommands() {
        return commands;
    }

    public void setCommands(ASTCommand commands) {
        this.commands = commands;
    }

    @Override
    public void generateJasmin(PrintWriter out, SymbolTab symbolTab) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void generatePython(PrintWriter out, SymbolTab symbolTab) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
