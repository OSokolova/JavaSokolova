package ru.academits.sokolova.VectorTest;

import ru.academits.sokolova.Vector.Vector;


public class VectorTest {
    public static void main(String[] args) {
        int size1 = 10;
        Vector vector1 = new Vector(size1);//вектор с нулями
        System.out.println(vector1.toString());

        Vector vector2 = new Vector(vector1);// вектор2 - копия вектора1
        System.out.println(vector2.toString());

        double[] a = {1, 2, 3, 4, 5};
        Vector vector3 = new Vector(a);// вектор заполняется из массива
        System.out.println(vector3.toString());

        Vector vector4 = new Vector(size1, a);// вектор заполняется из массива и с учетом нужной размерности
        System.out.println(vector4.toString());

        System.out.println(vector4.getSize());// размерность вектора

        System.out.println(vector4.toString());//печать компонентов вектора

        System.out.println(vector3.getSum(vector4).toString());

        System.out.println(vector3.getDiff(vector4).toString());

        System.out.println(vector3.getMult(4).toString());

        System.out.println(vector3.getInversion().toString());

        System.out.println(vector3.getLength());

        System.out.println(vector3.getComponent(4));

        vector3.setComponent(4, 15);
        System.out.println(vector3.toString());

        System.out.println(vector3.equals(vector3));
        System.out.println(vector3.hashCode());
        System.out.println(vector1.equals(vector2));
        System.out.println(vector1.hashCode());
        System.out.println(vector2.hashCode());

        System.out.println(Vector.getSum(vector1, vector3).toString());//вектор суммы векторов
        System.out.println(Vector.getDiff(vector1, vector3).toString());//вектор разности векторов
        System.out.println(Vector.getMult(vector3, vector3));//скалярное произведение векторов
    }
}

