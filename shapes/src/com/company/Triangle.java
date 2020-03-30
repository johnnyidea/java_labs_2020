package com.company;

public class Triangle implements Shape {
    private final double s1, s2, s3;

    Triangle(double s1, double s2, double s3){
        this.s1 = s1;
        this.s2 = s2;
        this.s3 = s3;
    }
    @Override
    public double getPerimeter() {
        return s1 + s2 + s3;
    }
}
