import java.util.ArrayList;
import java.util.List;

/*
    Autor: Ana Carolina de Oliveira Xavier
    Data: 29/03/2023
    IT Academy DELL

    Classe de criação de cidades. Sempre quando a classe for instancializada será criada uma nova
    cidade. Para toda cidade criada é necessário informar seu nome e as distancias entre as demais
    cidades em referencia a ela.

    São 24 cidades ao total. No vetor, estão na ordem dos indices do array (do 0 ao 23).
        exem: Se Araujo é a primeira cidade, então ela estará no indice 0.
        exem: Se Campo Grande é a quinta cidade, então ela estará no indice 4.
 */
public class Cidade {
    private String nome;
    // --> Lista de distancias referente a cidade.
    private List<String> distancias = new ArrayList<>();
    private int index;

    // --> Método construtor. Necessario que seja passado por parametro o nome da cidade e as distancias.
    public Cidade(String nome, String... distancias) {
        this.index = 1;
        this.nome = nome;
        // --> Adiciona todas as distancias passadas por parametro na lista de distancias.
        for (String distancia : distancias) {
            this.distancias.add(distancia);
        }
        index++;
    }
    // --> Retorna a distancia a partir do index informado.
    public String getDistanciaX(int index){
        return distancias.get(index);
    }

    // --> Getters, setters e toString simples.
    public int getIndex(){
        return index;
    }
    public String getNome() {
        return nome;
    }
    // --> Retorna a lista de distancias.
    public List<String> getDistancias() {
        return distancias;
    }
    // -->
    @Override
    public String toString() {
        // --> Fazer uma string com as informações importantes.
        StringBuilder sb = new StringBuilder();
        // --> Pega o nome da cidade.
        sb.append("Cidade: ").append(nome).append("\n");
        // --> Percorre o array e concatena a distancia da cidade com a sua posição na lista (1-24).
        for (int i = 0; i < distancias.size(); i++) {
            sb.append("Distância até cidade ").append(i + 1).append(": ").append(distancias.get(i)).append(" km\n");
        }
        // --> Retorna a string final.
        return sb.toString();
    }
}
