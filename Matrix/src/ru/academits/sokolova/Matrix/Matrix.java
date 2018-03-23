package ru.academits.sokolova.Matrix;

import ru.academits.sokolova.Vector.Vector;

import java.util.Arrays;

public class Matrix {
    private Vector[] vectors;

    public Matrix(int rowNumber, int columnNumber) {//n строк m столбцов
        if (rowNumber <= 0 || columnNumber <= 0) {
            throw new IllegalArgumentException("Матрица такой размерности не существует");
        } else {
            this.vectors = new Vector[rowNumber];
            for (int i = 0; i < rowNumber; i++) {
                this.vectors[i] = new Vector(columnNumber);
            }
        }
    }

    public Matrix(Matrix matrix) {
        this.vectors = new Vector[matrix.vectors.length];
        for (int i = 0; i < matrix.vectors.length; i++) {
            this.vectors[i] = new Vector(matrix.vectors[i]);
        }
    }

    public Matrix(double[][] array) {
        if (array.length == 0) {
            throw new IllegalArgumentException("Матрица такой размерности не существует");
        }
        this.vectors = new Vector[array.length];
        int max = array[0].length;
        for (double[] e : array) {
            max = (e.length > max) ? e.length : max;
        }
        for (int i = 0; i < array.length; i++) {
            this.vectors[i] = new Vector(max);
            for (int j = 0; j < array[i].length; j++) {
                this.vectors[i].setComponent(j, array[i][j]);
            }
        }
    }

    public Matrix(Vector... array) {
        if (array.length == 0) {
            throw new IllegalArgumentException("Матрица такой размерности не существует");
        } else {
            this.vectors = new Vector[array.length];
            int max = array[0].getSize();
            for (Vector e : array) {
                max = (e.getSize() > max) ? e.getSize() : max;
            }
            for (int i = 0; i < array.length; i++) {
                this.vectors[i] = new Vector(max);
                for (int j = 0; j < array[i].getSize(); j++) {
                    this.vectors[i].setComponent(j, array[i].getComponent(j));
                }
            }
        }
    }

    public int getRowNumber() {
        return vectors.length;
    }

    public int getColumnNumber() {
        return vectors[0].getSize();
    }

    public Vector getRow(int rowNumber) {
        if (rowNumber < 0 || rowNumber >= vectors.length) {
            throw new IndexOutOfBoundsException("Неправильный номер строки");
        } else {
            return new Vector(vectors[rowNumber]);
        }
    }

    public void setRow(int rowNumber, Vector vector) {
        if (rowNumber < 0 || rowNumber >= vectors.length) {
            throw new IndexOutOfBoundsException("Неправильный номер строки");
        } else {
            this.vectors[rowNumber] = new Vector(Arrays.copyOf(vector.getArray(), vector.getSize()));
        }
    }

    public Vector getColumn(int columnNumber) {
        if (columnNumber < 0 || columnNumber >= vectors[0].getSize()) {
            throw new IndexOutOfBoundsException("Неправильный номер столбца");
        } else {
            double[] column = new double[vectors.length];
            for (int i = 0; i < vectors.length; i++) {
                column[i] = vectors[i].getComponent(columnNumber);
            }
            return new Vector(column);
        }
    }

    public Matrix transpose() {
        int rowNumber = this.getRowNumber();
        int columnNumber = this.getColumnNumber();
        int maxSize = Math.max(rowNumber, columnNumber);
        Vector[] temp1 = new Vector[maxSize];//из него будем брать
        Vector[] temp2 = new Vector[maxSize];// в него будем вставлять
        if (rowNumber < columnNumber) {
            for (int i = 0; i < rowNumber; i++) {
                temp1[i] = new Vector(vectors[i]);
                temp2[i] = new Vector(vectors[i]);
            }
            for (int i = rowNumber; i < columnNumber; i++) {
                temp1[i] = new Vector(columnNumber);
                temp2[i] = new Vector(columnNumber);
            }
        } else {
            for (int i = 0; i < rowNumber; i++) {
                temp1[i] = new Vector(rowNumber, vectors[i].getArray());
                temp2[i] = new Vector(rowNumber, vectors[i].getArray());
            }
        }
        for (int i = 0; i < maxSize; i++) { // переставляем
            for (int j = 0; j < maxSize; j++) {
                double tempCell = temp1[i].getComponent(j);
                temp2[i].setComponent(j, temp1[j].getComponent(i));
                temp2[j].setComponent(i, tempCell);
            }
        }
        this.vectors = new Vector[columnNumber];
        for (int i = 0; i < columnNumber; i++) {//заполнили ее заново
            vectors[i] = new Vector(rowNumber,temp2[i].getArray());
        }
        return this;
    }

    public Matrix getScalarMultiplication(double scalar) {
        for (Vector e : vectors) {
            e.getMult(scalar);
        }
        return this;
    }

    public double getDeterminant(Matrix matrix) {
        if (matrix.vectors.length != matrix.vectors[0].getSize()) {
            System.out.println("Это неквадратная матрица, для нее считают гипердетерминант, а не обычный детерминант");
            return 0;
        }
        double determinant = 0;
        int range = matrix.vectors.length;
        if (range == 1) {
            return matrix.vectors[0].getComponent(0);// определитель матрицы из одного элемента
        }
        for (int i = 0; i < range; i++) {// разложение по первому столбцу, i - номер строки
            Matrix newMatrix = new Matrix(range - 1, range - 1);
            for (int j = 0; j < range - 1; j++) {// заполнение строк новой матрицы старыми строками со сдвигом на один элемент
                if (j < i) {
                    newMatrix.setRow(j, new Vector(Arrays.copyOfRange(matrix.vectors[j].getArray(), 1, range)));
                } else {
                    newMatrix.setRow(j, new Vector(Arrays.copyOfRange(matrix.vectors[j + 1].getArray(), 1, range)));
                }
            }
            determinant += Math.pow(-1, i) *
                    matrix.vectors[i].getComponent(0) *
                    getDeterminant(newMatrix);
        }
        return determinant;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("{{");
        for (Vector e : vectors) {
            for (int j = 0; j < vectors[0].getArray().length; j++) {
                s.append(e.getComponent(j)).append(", ");
                if (j == vectors[0].getArray().length) {
                    s.append("},{");
                }
            }
            s.delete(s.length() - 2, s.length()).append("},{");
        }
        return s.delete(s.length() - 2, s.length()).append("}").toString();
    }
}

