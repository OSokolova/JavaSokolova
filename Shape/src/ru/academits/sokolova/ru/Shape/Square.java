package ru.academits.sokolova.ru.Shape;

public class Square implements Shape {
    private static final String NAME = "квадрат";
    private static final int SQUARE_SIDE_NUMBER = 4;
    private double side;

    public Square(double side) {
        this.side = side;
    }

    public double getWidth() {
        return side;
    }

    public double getHeight() {
        return side;
    }

    public double getArea() {
        return side * side;
    }

    public double getPerimeter() {
        return side * SQUARE_SIDE_NUMBER;
    }

    @Override
    public int hashCode() {
        return 17 + NAME.hashCode() +
                (int) Double.doubleToLongBits(this.side);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || o.getClass() != this.getClass()) {
            return false;
        }
        Square t = (Square) o;
        return (side == t.side);
    }

    @Override
    public String toString() {
        return "Фигура " + NAME + ": периметр = " + getPerimeter() + ", площадь= " + getArea() +
                ", высота = " + getHeight() + ", ширина = " + getWidth();
    }
}
