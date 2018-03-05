package ru.academits.sokolova.ru.Shapes.Square;

import ru.academits.sokolova.ru.Shapes.Shapes;

public class Triangle extends Shapes {
    private double x1;
    private double x2;
    private double x3;
    private double y1;
    private double y2;
    private double y3;

    public Triangle(double x1, double x2, double x3, double y1, double y2, double y3) {
        this.x1 = x1;
        this.x2 = x2;
        this.x3 = x3;
        this.y1 = y1;
        this.y2 = y2;
        this.y3 = y3;
    }

    public double getWidth(double x1, double x2, double x3, double y1, double y2, double y3) {
        return Math.max(Math.max(x1, x2), x3) - Math.min(Math.min(x1, x2), x3);
    }

    public double getHeight(double x1, double x2, double x3, double y1, double y2, double y3) {
        return Math.max(Math.max(y1, y2), y3) - Math.min(Math.min(y1, y2), y3);
    }

    private double getSide(double x1,double y1, double x2, double y2) {
        return Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
    }

    public double getArea(double x1, double x2, double x3, double y1, double y2, double y3) {
        double p = this.getPerimeter(x1,x2,x3,y1,y2,y3)/2;
        return Math.sqrt(p*(p-this.getSide(x1, y1,x2, y2))*this.getSide(x1, y1,x3, y3)*this.getSide(x2, y2,x3, y3));
    }

    public double getPerimeter(double x1, double x2, double x3, double y1, double y2, double y3) {
        return this.getSide(x1, y1,x2, y2) + this.getSide(x1, y1,x3, y3) + this.getSide(x2, y2,x3, y3);
}

}
