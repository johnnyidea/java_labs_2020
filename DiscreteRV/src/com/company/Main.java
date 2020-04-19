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

        DistributionLaw dl = new DistributionLaw(words);

        MainParam mainParam = dl.getMainParams();

        System.out.print("\nmx= " + mainParam.mx);
        System.out.print("\ndx= " + mainParam.dx);

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