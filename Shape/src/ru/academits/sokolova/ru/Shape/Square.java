package ru.academits.sokolova.ru.Shape;

public class Square implements Shape {
    static final String NAME = "квадрат";
    static final int SIDEsNUMBER = 4;
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
        return side * SIDEsNUMBER;
    }

    public int hashCode() {
        return 17 + NAME.hashCode() +
                (int) Double.doubleToLongBits(this.side);
    }

    public boolean equals(Shape o) {
        if (this == o) {
            return true;
        }
        if (o == null || o.getClass() != this.getClass()) {
            return false;
        }
        Square t = (Square) o;
        return (side == t.side);
    }

    public String toString() {
        return "Фигура " + NAME + ": периметр = " + getPerimeter() + ", площадь= " + getArea() +
                ", высота = " + getHeight() + ", ширина = " + getWidth();
    }
}
