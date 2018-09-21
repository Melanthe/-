package task4;

import common.MyExceptions;
import jregex.Matcher;
import jregex.util.io.PathPattern;
import jregex.PatternSyntaxException;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;;

public class JavaCode {

    private String text;
    private String regex;

    JavaCode() {

        text = "";
        regex = "(\"[^\'].*?\"|print(?:ln|f)?\\(.*\\);)|(?(1)|((?s)\\/\\*.*?\\*\\/))|(?(1)|((?-s)\\/\\/.*))";
    }

    public void writeText() throws Exception {

        File file = new File("D:\\Java\\Laboratory works\\src\\task4\\code.txt");
        FileReader fr = new FileReader(file);
        Scanner sc = new Scanner(fr);

        if (!file.exists()) {
            throw new MyExceptions("File doesn't exist!");
        }


        while (sc.hasNextLine()) {

            text += sc.nextLine();
            text += '\n';
        }

        fr.close();
    }

    public void deleteComments() throws PatternSyntaxException {

        PathPattern pattern = new PathPattern(regex);
        Matcher matcher = pattern.matcher(text);

       text
               .replaceAll(matcher.group(2), "")
               .replaceAll(matcher.group(3), "");

    }

    public void printResult() throws Exception {

        FileWriter fw = new FileWriter("D:\\Java\\Laboratory works\\src\\task4\\Result.txt");

        fw.write(text);

        fw.close();
    }
}