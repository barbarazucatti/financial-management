package src.main.java.com.financial.operations.contas;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import src.main.java.com.financial.controller.MenuController;
import src.main.java.com.financial.model.Conta;
import src.main.java.com.financial.model.Transaction;
import src.main.java.com.financial.utils.ContaUtils;

public class Mesclar implements AccountOperations {

    DecimalFormat formato = new DecimalFormat("R$ #,##0.00");

    ////criar um array que vai mesclar a conta atual com as transações da outra conta, então vai somando nesse array as transações anteriores
    
@Override
public void executar(List<Conta> contas, Scanner entrada) {
    System.out.println("Você irá mesclar duas contas.");
    System.out.print("Digite o ID da conta que será mantida: ");
    int idMantida = entrada.nextInt();

    System.out.print("Digite o ID da conta que será mesclada (e removida): ");
    int idExcluida = entrada.nextInt();

    Conta contaMantida = ContaUtils.encontrarContaPorId(contas, idMantida);
    Conta contaExcluida = ContaUtils.encontrarContaPorId(contas, idExcluida);

    if (contaMantida == null || contaExcluida == null) {
        System.out.println("Uma das contas informadas não foi encontrada.");
        return;
    }

    // Mesclar as transações
    List<Transaction> transacoesMantida = contaMantida.getTransacoes();
    List<Transaction> transacoesExcluida = contaExcluida.getTransacoes();

    if (transacoesExcluida != null) {
        transacoesMantida.addAll(transacoesExcluida);
    }

    // Ordenar por data
    transacoesMantida.sort(Comparator.comparing(Transaction::getData));

    // Atualizar o saldo da conta mantida
    contaMantida.setSaldo(contaMantida.getSaldo() + contaExcluida.getSaldo());

    // Remover a conta excluída da lista
    contas.remove(contaExcluida);

    System.out.println("Contas mescladas com sucesso!");
    System.out.println("A conta " + contaExcluida.getIdConta() + " foi excluída.");
    System.out.println("Saldo final da conta mantida: R$ " + String.format("%.2f", contaMantida.getSaldo()));
}

}