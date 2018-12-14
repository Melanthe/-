package task9;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextHandler {

    private final String TEXT;
    private String workText;

    public TextHandler(String filePath) throws FileNotFoundException {

        Scanner sc = new Scanner(new File(filePath));
        StringBuilder sb = new StringBuilder();
        try(sc) {

            while (sc.hasNext()) {
             sb.append(sc.nextLine()).append("\r\n");
            }

            TEXT = sb.toString();
        }

        workText = TEXT;
    }

    public TextHandler() throws FileNotFoundException {
        this("input/input9.txt");
    }

    public void makeResult() throws FileNotFoundException {

        removeBrackets();
        //removeDigits();
        //removeZero();

        PrintWriter pw = new PrintWriter("output/output9.txt");
        try(pw) {
            pw.println(workText);
        }
    }

    private void removeBrackets() {

        int prevLen = workText.length();
        int newLen = 0;

       while(prevLen != newLen) {
           prevLen = newLen;
           workText = workText.replaceAll("((?s)\\([^\\(]*?\\))", "");
           newLen = workText.length();
       }
    }

    private void removeDigits() {

        String string = "(\\d{2})(\\d+)";

        Pattern pattern = Pattern.compile(string);
        Matcher matcher = pattern.matcher(workText);

        workText = matcher.replaceAll("$1");
    }

    private void removeZero() {

        String string = "(0+)([1-9]+)";

        Pattern pattern = Pattern.compile(string);
        Matcher matcher = pattern.matcher(workText);

        workText = matcher.replaceAll("$2");
    }
}