package task7;

import common.MyException;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static java.time.format.FormatStyle.SHORT;

public class Task7 {

    public static void main(String[] args) {

        try {

            RequestHandler requestHandler = new RequestHandler();
            requestHandler.requestsHandling();

        } catch (IOException e) {
            System.out.println("Problems with the files!");
        }
    }
}