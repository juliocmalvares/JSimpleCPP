/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast.expr;

import ast.ASTCommand;
import ast.ASTId;
import ast.expr.math.types.ASTInteger;
import java.io.PrintWriter;
import symbtab.SymbolTab;

/**
 *
 * @author juliocmalvares
 */
public class ASTAtrib extends ASTCommand{
    
    private Boolean isVector;
    private ASTId id;
    private ASTExpr expr, internal;
    private String type;

    
    public ASTAtrib(ASTId id, ASTExpr expr){
        this.id = id;
        this.expr = expr;
        this.isVector = false;
    }
    
    public ASTAtrib(ASTId id, ASTExpr expr, ASTExpr internal){
        this.id = id;
        this.expr = expr;
        this.internal = internal;
        this.isVector = true;
    }
   
    public Boolean getIsVector() {
        return isVector;
    }

    public void setIsVector(Boolean isVector) {
        this.isVector = isVector;
    }

    public ASTId getId() {
        return id;
    }

    public void setId(ASTId id) {
        this.id = id;
    }

    public ASTExpr getInternal() {
        return internal;
    }

    public void setInternal(ASTExpr internal) {
        this.internal = internal;
    }

    public ASTExpr getExpr() {
        return expr;
    }

    public void setExpr(ASTExpr expr) {
        this.expr = expr;
    }

    @Override
    public void semanticAnalysis(SymbolTab tab) throws Exception {
        if(tab.get(this.getId().getLexeme()) != -1){
            if(this.getIsVector()){
                if(this.getInternal() instanceof ASTInteger){
                    this.getExpr().semanticAnalysis(tab);
                }else if(this.getInternal() instanceof ASTId){
                    this.getExpr().semanticAnalysis(tab);
                }else{
                    throw new Exception("Incompatible type on internal variable. Line "+this.getLine());
                }
            }else{
                this.getExpr().semanticAnalysis(tab);
            }
            
        }else{
            throw new Exception("Unreported variable on line "+ this.getLine());
        }
        if(this.getProx() != null) this.getProx().semanticAnalysis(tab);
    }

    @Override
    public void generateJasmin(PrintWriter out, SymbolTab symbolTab) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void generatePython(PrintWriter out, SymbolTab symbolTab) throws Exception {
        System.out.println("ATRIB");
        if(symbolTab.getPadding() == 0){
            this.getId().generatePython(out, symbolTab);
            out.print(" = ");
            this.getExpr().generatePython(out, symbolTab);
            out.print('\n');
        }else{
            for(int i = 0; i < symbolTab.getPadding(); i++) out.print('\t');
            this.getId().generatePython(out, symbolTab);
            out.print(" = ");
            this.getExpr().generatePython(out, symbolTab);
            out.print('\n');
        }
        if(this.getProx() != null) this.getProx().generatePython(out, symbolTab);
    }
    
}
