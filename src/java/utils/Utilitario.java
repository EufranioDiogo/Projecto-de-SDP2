/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Canga
 */
public class Utilitario {

    public static List<String> convertNumeroToOperadora(List<String> telefones) {
        List<String> operadoras = new ArrayList<>();
        for (String telefone : telefones) {
            if (StringUtils.containsAny(telefone, "91", "99")) {
                if (!operadoras.contains("Movicel")) {
                    operadoras.add("Movicel");
                }
            } else if (StringUtils.containsAny(telefone, "92", "93", "94")) {
                if (!operadoras.contains("Unitel")) {
                    operadoras.add("Unitel");
                }
            } else if (StringUtils.containsAny(telefone, "22", "23")) {
                if (!operadoras.contains("Angola Telecom")) {
                    operadoras.add("Angola Telecom");
                }
            } else {
                if (!operadoras.contains("Outros")) {
                    operadoras.add("Outros");
                }
            }
        }
        return operadoras;
    }

    public static List<String> operadoraToNumber(List<String> operadoras) {
        List<String> list = new ArrayList<>();
        for (String operadora : operadoras) {
            if (operadora.equalsIgnoreCase("unitel")) {
                list.add("92");
                list.add("93");
                list.add("94");
            } else if (operadora.equalsIgnoreCase("movicel")) {
                list.add("91");
                list.add("99");
            } else if (operadora.equalsIgnoreCase("Angola Telecom")) {
                list.add("22");
                list.add("23");
            }
        }
        return list;
    }
}
