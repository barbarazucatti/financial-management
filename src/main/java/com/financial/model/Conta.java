package src.main.java.com.financial.model;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class Conta {
    private static int contadorContas = 1;

    private int idConta;
    private String banco;
    private int agencia;
    private int numero;
    private double saldo;
    private List<Transaction> transacoes;

    private static final NumberFormat formatoValores = new DecimalFormat("R$ #,##0.00");

    public Conta(String banco, int agencia, int numero) {
        this.idConta = contadorContas++;
        this.banco = banco;
        this.agencia = agencia;
        this.numero = numero;
        this.saldo = 0.0;
        this.transacoes = new ArrayList<>();
    }

    public int getIdConta() {
        return idConta;
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public int getAgencia() {
        return agencia;
    }

    public void setAgencia(int agencia) {
        this.agencia = agencia;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public double getSaldo() {
        return saldo;
    }

    public List<Transaction> getTransacoes() {
        return transacoes;
    }

    public void depositar(double valor) {
        if (valor > 0) {
            saldo += valor;
            System.out.println("Depósito realizado com sucesso.");
        } else {
            System.out.println("Valor inválido para depósito.");
        }
    }

    public void sacar(double valor) {
        if (valor > 0 && saldo >= valor) {
            saldo -= valor;
            System.out.println("Saque realizado com sucesso.");
        } else {
            System.out.println("Saldo insuficiente ou valor inválido.");
        }
    }

    public void transferir(Conta destino, double valor) {
        if (valor > 0 && saldo >= valor) {
            this.saldo -= valor;
            destino.saldo += valor;
            System.out.println("Transferência realizada com sucesso.");
        } else {
            System.out.println("Transferência inválida.");
        }
    }

    public void adicionarTransacao(Transaction t) {
        transacoes.add(t);
        if (t.getTipo().equalsIgnoreCase("Receita")) {
            saldo += t.getValor();
        } else {
            saldo -= t.getValor();
        }
    }

    @Override
    public String toString() {
        return "Conta #" + idConta + " - Banco: " + banco + " | Agência: " + agencia + "-" + numero +
               " | Saldo: " + formatoValores.format(saldo);
    }
}