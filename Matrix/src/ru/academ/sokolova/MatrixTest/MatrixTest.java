package ru.academ.sokolova.MatrixTest;

import ru.academits.sokolova.Matrix.Matrix;
import ru.academits.sokolova.Vector.Vector;

public class MatrixTest {
    public static void main(String[] args) {
        double[][] a = {{1, 0, 0}, {0, 1, 0}};
        Matrix n = new Matrix(1, 2);
        System.out.println(n.toString());
        Matrix m = new Matrix(a);
        System.out.println(m.getDeterminant(m));
    }
}
