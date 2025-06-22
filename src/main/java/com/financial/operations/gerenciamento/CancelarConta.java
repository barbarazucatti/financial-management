package src.main.java.com.financial.operations.gerenciamento;

import java.util.List;
import java.util.Scanner;

import src.main.java.com.financial.controller.MenuController;
import src.main.java.com.financial.model.Conta;
import src.main.java.com.financial.model.Transaction;
import src.main.java.com.financial.utils.ContaUtils;

public class CancelarConta implements FinancialOperations{

    public void executar(List<Conta> contas, Scanner entrada) {
        
    System.out.println("Digite o ID da conta que deseja cancelar: ");
    int idConta = entrada.nextInt();

    Conta contaEncontrada = ContaUtils.encontrarContaPorId(contas, idConta);

        System.out.println("Você tem certeza que deseja cancelar a conta" + idConta + "? (s/n)");
        String op2 = entrada.nextLine();

        if (contaEncontrada == null) {
            System.out.println("Conta não encontrada.");
            return;
        }

        if (op2.equalsIgnoreCase("s")) {

            contas.remove(contaEncontrada);

            } else {
            MenuController.gerenciadorContas();
            }
        
        System.out.println("O pedido de cancelamento da conta foi feito. Aguarde o contato do banco para os próximos passos.");

    }
    }

