/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast.expr.conditional;

import ast.ASTCommand;
import ast.expr.ASTExpr;
import java.io.PrintWriter;
import symbtab.SymbolTab;

/**
 *
 * @author juliocmalvares
 */
public class ASTIf extends ASTCommand{
    private ASTExpr condition;
    private ASTCommand commandsif;
    private ASTCommand commandselse;

    public ASTIf(ASTExpr condition, ASTCommand commandsif){
        this.condition = condition;
        this.commandsif = commandsif;
    }
    
    public ASTIf(ASTExpr condition, ASTCommand commandsif, ASTCommand commandselse){
        this.condition = condition;
        this.commandsif = commandsif;
        this.commandselse = commandselse;
    }

    public ASTExpr getCondition() {
        return condition;
    }

    public void setCondition(ASTExpr condition) {
        this.condition = condition;
    }

    public ASTCommand getCommandsif() {
        return commandsif;
    }

    public void setCommandsif(ASTCommand commandsif) {
        this.commandsif = commandsif;
    }

    public ASTCommand getCommandselse() {
        return commandselse;
    }

    public void setCommandselse(ASTCommand commandselse) {
        this.commandselse = commandselse;
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
