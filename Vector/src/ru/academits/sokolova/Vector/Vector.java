package ru.academits.sokolova.Vector;

import java.util.Arrays;

public class Vector {
    private double[] array;

    public Vector(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Вектор такой длины не существует");
        } else {
            this.array = new double[n];
        }
    }

    public Vector(Vector vector) {
        this.array = Arrays.copyOf(vector.array, vector.array.length);
    }

    public Vector(double... array) {
        if (array.length == 0) {
            throw new IllegalArgumentException("Вектор такой длины не существует");
        } else {
            this.array = Arrays.copyOf(array, array.length);
        }
    }

    public Vector(int n, double... array) {
        if (n <= 0) {
            throw new IllegalArgumentException("Вектор такой длины не существует");
        } else {
            this.array = new double[n];
            for (int i = 0; i < n; i++) {
                this.array[i] = (i < array.length) ? array[i] : 0;
            }
        }
    }

    public int getSize() {
        return array.length;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            s.append(getComponent(i)).append(", ");
        }
        return s.delete(s.length() - 2, s.length()).insert(0, "{").insert(s.length(), "}").toString();
    }

    public Vector getSum(Vector other) {
        if (this.array.length >= other.array.length) {
            for (int i = 0; i < other.array.length; i++) {
                this.array[i] = this.array[i] + other.array[i];
            }
        } else {
            double[] array = new double[other.array.length];
            for (int i = 0; i < other.array.length; i++) {
                array[i] = (i < this.array.length) ? this.array[i] + other.array[i] : other.array[i];
            }
            this.array = array;
        }
        return this;
    }

    public Vector getDiff(Vector other) {
        return this.getSum(other.getInversion());
    }

    public Vector getMult(double scalar) {
        for (int i = 0; i < array.length; i++) {
            this.array[i] *= scalar;
        }
        return this;
    }

    public Vector getInversion() {
        return this.getMult(-1);
    }

    public double getLength() {
        double length = 0;
        for (double e : array) {
            length += e * e;
        }
        return Math.sqrt(length);
    }

    public double getComponent(int index) {
        if ((index >= 0) && (index < array.length)) {
            return array[index];
        } else {
            throw new IndexOutOfBoundsException("Индекс больше размерности вектора");
        }
    }

    public void setComponent(int index, double number) {
        if (index >= array.length) {
            throw new IndexOutOfBoundsException("Индекс больше размерности вектора");}
            else if (index < 0){
            throw new IndexOutOfBoundsException("Отрицательного индекса не существует");
        } else {
            array[index] = number;
        }
    }

    public boolean equals(Object o) {
        if (o.getClass() != this.getClass()) {
            return false;
        }
        if (this == o) {
            return true;
        }
        Vector v = (Vector) o;
        if (array.length != v.array.length) {
            return false;
        }
        for (int i = 0; i < array.length; i++) {
            if (array[i] != v.array[i]) {
                return false;
            }
        }
        return true;
    }

    public int hashCode() {
        final int number = 17;
        long result = 1;
        for (double e : array) {
            result = result * number + Double.doubleToLongBits(e);
        }
        return (int) result;
    }

    public static Vector getSum(Vector vector1, Vector vector2) {
        Vector copyVector2 = new Vector(vector2);
        return new Vector(vector1.getSum(copyVector2));
    }

    public static Vector getDiff(Vector vector1, Vector vector2) {
        Vector copyVector2 = new Vector(vector2);
        return new Vector(vector1.getDiff(copyVector2));
    }

    public static double getMult(Vector vector1, Vector vector2) {
        double[] array1 = Arrays.copyOf(vector1.array, vector1.array.length);
        double[] array2 = Arrays.copyOf(vector2.array, vector2.array.length);
        int n = Math.min(array1.length, array2.length);
        double mult = 0;
        for (int i = 0; i < n; i++) {
            mult += array1[i] * array2[i];
        }
        return mult;
    }
}

