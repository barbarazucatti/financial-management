package src.main.java.com.financial.service;

import java.util.List;

import src.main.java.com.financial.model.Conta;
import src.main.java.com.financial.model.Transaction;

public class FinancialOperation {

     public static void depositar(Conta conta, double valor) {
        if (valor > 0) {
            conta.setSaldo(conta.getSaldo() + valor);
            System.out.println("Depósito realizado com sucesso.");
        } else {
            System.out.println("Valor inválido para depósito.");
        }
    }

    public static void sacar(Conta conta, double valor) {
        if (valor > 0 && conta.getSaldo() >= valor) {
            conta.setSaldo(conta.getSaldo() - valor);
            System.out.println("Saque realizado com sucesso.");
        } else {
            System.out.println("Saldo insuficiente ou valor inválido.");
        }
    }
    public static void transferir(Conta origem, Conta destino, double valor) {
        if (valor > 0 && origem.getSaldo() >= valor) {
            origem.setSaldo(origem.getSaldo() - valor);
            destino.setSaldo(destino.getSaldo() + valor);
            System.out.println("Transferência realizada com sucesso.");
        } else {
            System.out.println("Transferência inválida.");
        }
    }

    public static void adicionarTransacao(Conta conta, Transaction t) {
        List<Transaction> transacoes = conta.getTransacoes();
        transacoes.add(t);
        conta.setTransacoes(transacoes);

        if (t.getTipo().equalsIgnoreCase("Receita")) {
            conta.setSaldo(conta.getSaldo() + t.getValor());
        } else {
            conta.setSaldo(conta.getSaldo() - t.getValor());
        }
    }    
}
