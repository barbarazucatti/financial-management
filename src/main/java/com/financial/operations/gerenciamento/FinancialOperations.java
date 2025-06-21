package src.main.java.com.financial.operations.gerenciamento;

import java.util.List;
import java.util.Scanner;

import src.main.java.com.financial.model.Conta;

public interface FinancialOperations {

    public void executar(List<Conta> contas, Scanner entrada);
    
}