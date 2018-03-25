package ru.academits.sokolova.Matrix;

import ru.academits.sokolova.Vector.Vector;

import java.util.Arrays;

public class Matrix {
    private Vector[] rows;

    public Matrix(int rowsNumber, int columnsNumber) {
        if (rowsNumber <= 0 || columnsNumber <= 0) {
            throw new IllegalArgumentException("Матрица такой размерности не существует");
        }
        this.rows = new Vector[rowsNumber];
        for (int i = 0; i < rowsNumber; i++) {
            this.rows[i] = new Vector(columnsNumber);
        }
    }

    public Matrix(Matrix matrix) {
        this.rows = new Vector[matrix.rows.length];
        for (int i = 0; i < matrix.rows.length; i++) {
            this.rows[i] = new Vector(matrix.rows[i]);
        }
    }

    public Matrix(double[][] array) {
        if (array.length == 0) {
            throw new IllegalArgumentException("Матрица такой размерности не существует");
        }
        int max = array[0].length;
        for (double[] e : array) {
            max = Math.max(e.length, max);
        }
        if (max == 0) {
            throw new IllegalArgumentException("Матрица такой размерности не существует");
        }
        this.rows = new Vector[array.length];
        for (int i = 0; i < array.length; i++) {
            this.rows[i] = new Vector(max);
            for (int j = 0; j < array[i].length; j++) {
                this.rows[i].setComponent(j, array[i][j]);
            }
        }
    }

    public Matrix(Vector... array) {
        if (array.length == 0) {
            throw new IllegalArgumentException("Матрица такой размерности не существует");
        } else {
            this.rows = new Vector[array.length];
            int max = array[0].getSize();
            for (Vector e : array) {
                max = (e.getSize() > max) ? e.getSize() : max;
            }
            for (int i = 0; i < array.length; i++) {
                this.rows[i] = new Vector(max);
                for (int j = 0; j < array[i].getSize(); j++) {
                    this.rows[i].setComponent(j, array[i].getComponent(j));
                }
            }
        }
    }

    public int getRowsNumber() {
        return rows.length;
    }

    public int getColumnsNumber() {
        return rows[0].getSize();
    }

    public Vector getRow(int rowNumber) {
        if (rowNumber < 0 || rowNumber >= rows.length) {
            throw new IndexOutOfBoundsException("Неправильный номер строки");
        } else {
            return new Vector(rows[rowNumber]);
        }
    }

    public void setRow(int rowNumber, Vector vector) {
        if (rowNumber < 0 || rowNumber >= rows.length) {
            throw new IndexOutOfBoundsException("Неправильный номер строки");
        } else {
            this.rows[rowNumber] = new Vector(vector);
        }
    }

    public Vector getColumn(int columnNumber) {
        if (columnNumber < 0 || columnNumber >= getColumnsNumber()) {
            throw new IndexOutOfBoundsException("Неправильный номер столбца");
        }
        double[] column = new double[rows.length];
        for (int i = 0; i < rows.length; i++) {
            column[i] = rows[i].getComponent(columnNumber);
        }
        return new Vector(column);
    }

    public Matrix transpose() {
        Vector[] temp = new Vector[getColumnsNumber()];
        for (int i = 0; i < getColumnsNumber(); i++) {
            temp[i] = new Vector(getColumn(i));
        }
        this.rows = new Vector[getColumnsNumber()];
        for (int i = 0; i < temp.length; i++) {
            setRow(i, temp[i]);
        }
        return this;
    }


    public Matrix getScalarMultiplication(double scalar) {
        for (Vector e : rows) {
            e.getMult(scalar);
        }
        return this;
    }

    public double getDeterminant() {
        if (this.rows.length != this.getColumnsNumber()) {
            System.out.println("Это неквадратная матрица, для нее считают гипердетерминант, а не обычный детерминант");
            return 0;
        }
        double determinant = 0;
        int range = this.rows.length;
        if (range == 1) {
            return this.rows[0].getComponent(0);// определитель матрицы из одного элемента
        }
        for (int i = 0; i < range; i++) {// разложение по первому столбцу, i - номер строки
            Matrix newMatrix = new Matrix(range - 1, range - 1);
            for (int j = 0; j < range - 1; j++) {// заполнение строк новой матрицы старыми строками со сдвигом на один элемент
                if (j < i) {
                    newMatrix.setRow(j, new Vector(Arrays.copyOfRange(this.rows[j].toArray(), 1, range)));
                } else {
                    newMatrix.setRow(j, new Vector(Arrays.copyOfRange(this.rows[j + 1].toArray(), 1, range)));
                }
            }
            determinant += Math.pow(-1, i) *
                    this.rows[i].getComponent(0) *
                    newMatrix.getDeterminant();
        }
        return determinant;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("{");
        for (Vector e : rows) {
            s.append(e.toString()).append(", ");
        }
        return s.delete(s.length() - 2, s.length()).append("}").toString();
    }

    public Vector multiplyBy(Vector vector) {
        if (getColumnsNumber() != vector.getSize()) {
            throw new IndexOutOfBoundsException("Длина вектора не совпадает с числом строк матрицы");
        }
        Vector multiplicationResult = new Vector(getRowsNumber());
        for (int i = 0; i < getRowsNumber(); i++) {
            multiplicationResult.setComponent(i, Vector.getMultiplication(this.rows[i], vector));
        }
        return multiplicationResult;
    }

    public Matrix getSum(Matrix other) {
        if (getColumnsNumber() != other.getColumnsNumber() || getRowsNumber() != other.getRowsNumber()) {
            throw new IndexOutOfBoundsException("Размеры матриц не совпадают");
        }
        for (int i = 0; i < getRowsNumber(); i++) {
            rows[i].getSum(other.rows[i]);
        }
        return this;
    }

    public Matrix getDiff(Matrix other) {
        if (getColumnsNumber() != other.getColumnsNumber() || getRowsNumber() != other.getRowsNumber()) {
            throw new IndexOutOfBoundsException("Размеры матриц не совпадают");
        }
        for (int i = 0; i < getRowsNumber(); i++) {
            rows[i].getDiff(other.rows[i]);
        }
        return this;
    }

    public static Matrix getSum(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getRowsNumber() != matrix2.getRowsNumber() ||
                matrix1.getColumnsNumber() != matrix2.getColumnsNumber()) {
            throw new IllegalArgumentException("Размеры матриц должны быть одинаковы");
        }
        return new Matrix(matrix1).getSum(matrix2);
    }

    public static Matrix getDiff(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getRowsNumber() != matrix2.getRowsNumber() ||
                matrix1.getColumnsNumber() != matrix2.getColumnsNumber()) {
            throw new IllegalArgumentException("Размеры матриц должны быть одинаковы");
        }
        return new Matrix(matrix1).getDiff(matrix2);
    }

    public static Matrix getMultiplication(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getColumnsNumber() != matrix2.getRowsNumber()) {
            throw new IllegalArgumentException("Число столбцов первой матрицы должно быть равно числу строк второй");
        }
        int rowsNumber = matrix1.getRowsNumber();
        int columnsNumber = matrix2.getColumnsNumber();
        Matrix multiplicationResult = new Matrix(rowsNumber, columnsNumber);
        for (int i = 0; i < rowsNumber; i++) {
            multiplicationResult.rows[i] = new Vector(columnsNumber);
            for (int j = 0; j < columnsNumber; j++) {
                multiplicationResult.rows[i].setComponent(j, Vector.getMultiplication(matrix1.rows[i], matrix2.getColumn(j)));
            }
        }
        return multiplicationResult;
    }
}

