package src.main.java.com.financial.controller;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.text.SimpleDateFormat;

import src.main.java.com.financial.model.*;
import src.main.java.com.financial.operations.*;
import src.main.java.com.financial.operations.contas.AccountOperations;
import src.main.java.com.financial.operations.contas.Deposito;
import src.main.java.com.financial.operations.contas.Saque;
import src.main.java.com.financial.operations.contas.Transferencia;
import src.main.java.com.financial.operations.gerenciamento.CancelarConta;
import src.main.java.com.financial.operations.gerenciamento.ConsultarExtrato;
import src.main.java.com.financial.operations.gerenciamento.CriarConta;
import src.main.java.com.financial.operations.gerenciamento.FinancialOperations;
import src.main.java.com.financial.operations.gerenciamento.CancelarConta;
import src.main.java.com.financial.utils.*;

public class MenuController {

    static Scanner entrada = new Scanner(System.in);
    static ArrayList<Conta> contas = new ArrayList<Conta>();
    static ArrayList<Transaction> transacoes = new ArrayList<Transaction>();
    static int contadorContas = 1;
    static int contadorTransacoes = 1;

    public static void iniciar() {
        int opcao;

        do {
            menuPrincipal();
            opcao = entrada.nextInt();

            switch (opcao) {
                case 1:
                    gerenciadorContas();
                    break;
                case 2:
                    novaTransacao();
                    break;
                case 3:
                    painelGeral();
                    break;
                case 4:
                    System.out.println("Você está saindo do Gerenciador de Finanças!");
                    break;
                default:
                    System.out.println("Digite uma opção válida");
            }
        } while (opcao != 4);
    }

    public static void menuPrincipal() {
        System.out.println();
        System.out.println("\n       Bem vindo ao seu Gerenciador de Finanças!\n      ");
        System.out.println("        [1] Gerenciador de contas");
        System.out.println("        [2] Gerenciador de transações");
        System.out.println("        [3] Painel geral");
        System.out.println("        [4] Sair");
        System.out.println();
        System.out.println("        O que você gostaria de fazer? ");
    }

    public static void gerenciadorContas() {

        iniciarGerenciadorContas();

    }

    public static void menuGerenciadorContas() {
        System.out.println();
        System.out.println("\n       Você está gerenciando suas contas!\n      ");
        System.out.println("        [1] Criar uma nova conta");
        System.out.println("        [2] Consultar um extrato");
        System.out.println("        [3] Cancelar conta");
        System.out.println("        [4] Sair");
        System.out.println();
        System.out.println("        O que você gostaria de fazer? ");
    }

    public static void iniciarGerenciadorContas() {
        int escolha;

        do {
            menuGerenciadorContas();
            escolha = entrada.nextInt();

            switch (escolha) {
                case 1:
                    criarConta();
                    break;
                case 2:
                    extratoConta();
                    break;
                case 3:
                    cancelamento();
                    break;
                case 4:
                    System.out.println("Você está saindo do Gerenciador de Finanças!");
                    break;
                default:
                    System.out.println("Digite uma opção válida");
            }
        } while (escolha != 4);
    }

    public static void criarConta() {
        FinancialOperations criarConta = new CriarConta();
        criarConta.executar(contas, entrada);

    }

    //// depois de criar uma nova conta:

   public static void repetirOperacaoTransacao(){
   
    System.out.println("Você gostaria de fazer alguma transação nessa conta? (s/n)");

    String op = entrada.nextLine();

    if(op.equalsIgnoreCase("s"))
    {
        novaTransacao();
    }
    else
    {
        gerenciadorContas();
    }
}

public static void repetirOperacaoConta() {
    System.out.println("Você gostaria de cadastrar outras contas? (s/n)");
    String op2 = entrada.nextLine();

    if (op2.equalsIgnoreCase("s")) {
        criarConta();
     }

        System.out.println("Você gostaria de cadastrar uma nova transação nessa conta? (s/n)");
        String op3 = entrada.nextLine();

        if (op3.equalsIgnoreCase("s")) {
            novaTransacao();
        } else {
            System.out.println("Vamos retornar para o Menu de Gerenciamento de Contas!");
            gerenciadorContas();
        }
    }


    public static void extratoConta() {

    FinancialOperations consultarExtrato = new ConsultarExtrato();
    consultarExtrato.executar(contas, entrada);
       
    }

    public static void cancelamento() {

    
    FinancialOperations cancelarConta = new CancelarConta();
    cancelarConta.executar(contas, entrada);

    }
//// ORGANIZAR FLUXO DO MENU A PARTIR DAQUI
/// 
    public static void novaTransacao() {
        iniciarNovaTransacao();

    }

    public static void menuNovaTransacao() {
        System.out.println();
        System.out.println("\n       Você fazendo uma transação!\n      ");
        System.out.println("        [1] Depositar");
        System.out.println("        [2] Sacar");
        System.out.println("        [3] Transferir fundos");
        System.out.println("        [4] Sair");
        System.out.println();
        System.out.println("        O que você gostaria de fazer? ");
    }

    public static void iniciarNovaTransacao() {
        int escolha;

        do {
            menuNovaTransacao();
            escolha = entrada.nextInt();

            AccountOperations saque = new Saque();
            AccountOperations deposito = new Deposito();
            AccountOperations transferencia = new Transferencia();

            switch (escolha) {
                case 1:
                    deposito.executar(contas, entrada);
                    break;
                case 2:
                    saque.executar(contas, entrada);
                    break;

                case 3:
                    transferencia.executar(contas, entrada);
                    break;
                case 4:
                    System.out.println("Você está saindo do menu de transações!");
                    break;
                default:
                    System.out.println("Digite uma opção válida");
            }
        } while (escolha != 4);
    }

    public static void painelGeral() {
        System.out.println("Aqui gerencio o painel geral");
    }
}


