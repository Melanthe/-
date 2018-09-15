package task3;

import java.util.Collections;
import java.util.Scanner;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Text {

    private String string;
    private Scanner sc;
    private Vector<Integer> vc;

    public Text() {
        sc = new Scanner(System.in);
        vc = new Vector<Integer>();
    }

    public void writeText() {

        System.out.println("Start write a text: \n");

        try {

            string = sc.nextLine();

            while(!string.equals("")) {

                findNum();
                string = sc.nextLine();
            }


        } catch (NumberFormatException e) {
            System.out.println("Incorrect input!");

        } catch (Exception e) {
            System.out.println("Error!");
        }
    }

    private void findNum() {

        String mask = "\\d+";
        String found;

        Pattern pattern = Pattern.compile(mask);
        Matcher matcher = pattern.matcher(string);

        while (matcher.find()) {

            vc.add(Integer.parseInt(matcher.group()));
        }
    }

    public void showNums() {

        sortUp();

        System.out.println("Found numbers: ");
        vc.forEach(items -> System.out.print(items + " "));
    }

    private void sortUp() {

        Collections.sort(vc);
    }
}
