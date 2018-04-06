package ru.academits.sokolova.ArrayList;

public class Test {
    public static void main(String... args) {
        Integer [] a= {1,2,3};
        ArrayList<Integer> arrayList = new ArrayList<>(a);
        System.out.println(arrayList.indexOf(3));
        arrayList.clear();
        System.out.println(arrayList.indexOf(3));
    }
}
