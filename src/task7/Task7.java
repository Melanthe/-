package task7;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Task7 {
    public static void main(String[] args) {

        try {

            RequestHandler requestHandler = new RequestHandler();
            requestHandler.getRequests();

        } catch (NumberFormatException e) {
            System.out.println("Incorrect input!");
        }catch (FileNotFoundException e) {
            System.out.println("File doesn't exist!");
        }catch (IOException e) {
            System.out.println("Input error!");
        }
    }
}
