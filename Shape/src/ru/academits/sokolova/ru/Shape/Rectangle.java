package ru.academits.sokolova.ru.Shape;


public class Rectangle implements Shape {
    private static final String NAME = "прямоугольник";

    private double side1;
    private double side2;

    public Rectangle(double side1, double side2) {
        this.side1 = side1;
        this.side2 = side2;
    }

    public double getWidth() {
        return Math.max(side1, side2);
    }

    public double getHeight() {
        return Math.min(side1, side2);
    }

    public double getArea() {
        return side1 * side2;
    }

    public double getPerimeter() {
        return (side1 + side2) * 2;
    }

@Override
    public String toString() {
        return "Фигура " + NAME + ": периметр = " + getPerimeter() + ", площадь= " + getArea() +
                ", высота = " + getHeight() + ", ширина = " + getWidth();
    }

@Override
    public int hashCode() {
        return 17 + NAME.hashCode() +
                (int) Double.doubleToLongBits(this.side1) +
                        (int) Double.doubleToLongBits(this.side2);
    }

@Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || o.getClass() != this.getClass()) {
            return false;
        }
        Rectangle t = (Rectangle) o;
        return (side1 == t.side1 && side2 == t.side2);
    }
}
