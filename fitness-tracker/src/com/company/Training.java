package com.company;

public class Training{
    private String type;
    private double cal_hour;
    public double all_cal;

    Training(String type, double cal_hour){
        this.type = type;
        this.cal_hour = cal_hour;
    }

    double get_cal_hour(){
        return cal_hour;
    }

    void display(){
        System.out.println(type);
    }

    String get_type(){ return type; }

    void clear(){
        all_cal = 0.0;
    }
}