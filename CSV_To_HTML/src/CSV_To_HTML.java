import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class CSV_To_HTML {
    public static void main(String[] args) throws FileNotFoundException {
        try (PrintWriter writer = new PrintWriter("CSV1.html");
             Scanner scanner = new Scanner(new FileInputStream("C:\\Users\\pingvin\\IdeaProjects\\JavaSokolova\\CSV_To_HTML\\src\\CSV.csv"))) {
            writer.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01//EN\" \n" +
                    "  \"http://www.w3.org/TR/html4/strict.dtd\">\n" +
                    "<html>" +
                    "<table>");
            boolean doubleMarks1 = false;// флаг начала ячейки со спецсимволами
            boolean doubleMarks2 = false;// флаг  спецсимвола
            String s;

            while (scanner.hasNextLine()) {
                s = scanner.nextLine();
                if (!doubleMarks1) {// если в старой строке не было перевода строки
                    writer.print("<tr>");
                    writer.print("<td>");
                } else {
                    writer.print("</br>");
                }
                for (int i = 0; i < s.length(); i++) {
                    if (s.charAt(i) != '"') {// если не кавычка
                        if (s.charAt(i) == ',') {// если запятая
                            if (!doubleMarks1||doubleMarks2) {// если нет первого флага или есть второй флаг - запятая=разделитель
                                writer.print("</td>");
                                writer.print("<td>");
                                doubleMarks1 = false;
                                doubleMarks2 = false;
                            } else  {
                                writer.print(s.charAt(i));// запятая=символ
                            }
                        } else {
                            writer.print(s.charAt(i));
                        }
                    } else {// если кавычка
                        if (doubleMarks1) {// если флаг поднят
                            if (doubleMarks2) {// если второй флаг поднят
                                writer.print(s.charAt(i));// кавычка=символ
                                doubleMarks2 = false;// и сбрасываем второй флаг
                            } else {
                                doubleMarks2 = true;
                            }
                        } else {
                            doubleMarks1 = true;
                        }
                    }
                }
                if (doubleMarks1 && !doubleMarks2) {// если флаг поднят в ячейке с переводом строки, идем запрашивать следующую строку
                    continue;
                }
                writer.print("</td>");
                writer.println("</tr>");
            }
            writer.println("</table>" +
                    "</html>");
        }
    }
}





