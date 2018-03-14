package ru.academits.sokolova.CSVToHTML;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class CSVToHTML {
    public static void main(String[] args) throws FileNotFoundException {
        try (PrintWriter writer = new PrintWriter(args[0]);
             Scanner scanner = new Scanner(new FileInputStream(args[1]))) {
            writer.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01//EN\"");
            writer.println(" \"http://www.w3.org/TR/html4/strict.dtd\">");
            writer.println("<html>");
            writer.println("<head>");
            writer.println("<title> Проверка выполнения кода по конвертированию CSV-файла в HTML-файл </title>");
            writer.println("</head>");
            writer.println("<body>");
            writer.println("<table BORDER=\"1\" CELLSPACING=\"0\">");
            boolean doubleMarksCellOpening = false;// флаг начала ячейки со спецсимволами
            boolean doubleMarksCellClosing = false;// флаг  спецсимвола

            while (scanner.hasNextLine()) {
                String s = scanner.nextLine();
                if (!doubleMarksCellOpening) {// если в старой строке не было перевода строки
                    writer.print("<tr>");
                    writer.print("<td>");
                } else {
                    writer.print("<br/>");
                }
                for (int i = 0; i < s.length(); i++) {
                    if (s.charAt(i) != '"') {// если не кавычка
                        if (s.charAt(i) == ',') {// если запятая
                            if (!doubleMarksCellOpening || doubleMarksCellClosing) {// если нет первого флага или есть второй флаг - запятая=разделитель
                                writer.print("</td>");
                                writer.print("<td>");
                                doubleMarksCellOpening = false;
                                doubleMarksCellClosing = false;
                            } else {
                                writer.print(s.charAt(i));// запятая=символ
                            }
                        } else {
                            if (s.charAt(i) == '<') {
                                writer.print("&lt;");
                            } else if (s.charAt(i) == '>') {
                                writer.print("&gt;");
                            } else if (s.charAt(i) == '&') {
                                writer.print("&amp;");
                            } else {
                                writer.print(s.charAt(i));
                            }////////////////////////////////////
                        }
                    } else {// если кавычка
                        if (doubleMarksCellOpening) {// если флаг поднят
                            if (doubleMarksCellClosing) {// если второй флаг поднят
                                writer.print(s.charAt(i));// кавычка=символ
                                doubleMarksCellClosing = false;// и сбрасываем второй флаг
                            } else {
                                doubleMarksCellClosing = true;
                            }
                        } else {
                            doubleMarksCellOpening = true;
                        }
                    }
                }
                if (doubleMarksCellOpening && !doubleMarksCellClosing) {// если флаг поднят в ячейке с переводом строки, идем запрашивать следующую строку
                    continue;
                }
                writer.print("</td>");
                writer.println("</tr>");
            }
            writer.println("</table>");
            writer.println("</body>");
            writer.println("</html>");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Недостаточно аргументов");
        }
    }
}





