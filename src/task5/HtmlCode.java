package task5;

import common.MyException;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.*;
import java.util.regex.*;

public class HtmlCode {

    private String text;
    private List<String> tags;
    private List<String> textToFind;
    private Map<String, Integer> found;

    private String findTags;

    public HtmlCode() {
        text = "";
        tags = new ArrayList<>();
        textToFind = new ArrayList<>();
        found = new HashMap<>();
        findTags = "(<!--[^>]+-->)|(?:<(\\/?[^>]+)>)";
    }

    public void input() throws MyException, IOException {

        File codeFile = new File ("input/input5.1.txt");
        File toFindFile = new File ("input/input5.2.txt");

        if (!codeFile.exists()) {
            throw new MyException(codeFile.getName() + " doesn't exist!");
        }
        if (!toFindFile.exists()) {
            throw new MyException(toFindFile.getName() + " doesn't exist!");
        }

        try (Scanner codeScanner = new Scanner(codeFile);
             Scanner toFindScanner = new Scanner(toFindFile).useDelimiter(";")) {

            while (codeScanner.hasNextLine()) {

                text += codeScanner.nextLine();
            }
            while(toFindScanner.hasNext()) {
                textToFind.add(toFindScanner.next());
            }
        }
    }

    public void findAndSortTags() throws PatternSyntaxException{

        Pattern pattern = Pattern.compile(findTags);
        Matcher matcher = pattern.matcher(text);
        String tmp;

        while (matcher.find()) {

            if (matcher.group(1) == null) {
                tmp = matcher.group();

                if (tmp.charAt(1) != '/') {

                    tmp = tmp.toLowerCase();

                    if (!tags.contains(tmp)) {
                        tmp = tmp.replace('\n', ' ');
                        tags.add(tmp);
                    }
                }
            }
        }

        tags.sort(new Comparator<String>() {
            @Override
            public int compare(String x, String y) {
                return (x.length() - y.length());
            }
        });
    }

    public void findText() {

        text = text.replaceAll(findTags, "");

    }

    public void output() throws IOException {

        try (PrintStream ps = new PrintStream("output/result5.1.txt")) {

            tags.forEach(item -> ps.println(item));
        }
    }
}
