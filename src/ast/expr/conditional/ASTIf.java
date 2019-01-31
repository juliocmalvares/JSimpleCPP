/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast.expr.conditional;

import ast.ASTCommand;
import ast.expr.ASTExpr;
import ast.expr.math.logical.ASTBigger;
import ast.expr.math.logical.ASTBiggerEqual;
import ast.expr.math.logical.ASTEqual;
import ast.expr.math.logical.ASTLess;
import ast.expr.math.logical.ASTLessEqual;
import ast.expr.math.logical.ASTNotEqual;
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
    private Boolean _else;

    public ASTIf(ASTExpr condition, ASTCommand commandsif){
        this.condition = condition;
        this.commandsif = commandsif;
        this._else = false;
    }
    
    public ASTIf(ASTExpr condition, ASTCommand commandsif, ASTCommand commandselse){
        this.condition = condition;
        this.commandsif = commandsif;
        this.commandselse = commandselse;
        this._else = true;
    }

    public Boolean getElse() {
        return _else;
    }

    public void setElse(Boolean _else) {
        this._else = _else;
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
        if(symbolTab.getPadding() == 0){
            out.print("if ");
            this.condition.generatePython(out, symbolTab);
            out.print(":\n");
            symbolTab.pushPadding();
            this.getCommandsif().generatePython(out, symbolTab);
            if(this.getElse()){
                for(int i = 0; i < symbolTab.getPadding() - 1; i++) out.print("\t");
                this.getCommandselse().generatePython(out, symbolTab);
            }
        }else{
            for(int i = 0; i < symbolTab.getPadding(); i++) out.print("\t");
            out.print("if ");
            this.condition.generatePython(out, symbolTab);
            out.print(":\n");
            symbolTab.pushPadding();
            this.getCommandsif().generatePython(out, symbolTab);
            if(this.getElse()){
                for(int i = 0; i < symbolTab.getPadding() - 1; i++) out.print("\t");
                this.getCommandselse().generatePython(out, symbolTab);
            }
        }
        if(this.getProx() != null) this.getProx().generatePython(out, symbolTab);
    }


    @Override
    public void semanticAnalysis(SymbolTab tab) throws Exception {
        if(this.getCondition() instanceof ASTBigger || this.getCondition() instanceof ASTLess || this.getCondition() instanceof ASTBiggerEqual || this.getCondition() instanceof ASTLessEqual || this.getCondition() instanceof ASTEqual || this.getCondition() instanceof ASTNotEqual){
            if(this.getElse()){
                this.getCommandsif().semanticAnalysis(tab);
                this.getCommandselse().semanticAnalysis(tab);
            }else{
                this.getCommandsif().semanticAnalysis(tab);
            }
        }else{
            throw new Exception("Incompatible types for logical expression on line " + this.getLine());
        }
        if(this.getProx() != null) this.getProx().semanticAnalysis(tab);
    }

    
}
