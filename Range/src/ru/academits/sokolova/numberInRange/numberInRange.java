package ru.academits.sokolova.numberInRange;

import ru.academits.sokolova.range.Range;

import java.util.Scanner;

public class numberInRange {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите диапазон");
        Range range1 = new Range(scanner.nextDouble(), scanner.nextDouble());
        System.out.println("Длина диапазона равна " + range1.getLength());
        System.out.println("Введите число");
        if (range1.isInside(scanner.nextDouble())) {
            System.out.println("Число принадлежит диапазону");
        } else {
            System.out.println("Число не принадлежит диапазону");
        }
    }
}