/*
    Autor: Ana Carolina de Oliveira Xavier
    Data: 29/03/2023
    IT Academy DELL

    Classe "Enum" de criação de modalidades. A cada modalidade criada é necesário informar o nome da modalidade,
    o preço por quilometro e a capacidade em quilos do caminhão.
 */
public enum Modalidade {
    PEQUENO(1,"Pequeno", 1000, 4.87),
    MEDIO(2, "Medio", 4000, 11.92),
    GRANDE(3, "Grande", 10000, 27.44);
    // --> Variáveis constantes.
    private final String nome;
    private final int capacidade;
    private final double precoKm;
    private int index;

    // --> Capacidade é informada em quilos.
    //     --> exem: 1 tonelada = 1000.
    private Modalidade(int index, String nome, int capacidade, double precoKm){
        this.index = index;
        this.nome = nome;
        this.capacidade = capacidade;
        this.precoKm = precoKm;
    }

    // --> Getters, setters e toString simples.
    public int getIndex(){
        return index;
    }
    public String getNome(){
        return nome;
    }
    public double getCapacidade(){
        return capacidade;
    }

    public double getPrecoKm(){
        return precoKm;
    }

    public String toString(){
        return "Modalidade: " + getNome() + " - Preço por km: R$" + getPrecoKm();
    }
}
