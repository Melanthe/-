package task4;

import common.MyException;

import java.io.IOException;
import java.util.regex.*;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;;

public class JavaCode {

    private String text;
    private String removeComment;
    private String removePrintComment;
    private StringBuffer buffer;
    private String result;

    public JavaCode() {

        text = "";
        buffer = new StringBuffer();
        removeComment = "(\\\"[^\\'].*?\\\"|print(?:ln|f)?\\(.*\\);)|((?s)\\/\\*.*?\\*\\/)|((?-s)\\/\\/.*)";
        removePrintComment = "(\"(?:[^\\\\\"]+|\\\\.)*\")|((?s)\\/\\*.*?\\*\\/)|((?-s)\\/\\/.*)";
    }

    public void writeText() throws IOException, MyException {

        File file = new File("input/input4.txt");
        try (Scanner scanner = new Scanner(file)) {

            if (!file.exists()) {
                throw new MyException("File doesn't exist!");
            }

            while (scanner.hasNextLine()) {

                text += scanner.nextLine();
                text += "\r\n";
            }
        }
    }

    public void deleteComments() throws PatternSyntaxException {

        Pattern pattern = Pattern.compile(removeComment);
        Matcher matcher = pattern.matcher(text);
        String replacement;

        while (matcher.find()) {

            if (matcher.group(1) != null) {

                replacement = matcher.group().replaceAll(removePrintComment, "$1");
                matcher.appendReplacement(buffer, replacement);

            } else {

                matcher.appendReplacement(buffer, "");
            }
        }

        matcher.appendTail(buffer);
        result = buffer.toString();
    }

    public void printResult() throws IOException {

        FileWriter fw = new FileWriter("output/result4.txt");

        fw.write(result);

        fw.close();
    }
}