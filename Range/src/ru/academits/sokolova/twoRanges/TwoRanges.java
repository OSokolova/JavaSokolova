package ru.academits.sokolova.twoRanges;

import ru.academits.sokolova.range.Range;

import java.util.Scanner;

public class TwoRanges {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите первый интервал");
        Range range1 = new Range(scanner.nextDouble(), scanner.nextDouble());
        System.out.println("Введите второй интервал");
        Range range2 = new Range(scanner.nextDouble(), scanner.nextDouble());

        Range rangeCross;
        rangeCross = range1.getCross(range2);
        if (rangeCross == null) {
            System.out.println("Пересечения нет");
        } else {
            System.out.println("Пересечение = " + rangeCross.getFrom() + " " + rangeCross.getTo());
        }

        System.out.println("Объединение");
        for (Range e : range1.getUnion(range2)) {
            System.out.println(e.getFrom() + " " + e.getTo());
        }

        System.out.println("Разность");
        for (Range e : range1.getDifference(range2)) {
            System.out.println(e.getFrom() + " " + e.getTo());
        }
    }
}

