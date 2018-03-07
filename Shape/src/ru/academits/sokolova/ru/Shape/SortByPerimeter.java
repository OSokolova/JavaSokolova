package ru.academits.sokolova.ru.Shape;

import java.util.Comparator;

public class SortByPerimeter implements Comparator<Shape> {

    public int compare(Shape figure1, Shape figure2) {
        return Double.compare(figure1.getPerimeter(), figure2.getPerimeter());
    }
}
