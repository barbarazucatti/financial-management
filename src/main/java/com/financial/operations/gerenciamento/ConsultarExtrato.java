package src.main.java.com.financial.operations.gerenciamento;

import java.util.List;
import java.util.Scanner;

import src.main.java.com.financial.model.Conta;
import src.main.java.com.financial.model.Transaction;
import src.main.java.com.financial.utils.ContaUtils;

public class ConsultarExtrato implements FinancialOperations{

    @Override
    public void executar(List<Conta> contas, Scanner entrada) {
        System.out.println("Digite o ID da conta que deseja consultar: ");
        int idConta = entrada.nextInt();

        Conta contaEncontrada = ContaUtils.encontrarContaPorId(contas, idConta); // <-- usando o seu método

        if (contaEncontrada != null) {
            System.out.println("Transações da conta:");
            List<Transaction> transacoes = contaEncontrada.getTransacoes();
            if (transacoes == null || transacoes.isEmpty()) {
                System.out.println("Essa conta ainda não tem transações.");
            } else {
                for (Transaction t : transacoes) {
                    System.out.println(t);
                }
            }
        } else {
            System.out.println("Conta não encontrada.");
        }
    }

}
