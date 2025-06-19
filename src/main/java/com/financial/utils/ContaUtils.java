package src.main.java.com.financial.utils;

import java.util.List;

import src.main.java.com.financial.model.Conta;

public class ContaUtils {

    public static Conta encontrarContaPorId(List<Conta> contas, int id) {
    for (Conta conta : contas) {
        if (Conta.getIdConta() == id) {
            return conta;
        }
    }
    return null; // se não encontrar
}
}