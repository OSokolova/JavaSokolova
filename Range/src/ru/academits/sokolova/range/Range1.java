package ru.academits.sokolova.range;
import java.util.Scanner;

public class Range1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Range range1 = new Range();
        System.out.println("Введите диапазон");
        range1.setFrom(scanner.nextDouble());
        range1.setTo(scanner.nextDouble());
        System.out.println("Длина диапазона равна " + range1.getLength());
        System.out.println("Введите число");

        if (range1.IsInside(scanner.nextDouble())) {
            System.out.println("Число принадлежит диапазону");
        } else {
            System.out.println("Число не принадлежит диапазону");
        }
    }
}