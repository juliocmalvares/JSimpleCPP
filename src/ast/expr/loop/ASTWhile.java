package ast.expr.loop;
import ast.ASTCommand;
import ast.expr.ASTExpr;
import ast.expr.math.types.ASTBoolean;
import java.io.PrintWriter;
import symbtab.SymbolTab;

/**
 *
 * @author juliocmalvares
 */

public class ASTWhile extends ASTCommand {
    private ASTExpr condition;
    private ASTCommand commands;

    public ASTWhile(ASTExpr condition, ASTCommand commands) {
        this.condition = condition;
        this.commands = commands;
    }

    public ASTExpr getCondition() {
        return condition;
    }

    public void setCondition(ASTExpr condition) {
        this.condition = condition;
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
        out.print("while ");
        this.getCondition().generatePython(out, symbolTab);
        out.print(":\n");
        symbolTab.pushPadding();
        this.getCommands().generatePython(out, symbolTab);
        symbolTab.popPadding();
        if(this.getProx() != null) this.getProx().generatePython(out, symbolTab);
    }


    @Override
    public void semanticAnalysis(SymbolTab tab) throws Exception {
        if(this.condition instanceof ASTBoolean){
            this.getCondition().semanticAnalysis(tab);
            this.getCommands().semanticAnalysis(tab);
        }else{
            throw new Exception("Unespected type on condition, Boolean expected.");
            
        }
        if(this.getProx() != null) this.getProx().semanticAnalysis(tab);
    }
    
}
