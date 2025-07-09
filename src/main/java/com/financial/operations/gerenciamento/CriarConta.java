package src.main.java.com.financial.operations.gerenciamento;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import src.main.java.com.financial.controller.MenuController;
import src.main.java.com.financial.model.Conta;
import src.main.java.com.financial.model.Transaction;
import src.main.java.com.financial.utils.ContaUtils;

public class CriarConta implements FinancialOperations {

    ArrayList<Conta> contas = new ArrayList<Conta>();

    @Override
    public void executar(List<Conta> contas, Scanner entrada) {

        System.out.println("Você está criando uma nova conta");
        System.out.println();

        System.out.println("Digite o banco da sua conta:");
        String banco = entrada.nextLine();
        entrada.nextLine();

        System.out.println("Digite a sua agência:");
        int agencia = entrada.nextInt();
        entrada.nextLine();

        System.out.println("Digite o número da conta:");
        int numero = entrada.nextInt();
        entrada.nextLine();

        ContaUtils utils = new ContaUtils();
        int novoId = utils.gerarNovoId();
        Conta novaConta = new Conta(novoId, banco, agencia, numero, 0);
        ContaUtils.adicionarConta(contas, novaConta);
        
        System.out.println("\n Conta criada com sucesso!");
        System.out.println(" ");
        System.out.println(novaConta);
        
        for (Conta conta : contas) {
        System.out.println("Lista de contas: " + conta);
    }

        MenuController.repetirOperacaoConta();    
    }
}