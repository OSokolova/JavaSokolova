package ru.academits.sokolova.Matrix;

import ru.academits.sokolova.Vector.Vector;

import java.util.Arrays;


public class Matrix extends Vector {
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
        super(array.length);
        if (array.length == 0) {
            throw new IllegalArgumentException("Матрица такой размерности не существует");
        } else {
            for (int i = 0; i < array.length; i++) {
                this.array[i] = new Vector(array[i]);
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
        return array.length + " X" + array[1].getSize();
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
        double determinant = 0;
        if (getSize() == 1) {
            return array[0].getComponent(0);
        }
        for (int i = 0; i < array.length; i++) {
            Matrix newMatrix = new Matrix(getSize() - 1, getSize() - 1);
            for (int j = 0; j < newMatrix.array.length; j++) {
                if (j < i) {
                    newMatrix.setRow(j, new Vector(Arrays.copyOfRange(array[i].getArray(), 1, array.length - 1)));
                }
                if (j == i) {
                    continue;
                }
                if (j > i) {
                    newMatrix.setRow(j - 1, new Vector(Arrays.copyOfRange(array[i].getArray(), 1, array.length - 1)));
                }
                determinant += Math.pow(-1, i + 1) *
                        array[i].getComponent(1) *
                        getDeterminant(newMatrix);

            }
        }
        return determinant;
    }
}

