package ru.academits.sokolova.ArrayList;

import java.util.ListIterator;

public class Test {
    public static void main(String... args) {
        Integer[] a = {1, 2, 2, 2, 3};
        ArrayList<Integer> arrayList = new ArrayList<>(a);

        Integer[] b = {1, 2};
        ArrayList<Integer> arrayList2 = new ArrayList<>(b);

        arrayList.removeAll(arrayList2);

        ListIterator<Integer> iterator1 = arrayList.listIterator();
        while (iterator1.hasNext()) {
            System.out.println(iterator1.next());
            /*System.out.println(iterator1.next());
            System.out.println("prev " + iterator1.previous());*/

        }
        arrayList.retainAll(arrayList2);

        ListIterator<Integer> iterator2 = arrayList.listIterator();
        while (iterator2.hasNext()) {
            System.out.println(iterator2.next());
        }



    }
}
