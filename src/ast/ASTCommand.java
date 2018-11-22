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
public abstract class ASTCommand extends ASTNode{
    private ASTCommand prox;

    public ASTCommand getProx() {
        return prox;
    }

    public void setProx(ASTCommand prox) throws Exception{
        this.prox = prox;
    }
  
}
