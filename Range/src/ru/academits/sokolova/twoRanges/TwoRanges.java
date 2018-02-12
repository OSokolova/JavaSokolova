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

        Range range1Copy = new Range(range1.getFrom(), range1.getTo());
        Range range2Copy = new Range(range2.getFrom(), range2.getTo());

        Range rangeCross = range1Copy.getCross(range2Copy);
        if (rangeCross == null) {
            System.out.println("Пересечения нет");
        } else {
            System.out.println("Пересечение = " + rangeCross.getFrom() + " " + rangeCross.getTo());
        }

        System.out.println("Объединение");
        for (Range e : range1Copy.getUnion(range2Copy)) {
            System.out.println(e.getFrom() + " " + e.getTo());
        }

        Range[] rangeDifference = range1Copy.getDifference(range2Copy);
        System.out.println("Разность");
        if (rangeDifference == null) {
            System.out.println("пустое множество");
        } else {
            for (Range e : rangeDifference) {
                System.out.println(e.getFrom() + " " + e.getTo());
            }
        }
    }
}

