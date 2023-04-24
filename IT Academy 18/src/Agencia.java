import java.awt.desktop.SystemSleepEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;
/*
    Autor: Ana Carolina de Oliveira Xavier
    Data: 29/03/2023
    IT Academy DELL

    A classe Agencia é a principal para as funções do sistema.
    Esta classe se comunica com as demais classes como Modalidade, Itens, Transporte e Cidade (classes que a classe Main não se conecta).
    Sua função é interligar as demais classes com a classe Main.
 */

public class Agencia {
    // --> Lista para armazenar as cidades.
    private List<Cidade> listaCidades = new ArrayList<>();
    // Ler a linha de nomes das cidades. Para armazenar apenas os nomes. "Nome das colunas".
    // --> Já que guardará apenas os nomes das cidades, então podemos delimitar o tamanho.
    private String[] nomesCidades = new String[25];
    // --> Objeto para criação de itens.
    private Itens itens = new Itens();
    // --> Lista para armazenar os transportes.
    private List<Transporte> transportes = new ArrayList<>();
    // --> Lista para armazenar os trechos.
    private List<String> trechos = new ArrayList<>();
    // --> Chama o método "lerDados()" onde é realizada a leitura do arquivo CSV.
    //     --> Realiza a criação das cidades armazenando as distancias.
    public Agencia(){
        lerDados();
    }

    // --> O método "getNomeByIndex" retorna o nome da modalidade de acordo com a escolha que o usuário faz.
    public String getNomeByIndex(int index){
        switch (index){
            case 1:
                if(Modalidade.PEQUENO.getIndex() == index){
                    return  Modalidade.PEQUENO.getNome();
                }
                break;
            case 2:
                if(Modalidade.MEDIO.getIndex() == index){
                    return  Modalidade.MEDIO.getNome();
                }
                break;
            case 3:
                if(Modalidade.GRANDE.getIndex() == index){
                    return  Modalidade.GRANDE.getNome();
                }
                break;
        }
        System.out.println("Essa modalidade não existe.");
        return null;
    }
    // --> O método "getPrecoByIndex" retorna o preço por km da modalidade de acordo com a escolha que o usuário faz.
    public Double getPrecoKmByIndex(int index){
        switch (index){
            case 1:
                if(Modalidade.PEQUENO.getIndex() == index){
                    return  Modalidade.PEQUENO.getPrecoKm();
                }
                break;
            case 2:
                if(Modalidade.MEDIO.getIndex() == index){
                    return  Modalidade.MEDIO.getPrecoKm();
                }
                break;
            case 3:
                if(Modalidade.GRANDE.getIndex() == index){
                    return  Modalidade.GRANDE.getPrecoKm();
                }
                break;
        }
        System.out.println("Essa modalidade não existe.");
        return null;
    }
    // --> Calcula o valor do trecho que o usuário deseja consultar.
    //     --> Referente ao tópico 1 das funcionalidades do teste técnico.
    public void calcularTrecho(int indexSaida, int indexDestino, int modalidade){
        // --> Para armazenar o valor final calculado.
        double valorTotal = 0;

        // --> "cidadeSaida" armazena a cidade de saída que o usuário informar.
        Cidade cidadeSaida = listaCidades.get(indexSaida-1);
        // --> "cidadeDestino" armazena o valor (km) referente a cidade de destino informada.
        //      --> Para isso, é necessario utilizar a "cidadeSaida" que armazena a cidade de saída.
        //      --> "Estando" dentro do array da cidade de saída, buscamos a quilometragem da cidade de destino.
        String cidadeDestino = cidadeSaida.getDistanciaX(indexDestino-1);

        String mod = getNomeByIndex(modalidade);
        double valorMod = getPrecoKmByIndex(modalidade);

        // --> Calcula o valor total.
        valorTotal = Double.parseDouble(cidadeDestino) * valorMod;

        // --> Imprime as informações sobre a consulta.
        Cidade cidadeDestinoNome = listaCidades.get(indexDestino-1);
        System.out.println("O transporte de " + cidadeSaida.getNome() + " para " + cidadeDestinoNome.getNome()
                + "\n utilizando um caminhão de modalidade " + mod + ", a distancia é de " + cidadeDestino
                + " km e o custo será de R$" + valorTotal);
    }

    // --> Apenas imprime as modalidades disponíveis quando chamada.
    public void imprimeModalidades(){
        System.out.println("\n 1 - " + Modalidade.PEQUENO.toString() + "\n 2 - " + Modalidade.MEDIO.toString() + "\n 3 - " + Modalidade.GRANDE.toString());
    }
    // ===================================================================================================================================================
    // --> Calcula a distancia total a ser percorrida no transporte.
    public int calculaDistanciaTotal(List<String> cidades){
        // --> Variável que armazena a distancia total.
        int distanciaTotal = 0;
        // --> Varável para verificar se há antecedente.
        String antecedente = null;
        // --> Percorrre o arrar de cidades que foi informado.
        for(String nomeCidades: cidades){
            // --> Se houver uma cidade.
            if(antecedente != null){
                // --> Então soma a distancia total ao calculo do trecho entre as duas cidades.
                distanciaTotal += calcularDistancia(antecedente, nomeCidades);
            }
            // --> Recebe a próxima cidade da lista.
            antecedente = nomeCidades;
        }
        // --> Retorna a distancia total.
        return distanciaTotal;
    }
    // --> Calcula as distancias por trecho.
    public double calcularDistancia(String cSaida, String cDestino){
        // --> Percorre a lista de cidades.
        for(Cidade cidade: listaCidades){
            // --> Se a cidade atual possuir o nome igual ao informado.
            if(cidade.getNome().equals(cSaida)){
                // --> Então percorre o array de nomes de cidades.
                for(int i = 0; i < nomesCidades.length; i++){
                    // --> Se o nome da cidade for igual ao nome informado da cidade de destino.
                    if(nomesCidades[i].equals(cDestino)){
                        // --> Então retorna a distancia desta cidade da lista de cidades.
                        //     --> Lembrete: A lista de cidades armazena o nome da cidade no primeiro indice, e nos demais as distancias entre as outras cidades.
                        //                   O array de nomesCidades armazena somente os nomes das cidades. E com esse array, conseguimos localizar onde cada cidade
                        //                   se encontra na lista de cidades.
                        //                       exem: Se o nome da cidade BELEM se encontra no indice [1] do vetor, ele estará no indice get(1) da lista de cidades.
                        trechos.add("De " + cSaida + " para " + cDestino + " a distancia do trecho é: " + cidade.getDistanciaX(i) + "\n");
                        return Double.parseDouble(cidade.getDistanciaX(i));
                    }
                }
            }
        }
        // --> Caso não seja possivel calcular a distancia.
        System.out.println("Não foi possivel calcular a distância.");
        return -1;
    }
    // --> Realiza a lista de itens informada pelo usuário.
    public void listaDeItens(){
        // --> Objeto scanner para entrada de dados no sistema.
        Scanner entrada = new Scanner(System.in);
        // --> Auxiliar para saber se a lista continua ou não.
        boolean continua = true;
        // --> Enquanto o usuário quiser continuar.
        while(continua){
            System.out.println("   --> Digite o nome do item: ");
            System.out.println("     *Caso deseje terminar a lista, digite 'sair'");
            // --> Recebe o item.
            String nomeItem = entrada.nextLine();
            // --> Verifica se o item não é, na verdade, o usuário querendo terminar a lista.
            if(nomeItem.equalsIgnoreCase("sair")){
                // --> Se ele quiser terminar a lista, continua recebe false.
                continua = false;
            }else{
                // --> Se não, tenta:
                try{
                    // --> Coloca o nome do item para letra maiúscula (nome ser padrão de formatação).
                    String item = nomeItem.toUpperCase();
                    System.out.println("   --> Peso do item: ");
                    // --> Recebe o peso do item.
                    double peso = entrada.nextDouble();
                    // --> Limpa a entrada.
                    entrada.nextLine();
                    // --> Adicona o item.
                    itens.addItem(item, peso);
                // --> Retorna uma exceção, se necessário.
                }catch (IllegalArgumentException e){
                    System.out.println("Item inválido. Tente novamente :)");
                }
            }
        }
        // --> Percorre os itens.
        for(String item: itens.getItens().keySet()){
            // --> Imprime os itens.
            System.out.println(item + ": " + itens.getItens().get(item));
        }
        // --> Imprime o peso total da lista.
        System.out.println("Peso total dos itens: " + itens.pesoTotal());
    }
    // --> Informa o caminhão adequado para o transporte.
    public void caminhaoAdequado(List<String> cidades){
        // --> Criado uma lista simples com as modalidades de caminhao.
        List<Modalidade> caminhoes = new ArrayList<>();
        caminhoes.add(Modalidade.PEQUENO);
        caminhoes.add(Modalidade.MEDIO);
        caminhoes.add(Modalidade.GRANDE);

        // --> Cria uma lista com os caminhões necessários para o transporte.
        List<Modalidade> necessarios = new ArrayList<>();
        // --> Guarda o peso total dos itens.
        double pesoTotal = itens.pesoTotal();
        // --> Auxiliar para caso encontre um caminhão.
        boolean encontrouCaminhao = false;
        // --> Percorre o array de caminhões possíveis.
        for(Modalidade caminhao: caminhoes){
            // --> Se a capacidade do caminhao for maior ou igual ao peso dos itens.
            double capacidadeCaminhao = caminhao.getCapacidade();
            if(capacidadeCaminhao >= pesoTotal){
                // --> Então armazena que encontrou um caminhão possível.
                encontrouCaminhao = true;
                // --> Se o caminhão ainda não está na lista de caminhões necessários.
                if(!necessarios.contains(caminhao))
                    // --> Então adiciona o caminhão encontrado.
                    necessarios.add(caminhao);
            }
            // --> Diminui, então, o peso que já foi liberado.
            pesoTotal -= capacidadeCaminhao;
            // --> Caso o peso já tiver sido suprido, não continua no for.
            if(pesoTotal < 0){
                break;
            }
        }
        // --> No caso de não encontrar nenhum caminhão.
        if(!encontrouCaminhao){
            System.out.println("Tente novamente :)");
        }
        // --> Variável para armazenar o custo total do transporte.
        double custoTotal = 0;
        // --> Variável para armazenar o custo por unidade.
        double valorUnitario = 0;
        // --> Variável para armazenar a distancia total do transporte.
        int distanciaTotal = calculaDistanciaTotal(cidades);
        // --> Percorre o array de caminhões necessários.
        for(Modalidade caminhao: necessarios){
            // --> Armazena o custo total da distancia a partir do custo por km do caminhão.
            double custoTotalDistancia = caminhao.getPrecoKm() * distanciaTotal;
            // --> Soma a variável de custo total.
            custoTotal += custoTotalDistancia;
        }
        valorUnitario = custoTotal/itens.getItens().size();
        // --> Cria um novo transporte com as informações que adquirimos.
        Transporte transporte = new Transporte(pesoTotal, necessarios, custoTotal, valorUnitario);
        // --> Adiciona na lista de transportes.
        transportes.add(transporte);
        // --> Imprime o transporte criado.
        System.out.println();
        System.out.println(transporte.toString());
        itens.clear();
    }
    // ==============================================================================================================
    // --> Imprime algumas estatisticas
    public void dadosEstatisticos(){
        System.out.println("Trechos realizados: ");
        System.out.println(trechos.toString());
        System.out.println("Transportes realizados ");
        System.out.println(transportes.toString());
    }
    // ==============================================================================================================
    // --> Leitura do arquivo CSV e criação dos objetos que representam as cidades.
    public void lerDados(){
        // --> Separador da String do arquivo.
        String Split = ";";

        // --> n representará o indice do array de nomes de cidades futuramente.
        int n = 0;
        try (BufferedReader brCidades = new BufferedReader(new FileReader("src\\DNIT-Distancias.csv"))) {
            // --> Le a primeira linha.
            String lineCidades = brCidades.readLine();
            // --> Separa as Strings e armazena no vetor.
            nomesCidades = lineCidades.split(Split);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Ler dados de distancias.
        try (BufferedReader br = new BufferedReader(new FileReader("src\\DNIT-Distancias.csv"))) {
            // Ignora o cabeçalho (os nomes).
            br.readLine();
            String line = br.readLine();
            // --> Enquanto ainda conter linhas.
            while (line != null) {
                String[] data = line.split(Split);
                // --> Cria objeto Cidade com as informações da linha - distancias.
                //     --> Cada cidade será um objeto, para assim cada uma poder armazenar as distancias referente a cidade.
                //     --> O primeiro elemento a ser inserido será o nome da cidade.
                Cidade cidade = new Cidade(nomesCidades[n], data[0], data[1], data[2], data[3],
                                                            data[4], data[5], data[6], data[7],
                                                            data[8], data[9], data[10], data[11],
                                                            data[12], data[13], data[14], data[15],
                                                            data[16], data[17], data[18], data[19],
                                                            data[20], data[21], data[22], data[23]);
                // --> Adiciona cidade na lista de cidades.
                listaCidades.add(cidade);
                // --> Le nova linha.
                line = br.readLine();
                // --> Próximo índice do vetor de nomes de cidades.
                n++;
            }
        // --> Retorna uma exceção, se necessário.
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
