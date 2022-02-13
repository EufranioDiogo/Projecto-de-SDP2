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
public class Optional {

    public static String in(String[] v) {
        StringBuilder query = new StringBuilder();
        query.append("(");

        try {
            if (v.length > 0) {
                for (int i = 0; i < v.length; i++) {
                    query.append(v[i]).append(",");
                }
            }
            query.append(")");
        } catch (Exception e) {
            System.err.println("Erro ao in " + e);
        }
        query.replace(query.lastIndexOf(","), query.lastIndexOf(",") + 1, "");
        return query.toString();
    }

    public static String in(List<String> v) {
        StringBuilder query = new StringBuilder();
        query.append("(");

        try {
            if (v.size() > 0) {
                for (int i = 0; i < v.size(); i++) {
                    query.append(v.get(i)).append(",");
                }
            }
            query.append(")");
        } catch (Exception e) {
            System.err.println("Erro ao in " + e);
        }
        query.replace(query.lastIndexOf(","), query.lastIndexOf(",") + 1, "");
        return query.toString();
    }

    public static String inS(String[] v) {
        StringBuilder query = new StringBuilder();
        query.append("(");

        try {
            if (v.length > 0) {
                for (int i = 0; i < v.length; i++) {
                    if (!v[i].equals("")) {
                        String s = "\'" + v[i] + "\'";
                        query.append(s).append(",");

                    }
                }
            }
            query.append("')");
        } catch (Exception e) {
            System.err.println("Erro ao in " + e);
        }
        query.replace(query.lastIndexOf("',"), query.lastIndexOf(",") + 1, "");
        return query.toString();
    }

    public static String inS(List<String> v) {
        StringBuilder query = new StringBuilder();
        query.append("(");

        try {
            if (v.size() > 0) {
                for (int i = 0; i < v.size(); i++) {
                    if (!v.get(i).equals("")) {
                        String s = "\'" + v.get(i) + "\'";
                        query.append(s).append(",");
                    }
                }
            }
            query.append("')");
        } catch (Exception e) {
            System.err.println("Erro ao in " + e);
        }
        query.replace(query.lastIndexOf("',"), query.lastIndexOf(",") + 1, "");
        return query.toString();
    }
     
      public static Integer integer(String string)
    {
        return (string == null || string.equals("")) ? 0 : Integer.parseInt(string);
    }

}
