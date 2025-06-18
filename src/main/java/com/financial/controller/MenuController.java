package src.main.java.com.financial.controller;

import java.util.Scanner;
import java.util.ArrayList;
import java.text.SimpleDateFormat;

import src.main.java.com.financial.model.*;

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
                    gerenciadorTransacoes();
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
        System.out.println("\n       Bem vindo ao seu Gerenciador de Finanças!\n      ");
        System.out.println("        [1] Gerenciador de contas");
        System.out.println("        [2] Gerenciador de transações");
        System.out.println("        [3] Painel geral");
        System.out.println("        [4] Sair");
        System.out.println();
        System.out.println("        O que você gostaria de fazer? ");
    }

    public static void gerenciadorContas() {
        System.out.println("Aqui gerencio as contas");
    }

    public static void gerenciadorTransacoes() {
        System.out.println("Aqui gerencio as transações");
    }

    public static void painelGeral() {
        System.out.println("Aqui gerencio o painel geral");
    }

    
}
