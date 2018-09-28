package task3;

import java.util.regex.PatternSyntaxException;

public class Task3 {
    public static void main(String[] args) {

        try {

            Text text = new Text();

            text.writeAndFindNum();

            text.ShowSortNum();

        } catch (NumberFormatException e) {
            System.out.println("Incorrect input!");

        } catch (PatternSyntaxException e) {
            System.out.println("Patter syntax problem!");

        } catch (Exception e) {
            System.out.println("Error!");
        }
    }
}
