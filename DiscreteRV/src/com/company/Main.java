package com.company;
/*
Длина слова - дискретная случайная величина X. На вход программе передается текст.
Найти закон распределения X в форме таблицы, например:
Xi  4         5         8
Pi  0.23    0.5     0.27
Вычислите мат. ожидание и дисперсию X.
 */

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        //основные числовые характеристики
        double  mx = 0, dx = 0, mx2 = 0;

        ArrayList<String> words = new ArrayList<>();
        try (FileReader f_r = new FileReader("words.txt")) {
            words =  parseReadFromFile(f_r);
        } catch (IOException e) {
            System.err.println(e.getMessage());
            System.err.println("Terminating process");
            return;
        }

        int[] x = new int[words.size()];
        double[] p = new double [words.size()];
        int n = 0;

        for (int i = 0; i < words.size(); i++) n += words.get(i).length();

        System.out.println("Input words:");
        for (int i = 0; i < words.size(); i++) {
            System.out.print(words.get(i) + " ");
            x[i] = words.get(i).length();
            p[i] = words.get(i).length() /(double) n;
            BigDecimal bd = new BigDecimal(p[i]).setScale(2, RoundingMode.HALF_EVEN);
            p[i] = bd.doubleValue();
        }

        System.out.println("\nЗакон распределения X в задан в форме таблицы:");

        System.out.print("X:");
        for (int i = 0; i < x.length; i++){
            System.out.print( "   " + x[i]);
            //заодно вычислим все осн. параметры
            mx += x[i]*p[i];
            mx2 +=  Math.pow(x[i], 2) * p[i];
        }

        System.out.print("\nP:");
        for (int i = 0; i < x.length; i++){
            System.out.print(p[i] + " ");
        }

        System.out.print("\nmx= " + mx);
        System.out.print("\ndx= " + (mx2 - Math.pow(mx, 2)));

    }

    private static ArrayList<String> parseReadFromFile(InputStreamReader reader) throws IOException{

        ArrayList<String> res = new ArrayList<>();
        Scanner sc = new Scanner(reader);

        while (sc.hasNext()) {
            String[] parser = sc.nextLine().split(" ");
            int sz = parser.length;
            for (int i = 0; i < sz; i++) res.add(parser[i]);
        }
        return res;
    }
}