package src.main.java.com.financial.operations.contas;

import java.util.List;
import java.util.Scanner;
import src.main.java.com.financial.model.Conta;
import src.main.java.com.financial.utils.ContaUtils;

public class Saque implements AccountOperations {

    @Override
    public void executar(List<Conta> contas, Scanner entrada) {
        System.out.println("\nInforme o ID da conta para saque:");
        int idContaSaque = entrada.nextInt();
        System.out.println("Informe o valor do saque:");
        double valorSaque = entrada.nextDouble();

        Conta contaSaque = ContaUtils.encontrarContaPorId(contas, idContaSaque);

        if (contaSaque != null) {
            if (contaSaque.getSaldo() >= valorSaque && valorSaque > 0) {
                contaSaque.setSaldo(contaSaque.getSaldo() - valorSaque);
                System.out.println("Saque realizado no valor de " + valorSaque);
            } else {
                System.out.println("Saldo insuficiente ou valor inválido.");
            }
        } else {
            System.out.println("Conta não encontrada.");
        }
    }
}