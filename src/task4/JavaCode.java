package task4;

import common.MyExceptions;

import java.util.regex.*;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;;

public class JavaCode {

    private String text;
    private String removeComment;
    private String removePrintComment;
    private StringBuffer buffer;
    private String result;

    JavaCode() {

        buffer = new StringBuffer();
        removeComment = "(\\\"[^\\'].*?\\\"|print(?:ln|f)?\\(.*\\);)|((?s)\\/\\*.*?\\*\\/)|((?-s)\\/\\/.*)";
        removePrintComment = "(\"(?:[^\\\\\"]+|\\\\.)*\")|((?s)\\/\\*.*?\\*\\/)|((?-s)\\/\\/.*)";
    }

    public void writeText() throws Exception {

        File file = new File("D:\\Java\\Laboratory works\\src\\task4\\code.txt");
        FileReader fr = new FileReader(file);
        Scanner scanner = new Scanner(fr);

        if (!file.exists()) {
            throw new MyExceptions("File doesn't exist!");
        }

        while (scanner.hasNextLine()) {

            text += scanner.nextLine();
            text += "\r\n";
        }

        fr.close();
    }

    public void deleteComments() throws Exception {

        Pattern pattern = Pattern.compile(removeComment);
        Matcher matcher = pattern.matcher(text);
        String replacement;

        while (matcher.find()) {

            if (matcher.group(1) != null) {

                replacement = matcher.group().replaceAll(removePrintComment, "$1");
                matcher.appendReplacement(buffer, replacement);

            } else {

                matcher.appendReplacement(buffer, "$1");
            }
        }

        matcher.appendTail(buffer);
        result = buffer.toString();
    }

    public void printResult() throws Exception {

        FileWriter fw = new FileWriter("result4.txt");

        fw.write(result);

        fw.close();
    }
}