import java.util.ArrayList;
import java.util.List;

/*
Nome: Ana Carolina de Oliveira Xavier
Data: 14/06/2023

Classe responsável por armazenar as informações da conta do usuário.
 */
public class Conta {
    private String agencia;
    private int numero;
    private double saldo;
    private List<Transacao> transacoes;

    // --> Criando uma conta.
    public Conta(int numero, String agencia){
        this.agencia = agencia;
        this.numero = numero;
        this.saldo = 0;
        transacoes = new ArrayList<>();
    }

    // --> Getters e setters :)
    public int getNumeroConta(){
        return numero;
    }
    public String getAgencia(){
        return agencia;
    }
    public double getSaldo(){
        return saldo;
    }
    public void setSaldo(double s){
        saldo = s;
    }
    public List<Transacao> getTransacoes(){
        return transacoes;
    }
    public void adicionarTransacao(Transacao transacao){
        transacoes.add(transacao);                        // Adiciona na lista de transações da conta.
        saldo += transacao.getValor();                    // Modifica o saldo da conta.
    }

    public void setTransacoes(List<Transacao> nTransacoes){
        transacoes = nTransacoes;
    }

    public String toString(){
        return "Conta: número -> " + getNumeroConta() + ", agência -> " + getAgencia() + ", saldo -> " + getSaldo();
    }
}
