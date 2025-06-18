import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

public class Conta {
    private static int contadorContas = 1;
    private int idConta;
    static NumberFormat formatoValores = new DecimalFormat("R$ #,##0.00");
    static ArrayList<Transaction> transacoes = new ArrayList<Transaction>();
    public static Object setSaldo;
    private String banco;
    static Transaction transacao;

    private int agencia;
    private int numero;
    private static double saldo = 0;

    public Conta(int idConta, String banco, int agencia, int numero, double saldo, ArrayList<Transaction> transacoes) {
        this.banco = banco;
        this.agencia = agencia;
        this.numero = numero;
        this.saldo = saldo;
        this.idConta = contadorContas;
        this.transacoes = new ArrayList<Transaction>();
        contadorContas += 1;
    }

    public Conta(int idConta, String banco, int agencia, int numero, double saldo) {

    }

    public Conta(Transaction transacao) {
        this.transacao = transacao;
    }

    public Conta(int id, String banco2, int agencia2, int numero2, String tipoConta, double saldo2,
            ArrayList<Transaction> transacoes2) {
    }

    public int getIdConta() {
        return idConta;
    }

    public void setIdConta(int idConta) {
        this.idConta = idConta;
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

    public static double getSaldo() {
        return saldo;
    }

    public static void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public static int getContadorContas() {
        return contadorContas;
    }

    public static void setContadorContas(int contadorContas) {
        Conta.contadorContas = contadorContas;
    }

    private String formatoValores(double saldo) {
        return formatoValores.format(saldo);
    }

    public static Transaction getTransacao() {
        return transacao;
    }

    public void setTransaction(Transaction transacao) {
        this.transacao = transacao;
    }

    public ArrayList<Transaction> getTransacoes() {
        return transacoes;
    }

    public void setTransacoes(ArrayList<Transaction> transacoes) {
        this.transacoes = transacoes;
    }

    public String toString() {
        return "Conta - " + idConta + "|" + "\nBanco - " + banco + "|" + "Agência: " + agencia + "-" + numero + "|"
                + "Saldo: "
                + formatoValores(saldo) + "\n";
    }

    public void depositar(double valor) {
        if (valor > 0) {
            setSaldo(getSaldo() + valor);
            System.out.println("O seu depósito foi realizado!");
        } else {
            System.out.println("Atenção: Não foi possível realizar seu depósito.");
        }
    }

    public void sacar(double valor) {
        if (valor > 0 && this.getSaldo() >= valor) {
            setSaldo(getSaldo() - valor);
            System.out.println("Seu saque foi realizado com sucesso!");
        } else {
            if (this.getSaldo() < valor) {
                System.out.println("Saldo insuficiente! Não foi possível realizar o saque.");
            } else {
                System.out.println("Não foi possível realizar o saque. Digite um valor válido");

            }
        }
    }

    public void transferir(Conta contaDestino, double valor) {
        if (valor > 0 && this.getSaldo() >= valor) {
            setSaldo(getSaldo() - valor);
            contaDestino.saldo = contaDestino.getSaldo() + valor;
            System.out.println("Sua transferência foi realizada com sucesso!");
        } else {
            System.out.println("Não foi possível realizar a transferência");

        }

    }

    public static void atualizarSaldo(double saldo) {
        double saldoAtual = 0;
        for (Transaction transacao : transacoes) {
            if (transacao.getTipo().equalsIgnoreCase("Receita")) {
                saldoAtual += transacao.getValor();
            } else {
                saldoAtual -= transacao.getValor();

            }
        }
        double novoSaldo = getSaldo() + saldoAtual;
        setSaldo(novoSaldo);
    }

    public static void saldoTotal(){
        double saldoTotal = 0;
        for (Conta conta : contas) {
            if (Conta.getTransacao().getTipo().equalsIgnoreCase("Receita")) {
                saldoAtual += transacao.getValor();}
                saldoAtual ++;
                saldoTotal = saldoAtual;}
                else{ saldoAtual -= transacao.getValor();
                double novoSaldo = getSaldo() + saldoAtual;
                setSaldo(novoSaldo);
                }
            }
}
