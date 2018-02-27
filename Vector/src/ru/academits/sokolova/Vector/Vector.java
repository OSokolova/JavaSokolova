package ru.academits.sokolova.Vector;

import java.sql.Array;
import java.util.Arrays;

import static jdk.nashorn.internal.objects.ArrayBufferView.length;

public class Vector {
    private double[] array;

    public Vector(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException();
        } else {
            this.array = new double[n];
            for (int i = 0; i < n; i++) {
                this.array[i] = 0;
            }
        }
    }

    public Vector(Vector vector) {

        this.array = new double[vector.array.length];
        for (int i = 0; i < vector.array.length; i++) {
            this.array[i] = vector.array[i];
        }
    }

    public Vector(double... array) {
        this.array = new double[array.length];
        for (int i = 0; i < array.length; i++) {
            this.array[i] = array[i];
        }
    }

    public Vector(int n, double... array) {
        this.array = new double[n];
        if (n <= 0) {
            throw new IllegalArgumentException();
        } else {
            for (int i = 0; i < n; i++) {
                this.array[i] = (i < array.length) ? array[i] : 0;
            }
        }
    }

    public double[] getArray() {
        return array;
    }

    public int getSize() {
        return array.length;
    }

    public String toString() {
        String s = Arrays.toString(array).substring(1, Arrays.toString(array).length() - 1);
        return "{" + s + "}";
    }

    public double[] getSum(Vector other) {
        int n = Math.max(this.array.length, other.array.length);
        double[] sum = new double[n];
        for (int i = 0; i < n; i++) {
            sum[i] = ((i < this.array.length) ? this.array[i] : 0) + ((i < other.array.length) ? other.array[i] : 0);
        }
        return sum;
    }

    public double[] getDif(Vector other) {
        int n = Math.max(this.array.length, other.array.length);
        double[] dif = new double[n];
        for (int i = 0; i < n; i++) {
            dif[i] = ((i < this.array.length) ? this.array[i] : 0) - ((i < other.array.length) ? other.array[i] : 0);
        }
        return dif;
    }

    public double[] getMult(double scalar) {
        double[] mult = new double[array.length];
        for (int i = 0; i < array.length; i++) {
            mult[i] = array[i] * scalar;
        }
        return mult;
    }

    public double[] getInversion() {
        double[] inversion = new double[array.length];
        for (int i = 0; i < array.length; i++) {
            inversion[i] = array[i] * (-1);
        }
        return inversion;
    }

    public double getLength() {
        double length = 0;
        for (int i = 0; i < array.length; i++) {
            length += array[i] * array[i];
        }
        length = Math.sqrt(length);
        return length;
    }

    public double getComponent(int index) {
        if ((index >= 0) && (index < array.length)) {
            return array[index];
        } else {
            throw new IllegalArgumentException();
        }
    }

    public void setComponent(int index, int number) {
        if (index >= array.length) {
            throw new IllegalArgumentException();
        } else {
            array[index] = number;
        }
    }

    public boolean equals(Vector vector) {
        if (array == vector.array) {
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
        for (double e : array
                ) {
            result = result * number + Double.doubleToLongBits(e);
        }
        return (int)result;
    }

    public static Vector getSum(Vector vector1, Vector vector2) {
        int n = Math.max(vector1.array.length, vector2.array.length);
        double[] sum = new double[n];
        for (int i = 0; i < n; i++) {
            sum[i] = ((i < vector1.array.length) ? vector1.array[i] : 0) + ((i < vector2.array.length) ? vector2.array[i] : 0);
        }
        return new Vector(sum);
    }

    public static Vector getDif(Vector vector1, Vector vector2) {
        int n = Math.max(vector1.array.length, vector2.array.length);
        double[] dif = new double[n];
        for (int i = 0; i < n; i++) {
            dif[i] = ((i < vector1.array.length) ? vector1.array[i] : 0) - ((i < vector2.array.length) ? vector2.array[i] : 0);
        }
        return new Vector(dif);
    }

    public static double getMult(Vector vector1, Vector vector2) {
        int n = Math.max(vector1.array.length, vector2.array.length);
        double mult = 0;
        for (int i = 0; i < n; i++) {
            mult += ((i < vector1.array.length) ? vector1.array[i] : 0) * ((i < vector2.array.length) ? vector2.array[i] : 0);
        }
        return mult;
    }
}

