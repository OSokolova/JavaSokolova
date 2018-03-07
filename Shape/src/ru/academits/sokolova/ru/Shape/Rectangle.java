package ru.academits.sokolova.ru.Shape;


public class Rectangle implements Shape {
    static final String NAME = "прямоугольник";

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

    public String toString() {
        return "Фигура " + NAME + ": периметр = " + getPerimeter() + ", площадь= " + getArea() +
                ", высота = " + getHeight() + ", ширина = " + getWidth();
    }

    public int hashCode() {
        return 17 + NAME.hashCode() +
                (int) Double.doubleToLongBits(this.side1) +
                        (int) Double.doubleToLongBits(this.side2);
    }

    public boolean equals(Shape o) {
        if (this == o) {
            return true;
        }
        if (o == null || o.getClass() != this.getClass()) {
            return false;
        }
        Rectangle t = (Rectangle) o;
        return (side1 == t.side1 && side2 == t.side2 || side1 == t.side2 && side2 == t.side1);
    }
}
