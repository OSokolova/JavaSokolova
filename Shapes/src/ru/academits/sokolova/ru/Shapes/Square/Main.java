package ru.academits.sokolova.ru.Shapes.Square;

import ru.academits.sokolova.ru.Shapes.Shapes;

import java.util.Arrays;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
        Shapes[] figures = {
                new Circle(15),
                new Rectangle(26, 17),
                new Square(20),
                new Triangle(0, 15, 20, 0, 15, 20),
                new Circle(45),
                new Rectangle(30, 25),
                new Square(40),
                new Triangle(20, 50, 30, 80, 60, 20)
        };
        Arrays.sort(figures, new Comparator<Shapes>() {
            @Override
            public int compare(Shapes figure1, Shapes figure2) {
                return figure1.getArea().compareTo(figure2.getArea());
            }
        });
        System.out.println(figures[figures.length-1].getArea());
    }
}
