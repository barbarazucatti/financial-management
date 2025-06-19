
package src.main.java.com.financial;

import src.main.java.com.financial.controller.MenuController;

public class Main {
    public static void main(String[] args) {
        MenuController.iniciar();
    }
}

/*
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
}*/