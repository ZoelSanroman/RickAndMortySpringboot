package com.cebem.rickandmorty.controllers.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Utils {
    public static boolean isPalindrome(String word) {
        String inverseWord = new StringBuilder(word).reverse().toString();
        return inverseWord.equalsIgnoreCase(word);

    }

    public static void writeOnDisk(String fileName, String info) throws IOException {
        FileWriter fw = null;
        try {
            fw = new FileWriter(fileName, true);
            fw.write(info);
            ;
        } finally {
            if (fw != null)
                fw.close();
        }
    }

    public static boolean deleteFromDisk(String fileName) {
        File f = new File(fileName);
        return f.delete();
    }

    public static String readFromDisk(String fileName) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        } finally {
            if (br != null) {
                br.close();
            }
        }
        return sb.toString();
    }

    public static void emptyFile(String archivoPath) throws IOException {
        PrintWriter writer = new PrintWriter(new FileWriter(archivoPath));
        writer.print("");
        writer.close();
    }

    public static float maxOfElements(float... numeros) {
        if (numeros == null || numeros.length == 0) {
            throw new NumberFormatException();
        }
        float mayor = numeros[0];
        for (int i = 1; i < numeros.length; i++) {
            if (numeros[i] > mayor) {
                mayor = numeros[i];
            }
        }
        return mayor;
    }
    public static String capitalizar(String frase) {
        String[] palabras = frase.split("\\s+");
        StringBuilder resultado = new StringBuilder();
        for (String palabra : palabras) {
            if (!palabra.isEmpty()) {
                String primeraLetra = palabra.substring(0, 1).toUpperCase();
                String restoPalabra = palabra.substring(1).toLowerCase();
                resultado.append(primeraLetra).append(restoPalabra).append(" ");
            }
        }
        return resultado.toString().trim();
    }

    public static int getRandomValue(int max){
        return (int) Math.floor(Math.random() * max);
    }
}