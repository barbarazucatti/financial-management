package src.main.java.com.financial.operations.contas;

import java.util.List;
import java.util.Scanner;

import src.main.java.com.financial.model.Conta;
import src.main.java.com.financial.utils.ContaUtils;

public class Deposito implements AccountOperations {

    @Override
    public void executar(List<Conta> contas, Scanner entrada) {
        System.out.println("\nInforme o ID da conta para depósito:");
        int idContaDeposito = entrada.nextInt();
        System.out.println("Informe o valor do depósito:");
        double valorDeposito = entrada.nextDouble();

        Conta contaDeposito = ContaUtils.encontrarContaPorId(contas, idContaDeposito);

        if (contaDeposito != null) {
            ContaUtils.depositar(contaDeposito, valorDeposito); // chama o serviço
        } else {
            System.out.println("Conta não encontrada.");
        }
    }
}
