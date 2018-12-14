package task9;

import java.io.FileNotFoundException;

public class Task9 {
    public static void main(String[] args) {

        try {

            TextHandler th = new TextHandler();
            th.makeResult();

        } catch (FileNotFoundException e) {
            System.out.println("File problems!");
        }
    }
}
