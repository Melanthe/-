package task3;

import java.util.Collections;
import java.util.Scanner;
import java.util.Vector;
import java.util.regex.*;

public class Text {

    private String string;
    private Vector<Integer> vc;

    public Text() {
        vc = new Vector<Integer>();
    }

    public void writeAndFindNum() {

        System.out.println("Start write a text: \n");
        Scanner sc = new Scanner(System.in);

            string = sc.nextLine();

            while(!string.equals("")) {

                findNum();
                string = sc.nextLine();
            }
    }

    private void findNum() throws PatternSyntaxException {

        String mask = "-?\\d+";

        Pattern pattern = Pattern.compile(mask);
        Matcher matcher = pattern.matcher(string);

        while (matcher.find()) {

            vc.add(Integer.parseInt(matcher.group()));
        }
    }

    public void ShowSortNum() {

        sortUp();

        System.out.println("Found numbers: ");
        vc.forEach(items -> System.out.print(items + " "));
    }

    private void sortUp() {

        Collections.sort(vc);
    }
}
