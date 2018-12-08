package symbtab;
import java.util.HashMap;

public class SymbolTab {
    private HashMap<String, Integer> map; //string nome da variavel e object posição de memoria de offset
    private int stack;
    
    public SymbolTab() {
        this.map = new HashMap<>();
    }
    
    public void set(String id){
        if(this.map.containsKey(id)){
            this.map.put(id, map.size());
        } //exemplo para o mips. Para o trabalho é necessário fazer os controles
    }
    
    public Integer get(String nome) throws Exception{
        if(!this.map.containsKey(nome)){
            throw new Exception("Variável " + nome + "não foi declarada! ");
        }
        return this.map.get(nome);
    }
    
    public int push(){
        return this.stack++;
    }
    public int pop(){
       return this.stack--; 
    }
}
