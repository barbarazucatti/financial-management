package src.main.java.com.financial.controller;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.text.SimpleDateFormat;

import src.main.java.com.financial.model.*;
import src.main.java.com.financial.service.*;
import src.main.java.com.financial.utils.*;

public class MenuController {

    static Scanner entrada = new Scanner(System.in);
    static ArrayList<Conta> contas = new ArrayList<Conta>();
    static ArrayList<Transaction> transacoes = new ArrayList<Transaction>();

    static int contadorContas = 1;
    static int contadorTransacoes = 1;

    public static void iniciar() {
        int opcao;

        do {
            menuPrincipal();
            opcao = entrada.nextInt();

            switch (opcao) {
                case 1:
                    gerenciadorContas();
                    break;
                case 2:
                    novaTransacao();
                    break;
                case 3:
                    painelGeral();
                    break;
                case 4:
                    System.out.println("Você está saindo do Gerenciador de Finanças!");
                    break;
                default:
                    System.out.println("Digite uma opção válida");
            }
        } while (opcao != 4);
    }

    public static void menuPrincipal() {
        System.out.println();
        System.out.println("\n       Bem vindo ao seu Gerenciador de Finanças!\n      ");
        System.out.println("        [1] Gerenciador de contas");
        System.out.println("        [2] Gerenciador de transações");
        System.out.println("        [3] Painel geral");
        System.out.println("        [4] Sair");
        System.out.println();
        System.out.println("        O que você gostaria de fazer? ");
    }

    public static void gerenciadorContas() {

        menuGerenciadorContas();
        iniciarGerenciadorContas();

    }

    public static void menuGerenciadorContas() {
        System.out.println();
        System.out.println("\n       Você está gerenciando suas contas!\n      ");
        System.out.println("        [1] Criar uma nova conta");
        System.out.println("        [2] Consultar um extrato");
        System.out.println("        [3] Cancelar conta");
        System.out.println("        [4] Sair");
        System.out.println();
        System.out.println("        O que você gostaria de fazer? ");
    }

    public static void iniciarGerenciadorContas() {
        int escolha;

        do {
            menuGerenciadorContas();
            escolha = entrada.nextInt();

            switch (escolha) {
                case 1:
                    novaConta();
                    break;
                case 2:
                    extratoConta();
                    break;
                case 3:
                    cancelamento();
                    break;
                case 4:
                    System.out.println("Você está saindo do Gerenciador de Finanças!");
                    break;
                default:
                    System.out.println("Digite uma opção válida");
            }
        } while (escolha != 4);
    }

    public static Conta novaConta() {
        System.out.println("Você está criando uma nova conta");
        System.out.println();

        entrada.nextLine(); // Limpar buffer

        System.out.println("Digite o banco da sua conta:");
        String banco = entrada.nextLine();

        System.out.println("Digite a sua agência:");
        int agencia = entrada.nextInt();

        System.out.println("Digite o número da conta:");
        int numero = entrada.nextInt();

        double saldo = 0;

        Conta novaConta = new Conta(contadorContas, banco, agencia, numero, saldo, new ArrayList<Transaction>());
        contadorContas++;

        contas.add(novaConta);
        System.out.println("Sua nova conta foi criada!");
        System.out.println("Conta criada: " + novaConta);

        for (Conta conta : contas) {
            System.out.println("Lista de contas: " + conta);
        }

        entrada.nextLine(); // Limpar buffer

        System.out.println("Você gostaria de fazer alguma transação nessa conta? (s/n)");
        String op = entrada.nextLine();

        if (op.equalsIgnoreCase("s")) {
            novaTransacao();
        } else {
            System.out.println("Você gostaria de cadastrar outras contas? (s/n)");
            String op2 = entrada.nextLine();
            if (op2.equalsIgnoreCase("s")) {
                novaConta();
            } else {
                gerenciadorContas();
            }
        }
        return novaConta;
    }

    public static void extratoConta() {
        System.out.println("Digite o ID da conta que deseja consultar: ");
        int idConta = entrada.nextInt();

        Conta contaEncontrada = null;

        for (Conta conta : contas) {
            if (conta.getIdConta() == idConta) {
                contaEncontrada = conta;
                break;
            }
        }

        if (contaEncontrada != null) {
            System.out.println("Transações da conta:");
            List<Transaction> listaTransacoes = contaEncontrada.getTransacoes();
            if (listaTransacoes.isEmpty()) {
                System.out.println("Essa conta ainda não tem transações.");
            } else {
                for (Transaction t : listaTransacoes) {
                    System.out.println(t);
                }
            }
        } else {
            System.out.println("Conta não encontrada.");
        }
    }

    public static void cancelamento() {
        System.out.println(
                "O pedido de cancelamento da conta foi feito, aguarde o contato do banco para os próximos passos.");
    }

    public static void novaTransacao() {
        menuNovaTransacao();
        iniciarNovaTransacao();

    }

    public static void menuNovaTransacao() {
        System.out.println();
        System.out.println("\n       Você fazendo uma transação!\n      ");
        System.out.println("        [1] Depositar");
        System.out.println("        [2] Sacar");
        System.out.println("        [3] Transferir fundos");
        System.out.println("        [4] Sair");
        System.out.println();
        System.out.println("        O que você gostaria de fazer? ");
    }

    public static void iniciarNovaTransacao() {
        int escolha;

        do {
            menuGerenciadorContas();
            escolha = entrada.nextInt();

            switch (escolha) {

                case 1:
                    System.out.println("\nInforme o ID da conta para saque:");
                    int idContaSaque = entrada.nextInt();
                    System.out.println("Informe o valor do saque:");
                    double valorSaque = entrada.nextDouble();

                    Conta contaSaque = ContaUtils.encontrarContaPorId(contas, idContaSaque);

                    if (contaSaque != null) {
                        FinancialOperation.sacar(contaSaque, valorSaque);
                    } else {
                        System.out.println("Conta não encontrada.");
                    }
                    break;

                case 2:
                    System.out.println("\nInforme o ID da conta para depósito:");
                    int idContaDeposito = entrada.nextInt();
                    System.out.println("Informe o valor do depósito:");
                    double valorDeposito = entrada.nextDouble();

                    Conta contaDeposito = ContaUtils.encontrarContaPorId(contas, idContaDeposito);

                    if (contaDeposito != null) {
                        FinancialOperation.depositar(contaDeposito, valorDeposito);
                    } else {
                        System.out.println("Conta não encontrada.");
                    }
                    break;

                case 3:
                    transferirFundos();
                    break;
                case 4:
                    System.out.println("Você está saindo do menu de transações!");
                    break;
                default:
                    System.out.println("Digite uma opção válida");
            }
        } while (escolha != 4);
    }

    public static void transferirFundos() {
        System.out.println("Digite o ID da conta de origem: ");
        int idOrigem = entrada.nextInt();
        System.out.println("Digite o ID da conta de destino: ");
        int idDestino = entrada.nextInt();

        Conta origem = null, destino = null;
        for (Conta c : contas) {
            if (c.getIdConta() == idOrigem)
                origem = c;
            if (c.getIdConta() == idDestino)
                destino = c;
        }

        if (origem == null || destino == null) {
            System.out.println("Uma das contas não foi encontrada.");
            return;
        }

        System.out.println("Digite o valor da transferência: ");
        double valor = entrada.nextDouble();
        entrada.nextLine();

        if (valor > origem.getSaldo()) {
            System.out.println("Saldo insuficiente.");
            return;
        }

        origem.setSaldo(origem.getSaldo() - valor);
        destino.setSaldo(destino.getSaldo() + valor);

        origem.getTransacoes().add(
                new Transaction(0, -valor, 0, "Despesa", "Transferência", "Transferência para conta " + idDestino));
        destino.getTransacoes()
                .add(new Transaction(0, valor, 0, "Receita", "Transferência", "Recebido da conta " + idOrigem));

        System.out.println("Transferência realizada com sucesso.");
    }

    public static void painelGeral() {
        System.out.println("Aqui gerencio o painel geral");
    }

}
