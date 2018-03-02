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
            this.array = Arrays.copyOf(array, n);
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
        return s.insert(0, "{").delete(s.length() - 2, s.length()).insert(s.length(), "}").toString();
    }

    public Vector getSum(Vector other) {
        if (array.length < other.array.length) {
            array = Arrays.copyOf(array, other.array.length);
        } else {
            other.array = Arrays.copyOf(other.array, array.length);
        }
        for (int i = 0; i < array.length; i++) {
            array[i] = array[i] + other.array[i];
        }
        return this;
    }

    public Vector getDiff(Vector other) {
        if (array.length < other.array.length) {
            array = Arrays.copyOf(array, other.array.length);
        } else {
            other.array = Arrays.copyOf(other.array, array.length);
        }
        for (int i = 0; i < array.length; i++) {
            array[i] = array[i] - other.array[i];
        }
        return this;
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
        if (index >= array.length || index < 0) {
            throw new IndexOutOfBoundsException("Неправильный индекс");
        } else {
            array[index] = number;
        }
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o.getClass() == null || o.getClass() != this.getClass()) {
            return false;
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
        if (vector1.array.length < vector2.array.length) {
            Vector copyVector2 = new Vector(vector2);
            return new Vector(vector1.getSum(copyVector2));
        } else {
            Vector copyVector1 = new Vector(vector1);
            return new Vector(copyVector1.getSum(vector2));
        }
    }

    public static Vector getDiff(Vector vector1, Vector vector2) {
        if (vector1.array.length < vector2.array.length) {
            Vector copyVector2 = new Vector(vector2);
            return new Vector(vector1.getDiff(copyVector2));
        } else {
            Vector copyVector1 = new Vector(vector1);
            return new Vector(copyVector1.getDiff(vector2));
        }
    }

    public static double getMult(Vector vector1, Vector vector2) {
        int n = Math.min(vector1.array.length, vector2.array.length);
        double mult = 0;
        for (int i = 0; i < n; i++) {
            mult += vector1.array[i] * vector2.array[i];
        }
        return mult;
    }
}

