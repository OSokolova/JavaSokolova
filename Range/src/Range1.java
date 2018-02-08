import java.util.Scanner;

public class Range1 {
    public static void main(String[] args) {
        class Range {
            private double from;
            private double to;

            public Range(double from, double to) {
                this.from = from;
                this.to = to;
            }

            public double getLength() {
                return to - from;
            }

            public boolean IsInside(double number) {
                return number >= this.from && number <= this.to;
            }
        }
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите диапазон");
        Range range1 = new Range(scanner.nextDouble(), scanner.nextDouble());
        System.out.println("Длина диапазона равна " + range1.getLength());
        System.out.println("Введите число");

        if (range1.IsInside(scanner.nextDouble())) {
            System.out.println("Число принадлежит диапазону");
        } else {
            System.out.println("Число не принадлежит диапазону");
        }
    }
}