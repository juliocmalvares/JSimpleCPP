/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast;

import ast.expr.ASTExpr;

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
    
    
}
