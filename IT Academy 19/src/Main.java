import java.util.Scanner;

/*
Nome: Ana Carolina de Oliveira Xavier
Data: 14/06/2023

Classe Main responsável pela interação com o usuário.
 */
public class Main {
    public static void main(String[] args) {
        // --> Objeto scanner para entrada de dados no sistema.
        Scanner entrada = new Scanner(System.in);

        // --> Instancia objeto para criar o painel de controle do sistema.
        Painel painel = new Painel();

        System.out.println("Gerenciamento de despesas pessoais!");

        // --> Variável auxiliar para o switch case.
        int opcao;
        do{
            opcao = menuPrincipal();
            switch(opcao){
                case 0:
                    System.out.println("Fim do programa :)");
                    break;
                // --> Tópico 1
                case 1:
                    opcao = gerenciarContas();
                    switch(opcao){
                        case 1:
                            painel.cadastrarConta();
                            break;
                        case 2:
                            painel.removerConta();
                            break;
                        case 3:
                            painel.mesclarConta();
                            break;
                    }
                    break;
                // --> Tópico 2
                case 2:
                    opcao = gerenciarTransacoes();
                    switch(opcao){
                        case 1:
                            painel.extratoDaConta();
                            break;
                        case 2:
                            painel.incluirTransacao();
                            break;
                        case 3:
                            painel.editarUltimaTransacao();
                            break;
                        case 4:
                            painel.transferirFundos();
                            break;
                    }
                    break;
                // --> Tópico 3
                case 3:
                    opcao = painelEstatistico();
                    switch(opcao){
                        case 1:
                            painel.resumoContas();
                            break;
                        case 2:
                            painel.resumoReceitasDespesasMes();
                            break;
                        case 3:
                            painel.saldoUltimos6Meses();
                            break;
                        case 4:
                            painel.resumoPorMesTipo();
                            break;
                    }
                    break;
            }
        }while(opcao != 0);
    }

    // --> Menu principal do sistema. Inicia com ele.
    public static int menuPrincipal(){
        Scanner entrada = new Scanner(System.in);
        int opcao;
        do{
            System.out.println("=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
            System.out.println("    Menu de opções! :)                  ");
            System.out.println("   1 - Gerenciar Contas                 ");
            System.out.println("   2 - Gerenciar Transações             ");
            System.out.println("   3 -  Painel Geral                    ");
            System.out.println("   0 -  Sair do Sistema                 ");
            System.out.print("Opção desejada: (número referente a opção): ");
            opcao = entrada.nextInt();
            System.out.println("=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");

            if(opcao < 0 || opcao > 3){
                System.out.println("Opção Inválida");
            }
        }while(opcao < 0 || opcao > 3);
        return opcao;
    }

    // --> Menu contendo as opções do tópico 1.
    public static int gerenciarContas(){
        Scanner entrada = new Scanner(System.in);
        int opcao;
        do{
            System.out.println("----------------------------------------");
            System.out.println(" Menu: Gerenciar Contas :)              ");
            System.out.println("   1 - Cadastrar Conta                  ");
            System.out.println("   2 - Remover Conta                    ");
            System.out.println("   3 - Mesclar Contas                   ");
            System.out.println("   0 - Sair do gerenciamento de contas  ");
            System.out.print("Opção desejada: (número referente a opção): ");
            opcao = entrada.nextInt();
            System.out.println("----------------------------------------");

            if(opcao < 0 || opcao > 3){
                System.out.println("Opção Inválida");
            }
        }while(opcao < 0 || opcao > 3);
        if(opcao == 0){
            return -1;
        }else{
            return opcao;
        }
    }
    // --> Menu contendo as opções do tópico 2.
    public static int gerenciarTransacoes(){
        Scanner entrada = new Scanner(System.in);
        int opcao;
        do{
            System.out.println("--------------------------------------------");
            System.out.println(" Menu: Gerenciar Transações :)              ");
            System.out.println("   1 - Extrato da Conta                     ");
            System.out.println("   2 - Incluir Transação                    ");
            System.out.println("   3 - Editar última transação              ");
            System.out.println("   4 - Transferir fundos                    ");
            System.out.println("   0 - Sair do gerenciamento de transações  ");
            System.out.print("Opção desejada: (número referente a opção):   ");
            opcao = entrada.nextInt();
            System.out.println("--------------------------------------------");

            if(opcao < 0 || opcao > 4){
                System.out.println("Opção Inválida");
            }
        }while(opcao < 0 || opcao > 4);
        if(opcao == 0){
            return -1;
        }else{
            return opcao;
        }
    }
    // --> Menu contendo as opções do tópico 3.
    public static int painelEstatistico(){
        Scanner entrada = new Scanner(System.in);
        int opcao;
        do{
            System.out.println("--------------------------------------------");
            System.out.println(" Menu: Painel de Estatísticas :)            ");
            System.out.println("   1 - Resumo das contas                    ");
            System.out.println("   2 - Resumo de receitas e despesas do mês ");
            System.out.println("   3 - Saldo geral dos últimos 6 meses      ");
            System.out.println("   4 - Resumo por mês e tipo                ");
            System.out.println("   0 - Sair do painel                       ");
            System.out.print("Opção desejada: (número referente a opção):   ");
            opcao = entrada.nextInt();
            System.out.println("--------------------------------------------");

            if(opcao < 0 || opcao > 4){
                System.out.println("Opção Inválida");
            }
        }while(opcao < 0 || opcao > 4);
        if(opcao == 0){
            return -1;
        }else{
            return opcao;
        }
    }
}