
import java.util.ArrayList;
import java.util.Scanner;
import java.text.*;

public class Main {
    static Scanner entrada = new Scanner(System.in);
    static ArrayList<Conta> contas = new ArrayList<Conta>();
    static ArrayList<Transaction> transacoes = new ArrayList<Transaction>();
    static SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

    public static void main(String[] args) {

        // rodando o menu principal
        int opcao;
        Scanner entrada = new Scanner(System.in);
        do {
            menuPrincipal();
            opcao = entrada.nextInt();

            switch (opcao) {
                case 1:
                    gerenciadorContas();
                    break;

                case 2:
                    gerenciadorTransacoes();
                    break;

                case 3:
                    painelGeral();
                    break;

                case 4:
                    System.out.println("Você está saindo do Gerenciador de Finanças!");

                    break;

                default:
                    System.out.println("Digite uma opção válida");
                    menuPrincipal();
            }
        } while (opcao != 0);
    }

    // criando o menu principal
    public static void menuPrincipal() {

        System.out.println("\n       Bem vindo ao seu Gerenciador de Finanças!\n      ");
        System.out.println("        [1] Gerenciador de contas");
        System.out.println("        [2] Gerenciador de transações");
        System.out.println("        [3] Painel geral");
        System.out.println("        [4] Sair");
        System.out.println();
        System.out.println("        O que você gostaria de fazer? ");
    }

    // 1. MENU GERENCIADOR DE CONTAS - menu dentro do menu principal
    public static void gerenciadorContas() {
        Scanner entrada = new Scanner(System.in);
        System.out.println("\nVocê está entrando no Gerenciador de Contas");
        System.out.println("\nO que você gostaria de fazer? ");
        System.out.println();
        System.out.println(" 1 - Cadastrar uma nova conta");
        System.out.println(" 2 - Remover uma conta");
        System.out.println(" 3 - Mesclar contas");
        System.out.println(" 4 - Voltar ao menu principal");

        int escolha = entrada.nextInt();

        if (escolha == 1) {
            contas.add(novaConta());

        }

        if (escolha == 2) {
            removerConta();
        }

        if (escolha == 3) {
            mesclarContas();

        }

        if (escolha == 4) {
            menuPrincipal();

        }
    }

    // 1.1. CADASTRO DE NOVAS CONTAS - criar uma nova conta
    public static Conta novaConta() {

        System.out.println("Você está criando uma nova conta");
        System.out.println("");
        System.out.println("\nDigite o banco da sua conta: ");
        String banco = entrada.nextLine();
        System.out.println();
        System.out.println("\nDigite a sua agência: ");
        int agencia = entrada.nextInt();
        System.out.println();
        System.out.println("\nDigite o número da agência: ");
        int numero = entrada.nextInt();
        System.out.println();

        int id = 1;
        double saldo = 0;

        Conta novaConta = new Conta(id, banco, agencia, numero, saldo, transacoes);
        System.out.println("Sua nova conta foi criada!\n");
        System.out.println("Conta criada: " + novaConta);
        contas.add(novaConta);
        id++;

        for (Conta conta : contas)
            System.out.println("\n Lista de contas: " + conta);

        System.out.println("\n Você gostaria de cadastrar alguma transação nessa conta? (s/n) ");
        entrada.nextLine();
        String op = entrada.nextLine();
        if (op.equalsIgnoreCase("s")) {
            novaTransacao();
            transacoes.add(novaTransacao());

        } else {
            // chama novamente o menu anterior
            System.out.println("\n Você gostaria de cadastrar outras contas? (s/n) ");
            String op2 = entrada.nextLine();
            if (op2.equalsIgnoreCase("s")) {
                novaConta();
            } else {
                gerenciadorContas();

            }
        }

        return novaConta;
    }

    // criar transações - opção dentro de criar uma conta

    public static Transaction novaTransacao() {

        Scanner entrada = new Scanner(System.in);
        System.out.println("Você está criando uma nova transacao");
        System.out.println("");
        System.out.println("\nDigite o valor da transacao: ");
        double valor = entrada.nextDouble();
        System.out.println();
        System.out.println("\nDigite a data da transação ");
        int data = entrada.nextInt();
        System.out.println();
        System.out.println("\nDigite o tipo de transação: Receita/Despesa ");
        String tipo = entrada.nextLine();
        System.out.println("\nDefina uma categoria para a transação: ");
        String categoria = entrada.nextLine();
        System.out.println();
        System.out.println("\nDefina uma descrição para a transação: ");
        String descricao = entrada.nextLine();
        System.out.println();
        int idTransacao = 1;

        Transaction novaTransacao = new Transaction(idTransacao, valor, data, tipo, categoria, descricao);
        System.out.println("Sua nova transacão foi criada!\n");
        System.out.println("Transação criada: " + novaTransacao);
        transacoes.add(novaTransacao);
        idTransacao++;
        Conta.getTransacao().setTransacoes(transacoes); // - Incluir a nova transação dentro da conta
        // 1Conta.atualizarSaldo(); // - atualizar o saldo da respectiva conta.

        System.out.println("Lista de transações da conta: " + transacoes);

        for (Conta conta : contas) {
            System.out.println("\nContas:");
            transacoes = conta.getTransacoes();

            if (transacoes == null) {
                transacoes = new ArrayList<>();
            }
            System.out.println("\n Lista de transações na conta: " +
                    conta.getIdConta() + ":");
            for (Transaction transacao : transacoes) {
                System.out.println(transacao);
            }
        }
        System.out.println("\n Você gostaria de cadastrar outras transações? (s/n) ");
        String op = entrada.nextLine();

        if (op.equalsIgnoreCase("s")) {
            novaTransacao();
        } else {
            // chama novamente o menu anterior
            System.out.println("\n Você gostaria de cadastrar uma nova conta? (s/n) ");
            String op2 = entrada.nextLine();
            if (op2.equalsIgnoreCase("s")) {
                novaConta();
            } else {
                gerenciadorContas();
            }
        }

        return novaTransacao;
    }

    // 1.2. REMOVER CONTA - o método para remover uma conta:

    // para encontrar a conta certa a remover, o método encontrarConta pode ser util
    // para esse e outras funcionalidades;
    public static Conta encontrarConta(int indice) {
        Conta conta = null; // inicializar uma conta com null, porque o retorno do método vai ser a conta
                            // encontrada
        if (contas.size() > 0) { // verifica se o tamanho da lista de contas é maior que 0
            for (Conta c : contas) { // percorre a lista de contas
                if (c.getIdConta() == indice)
                    ; // verifica se a idConta da lista conta for == o indice inputado pelo usuário
                c = conta; // atualiza a variável conta, pra conta encontrada
            }
        }
        return conta;
    }

    public static void removerConta() {
        System.out.println("\nAtenção: você está prestes a excluir uma de suas contas! \n");
        System.out.println("\n Lista de contas: " + contas);
        System.out.println("\nInsira o índice da conta que deseja remover: \n");

        int indice = entrada.nextInt();

        entrada.nextLine(); // Consumir a nova linha após a leitura do índice

        Conta contaEncontrada = null;

        for (Conta conta : contas) {
            if (conta.getIdConta() == indice) {
                contaEncontrada = conta;
                break;
            }
        }

        if (contaEncontrada != null) {
            contas.remove(contaEncontrada);
            System.out.println("\nSua conta foi removida com sucesso!");
            System.out.println("\nLista de contas atualizada: " + contas);

            System.out.println("\nVocê gostaria de excluir outra conta? (s/n) ");
            String op = entrada.nextLine();
            if (op.equalsIgnoreCase("s")) {
                removerConta();
            } else {
                gerenciadorContas();
            }
        } else {
            System.out.println("\nÍndice inválido.Nenhuma conta foi removida.");
            System.out.println("\nVocê gostaria de excluir outra conta? (s/n).");
            String op2 = entrada.nextLine();
            if (op2.equalsIgnoreCase("s")) {
                removerConta();
            } else {
                // chama novamente o menu anterior
                gerenciadorContas();
            }
        }
    }

    // 1.3.MESCLAR CONTAS - método para mesclar transações de duas contas
    public static void mesclarContas() {
        System.out.println("Informe o número da conta de origem da transferência: ");
        int indiceOrigem = entrada.nextInt();

        Conta contaOrigemEncontrada = null;

        for (Conta conta : contas) {
            if (conta.getIdConta() == indiceOrigem) {
                contaOrigemEncontrada = conta;
                break;
            }
        }

        if (contaOrigemEncontrada != null) {
            System.out.println("Informe a conta de destino: ");
            int indiceDestino = entrada.nextInt();

            Conta contaDestinoEncontrada = null;

            for (Conta conta : contas) {
                if (conta.getIdConta() == indiceDestino) {
                    contaDestinoEncontrada = conta;
                    break;
                }
            }

            if (contaDestinoEncontrada != null) {
                System.out.println("Informe o valor da transferência: ");
                double valorTransferencia = entrada.nextInt();
                contaOrigemEncontrada.transferir(contaDestinoEncontrada, valorTransferencia);

            } else {
                System.out.println("Valor inválido!");
            }
        } else {
            System.out.println("Conta não encontrada!");
            System.out.println("\nVocê gostaria de mesclar outra conta? (s/n).");
            String op2 = entrada.nextLine();
            entrada.nextLine();
            if (op2.equalsIgnoreCase("s")) {
                mesclarContas();
            } else {
                gerenciadorContas();
            }

        }
    }

    // 2. GERENCIADOR DE TRANSAÇÕES
    public static void gerenciadorTransacoes() {

        Scanner entrada = new Scanner(System.in);
        System.out.println("\nVocê está entrando no Gerenciador de Transações");
        System.out.println("\nO que você gostaria de fazer? ");
        System.out.println();
        System.out.println(" 1 - Extrato de uma conta");
        System.out.println(" 2 - Incluir transações");
        System.out.println(" 3 - Editar a última transação");
        System.out.println(" 4 - Transferir fundos");
        System.out.println(" 5 - Voltar ao menu principal");

        int escolha = entrada.nextInt();

        if (escolha == 1) {
            extratoConta();
        }

        if (escolha == 2) {
            incluirTransacao();

        }

        if (escolha == 3) {
            editarTransacao();

        }

        if (escolha == 4) {
            transferirFundos();
            // 1. pedir o usuário o indíce da conta de destino e da conta de origem
            // 2. fazer um loop de que só vai funcionar se o saldo da conta de origem for
            // maior que o saldo da conta de destino
            // 3. fazer uma transação de receita na conta que estará recebendo as
            // informações
            // 4. fazer uma transção de despesa na conta que estará dando as informações;
            // Utilizar o mesmo esquema de mesclar contas;

            if (escolha == 5) {
                menuPrincipal();
            }
        }
    }

    public static void extratoConta() {
        System.out.println(
                "Atenção: essa funcionalidade possui erros, portanto não irá rodar. \nDeixei meu raciocínio para desenvolver o problema comentado no código!");
        // System.out.println("Informe o número da conta que deseja ver o extrato: ");
        // System.out.println("Lista de contas" + contas)
        // int indiceConta = entrada.nextInt();

        // Conta2 contaEncontrada = null;

        // for (Conta conta : contas) {
        // if (conta.getIdConta() == indiceConta) {
        // contaEncontrada = conta;
        // break;
        // }
        // }
        // if (contaEncontrada != null){
        // System.out.print.ln("--------------Extrado da conta "+ idConta
        // +"--------------");
        // System.out.print.ln("Lista de Transações: " + Collections.sort(transacoes) +
        // " | " + valor"); // output esperado:
        // Lista de Transações: Transação 1 -> 2500 | 20/03/2023 | Alguel |Pagamento
        // aluguel Ref. Mar/23|Despesa|;
    }

    private static void incluirTransacao() {
        System.out.println(
                "Atenção: essa funcionalidade possui erros, portanto não irá rodar. \nDeixei meu raciocínio para desenvolver o problema comentado no código!");
        // System.out.println(contas + "\nDigite a conta que deseja incluir uma
        // transação: ");
        // int indiceConta = entrada.nextInt();

        // Conta2 contaEncontrada = null;

        // for (Conta2 conta : contas) {
        // if (conta.getIdConta() == indiceConta) {
        // contaEncontrada = conta;
        // break;
        // }
        // }
        // if (contaEncontrada != null){
        // System.out.println("Você está criando uma nova transacao");
        // System.out.println("");
        // System.out.println("\nDigite o valor da transacao: ");
        // double valor = entrada.nextDouble();
        // System.out.println();
        // System.out.println("\nDigite a data da transação ");
        // int data = entrada.nextInt();
        // System.out.println();
        // System.out.println("\nDigite o tipo de transação: Receita/Despesa ");
        // String tipo = entrada.nextLine();
        // System.out.println("\nDefina uma categoria para a transação: ");
        // String categoria = entrada.nextLine();
        // System.out.println();
        // System.out.println("\nDefina uma descrição para a transação: ");
        // String descricao = entrada.nextLine();
        // System.out.println();
        // int idTransacao = 1;

        // Transacao novaTransacao = new Transacao(idTransacao, valor, data, tipo,
        // categoria, descricao);
        // System.out.println("Sua nova transacão foi criada!\n");
        // transacoes.add(novaTransacao);
        // idTransacao++;
        // contaEncontrada.getTransacao().setTransacoes(transacoes); //- Incluir a nova
        // transação dentro da conta
        // contaEncontrada.atualizarSaldo(); //- atualizar o saldo da respectiva conta.

        // System.out.println("Lista de transações da conta: " + transacoes);

        // for (Conta2 conta : contas) {
        // System.out.println("\nContas:");
        // transacoes = conta.getTransacoes();

        // if (transacoes == null) {
        // transacoes = new ArrayList<>();
        // }
        // System.out.println("\n Lista de transações na conta: " +
        // conta.getIdConta() + ":");
        // for (Transacao transacao : transacoes) {
        // System.out.println(transacao);
        // }
        // }
        // System.out.println("\n Você gostaria de cadastrar outras transações? (s/n)
        // ");
        // String op = entrada.nextLine();

        // if (op.equalsIgnoreCase("s")) {
        // novaTransacao();
        // } else {
        // gerenciadorContas();
        // }
        // }

    }

    private static void editarTransacao() { // deve ser um método Transação e retornar uma transaçãoEditada com objeto.
                                            // Não alterei o void para não bugar o código
        System.out.println(
                "Atenção: essa funcionalidade possui erros, portanto não irá rodar. \nDeixei meu raciocínio para desenvolver o problema comentado no código!");
        // System.out.println("Atenção: Você está editando sua última transação"");
        // ultimaTransacao = transacoes.size() - 1;
        // System.out.println(ultimaTrasacao);
        // System.out.println("O que você gostaria de editar?");
        // System.out.println("[1] valor");
        // System.out.println("[2] data");
        // System.out.println("[3] tipo");
        // System.out.println("[4] categoria");
        // System.out.println("[5] descrição");
        // System.out.println("[6] voltar");
        // System.out.println("Digite sua escolha: ");
        // opcao = entrada.nextInt();
        // if (opcao == 1){
        // valorEditado = ultimaTransacao.setValor();
        // }
        // if (opcao == 2){
        // dataEditado = ultimaTransacao.setData();
        // }
        // if (opcao == 3){
        // tipoEditado = ultimaTransacao.setTipo();
        // }
        // if (opcao == 4){
        // categoriaEditado = ultimaTransacao.setCategoria();
        // }
        // if (opcao == 5){
        // descricaoaEditado = ultimaTransacao.setDescricao();
        // }
        // if (opcao == 6){
        // gerenciadorTransacoes();
        // id=1;
        // Transacao transacaoEditada = new Transacao(id,valorEditado, dataEditado,
        // tipoEditado, categoriaEditado,descricaoEditado)
        // transacoes.add(trasacaoEditada);
        // id++
        // } else {
        // System.out.println("Opção inválida");
        // editarTransacao();
        // }

    }

    private static void transferirFundos() {
        System.out.println(
                "Atenção: essa funcionalidade possui erros, portanto não irá rodar. \nDeixei meu raciocínio para desenvolver o problema comentado no código!");
        // System.out.println(contas + "Digite a conta que de origem da
        // transferência:");
        // int indiceConta = entrada.nextInt();

        // Conta2 contaOrigemEncontrada = null;

        // for (Conta2 conta : contas) {
        // if (conta.getIdConta() == indiceConta) {
        // contaOrigemEncontrada = conta;
        // break;
        // }
        // }
        // if (contaOrigemEncontrada != null){
        // System.out.println("Você está criando uma transferência entre contas");
        // System.out.println(contas + "Digite a conta que de destino da
        // transferência:");
        // int indiceConta = entrada.nextInt();

        // Conta2 contaDestinoEncontrada = null;

        // for (Conta2 conta : contas) {
        // if (conta.getIdConta() == indiceConta) {
        // contaDestinoEncontrada = conta;
        // break;
        // }
        // }
        // if (contaDestinoEncontrada != null){
        // System.out.println("Digite o valor da transferência: ");
        // double valorTransferencia = entrada.nextDouble();

        // if (valorTransferencia > 0 && contaOrigemEncontrada.getSaldo() >=
        // valorTransferencia ){ // verifica se o valor de transferência é maior que 0 e
        // se o saldo da conta de origem é maior ou igual ao valor a ser transferido.
        // Conta2.transferir(contaDestinoEncontrada,valorTransferencia); // chama o
        // método transferir da classe Conta e digo que os parâmetros são a conta de
        // destino e o valor da transferência informado pelo usuário.
        // o método transferir analisa se o valor informado é mair que 0 e se existe
        // saldo suficiente na conta informada. Se tiver, ele fará a transferência do
        // valor, diminuindo o mesmo da conta de destino.
        // Depois, ele irá atualizar o saldo da conta de destino. Caso a conta não tenha
        // saldo suficiente, ele não irá fazer a trasnferência e irá informar uma
        // mensagem de erro.

    }

    // 3. PAINEL GERAL
    public static void painelGeral() {
        Scanner entrada = new Scanner(System.in);
        System.out.println("\nVocê está entrando no Painel Geral");
        System.out.println("\nO que você gostaria de fazer? ");
        System.out.println();
        System.out.println(" 1 - Resumo das contas");
        System.out.println(" 2 - Resumo das receitas e do consumo do mês");
        System.out.println(" 3 - Saldo geral dos últimos 6 messes");
        System.out.println(" 4 - Gastos por categoria");
        System.out.println(" 5 - Voltar ao menu principal");

        int escolha = entrada.nextInt();

        if (escolha == 1) {
            resumoContas();
            // exibir cada conta e suas respectivas transações;

        }

        if (escolha == 2) {
            receitasECosumos();

        }

        if (escolha == 3) {
            saldoSeisMeses();
            // idem anterior, porém com um range de 6 meses;

        }

        if (escolha == 4) {
            gastosPorCategoria();
        }
        // 1. agrupar transações por categorias e calcular o valor gasto em cada
        // categoria
        // 2. parecido com quantidadeTipoPorLoJa;

    }

    private static void resumoContas() {
        System.out.println(
                "Atenção: essa funcionalidade possui erros no código, portanto não irá rodar. \nDeixei meu raciocínio para desenvolver o problema comentado no código!");
        // System.out.println("Lista de Contas" + contas");
        // System.out.println("Digite a conta que você deseja ver o resumo");
        // int indiceConta = entrada.nextInt();
        // Conta contaEscolhida = encontrarConta(indiceConta);
        // if(contaEscolhida != null){
        //
        // System.out.println("\n Lista de transações na conta: " +
        // conta.getIdConta() + ":");
        // transacoesConta = contaEscolhida.getTransacoes().getSaldo();
        //
        // for (Transacao transacao : transacoes) {
        // listaTransacoes = contaEscolhida.getTransacao().getSaldo();
        // saldoTotalConta = saldoTotal();
        // System.out.println ("\n Lista de transações na conta: " + listaTransações);
        // System.out.println ("Saldo total: " + saldoTotal);
        //
        // }

        // saldoTotalConta

        // }
    }

    private static void receitasECosumos() {
        System.out.println(
                "Atenção: essa funcionalidade possui erros no código, portanto não irá rodar. \nDeixei meu raciocínio para desenvolver o problema comentado no código!");

        // ArrayList <Transacao> receitas = new ArrayList();
        // ArrayList <Transacao> despesas = new ArrayList();
        // receitas = Conta2.getTransacoes().getTipo().equalsIgnoreCase("Receita");
        // receitas.sort(Comparator.comparing(Transacao::data); // Ordena a lista
        // receitas de acordo com a data.
        // Date mesAtual = new Date;
        // if (receitas =! null){
        // if (receitas.getData() == mesAtual){
        // ultimaTransacao = receitas.getSize() - 1;
        // ultimaTransacao = getSaldo();
        // System.out.println("Saldo total das receitas: " + ultimaTransacao "\n"");
        // }
        // despesas = Conta2.getTransacoes().getTipo().equalsIgnoreCase("Despesas");
        // despesas.sort(Comparator.comparing(Transacao::data); // Ordena a lista
        // receitas de acordo com a data.
        // Date mesAtual = new Date;
        // if (despesas =! null){
        // if (despesas.getData() == mesAtual){
        // ultimaTransacao = despesas.getSize() - 1;
        // ultimaTransacao = getSaldo();
        // System.out.println("Saldo total das despesas: " + ultimaTransacao "\n"");
        // }
        // }

    }

    private static void saldoSeisMeses() {
        System.out.println(
                "Atenção: essa funcionalidade possui erros no código, portanto não irá rodar. \nDeixei meu raciocínio para tentar desenvolver o problema comentado no código!");
        // Date seisMeses = new Date;
        // if (Conta2.transacoes.getData() == mesAtual){
        // ultimaTransacao = receitas.getSize() - 1;
        // ultimaTransacao = getSaldo();
    }

    private static void gastosPorCategoria() { // o método é do tipo Transacao, não coloquei para não bugar o código
        System.out.println(
                "Atenção: essa funcionalidade possui erros no código, portanto não irá rodar. \nDeixei meu raciocínio para desenvolver o problema comentado no código!");
        // ArrayList <Transacao> alimentacao = new ArrayList();
        // alimentacao =
        // Conta2.getTransacoes().getCategoria().equalsIgnoreCase("Alimentacao");
        // if (alimentacao != null){
        // categoriaSaldo = Conta2.transacoes.getTipo("Receita").getSaldo();
        // return categoriaSaldo;
    }

}
