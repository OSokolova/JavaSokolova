package ru.academits.sokolova.ru.Shape;

public class Circle implements Shape {
    private static final String NAME = "круг";

    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    public double getWidth() {
        return radius * 2;
    }

    public double getHeight() {
        return radius * 2;
    }

    public double getArea() {
        return Math.PI * radius * radius;
    }

    public double getPerimeter() {
        return Math.PI * radius * 2;
    }

    public int hashCode() {
        return 17 + NAME.hashCode() +
                (int) Double.doubleToLongBits(this.radius);
    }
@Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || o.getClass() != this.getClass()) {
            return false;
        }
        Circle t = (Circle) o;
        return (radius == t.radius);
    }

    public String toString() {
        return "Фигура " + NAME + ": периметр = " + getPerimeter() + ", площадь= " + getArea() +
                ", высота = " + getHeight() + ", ширина = " + getWidth();
    }
}
