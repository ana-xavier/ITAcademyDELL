import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
/*
    Autor: Ana Carolina de Oliveira Xavier
    Data: 29/03/2023
    IT Academy DELL

    Classe principal (Main). O contato principal com o usuário ocorre a partir desta classe.
    Os menus estão nessa classe.
 */
public class Main {
    public static void main(String[] args) {
        // --> Objeto scanner para entrada de dados no sistema.
        Scanner entrada = new Scanner(System.in);

        // --> Instancia objeto para criar transportes.
        Agencia agencia = new Agencia();

        System.out.println("Bem vindo ao sistema de transporte interestadual de cargas!");

        // -->  Variável de auxilio para o switch case.
        int opcao;
        do{
            // --> Variável recebe o retorno do método "menuPrincipal()".
            opcao = menuPrincipal();
            switch(opcao){
                case 0:
                    // --> Sempre que o usuário estiver no menu principal, ele poderá encerrar o programa.
                    System.out.println("Fim do programa :)");
                    break;
                // --> Case 1: "Consultar trechos x modalidade".
                case 1:
                    // --> Titulo
                    System.out.println("Consulta de trechos e modalidades");
                    // --> Imprime as modalidades de transporte disponíveis.
                    System.out.print("  --> Modalidades de transporte disponíveis: ");
                    agencia.imprimeModalidades();
                    // -->> Imprime os nomes das cidades disponíveis.
                    imprimeNomesCidades();

                    // --> Contato com o usuário
                    System.out.println("    > Digite os dados do transporte: ");
                    System.out.println("    * Indique o número referente a cidade e a modalidade: ");
                    System.out.print("      Cidade de saída: ");
                    int saida = entrada.nextInt();
                    // --> Os próximos três "if"s são para verificação se a cidade ou modalidade escolhida existe.
                    if(saida < 0 || saida > 25){
                        System.out.println("Cidade inválida");
                        break;
                    }
                    System.out.print("      Cidade de destino: ");
                    int destino = entrada.nextInt();
                    if(destino < 0 || destino > 25){
                        System.out.println("Cidade inválida");
                        break;
                    }
                    System.out.print("      Modalidade: ");
                    int modalidade = entrada.nextInt();
                    if(modalidade < 0 || modalidade > 4){
                        System.out.println("Modalidade inválida");
                        break;
                    }
                    System.out.println();
                    // --> Chama o método "calcularTrecho" passando por parametros as informações oferecidas pelo usuário.
                    agencia.calcularTrecho(saida, destino, modalidade);

                    // --> Limpa a entrada.
                    entrada.nextLine();
                    break;
                // --> Case 2: "Cadastrar transporte".
                case 2:
                    // --> Contato com o usuário
                    System.out.println("Cadastrar transporte");
                    System.out.println("Cidades disponíveis: ");
                    // -->> Imprime os nomes das cidades disponíveis.
                    imprimeNomesCidades();
                    System.out.println();
                    System.out.println("Informe as cidades para o transporte: ");
                    System.out.println("* A sequencia de cidades deve ser separadas por vírgula, SEM espaço entre as vírgulas e escritas como foi apresentado (usar letra maiúscula).");
                    System.out.println("* Necessário informar ao menos DUAS cidades");
                    // --> Armazena as cidades
                    String cidades = entrada.nextLine();
                    // --> Separa as cidades informadas por vírgula
                    List<String> cidadesInformadas = Arrays.asList(cidades.split(","));
                    // --> Armazena a distancia total (calculada através do método "calculaDistanciaTotal) a partir das cidades informadas.
                    int distanciaTotal = agencia.calculaDistanciaTotal(cidadesInformadas);
                    // --> Verifica se a distancia é válida.
                    if(distanciaTotal != 0 && distanciaTotal != -1){
                        System.out.println("Distância total: " + distanciaTotal);
                    }else{
                        // --> Caso a distancia não seja valida.
                        System.out.println("Parece que houve um problema na informação das cidades. Tente novamente :)");
                        break;
                    }
                    // --> Imprime exemplos de itens ao usuário.
                    imprimeItens();
                    System.out.println();
                    // --> Contato para o usuário informar os itens desejados.
                    System.out.println("Informe os itens de transporte: ");
                    // --> Chama o método que realiza a lista de itens.
                    agencia.listaDeItens();
                    // --> Chama o método que informa o caminhão adequada.
                    //     --> Caminhão que tenha capacidade de levar os itens.
                    agencia.caminhaoAdequado(cidadesInformadas);
                    break;
                // --> Case 3: "Dados estatísticos".
                case 3:
                    System.out.println("Dados estatísticos");
                    // --> Chama o método que informa as estatisticas.
                    agencia.dadosEstatisticos();
                    break;
                default:
                    System.out.println("  ");
                    break;
            }
        }while(opcao != 0);
        // --> O programa rodará enquanto a opção for diferente de 0.
        //     --> Caso a opção seja 0, o programa encerrará.
    }

    // --> Menu principal, contem as opções iniciais do programa.
    public static int menuPrincipal(){
        // --> Objeto para ler a entrada do usuário.
        Scanner entrada = new Scanner(System.in);
        int opcao;
        // --> Menu organizado. Cada número representa uma opção.
        //     --> O usuário deve informar o número referente a opção que deseja realizar.
        do{
            System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
            System.out.println("         Menu de opções!                           ");
            System.out.println("   1 --> Consultar trechos x modalidade            ");
            System.out.println("   2 --> Cadastrar transporte                      ");
            System.out.println("   3 --> Dados estatísticos                        ");
            System.out.println("   0 --> Sair do programa                          ");
            System.out.print("Opção desejada (informe o número referente a opção): ");
            opcao = entrada.nextInt();
            System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");

            // --> Caso o usuário informe um número indisponivel, é informado que a opção é inválida.
            if(opcao < 0 || opcao > 3){
                System.out.println("Opção inválida.");
            }
        }while(opcao < 0 || opcao > 3);

        // --> Retorna a opção que o usuário deseja.
        return opcao;
    }

    public static void imprimeNomesCidades(){
        System.out.println("Cidades com trechos de destino e saída: ");
        System.out.println(" 1 - ARACAJU,         2 - BELEM,         3 - BELO HORIZONTE");
        System.out.println(" 4 - BRASILIA,        5 - CAMPO GRANDE,  6 - CUIABA");
        System.out.println(" 7 - CURITIBA,        8 - FLORIANOPOLIS, 9 - FORTALEZA,");
        System.out.println("10 - GOIANA,         11 - JOAO PESSOA,  12 - MACEIO,");
        System.out.println("13 - MANAUS,         14 - NATAL,        15 - PORTO ALEGRE");
        System.out.println("16 - PORTO VELHO,    17 - RECIFE,       18 - RIO BRANCO");
        System.out.println("19 - RIO DE JANEIRO, 20 - SALVADOR,     21 - SAO LUIS");
        System.out.println("22 - SAO PAULO,      23 - TERESINA,     24 - VITORIA");
    }

    public static void imprimeItens(){
        System.out.println("Exemplo de itens para transporte: ");
        System.out.println("CELULAR -> peso: 0,5");
        System.out.println("GELADEIRA -> peso: 60,0");
        System.out.println("FREEZER -> peso: 100,0");
        System.out.println("CADEIRA -> peso: 5,0");
        System.out.println("LUMINARIA -> peso: 0,8");
        System.out.println("LAVADORA DE ROUPA -> peso: 120,0");
        System.out.println("*Informe o nome do item e peso como os exemplos acima ^");
    }
}