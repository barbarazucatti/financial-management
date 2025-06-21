package src.main.java.com.financial.operations.contas;

import java.util.List;
import java.util.Scanner;

import src.main.java.com.financial.model.Conta;

public interface AccountOperations {
void executar(List<Conta> contas, Scanner entrada);
}
