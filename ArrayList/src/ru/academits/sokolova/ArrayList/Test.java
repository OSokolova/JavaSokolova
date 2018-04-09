package ru.academits.sokolova.ArrayList;
//import java.util.ArrayList;
//import java.util.Arrays;


import java.util.ListIterator;

public class Test {
    public static void main(String... args) {
        Integer[] a = {1, 2, 3, 4, 5};
       // java.util.ArrayList<Integer> arrayList = new java.util.ArrayList<>(Arrays.asList(a));

       ArrayList<Integer> arrayList = new ArrayList<>(a);
        Integer[] b = {0, 0};
       //ArrayList<Integer> arrayList2 = new ArrayList<>(b);

       // arrayList.removeAll(arrayList2);

        ListIterator<Integer> iterator1 = arrayList.listIterator(5);
        while (iterator1.hasNext()) {
            System.out.println(iterator1.next());
            System.out.println(iterator1.next());
            System.out.println("prev " + iterator1.previous());

        }
        System.out.println("-----------------");
        ListIterator<Integer> iterator2 = arrayList.listIterator(5);
        while (iterator2.hasPrevious()) {
            System.out.println(iterator2.previous());
        }


    }
}
