package src.main.java.com.financial.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Transaction {
    private int idTransacao;
    private double valor;
    private LocalDate data;
    private String tipo;
    private String categoria;
    private String descricao;
    private Conta conta;
    private static int contador = 1;
    
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");



    public Transaction(double valor, String tipo, String categoria, String descricao) {
        this.idTransacao = contador++;
        this.valor = valor;
        this.tipo = tipo;
        this.categoria = categoria;
        this.descricao = descricao;
        //this.conta = conta;
        this.data = LocalDate.now();
    }

    public int getIdTransacao() {
        return idTransacao;
    }

    public double getValor() {
        return valor;
    }
    
    public LocalDate getData() {
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
        return "Transação #" + idTransacao +
               " | " + tipo +
               " | " + categoria +
               " | " + descricao +
               " | Valor: R$" + String.format("%.2f", valor) +
               " | Data: " + data.format(formatter);
    }
}
