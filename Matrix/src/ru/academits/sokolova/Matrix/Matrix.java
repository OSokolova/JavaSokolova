package ru.academits.sokolova.Matrix;

import ru.academits.sokolova.Vector.Vector;

import java.util.Arrays;


public class Matrix {
    private Vector[] array;


    public Matrix(int n, int m) {//n строк m столбцов
        if (n <= 0 || m <= 0) {
            throw new IllegalArgumentException("Матрица такой размерности не существует");
        } else {
            this.array = new Vector[n];
        }
    }

    public Matrix(Matrix matrix) {
        this.array = Arrays.copyOf(matrix.array, matrix.array.length);
    }

    public Matrix(double[][] array) {
        if (array.length == 0) {
            throw new IllegalArgumentException("Матрица такой размерности не существует");
        }
        this.array = new Vector[array.length];
        for (int i = 0; i < array.length; i++) {
            this.array[i] = new Vector(array[i]);
            for (int j = 0; j < array[0].length; j++) {
                this.array[i].setComponent(j, array[i][j]);
            }
        }
    }

    public Matrix(Vector... array) {

        if (array.length == 0) {
            throw new IllegalArgumentException("Матрица такой размерности не существует");
        } else {
            this.array = Arrays.copyOf(array, array.length);
        }
    }

    public String getMatrixSize() {
        return array.length + " X" + array[0].getSize();
    }

    public Vector getRow(int rowNumber) {
        return new Vector(array[rowNumber]);
    }

    public void setRow(int rowNumber, Vector vector) {
        this.array[rowNumber] = vector;
    }

    public Vector getColumn(int columnNumber) {
        double[] column = new double[array.length];
        for (int i = 0; i < array.length; i++) {
            column[i] = array[i].getComponent(columnNumber);
        }
        return new Vector(column);
    }

    public Matrix getTranspose() {// если нужно сохранить исходную матрицу, если не нужно, то все равно нужно создавать новую матрицу - копию исходной, или двумерный массив.
        Matrix transposedMatrix = new Matrix(array[1].getSize(), array.length);
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[1].getSize(); j++) {
                transposedMatrix.array[j].setComponent(i, array[i].getComponent(j));
            }
        }
        return transposedMatrix;
    }

    public Matrix getScalarMultiplication(double scalar) {
        for (int i = 0; i < array.length; i++) {
            array[i] = array[i].getMult(scalar);
        }
        return this;
    }

    public double getDeterminant(Matrix matrix) {
        if (matrix.array.length != matrix.array[0].getSize()) {
            System.out.println("Это неквадратная матрица, для нее считают гипердетерминант, а не обычный детерминант");
            return 0;
        }
        double determinant = 0;
        int range = matrix.array.length;
        if (range == 1) {
            return matrix.array[0].getComponent(0);// определитель матрицы из одного элемента
        }
        for (int i = 0; i < range; i++) {// разложение по первому столбцу, i - номер строки
            Matrix newMatrix = new Matrix(range - 1, range - 1);
            for (int j = 0; j < range - 1; j++) {// заполнение строк новой матрицы старыми строками со сдвигом на один элемент
                if (j < i) {
                    newMatrix.setRow(j, new Vector(Arrays.copyOfRange(matrix.array[j].getArray(), 1, range)));
                } else {
                    newMatrix.setRow(j, new Vector(Arrays.copyOfRange(matrix.array[j + 1].getArray(), 1, range)));
                }
            }
            determinant += Math.pow(-1, i) *
                    matrix.array[i].getComponent(0) *
                    getDeterminant(newMatrix);
        }
        return determinant;
    }
}

