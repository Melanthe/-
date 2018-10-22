package task6;

import common.MyException;

public class Task6 {
    public static void main(String[] args) {

        try {

            Menu menu = new Menu();

            menu.menu();

        } catch (NumberFormatException e) {
            System.out.println("Incorrect input!");
        }
    }
}
