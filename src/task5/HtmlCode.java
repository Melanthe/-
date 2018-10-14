package task5;

import common.MyException;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.*;
import java.util.regex.*;

public class HtmlCode {

    private String text;
    private Set<String> tags;
    private List<String> textToFind;
    private List<String> notFoundText;
    private Map<String, Integer> found;

    private String findTags;

    public HtmlCode() {
        text = "";
        tags = new TreeSet<>(new Comparator<String>() {
            @Override
            public int compare(String x, String y) {

                if ((x.length() - y.length()) == 0) {
                    return x.compareToIgnoreCase(y);
                } else {
                    return (x.length() - y.length());
                }
            }
        });

        textToFind = new ArrayList<>();
        notFoundText = new ArrayList<>();
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
             Scanner toFindScanner = new Scanner(toFindFile).useDelimiter("[;\\n\\r]+")) {

            while (codeScanner.hasNextLine()) {

                text += codeScanner.nextLine();
                text += "\n";
            }
            while(toFindScanner.hasNext()) {
                textToFind.add(toFindScanner.next().toLowerCase());
            }
        }
    }

    public void findTags() throws PatternSyntaxException{

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
    }

    public void findText() {

        int line = 0;
        String tmp = "";

        text = text.replaceAll(findTags, "");

        try (Scanner lines = new Scanner(text)) {

            while(lines.hasNextLine()) {

                tmp = lines.nextLine();
                tmp = tmp.toLowerCase();

                if (!tmp.equals("")) {
                    for (String item : textToFind) {

                        if (tmp.contains(item)) {

                            if (!found.containsKey(item)) {
                                found.put(item, line);
                            }
                        }
                    }
                }
                line++;
            }

            for (String item: textToFind) {

                if (!found.containsKey(item)) {
                    found.put(item, -1);
                }
            }
        }

        for (String item: textToFind) {

            if (found.get(item) == -1) {
                notFoundText.add(item);
            }
        }
    }

    public void output() throws IOException {

        try (PrintStream ps1 = new PrintStream("output/result5.1.txt");
             PrintStream ps2 = new PrintStream("output/result5.2.txt");
             PrintStream ps3 = new PrintStream("output/result5.3.txt")) {

            tags.forEach(item -> ps1.println(item));

            for(Map.Entry<String, Integer> item : found.entrySet()) {
                ps2.println(item.getValue() + "\t" + item.getKey());
            }

            notFoundText.forEach(item -> ps3.println(item));
        }
    }
}
