package ru.academ.sokolova.MatrixTest;

import ru.academits.sokolova.Matrix.Matrix;
import ru.academits.sokolova.Vector.Vector;

public class MatrixTest {
    public static void main(String[] args) {
        double[][] a = {{0}, {0},{1}};
        Matrix n = new Matrix(2, 2);
        System.out.println(n);
        Matrix m = new Matrix(a);
        System.out.println(m.getDeterminant(m));
        System.out.println(m);
        System.out.println(new Matrix(m));
        Vector [] v = {new Vector(4), new Vector(7),new Vector(10)};
        System.out.println(new Matrix(v));
        System.out.println(m.transpose());

    }
}
