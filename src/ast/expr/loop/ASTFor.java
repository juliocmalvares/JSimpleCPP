package ast.expr.loop;

import ast.ASTCommand;
import ast.expr.ASTAtrib;
import ast.expr.ASTExpr;
import ast.expr.atrib.ASTDecl;
import ast.expr.math.ASTSoma;
import ast.expr.math.ASTSub;
import ast.expr.math.logical.ASTBigger;
import ast.expr.math.logical.ASTBiggerEqual;
import ast.expr.math.logical.ASTEqual;
import ast.expr.math.logical.ASTLess;
import ast.expr.math.logical.ASTLessEqual;
import ast.expr.math.logical.ASTNotEqual;
import ast.expr.math.types.ASTInteger;
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
        for(int i = 0; i < symbolTab.getPadding(); i++) out.print("\t");
        out.print("for " + ((ASTDecl)this.getInit()).getId() );
        
        out.print(" in range(0 , " + ((ASTInteger)this.getCondition().getDir()).getLexeme() + "):\n");
        symbolTab.pushPadding();
        this.getCommands().generatePython(out, symbolTab);
        symbolTab.popPadding();
        if(this.getProx() != null) this.getProx().generatePython(out, symbolTab);
    }


    @Override
    public void semanticAnalysis(SymbolTab tab) throws Exception {
        if(this.getInit() instanceof ASTDecl || this.getInit() instanceof ASTAtrib){
            if(this.getCondition() instanceof ASTBigger || this.getCondition() instanceof ASTLess || this.getCondition() instanceof ASTBiggerEqual || this.getCondition() instanceof ASTLessEqual || this.getCondition() instanceof ASTEqual || this.getCondition() instanceof ASTNotEqual){
                if(this.getIteration() instanceof ASTSoma || this.getIteration() instanceof ASTSub){
                    this.getCommands().semanticAnalysis(tab);
                }else
                    throw new Exception("An increase or decrease was expected on line " + this.getLine());
            }else
                throw new Exception("A condition was expected on line " + this.getLine());
        }else
            throw new Exception("Expected statement or attribution on line " + this.getLine());

        if(this.getProx() != null) this.getProx().semanticAnalysis(tab);
    }
    
}
