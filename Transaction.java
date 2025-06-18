import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import javax.xml.crypto.Data;

public class Transaction {

    // variáveis de instância
    private int idTransacao;
    private double valor;
    private int data;
    private String tipo, categoria, descricao;
    protected static Conta conta;
    static ArrayList<Transaction> transacoes = new ArrayList<Transaction>();

    /**
     * Construtor para objetos da classe Transacao para o tipo Receita
     */
    public Transaction(int idTransacao, double valor, int data, String tipo, String categoria, String descricao) {
        // inicializa variáveis de instância
        this.idTransacao = idTransacao;
        this.valor = valor;
        this.data = data;
        this.tipo = tipo;
        this.categoria = categoria;
        this.descricao = descricao;
        this.conta = conta;

    }

    /**
     * Métodos de acesso
     */
    public void setValor(double valor) {
        this.valor = valor;
    }

    public void setData(int data) {
        this.data = data;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }

    public void setIdTransacao(int idTransacao) {
        this.idTransacao = idTransacao;
    }

    public int getIdTransacao() {
        return idTransacao;
    }

    public double getValor() {
        return valor;
    }

    public int getData() {
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

    public void setTransacoes(List<Transaction> transacoes) {
    }

    public ArrayList<Transaction> getTransacoes() {
        return transacoes;
    }

    @Override
    public String toString() {
        return "Transação " + idTransacao + " ->" + "Valor: " + valor + "|" + data + "|" + categoria + "|" + descricao
                + "|"
                + "Tipo de transação: " + tipo + " | ";
    }

}
