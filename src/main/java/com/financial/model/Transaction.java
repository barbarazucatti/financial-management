package src.main.java.com.financial.model;

import java.util.Date;

public class Transaction {
    private int idTransacao;
    private double valor;
    private Date data;
    private String tipo;
    private String categoria;
    private String descricao;
    private Conta conta;

    public Transaction(int idTransacao, double valor, Date data, String tipo, String categoria, String descricao, Conta conta) {
        this.idTransacao = idTransacao;
        this.valor = valor;
        this.data = data;
        this.tipo = tipo;
        this.categoria = categoria;
        this.descricao = descricao;
        this.conta = conta;
    }

    public int getIdTransacao() {
        return idTransacao;
    }

    public double getValor() {
        return valor;
    }

    public Date getData() {
        return data;
    }

    public String getTipo() {
        return tipo;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getDescricao() {
        return descricao;
    }

    public Conta getConta() {
        return conta;
    }

    
    @Override
    public String toString() {
        return "Transação #" + idTransacao + " | " + tipo + " | " + categoria + " | " + descricao +
               " | Valor: R$" + String.format("%.2f", valor) + " | Data: " + data;
    }
}