package task5;

import common.MyException;

import java.io.IOException;

public class Task5 {
    public static void main(String[] args) {

        try {

            HtmlCode html = new HtmlCode();

            html.input();

            html.findTags();
            html.findText();

            html.output();

        } catch (MyException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println("Input/Output problems!");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
