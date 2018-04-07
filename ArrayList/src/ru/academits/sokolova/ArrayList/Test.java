package ru.academits.sokolova.ArrayList;

import java.util.ListIterator;

public class Test {
    public static void main(String... args) {
        Integer [] a= {1,2,3};
        ArrayList<Integer> arrayList = new ArrayList<>(a);

        ListIterator<Integer> iterator1 = arrayList.listIterator();
        while (iterator1.hasNext()){
            System.out.println(iterator1.next());
            System.out.println(iterator1.next());
            System.out.println("prev " + iterator1.previous());
        }
    }
}
