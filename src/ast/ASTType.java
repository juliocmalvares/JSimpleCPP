/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast;

/**
 *
 * @author juliocmalvares
 */
public class ASTType extends ASTNode {
    private String type;
    
    public ASTType(String type){
        this.type = type;
    }
    public ASTType(){
        this.type = "";
    }
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
}
