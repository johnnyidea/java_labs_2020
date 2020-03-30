package com.company;

public class Rectangle implements Shape {
    private final double a, b;

    Rectangle( double a, double b){
        this.a = a;
        this.b = b;
    }

    @Override
    public double getPerimeter() {
        return 2*( a + b );
    }
}
