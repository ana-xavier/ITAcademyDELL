
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;


/*
Nome: Ana Carolina de Oliveira Xavier
Data: 14/06/2023

Classe responsável por gerenciar as contas do usuário e suas transações.
 */
public class ContaManager {
    private List<Conta> continhas;     //Armazena as contas criadas!

    public ContaManager() {
        continhas = new ArrayList<>();
        iniciandoContas();             // Inicia as contas (2) e as transações (10 em cada)
    }

    //=============================================================================================
    // 1. [Gerenciar Contas]
    public void cadastrarConta() {
        Scanner entrada = new Scanner(System.in);

        // --> Solicita as informações necessárias para o cadastro da conta.
        System.out.println(" == Menu: Cadastrar conta :)");
        System.out.print("Digite o número da conta: ");
        int nConta = entrada.nextInt();
        entrada.nextLine();

        // --> Verifica se a conta já existe.
        for (Conta conta : continhas) {
            if (conta.getNumeroConta() == nConta) {
                System.out.println("Essa conta já existe, seu cadastro será cancelado :(");
                return;
            }
        }

        System.out.print("Digite a agência da conta: ");
        String aConta = entrada.nextLine();

        // --> Cria nova conta e armazena na lista.
        Conta continha = new Conta(nConta, aConta);
        continhas.add(continha);

        System.out.println("O cadastro da conta foi realizado com sucesso :)");
        System.out.println(" == Informações da conta: " + continha);
    }

    public void removerConta() {
        Scanner entrada = new Scanner(System.in);
        // --> Solicita as informações necessárias para remover conta.
        System.out.println(" == Menu: Remover conta :)");
        System.out.print("Digite o número da conta a ser removida: ");
        int nConta = entrada.nextInt();
        entrada.nextLine();
        Conta cEncontrada = null;      // --> Encontra a conta.
        for (Conta conta : continhas) {
            if (conta.getNumeroConta() == nConta) {
                cEncontrada = conta;
                break;
            }
        }
        // --> Se encontrar a conta.
        if (cEncontrada != null) {
            System.out.println(" == ALERTA: Todos os dados da conta " + cEncontrada.getNumeroConta() + " serão apagados");
            System.out.print("    == Deseja continuar (S ou N)? ");
            String opcao = entrada.nextLine().toUpperCase();
            // --> Realiza a operação de acordo com a opção do usuário.
            if (opcao.equals("S")) {
                continhas.remove(cEncontrada);
                System.out.println("A conta " + cEncontrada.getNumeroConta() + " foi removida com sucesso :)");
            } else if (opcao.equals("N")) {
                System.out.println("A operação foi cancelada!");
            }
            // --> Se não encontrar a conta.
        } else {
            System.out.println("A conta " + cEncontrada.getNumeroConta() + " não foi encontrada. Operação cancelada ):");
        }
    }

    public void mesclarConta() {
        Scanner entrada = new Scanner(System.in);
        Conta conta1 = null;
        Conta conta2 = null;

        // --> Solicita as informações necessárias para mesclar contas.
        System.out.println(" == Menu: Mesclar contas :)");
        System.out.println("ALERTA: A PRIMEIRA conta será mantida! ");

        System.out.print("Digite o número da primeira conta: ");
        int nConta1 = entrada.nextInt();
        entrada.nextLine();

        System.out.print("Digite o número da segunda conta: ");
        int nConta2 = entrada.nextInt();
        entrada.nextLine();

        // --> Encontra as contas.
        for (Conta conta : continhas) {
            if (conta.getNumeroConta() == nConta1) {
                conta1 = conta;
            } else if (conta.getNumeroConta() == nConta2) {
                conta2 = conta;
            }
        }

        // --> Se encontrar as contas.
        if (conta1 != null && conta2 != null) {
            // --> Realiza uma lista com as transações da conta 1.
            List<Transacao> transacoes = new ArrayList<>(conta1.getTransacoes());
            // --> Adiciona todas as transações da conta 2.
            transacoes.addAll(conta2.getTransacoes());

            // --> Filtra todas as transações e as ordena por data.
            transacoes = transacoes.stream()
                    .filter(transacao -> transacao.getData() != null)
                    .collect(Collectors.toList());
            Collections.sort(transacoes, Comparator.comparing(Transacao::getData));

            // --> Seta as transações da conta 1 com todas as transações filtradas e mescladas.
            conta1.setTransacoes(transacoes);
            // --> Altera o saldo da conta 1.
            conta1.setSaldo(conta1.getSaldo() + conta2.getSaldo());
            // --> Remove a conta 2.
            continhas.remove(conta2);

            System.out.println("As contas foram mescladas com sucesso :)");
            // --> Se não encontrar as contas.
        }else{
            System.out.println("Não foi possível encontrar uma, ou ambas, contas. Operação cancelada ):");
        }
    }
    //=============================================================================================
    // 2. [Gerenciar Transações]

    // --> Método auxiliar. Selecionar conta desejada.
    public Conta contaSelecionada() {
        Scanner entrada = new Scanner(System.in);

        System.out.println(" == Menu: Gerenciar transações :)");
        System.out.print("Digite o número da conta que deseja: ");
        int nConta = entrada.nextInt();
        entrada.nextLine();

        // --> Encontra conta.
        Conta cEncontrada = null;
        for (Conta conta : continhas) {
            if (conta.getNumeroConta() == nConta) {
                cEncontrada = conta;
                break;
            }
        }
        return cEncontrada;
    }

    public void incluirTransacao() {
        Scanner entrada = new Scanner(System.in);
        Conta cEncontrada = contaSelecionada();
        System.out.println(" == Menu: Incluir Transação :)");
        // --> Se conta for encontrada.
        if (cEncontrada != null) {
            // --> Solicita as informação da transação.
            System.out.print(" = Data da transação (dd/mm/aaaa): ");
            String data = entrada.nextLine();

            System.out.print(" = Valor da transação: ");
            double valor = entrada.nextDouble();
            entrada.nextLine();

            System.out.print(" = Tipo de transação (receita ou despesa): ");
            String tipoUser = entrada.nextLine().toUpperCase();

            // --> Verifica o tipo da transação.
            tTransacao tipo;
            if (tipoUser.equals("RECEITA")) {
                tipo = tTransacao.RECEITA;
            } else if (tipoUser.equals("DESPESA")) {
                tipo = tTransacao.DESPESA;
            } else {
                System.out.println("Tipo de transação inválida. A operaçao será cancelada :(");
                return;
            }

            System.out.print(" = Categoria da transação: ");
            String cat = entrada.nextLine();

            System.out.print(" = Descrição da transação: ");
            String desc = entrada.nextLine();

            // --> Caso a transação for uma despesa, o valor será subtraído do saldo da conta.
            if (tipo == tTransacao.DESPESA) {
                Transacao transacao = new Transacao(-valor, data, tipo, cat, desc);
                cEncontrada.adicionarTransacao(transacao);
            // --> Senão, o valor será somado ao saldo da conta.
            } else {
                Transacao transacao = new Transacao(valor, data, tipo, cat, desc);
                cEncontrada.adicionarTransacao(transacao);
            }

            System.out.printf("A transação para a conta %s no valor de R$%.2f foi realizada! Novo saldo da conta: R$%.2f%n",
                    cEncontrada.getNumeroConta(), valor, cEncontrada.getSaldo());
        // --> Se conta não for encontrada, cancela operação.
        } else {
            System.out.println("Não foi possível encontrar a conta mencionada. Operação cancelada :(");
        }
    }

    public void editarUltimaTransacao() {
        Scanner entrada = new Scanner(System.in);

        Conta cEncontrada = contaSelecionada();

        System.out.println(" == Menu: Editar última transação :)");

        // --> Se a conta for encontrada.
        if (cEncontrada != null) {
            // --> Cria uma lista de transações com as tranações da conta.
            List<Transacao> transacoes = cEncontrada.getTransacoes();
            // --> Se haver transações na lista
            if (!transacoes.isEmpty()) {
                Transacao uTransacao = transacoes.get(transacoes.size() - 1);   //Identifica a última transação
                double vAntigo = uTransacao.getValor();                         //Identifica o seu valor

                // --> Caso a última transação for uma despesa
                if (uTransacao.getTipo() == tTransacao.DESPESA) {
                    cEncontrada.setSaldo(cEncontrada.getSaldo() + vAntigo);     //Soma o valor subtraído anteriormente
                } else if (uTransacao.getTipo() == tTransacao.RECEITA) {
                    cEncontrada.setSaldo(cEncontrada.getSaldo() - vAntigo);     //Subtraí o valor somado anteriormente
                }

                System.out.printf("Valor e tipo da última transação: R$%.2f, %s\n", uTransacao.getValor(), uTransacao.getTipo());
                System.out.print("Novo tipo de transação (receita ou despesa): ");
                String nTipo = entrada.nextLine().toUpperCase();
                // --> Identifica o novo tipo de transação.
                tTransacao tipo;
                if (nTipo.equals("RECEITA")) {
                    tipo = tTransacao.RECEITA;
                } else if (nTipo.equals("DESPESA")) {
                    tipo = tTransacao.DESPESA;
                }else {
                    System.out.println("Tipo de transação inválida. A operaçao será cancelada :(");
                    return;
                }
                uTransacao.setTipo(tipo);
                System.out.print("Novo valor da transação: ");
                double valor = entrada.nextDouble();
                entrada.nextLine();

                // --> Calcula a diferença dos valores.
                double dValorT = valor - vAntigo;
                cEncontrada.setSaldo(cEncontrada.getSaldo() + dValorT);  //Modifica o saldo da conta a partir da diferença dos valores.
                uTransacao.setValor(valor);                              //Modifica o valor da última transação.

                System.out.print("Nova descrição da transação: ");
                String descricao = entrada.nextLine();
                uTransacao.setDescricao(descricao);
                System.out.printf("A edição da útima transação da conta %s no valor de R$%.2f foi realizada! Novo saldo da conta: R$%.2f%n",
                        cEncontrada.getNumeroConta(), valor, cEncontrada.getSaldo());
            }
        // --> Se conta não for encontrada, cancela operação.
        } else {
            System.out.println("Não foi possível encontrar a conta mencionada. Operação cancelada :(");
        }
    }

    public void transferirFundos() {
        Scanner entrada = new Scanner(System.in);
        Conta oConta = null;
        Conta dConta = null;

        // --> Solicita as informações necessárias para tranferir fundos.
        System.out.println(" == Menu: Transferir fundos :)");

        System.out.print("Digite o número da primeira conta: ");
        int nContaO = entrada.nextInt();
        entrada.nextLine();

        System.out.print("Digite o número da segunda conta: ");
        int nContaD = entrada.nextInt();
        entrada.nextLine();

        // --> Encontra ambas as contas.
        for (Conta conta : continhas) {
            if (conta.getNumeroConta() == nContaO) {
                oConta = conta;
            } else if (conta.getNumeroConta() == nContaD) {
                dConta = conta;
            }
            if (oConta == null && dConta == null) {
                System.out.println(" Não foi possível encontrar uma, ou ambas, conta :(");
                break;
            }
        }

        // --> Se encontrar as contas.
        if (oConta != null && dConta != null) {
            System.out.print(" = Valor a ser transferido: ");
            double valor = entrada.nextDouble();

            // --> Se o saldo da conta de origem for maior ou igual ao valor desejado para trasferir, continua.
            if (oConta.getSaldo() >= valor) {
                // --> Formatação de data/pega a data atual para criar a transação.
                LocalDate dAtual = LocalDate.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                String dataAtual = dAtual.format(formatter);

                // --> Cria uma transação de saque (para a conta origem) e uma transação deposito (para conta destino)
                Transacao saque = new Transacao(-valor, dataAtual, tTransacao.DESPESA, "Transferencia", "Transferência para conta " + dConta.getNumeroConta());
                Transacao deposito = new Transacao(valor, dataAtual, tTransacao.RECEITA, "Transferencia", "Transferência da conta " + oConta.getNumeroConta());

                // --> Adiciona ambas transações.
                oConta.adicionarTransacao(saque);
                dConta.adicionarTransacao(deposito);

                System.out.printf("A transferencia da conta %s para a conta %s foi realizada :)" +
                        "\n Novo saldo da conta %s: R$%.2f \n Novo saldo da conta %s: R$%.2f\n", oConta.getNumeroConta(), dConta.getNumeroConta(),
                        oConta.getNumeroConta(), oConta.getSaldo(), dConta.getNumeroConta(), dConta.getSaldo());
            } else {
                System.out.println("Saldo insuficiente para realizar a transferencia. Operação cancelada :(");
            }
        }
    }

    public void extratoDaConta() {
        Conta cEncontrada = contaSelecionada();

        // --> Extrato bonitinho :)
        System.out.println(" == Menu: Extrato da Conta :)");

        if (cEncontrada != null) {
            List<Transacao> transacaos = cEncontrada.getTransacoes();
            Collections.sort(transacaos, Comparator.comparing(Transacao::getData));
            System.out.println("------------------------------------------------------------------------------");
            System.out.println(" Extrato da conta: " + cEncontrada.getNumeroConta());
            System.out.println("------------------------------------------------------------------------------");
            System.out.println("  Data     |  Tipo     | Categoria   |  Descrição             |  Valor  ");
            System.out.println("------------------------------------------------------------------------------");
            for (Transacao t : transacaos) {
                System.out.printf("%s | %-9s |  %-10s | %-21s  | %.2f\n", t.getData(), t.getTipo(), t.getCategoria(), t.getDescricao(), t.getValor());
            }
            System.out.println("------------------------------------------------------------------------------");
            System.out.printf(" Saldo total: R$ %.2f\n", cEncontrada.getSaldo());
            System.out.println("------------------------------------------------------------------------------");

        } else {
            System.out.println("A conta não foi encontrada. Operação cancelada :(");
        }
    }

    //=============================================================================================
    // 3. [Painel Geral]
    public void resumoContas() {
        System.out.println(" == Menu: Resumo das contas :)");
        double saldoT = 0;

        // --> Imprime o número e saldo de todas as contas.
        for (Conta conta : continhas) {
            System.out.println("---------------------------------");
            System.out.println("Número da conta: " + conta.getNumeroConta());
            System.out.printf("Saldo: R$%.2f\n", conta.getSaldo());
            System.out.println("---------------------------------");

            saldoT += conta.getSaldo();
        }
        // --> Informa o saldo total das contas.
        System.out.printf("Saldo total das contas: R$%.2f\n", saldoT);
        System.out.println("---------------------------------");
    }

    // --> Funcionalidade adicional.
    //     --> Solicita um mês e qual tipo de transação que o usuário deseja visualizar.
    public void resumoPorMesTipo() {
        Scanner entrada = new Scanner(System.in);
        Conta cEncontrada = contaSelecionada();

        System.out.println(" == Menu: Resumo por mês e tipo :)");

        // --> Se encontrar conta.
        if (cEncontrada != null) {
            System.out.print(" = Digite o mês (mm/aaaa): ");
            String mesAno = entrada.nextLine();

            System.out.print(" = Deseja visualizar somente as receitas ou as despesas? (receitas/despesas/todas): ");
            String tExtrato = entrada.nextLine().toUpperCase();

            // --> Cria uma lista de transações.
            List<Transacao> transacaos = new ArrayList<>();
            DateTimeFormatter f = DateTimeFormatter.ofPattern("MM/yyyy"); //Formatação de data
            // --> Para cada transação da lista de transações da conta selecionada.
            for (Transacao t : cEncontrada.getTransacoes()) {
                LocalDate dTransacao = t.getData();           //Identifica a data da trnsação.
                String maTransacao = dTransacao.format(f);    //Seleciona o mes e ano da transação.

                // --> Identificando o mes/ano, então adiciona a transação na lista a partir da escolha do usuário.
                if (maTransacao.equals(mesAno)) {
                    if (tExtrato.equals("RECEITAS") && t.getTipo() == tTransacao.RECEITA) {
                        transacaos.add(t);
                    } else if (tExtrato.equals("DESPESAS") && t.getTipo() == tTransacao.DESPESA) {
                        transacaos.add(t);
                    } else if (tExtrato.equals("TODAS")) {
                        transacaos.add(t);
                    }
                }
            }
            if (transacaos.isEmpty()) {
                System.out.println("Não há transações nesse mês e tipo!");
            } else {
                System.out.println("------------------------------------------------------------------------------");
                System.out.println(" Conta: " + cEncontrada.getNumeroConta());
                System.out.println("------------------------------------------------------------------------------");
                System.out.println("  Data      |  Tipo     | Categoria    |  Descrição                 |  Valor  ");
                System.out.println("------------------------------------------------------------------------------");
                for (Transacao t : transacaos) {
                    System.out.printf("%s | %-9s |  %-10s | %-21s  | %.2f\n", t.getData(), t.getTipo(), t.getCategoria(), t.getDescricao(), t.getValor());
                }
            }
        } else {
            System.out.println("A conta não foi encontrada. Operação cancelada :(");
        }

    }

    // --> Método auxiliar. Identificar as transações a partir de uma conta e mês selecionado.
    private List<Transacao> getTransacoes(Conta c, int m) {
        List<Transacao> tMes = new ArrayList<>();
        for (Transacao t : c.getTransacoes()) {
            LocalDate dTransacao = t.getData();
            if (dTransacao.getMonthValue() == m) {
                tMes.add(t);
            }
        }
        return tMes;
    }

    public void resumoReceitasDespesasMes() {
        System.out.println(" == Menu: Resumo de receitas e despesas do mês :)");
        double tReceitas = 0;  //Total receita.
        double tDespesas = 0;  //Total despesa.
        int mesAtual = LocalDate.now().getMonthValue();   //Identifica o mês atual.

        // --> Para cada conta na lista de contas
        for (Conta conta : continhas) {
            List<Transacao> tMesAtual = getTransacoes(conta, mesAtual); //Identificamos a lista de transações do mês.
            // --> Para cada transação do mês
            for (Transacao t : tMesAtual) {
                if (t.getTipo() == tTransacao.RECEITA) {
                    tReceitas += t.getValor();   //Soma ao total de receitas.
                } else if (t.getTipo() == tTransacao.DESPESA) {
                    tDespesas += t.getValor();   //Soma ao total de despesas.
                }
            }
        }
        System.out.println(" = Mês: " + mesAtual);
        System.out.printf(" = Total de receitas do mês: R$%.2f\n", tReceitas);
        System.out.printf(" = Total de despesas do mês: R$%.2f\n", tDespesas);
    }

    public void saldoUltimos6Meses() {
        System.out.println(" == Menu: Saldo geral dos últimos 6 meses :)");
        LocalDate dAtual = LocalDate.now();  //Identifica a data atual.

        for (int i = 0; i < 6; i++) {  // De 0 a 5 (6 meses).
            double saldo = 0;
            LocalDate mAtual = dAtual.minusMonths(i); //Identifica o mês a partir do índice
            int ano = mAtual.getYear();               //Identifica o ano
            int mes = mAtual.getMonthValue();         //Identifica o mês de fato

            // --> A cada conta, identifica as transações do mês e para cada transação, soma na variável.
            for (Conta conta : continhas) {
                List<Transacao> tMesAtual = getTransacoes(conta, mes);
                for (Transacao t : tMesAtual) {
                    System.out.printf(" = Conta: %s > R$: %.2f\n", conta.getNumeroConta(), t.getValor());
                    saldo += t.getValor();
                }
            }
            System.out.println("^ Mês: " + mes + "/" + ano);
            System.out.printf("Saldo: R$%.2f\n\n", saldo);
        }
    }

    //=============================================================================================
    // [Iniciando contas e transações
    public void iniciandoContas() {
        Conta continha1 = new Conta(1234, "Itau");
        continhas.add(continha1);
        Conta continha2 = new Conta(5678, "Bradesco");
        continhas.add(continha2);

        Transacao transacao1 = new Transacao(2700.0, "02/12/2022", tTransacao.RECEITA, "Salário", "Pagamento ref. Nov/23");
        continha1.adicionarTransacao(transacao1);
        Transacao transacao2 = new Transacao(-34.98, "05/06/2023", tTransacao.DESPESA, "Lazer", "Cinema");
        continha1.adicionarTransacao(transacao2);
        Transacao transacao3 = new Transacao(-9.76, "16/02/2023", tTransacao.DESPESA, "Lanche", "Alimentação");
        continha1.adicionarTransacao(transacao3);
        Transacao transacao4 = new Transacao(2700.0, "10/01/2023", tTransacao.RECEITA, "Salário", "Pagamento ref. Dez/23");
        continha1.adicionarTransacao(transacao4);
        Transacao transacao5 = new Transacao(-43.90, "31/03/2023", tTransacao.DESPESA, "Lanche", "Alimentação");
        continha1.adicionarTransacao(transacao5);
        Transacao transacao6 = new Transacao(-679.90, "29/05/2023", tTransacao.DESPESA, "Mercado", "Alimentação");
        continha1.adicionarTransacao(transacao6);
        Transacao transacao7 = new Transacao(-67.90, "18/12/2022", tTransacao.DESPESA, "Assinatura", "Taxa de assinatura");
        continha1.adicionarTransacao(transacao7);
        Transacao transacao8 = new Transacao(-10.70, "08/03/2023", tTransacao.DESPESA, "Lanche", "Alimentação");
        continha1.adicionarTransacao(transacao8);
        Transacao transacao9 = new Transacao(2700.0, "10/03/2023", tTransacao.RECEITA, "Salário", "Pagamento ref. Fev/23");
        continha1.adicionarTransacao(transacao9);
        Transacao transacao10 = new Transacao(-70.89, "28/04/2023", tTransacao.DESPESA, "Contas", "Água");
        continha1.adicionarTransacao(transacao10);

        Transacao transacao11 = new Transacao(-67.98, "02/12/2022", tTransacao.DESPESA, "Casa", "Materias de construção");
        continha2.adicionarTransacao(transacao11);
        Transacao transacao12 = new Transacao(3100.0, "10/12/2022", tTransacao.RECEITA, "Salário", "Pagamento ref. Nov/22");
        continha2.adicionarTransacao(transacao12);
        Transacao transacao13 = new Transacao(-798.90, "18/01/2023", tTransacao.DESPESA, "Mercado", "Alimentação");
        continha2.adicionarTransacao(transacao13);
        Transacao transacao14 = new Transacao(-189.89, "21/03/2023", tTransacao.DESPESA, "Casa", "Materias de construção");
        continha2.adicionarTransacao(transacao14);
        Transacao transacao15 = new Transacao(3100.0, "10/02/2023", tTransacao.RECEITA, "Salário", "Pagamento ref. Jan/23");
        continha2.adicionarTransacao(transacao15);
        Transacao transacao16 = new Transacao(-10.90, "27/02/2023", tTransacao.DESPESA, "Lanche", "Alimentação");
        continha2.adicionarTransacao(transacao16);
        Transacao transacao17 = new Transacao(-2.90, "12/06/2023", tTransacao.DESPESA, "Lanche", "Alimentação");
        continha2.adicionarTransacao(transacao17);
        Transacao transacao18 = new Transacao(-58.60, "20/04/2023", tTransacao.DESPESA, "Casa", "Materias de construção");
        continha2.adicionarTransacao(transacao18);
        Transacao transacao19 = new Transacao(-54.90, "17/05/2023", tTransacao.DESPESA, "Contas", "Água");
        continha2.adicionarTransacao(transacao19);
        Transacao transacao20 = new Transacao(-1.90, "24/05/2023", tTransacao.DESPESA, "Lanche", "Alimentação");
        continha2.adicionarTransacao(transacao20);
    }
    //=============================================================================================
}