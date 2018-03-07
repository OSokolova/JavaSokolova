package ru.academits.sokolova.ru.Shape.Main;

import ru.academits.sokolova.ru.Shape.Shape;

import java.util.Comparator;

public class SortByAreaComparator implements Comparator<Shape> {

    public int compare(Shape figure1, Shape figure2) {
        return Double.compare(figure1.getArea(), figure2.getArea());
    }
}
