package ru.academits.sokolova.ru.Shapes.Square;

import ru.academits.sokolova.ru.Shapes.Shapes;

public class Circle extends Shapes {
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    public double getWidth(double radius){
        return radius*2;
    }
    public double getHeight(double radius){
        return radius*2;
    }
    public double getArea(double radius){
        return Math.PI*radius*radius;
    }
    public double getPerimeter (double radius){
        return Math.PI*radius*2;
    }
}
