package com.company;

public final class Circle implements Shape{
    private final double r;

    Circle(double r){
        this.r = r ;
    };

    @Override
    public double getPerimeter() {
        return 2 * Math.PI * r;
    }
}