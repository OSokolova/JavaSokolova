package ru.academits.sokolova.Vector;

public class Vector {
    private double[] array;

    public Vector(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Вектор такой длины не существует");
        } else {
            this.array = new double[n];
            for (int i = 0; i < n; i++) {
                this.array[i] = 0;
            }
        }
    }

    public Vector(Vector vector) {
        this.array = new double[vector.array.length];
        System.arraycopy(vector.array, 0, this.array, 0, vector.array.length);

    }

    public Vector(double... array) {
        if (array.length == 0) {
            throw new IllegalArgumentException("Вектор такой длины не существует");
        } else {
            this.array = new double[array.length];
            System.arraycopy(array, 0, this.array, 0, array.length);
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
        return "{" + s.delete(s.length() - 2, s.length()) + "}";
    }

    public Vector getSum(Vector other) {
        Vector copyThis = new Vector(this);
        Vector copyOther = new Vector(other);
        int n = Math.max(copyThis.array.length, copyOther.array.length);
        this.array = new double[n];
        for (int i = 0; i < n; i++) {
            this.array[i] = ((i < copyThis.array.length) ? copyThis.array[i] : 0) + ((i < copyOther.array.length) ? copyOther.array[i] : 0);
        }
        return this;
    }

    public Vector getDiff(Vector other) {
        Vector copyThis = new Vector(this);
        Vector copyOther = new Vector(other);
        int n = Math.max(copyThis.array.length, copyOther.array.length);
        this.array = new double[n];
        for (int i = 0; i < n; i++) {
            this.array[i] = ((i < copyThis.array.length) ? copyThis.array[i] : 0) - ((i < copyOther.array.length) ? copyOther.array[i] : 0);
        }
        return this;
    }

    public Vector getMult(double scalar) {
        for (int i = 0; i < array.length; i++) {
            this.array[i] = array[i] * scalar;
        }
        return this;
    }

    public Vector getInversion() {
        this.getMult(-1);
        return this;
    }

    public double getLength() {
        double length = 0;
        for (double e :
                array) {
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

    public void setComponent(int index, int number) {
        if (index >= array.length) {
            throw new IndexOutOfBoundsException("Индекс больше размерности вектора");
        } else {
            array[index] = number;
        }
    }

    public boolean equals(Vector vector) {
        if (vector == null) {
            return false;
        }
        if (vector == this) {
            return true;
        }
        if (array.length != vector.array.length) {
            return false;
        }
        for (int i = 0; i < array.length; i++) {
            if (array[i] != vector.array[i]) {
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
        Vector copyVector1 = new Vector(vector1);
        Vector copyVector2 = new Vector(vector2);
        return new Vector(copyVector1.getSum(copyVector2));
    }

    public static Vector getDiff(Vector vector1, Vector vector2) {
        Vector copyVector1 = new Vector(vector1);
        Vector copyVector2 = new Vector(vector2);
        return new Vector(copyVector1.getDiff(copyVector2));
    }

    public static double getMult(Vector vector1, Vector vector2) {
        Vector copyVector1 = new Vector(vector1);
        Vector copyVector2 = new Vector(vector2);
        int n = Math.min(copyVector1.array.length, copyVector2.array.length);
        double mult = 0;
        for (int i = 0; i < n; i++) {
            mult += copyVector1.array[i] * copyVector2.array[i];
        }
        return mult;
    }
}

