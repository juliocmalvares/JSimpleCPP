package symbtab;

import java.util.HashMap;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author juliocmalvares
 */
public class SymbolTab {
    private HashMap<String, Integer> map; //string nome da variavel e object posição de memoria de offset
    private int stack;
    
    public SymbolTab() {
        map = new HashMap<>();
    }
    
    public void set(String id){
        if(map.containsKey(id)){
            map.put(id, map.size());
        } //exemplo para o mips. Para o trabalho é necessário fazer os controles
    }
    
    public Integer get(String nome) throws Exception{
        if(!map.containsKey(nome)){
            throw new Exception("Variável " + nome + "não foi declarada! ");
        }
        return map.get(nome);
    }
    
    public int push(){
        return this.stack++;
    }
    public int pop(){
       return this.stack--; 
    }
}
