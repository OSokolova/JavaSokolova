package ru.academits.sokolova.ArrayListHome;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class ArrayListHome {
    public static void main(String... args) throws IOException {
        try (Scanner scanner = new Scanner(new FileInputStream("C:\\Users\\pingvin\\IdeaProjects\\JavaSokolova\\CSVToHTML\\CSV.csv"))
        ) {
            ArrayList<String> names = new ArrayList<>();
            while (scanner.hasNextLine()) {
                String s = scanner.nextLine();
                names.add(s);
            }
            System.out.println(names);
        }
        ArrayList<Integer> numbers1 = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6));
        for (int i = 0; i < numbers1.size(); i++) {
            if (numbers1.get(i) % 2 == 0) {
                numbers1.remove(i);
            }
        }
        System.out.println(numbers1);

        ArrayList<Integer> numbers2 = new ArrayList<>(Arrays.asList(1, 5, 2, 1, 3, 5));
        ArrayList<Integer> numbers3 = new ArrayList<>();

        for (int i = 0; i < numbers2.size(); i++) {
            for (int j = i + 1; j < numbers2.size(); j++)
                if (numbers2.get(i).equals(numbers2.get(j))) {
                    numbers2.remove(j);
                }
            numbers3.add(numbers2.get(i));
        }
        System.out.println(numbers3);
    }
}



