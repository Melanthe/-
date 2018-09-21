package task4;

import common.MyExceptions;
import java.util.regex.*;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;;

public class JavaCode {

    private String text;
    private String regex;
    private StringBuffer buf;

    JavaCode() {

        text = "";
        buf = new StringBuffer();
        regex = "(\"[^\\'].*[^\\\\]?\")|((?s)\\/\\*.*?\\*\\/)|((?-s)\\/\\/.*)";
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
            text += "\r\n";
        }

        fr.close();
    }

    public void deleteComments() throws Exception {

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);

        int start = 0;
        boolean flag = matcher.find();

        while(flag) {

            matcher.appendReplacement(buf, "$1");
            flag = matcher.find();
        }

        matcher.appendTail(buf);

    }

    public void printResult() throws Exception {

        FileWriter fw = new FileWriter("result.txt");

        fw.write(buf.toString());

        fw.close();
    }
}