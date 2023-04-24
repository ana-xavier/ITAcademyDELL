import java.util.HashMap;
import java.util.Map;

/*
    Autor: Ana Carolina de Oliveira Xavier
    Data: 29/03/2023
    IT Academy DELL

    A classe Itens realiza os mapas dos itens.
 */

public class Itens {
    // --> Utilizando o método Map é possível manter relacionado o item e o peso dos itens da lista.
    private Map<String, Double> itens;

    public Itens(){
        itens = new HashMap<String, Double>();
    }
    // --> Recebe o nome e a peso desse item para a lista desejada do usuário.
    public void addItem(String nome, double peso){
        itens.put(nome, peso);
    }

    // --> Retorna o peso total da lista.
    public double pesoTotal(){
        double pesoTotal = 0;
        // --> Percorre todos os elementos do mapa de itens.
        for(Double pesos: itens.values()){
            // --> Obtem o item correspondente ao peso.
            //     --> Multiplica pela quantidade.
            pesoTotal += pesos;
        }
        // --> Retorna o peso total.
        return pesoTotal;
    }
    // --> Limpa a lista para ser possível que uma nova lista seja criada.
    public void clear(){
        itens.clear();
    }
    // --> Retorna os itens.
    public Map<String, Double> getItens(){
        return itens;
    }

}
