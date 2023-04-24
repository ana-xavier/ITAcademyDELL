import java.util.List;
/*
    Autor: Ana Carolina de Oliveira Xavier
    Data: 29/03/2023
    IT Academy DELL

    Classe onde será cadastrados os transportes. Para criação de um novo transporte, é necessário
    informar o peso dos itens, a lista de caminhões e o valor total do transporte.
 */
public class Transporte {
    // --> Variável para identificação do transporte.
    private static int id = 0;
    private Double pesoItens;
    private List<Modalidade> caminhoes;
    private double valorTotal;
    private double valorUnitario;

    // --> Recebe por parametro todas as informações para a criação de um novo transporte.
    public Transporte(double itens, List<Modalidade> caminhoes, double valorTotal, double valorUnitario){
        this.pesoItens = itens;
        this.caminhoes = caminhoes;
        this.valorTotal = valorTotal;
        this.valorUnitario = valorUnitario;
        this.id = id + 1;
    }
    // --> Getters, setters e toString simples.
    public int getId(){
        return id;
    }
    public double getValorTotal(){
        return valorTotal;
    }
    public double getPesoItens(){
        return pesoItens;
    }
    public double getValorUnitario(){
        return valorUnitario;
    }
    public List<Modalidade> getCaminhoes(){
        return caminhoes;
    }

    public String toString(){ return "Valores do transporte: \n N° de transportes " + getId() + "\n --> Caminhão (s) necessários: " + getCaminhoes() + "\n  --> Valor total do transporte: " + getValorTotal() + "\n  --> Valor unitário: " + getValorUnitario();
    }
}
