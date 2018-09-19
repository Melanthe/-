package task4;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

import common.MyExceptions;

public class JavaCode {

    private String tmpString;
    private int len;
    private char tmpChar;
    private char nextChar;
    private char prevChar;
    private boolean hasEnd;
    private boolean hasQuotes;
    private String result;

    JavaCode() {

        hasEnd = false;
        hasQuotes = false;
        result = "";
    }

    public void writeText() throws Exception {

        File file = new File("D:\\Java\\Laboratory works\\src\\task4\\code.txt");
        FileReader fr = new FileReader(file);
        Scanner sc = new Scanner(fr);

        if (!file.exists()) {
            throw new MyExceptions("File doesn't exist!");
        }

        while (sc.hasNextLine()) {

            tmpString = sc.nextLine();
            len = tmpString.length();

            for (int i = 0; i < len; ++i) {

                tmpChar = tmpString.charAt(i);

                if ((i + 1) < len) {

                    nextChar = tmpString.charAt(i + 1);

                    if (i != 0) {
                        prevChar = tmpString.charAt(i - 1);
                    }
                    else {
                        prevChar = 0;
                    }

                    if(!hasQuotes) {

                        if (tmpChar == '/') {
                            if (nextChar == '*') {

                                hasEnd = !hasEnd;
                            }
                            if (nextChar == '/') {

                                break;
                            }
                        }

                        if ((tmpChar == '*') && (nextChar == '/')) {

                            hasEnd = !hasEnd;
                            i++;
                            continue;
                        }
                    }

                    if ((prevChar != '\'') && (tmpChar == '"') && (nextChar != '\'')) {
                        hasQuotes = !hasQuotes;
                    }
                }


                if (hasQuotes) {
                    result += tmpChar;
                    continue;
                }

                if (!hasEnd) {

                    result += tmpChar;
                }
            }

            result += "\r\n";
        }

        FileWriter fw = new FileWriter("D:\\Java\\Laboratory works\\src\\task4\\result.txt");
        fw.write(result);

        fr.close();
        fw.close();
    }
}
