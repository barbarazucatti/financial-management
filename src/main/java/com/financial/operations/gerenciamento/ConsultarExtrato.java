package src.main.java.com.financial.operations.gerenciamento;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Scanner;

import src.main.java.com.financial.model.Conta;
import src.main.java.com.financial.model.Transaction;
import src.main.java.com.financial.utils.ContaUtils;

import java.text.DecimalFormat;

public class ConsultarExtrato implements FinancialOperations{

    DecimalFormat formato = new DecimalFormat("R$ #,##0.00");


    @Override
    public void executar(List<Conta> contas, Scanner entrada) {
        System.out.println("Digite o ID da conta que deseja consultar: ");
        int idConta = entrada.nextInt();

           ///arrumar a questão do static
        Conta contaEncontrada = ContaUtils.encontrarContaPorId(contas, idConta); // <-- usando o seu método

        if (contaEncontrada != null) {
            System.out.println("Transações da conta:");
            List<Transaction> transacoes = contaEncontrada.getTransacoes();
            if (transacoes == null || transacoes.isEmpty()) {
                System.out.println("Essa conta ainda não tem transações.");
                System.out.println("Saldo: " + formato.format(contaEncontrada.getSaldo()));
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
