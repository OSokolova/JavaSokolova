package Main;

import ru.academits.sokolova.ru.Shape.*;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Shape[] figures = {
                new Circle(15),
                new Rectangle(26, 17),
                new Square(20),
                new Triangle(0, 15, 20, 0, 15, 20),
                new Circle(45),
                new Rectangle(30, 25),
                new Square(40),
                new Triangle(20, 50, 30, 80, 60, 20)
        };
        Arrays.sort(figures, new SortByAreaComparator());
        System.out.println(figures[figures.length - 1].toString());
        Arrays.sort(figures, new SortByPerimeterComparator());
        System.out.println(figures[figures.length - 2].toString());
    }
}
