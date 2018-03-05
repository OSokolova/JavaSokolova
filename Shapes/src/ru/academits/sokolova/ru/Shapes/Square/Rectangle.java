package ru.academits.sokolova.ru.Shapes.Square;


import ru.academits.sokolova.ru.Shapes.Shapes;

public class Rectangle extends Shapes {
    private double side1;
    private double side2;

    public Rectangle(double side1, double side2) {
        this.side1 = side1;
        this.side2 = side2;
    }

    public double getWidth(double side1,double side2){
        return Math.max(side1,side2);
    }
    public double getHeight(double side1, double side2){
        return Math.min(side1,side2);
    }
    public double getArea(double side1,double side2){
        return side1*side2;
    }
    public double getPerimeter (double side1, double side2){
        return (side1+side2)*2;
    }


}
