package src.main.java.com.financial;

import java.util.ArrayList;
import java.util.Scanner;

import src.main.java.com.financial.model.Conta;
import src.main.java.com.financial.model.Transaction;

import java.text.*;

public class Main {

    static Scanner entrada = new Scanner(System.in);
    static ArrayList<Conta> contas = new ArrayList<Conta>();
    static ArrayList<Transaction> transacoes = new ArrayList<Transaction>();
    static SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

    static int contadorContas = 1;
    static int contadorTransacoes = 1;

    public static void main(String[] args) {

        int opcao;

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
            }
        } while (opcao != 4); // Corrigido: sair quando for 4
    }

    public static void menuPrincipal() {
        System.out.println("\n       Bem vindo ao seu Gerenciador de Finanças!\n      ");
        System.out.println("        [1] Gerenciador de contas");
        System.out.println("        [2] Gerenciador de transações");
        System.out.println("        [3] Painel geral");
        System.out.println("        [4] Sair");
        System.out.println();
        System.out.println("        O que você gostaria de fazer? ");
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

    System.out.println("Você gostaria de cadastrar alguma transação nessa conta? (s/n)");
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
        ArrayList<Transaction> listaTransacoes = contaEncontrada.getTransacoes();
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

public static void incluirTransacao() {
    System.out.println("Digite o ID da conta onde deseja adicionar a transação: ");
    int idConta = entrada.nextInt();
    entrada.nextLine(); // consumir quebra de linha

    Conta contaEncontrada = null;
    for (Conta conta : contas) {
        if (conta.getIdConta() == idConta) {
            contaEncontrada = conta;
            break;
        }
    }

    if (contaEncontrada != null) {
        Transaction nova = novaTransacao();
        contaEncontrada.adicionarTransacao(nova);
        System.out.println("Transação adicionada com sucesso!");
    } else {
        System.out.println("Conta não encontrada.");
    }
}

public static void editarTransacao() {
    System.out.println("Digite o ID da conta que contém a transação: ");
    int idConta = entrada.nextInt();
    entrada.nextLine();

    Conta conta = null;
    for (Conta c : contas) {
        if (c.getIdConta() == idConta) {
            conta = c;
            break;
        }
    }

    if (conta != null && !conta.getTransacoes().isEmpty()) {
        Transaction ultima = conta.getTransacoes().get(conta.getTransacoes().size() - 1);

        System.out.println("Última transação encontrada:");
        System.out.println(ultima);

        System.out.println("Digite uma nova descrição: ");
        String novaDescricao = entrada.nextLine();
        ultima.setDescricao(novaDescricao);

        System.out.println("Transação atualizada:");
        System.out.println(ultima);
    } else {
        System.out.println("Conta não encontrada ou sem transações.");
    }
}

public static void transferirFundos() {
    System.out.println("Digite o ID da conta de origem: ");
    int idOrigem = entrada.nextInt();
    System.out.println("Digite o ID da conta de destino: ");
    int idDestino = entrada.nextInt();

    Conta origem = null, destino = null;
    for (Conta c : contas) {
        if (c.getIdConta() == idOrigem) origem = c;
        if (c.getIdConta() == idDestino) destino = c;
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

    origem.getTransacoes().add(new Transaction(0, -valor, 0, "Despesa", "Transferência", "Transferência para conta " + idDestino));
    destino.getTransacoes().add(new Transaction(0, valor, 0, "Receita", "Transferência", "Recebido da conta " + idOrigem));

    System.out.println("Transferência realizada com sucesso.");
}
}






}