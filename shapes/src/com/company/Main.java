
//Создать 10 случайных фигур Circle, Triangle, Rectangle со случайными параметрами – радиусом, длиной стороны.
//Все фигуры имплементируют интерфейс Shape с одним методом getPerimeter():double, возвращающим периметр (или длину окружности)
//Найти и вывести информацию о фигуре с максимальным/минимальным периметром
//12Б
package com.company;

import java.math.*;
import java.util.*;
import java.util.Map;

public class Main {
    public enum Random_shape {
        _circle,
        _triangle,
        _rectangle;
    }

    public static void main(String[] args) {
	// write your code here
        HashMap < Double, Random_shape> res = new HashMap<>();
        for(int i = 0; i < 10; i++){

            int rand_shape_num = (int)(Math.random() * 3);
            double rand_param1 = (double)(Math.random() * 10 + 5);
            double rand_param2 = (double)(Math.random() * 10 + 5);
            double rand_param3 = (double)(Math.random() * 10 + 5);
            Random_shape shape = Random_shape.values()[rand_shape_num];

            switch (shape) {
                case _circle:
                    Circle c = new Circle(rand_param1);
                    res.put( c.getPerimeter() , Random_shape._circle);
                    break;
                case _triangle:
                    Triangle t = new Triangle( rand_param1, rand_param2, rand_param3 );
                    res.put( t.getPerimeter() , Random_shape._triangle);
                    break;
                case _rectangle:
                    Rectangle r = new Rectangle( rand_param1, rand_param2 );
                    res.put( r.getPerimeter() , Random_shape._rectangle);
                    break;
                default:
                    System.out.println("Something went wrong.");
                    break;
            }
        }

        SortedSet<Double> keys = new TreeSet<>(res.keySet());
        System.out.println( "the largest perimeter is " + keys.last() + " at the " +  res.get(keys.last()) );
        System.out.println( "the smallest perimeter is " + keys.first() + " at the " +  res.get(keys.first()) );

        return;
    }
}
