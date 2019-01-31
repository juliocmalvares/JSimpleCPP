/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast.expr.atrib;
import ast.ASTCommand;
import ast.ASTId;
import ast.expr.ASTExpr;
import ast.expr.math.ASTDiv;
import ast.expr.math.ASTMult;
import ast.expr.math.ASTSoma;
import ast.expr.math.ASTSub;
import ast.expr.math.logical.ASTAnd;
import ast.expr.math.logical.ASTBigger;
import ast.expr.math.logical.ASTBiggerEqual;
import ast.expr.math.logical.ASTEqual;
import ast.expr.math.logical.ASTLess;
import ast.expr.math.logical.ASTLessEqual;
import ast.expr.math.logical.ASTNotEqual;
import ast.expr.math.logical.ASTOr;
import ast.expr.math.types.ASTBoolean;
import ast.expr.math.types.ASTChar;
import ast.expr.math.types.ASTDouble;
import ast.expr.math.types.ASTInteger;
import ast.expr.math.types.ASTString;
import java.io.PrintWriter;
import symbtab.SymbolTab;

/**
 *
 * @author juliocmalvares
 */
public class ASTDecl extends ASTCommand{
    private ASTExpr expr;
    private String id, type;
    private Boolean isVector;
    private ASTExpr padding;
    
    public ASTDecl(ASTExpr expr, String id, String type){
        this.expr = expr;
        this.id = id;
        this.type = type;
        this.isVector = false;
    }
    
    public ASTDecl(ASTExpr expr, String id, Boolean isVector, ASTExpr padding, String type){
        this.expr = expr;
        this.id = id;
        this.isVector = true;
        this.padding = padding;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getIsVector() {
        return isVector;
    }

    public void setIsVector(Boolean isVector) {
        this.isVector = isVector;
    }

    public ASTExpr getPadding() {
        return padding;
    }

    public void setPadding(ASTExpr padding) {
        this.padding = padding;
    }
    

    public ASTExpr getExpr() {
        return expr;
    }

    public void setExpr(ASTExpr expr) {
        this.expr = expr;
    }
    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    
    @Override
    public void generateJasmin(PrintWriter out, SymbolTab symbolTab) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void generatePython(PrintWriter out, SymbolTab symbolTab) throws Exception {
        if(symbolTab.getPadding() == 0){
            out.print(this.getId() + " = ");
            this.getExpr().generatePython(out, symbolTab); 
            out.print('\n');
        }else{
            for(int i = 0; i < symbolTab.getPadding(); i++) out.print("\t");
            out.print(this.getId() + " = ");
            this.getExpr().generatePython(out, symbolTab); 
            out.print('\n');
        }
        if(this.getProx() != null) this.getProx().generatePython(out, symbolTab);
    }

    @Override
    public void semanticAnalysis(SymbolTab tab) throws Exception {
        Boolean pass = false;
        if(this.getIsVector()){
            if(this.getPadding() instanceof ASTId){
                pass = true;
            }else if(this.getPadding() instanceof ASTInteger){
                pass = true;
            }else{
                pass = false;
            }
        }
        if(pass == false && this.getIsVector() == true){
            throw new Exception("Incompatible type in declaration of vector. Line " + this.getLine());
        }

        switch (this.getType()) {
            case "int":
                if(this.getExpr() instanceof ASTInteger){
                    this.getExpr().semanticAnalysis(tab);
                }else if(this.getExpr() instanceof ASTSoma){
                    this.getExpr().semanticAnalysis(tab);
                }else if(this.getExpr() instanceof ASTSub){
                    this.getExpr().semanticAnalysis(tab);
                }else if(this.getExpr() instanceof ASTDiv){
                    this.getExpr().semanticAnalysis(tab);
                }else if(this.getExpr() instanceof ASTMult){
                    this.getExpr().semanticAnalysis(tab);
                }else if(this.getExpr() instanceof ASTId){
                    this.getExpr().semanticAnalysis(tab);
                }else{
                    throw new Exception("Incompatible type for this variable, expected a integer type on line " + this.getLine());
                }
            case "double":
                if(this.getExpr() instanceof ASTDouble){
                    this.getExpr().semanticAnalysis(tab);
                }else if(this.getExpr() instanceof ASTSoma){
                    this.getExpr().semanticAnalysis(tab);
                }else if(this.getExpr() instanceof ASTSub){
                    this.getExpr().semanticAnalysis(tab);
                }else if(this.getExpr() instanceof ASTDiv){
                    this.getExpr().semanticAnalysis(tab);
                }else if(this.getExpr() instanceof ASTMult){
                    this.getExpr().semanticAnalysis(tab);
                }else if(this.getExpr() instanceof ASTId){
                    this.getExpr().semanticAnalysis(tab);
                }else{
                    throw new Exception("Incompatible type for this variable, expected a float type on line " + this.getLine());
                }
            case "char":
                if(this.getExpr() instanceof ASTChar){
                    this.getExpr().semanticAnalysis(tab);
                }else{
                    throw new Exception("Incompatible type for this variable, expected a character on line " + this.getLine());
                }
            case "boolean":
               if(this.getExpr() instanceof ASTBoolean){
                    this.getExpr().semanticAnalysis(tab);
                }else if(this.getExpr() instanceof ASTAnd){
                    this.getExpr().semanticAnalysis(tab);
                }else if(this.getExpr() instanceof ASTBigger){
                    this.getExpr().semanticAnalysis(tab);
                }else if(this.getExpr() instanceof ASTBiggerEqual){
                    this.getExpr().semanticAnalysis(tab);
                }else if(this.getExpr() instanceof ASTEqual){
                    this.getExpr().semanticAnalysis(tab);
                }else if(this.getExpr() instanceof ASTLess){
                    this.getExpr().semanticAnalysis(tab);
                }else if(this.getExpr() instanceof ASTLessEqual){
                    this.getExpr().semanticAnalysis(tab);
                }else if(this.getExpr() instanceof ASTNotEqual){
                    this.getExpr().semanticAnalysis(tab);
                }else if(this.getExpr() instanceof ASTOr){
                    this.getExpr().semanticAnalysis(tab);
                }else{
                    throw new Exception("Incompatible type for this variable, expected a float type on line " + this.getLine());
                }
            case "string":
                if(this.getExpr() instanceof ASTString){
                    this.getExpr().semanticAnalysis(tab);
                }else{
                    throw new Exception("Incompatible type for this variable, expected a character on line " + this.getLine());
                }
        }
        if(this.getProx() != null) this.getProx().semanticAnalysis(tab);
    }
    
}
