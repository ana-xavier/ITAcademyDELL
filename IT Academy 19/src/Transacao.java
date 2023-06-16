
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/*
Nome: Ana Carolina de Oliveira Xavier
Data: 14/06/2023

Classe responsável por armazenar informações sobre transações.
 */
public class Transacao {
    private double valor;
    private LocalDate data;
    private tTransacao tipo;
    private String categoria;
    private String descricao;

    // --> Criando uma transação.
    public Transacao(double valor, String tData, tTransacao tipo, String categoria, String descricao){
        // --> Validação da data. Recebe como String e formata para data.
        DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        try{
            this.data = LocalDate.parse(tData, formatoData);
        }catch(DateTimeException e){
            System.out.println("Formato de data inválido :(");
        }

        this.valor = valor;
        this.tipo = tipo;
        this.categoria = categoria;
        this.descricao = descricao;
    }
    // --> Getters e setters :)
    public double getValor(){
        return this.valor;
    }
    public void setValor(double v){
        valor = v;
    }
    public LocalDate getData(){
        return data;
    }
    public tTransacao getTipo(){
        return tipo;
    }
    public void setTipo(tTransacao t){
        tipo = t;
    }
    public String getCategoria(){
        return categoria;
    }
    public String getDescricao(){
        return descricao;
    }

    public void setDescricao(String descricao){
        this.descricao = descricao;
    }
}
