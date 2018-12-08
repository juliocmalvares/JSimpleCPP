/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast.expr.atrib;

import ast.ASTCommand;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import symbtab.SymbolTab;

/**
 *
 * @author juliocmalvares
 */
public class ASTListVariables extends ASTCommand{
    private List<ASTDecl> list;

    public ASTListVariables() {
        this.list = new ArrayList<>();
    }
    
    public void setProx(ASTDecl decl){
        this.list.add(decl);
    }

    public List<ASTDecl> getList() {
        return list;
    }

    public void setList(List<ASTDecl> list) {
        this.list = list;
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
