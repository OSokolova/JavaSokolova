package ru.academits.sokolova.ru.Shape;

public class Triangle implements Shape {
    static final String NAME = "треугольник";

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

    public double getWidth() {
        return Math.max(Math.max(x1, x2), x3) - Math.min(Math.min(x1, x2), x3);
    }

    public double getHeight() {
        return Math.max(Math.max(y1, y2), y3) - Math.min(Math.min(y1, y2), y3);
    }

    private double getSide(double x1, double y1, double x2, double y2) {
        return Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
    }

    public double getArea() {
        double p = this.getPerimeter() / 2;
        return Math.sqrt(p * (p - this.getSide(x1, y1, x2, y2)) * this.getSide(x1, y1, x3, y3) * this.getSide(x2, y2, x3, y3));
    }

    public double getPerimeter() {
        return this.getSide(x1, y1, x2, y2) + this.getSide(x1, y1, x3, y3) + this.getSide(x2, y2, x3, y3);
    }

    public String toString() {
        return "Фигура " + NAME + ": периметр = " + getPerimeter() + ", площадь= " + getArea() +
                ", высота = " + getHeight() + ", ширина = " + getWidth();
    }

    public int hashCode() {
        return 17 + NAME.hashCode() +
                (int) Double.doubleToLongBits(this.getPerimeter()) +
                (int) Double.doubleToLongBits(this.getArea() +
                        (int) Double.doubleToLongBits(this.getWidth() +
                                (int) Double.doubleToLongBits(this.getHeight())));
    }


    public boolean equals(Shape o) {
        if (this == o) {
            return true;
        }
        if (o == null || o.getClass() != this.getClass()) {
            return false;
        }
        Triangle t = (Triangle) o;
        if (getSide(x1, y1, x2, y2) != t.getSide(x1, y1, x2, y2) && getSide(x1, y1, x2, y2) != t.getSide(x2, y2, x3, y3)
                && getSide(x1, y1, x3, y3) != t.getSide(x1, y1, x3, y3)) {
            return false;
        } else if (getSide(x1, y1, x3, y3) != t.getSide(x1, y1, x2, y2) && getSide(x1, y1, x2, y2) != t.getSide(x2, y2, x3, y3)
                && getSide(x1, y1, x3, y3) != t.getSide(x1, y1, x3, y3)) {
            return false;
        } else if (getSide(x2, y2, x3, y3) != t.getSide(x1, y1, x2, y2) && getSide(x1, y1, x2, y2) != t.getSide(x2, y2, x3, y3)
                && getSide(x1, y1, x3, y3) != t.getSide(x1, y1, x3, y3)) {
            return false;
        }
        return true;
    }

}
