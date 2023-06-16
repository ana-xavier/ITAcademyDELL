/*
Nome: Ana Carolina de Oliveira Xavier
Data: 14/06/2023

Classe responsável pelo controle do painel do sistema.
--> A classe recebe as escolhas do usuário, e controla quais métodos serao usados.
 */
public class Painel {
    private ContaManager contaManager;
    public Painel(){
        contaManager = new ContaManager();
    }

    // --> Chamadas dos métodos :)
    public void cadastrarConta(){
        contaManager.cadastrarConta();
    }

    public void removerConta(){
        contaManager.removerConta();
    }

    public void mesclarConta(){
        contaManager.mesclarConta();
    }

    public void incluirTransacao(){
        contaManager.incluirTransacao();
    }

    public void editarUltimaTransacao(){
        contaManager.editarUltimaTransacao();
    }

    public void transferirFundos(){
        contaManager.transferirFundos();
    }
    public void extratoDaConta(){
        contaManager.extratoDaConta();
    }

    public void resumoContas(){
        contaManager.resumoContas();
    }
    public void resumoReceitasDespesasMes(){
        contaManager.resumoReceitasDespesasMes();
    }
    public void saldoUltimos6Meses(){
        contaManager.saldoUltimos6Meses();
    }
    public void resumoPorMesTipo(){
        contaManager.resumoPorMesTipo();
    }
}
