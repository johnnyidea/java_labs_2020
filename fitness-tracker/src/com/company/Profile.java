package com.company;

public class Profile {
    private String name;
    private double all_cal = 0;
    private double all_time = 0;

    Profile(String name){
        this.name = name;
    }

    public void add_time(double dt){
        this.all_time += dt;
    }

    public void add_cal(double new_cal){
        this.all_cal += new_cal;
    }


    public String get_name(){
        return this.name;
    }

    public double get_time(){
        return this.all_time;
    }

    public double get_cal(){
        return this.all_cal;
    }
}
