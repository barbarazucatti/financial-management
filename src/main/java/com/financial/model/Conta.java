package src.main.java.com.financial.model;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class Conta {

    private static int contadorContas = 1;

    private static int idConta;
    private String banco;
    private int agencia;
    private int numero;
    private double saldo;
    private List<Transaction> transacoes;

    private static final NumberFormat formatoValores = new DecimalFormat("R$ #,##0.00");

    public Conta(String banco, int agencia, int numero) {
        this.banco = banco;
        this.agencia = agencia;
        this.numero = numero;
    }

    public Conta(int contadorContas, String banco, int agencia, int numero, double saldo) {
        
        this.idConta = contadorContas++;
        this.banco = banco;
        this.agencia = agencia;
        this.numero = numero;
        this.saldo = saldo;
        this.transacoes = new ArrayList<>();
    }

    public static int getIdConta() {
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

    public void setSaldo(double saldo) {
        this.saldo = saldo;

        }

    public List<Transaction> getTransacoes() {
       return transacoes;
    }

    public void setTransacoes(List<Transaction> transacoes) {
        this.transacoes = transacoes;
}

        @Override
    public String toString() {
        return "Conta #" + idConta + " - Banco: " + banco + " | Agência: " + agencia + "-" + numero +
               " | Saldo: " + formatoValores.format(saldo);
    }



    
}
