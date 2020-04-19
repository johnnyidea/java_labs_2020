package com.company;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

public class DistributionLaw {
    private ArrayList<String> words;
    private int n = 0 ;
    public DistributionLaw(ArrayList<String> words){
        this.words = words;
    }


    public MainParam getMainParams(){
        MainParam params = new MainParam();
        params.x = new int[words.size()];
        params.p = new double [words.size()];

        for (int i = 0; i < words.size(); i++) n += words.get(i).length();

        System.out.println("Input words:");
        for (int i = 0; i < words.size(); i++) {
            System.out.print(words.get(i) + " ");
            params.x[i] = words.get(i).length();
            params.p[i] = words.get(i).length() /(double) n;
            BigDecimal bd = new BigDecimal(params.p[i]).setScale(2, RoundingMode.HALF_EVEN);
            params.p[i] = bd.doubleValue();
        }

        System.out.println("\nЗакон распределения X в задан в форме таблицы:");

        System.out.print("X:");
        for (int i = 0; i < params.x.length; i++){
            System.out.print( "   " + params.x[i]);
            //заодно вычислим осн. параметры
            params.mx += params.x[i]*params.p[i];
            params.mx2 +=  Math.pow(params.x[i], 2) * params.p[i];
        }

        System.out.print("\nP:");
        for (int i = 0; i < params.x.length; i++){
            System.out.print(params.p[i] + " ");
        }

        params.dx = (params.mx2 - Math.pow(params.mx, 2));

        return params;
    }


}
