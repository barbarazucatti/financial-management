package src.main.java.com.financial.operations.contas;

import java.util.List;
import java.util.Scanner;

import src.main.java.com.financial.model.Conta;
import src.main.java.com.financial.model.Transaction;
import src.main.java.com.financial.utils.ContaUtils;

public class Transferencia implements AccountOperations {

    @Override
    public void executar(List<Conta> contas, Scanner entrada) {

        System.out.println("Digite o ID da conta de origem: ");
        int idOrigem = entrada.nextInt();
        System.out.println("Digite o ID da conta de destino: ");
        int idDestino = entrada.nextInt();

        Conta origem = ContaUtils.encontrarContaPorId(contas, idOrigem);
        Conta destino = ContaUtils.encontrarContaPorId(contas, idDestino);

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
}