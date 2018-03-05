package ru.academits.sokolova.ru.Shapes.Square;

import ru.academits.sokolova.ru.Shapes.Shapes;

public class Square extends Shapes {
    private double side;

    public Square(double side) {
        this.side = side;
    }
    public double getWidth(double side){
        return side;
    }
    public double getHeight(double side){
        return side;
    }
    public double getArea(double side){
        return side*side;
    }
    public double getPerimeter (double side){
        return side*4;
    }
}
