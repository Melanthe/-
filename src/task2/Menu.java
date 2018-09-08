package task2;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {

    public void startMenu() {

        String answer;
        Scanner sc = new Scanner(System.in);

        try {
            do {
                System.out.println("Input size of the matrix:");

                int lines = sc.nextInt();
                int column = sc.nextInt();

                Matrix matrix = new Matrix(lines, column);

                Menu m = new Menu();
                m.functionMenu(matrix);

                System.out.println("Do you want to try with a new matrix? (yes/no)");
                sc.nextLine();
                answer = sc.nextLine();

            } while (answer.equals("yes"));

        } catch (InputMismatchException e) {
            System.out.println("Incorrect input!");

        } catch (Exception e) {
            System.out.println("Error!");
        }
    }

    private void functionMenu(Matrix matrix) {

            int choice;

            Scanner sc = new Scanner(System.in);

            do {

                text();

                choice = sc.nextInt();

                switch (choice) {

                    case 1:
                        System.out.println("Input elements of the matrix:");
                        matrix.inputElements();
                        break;
                    case 2:
                        matrix.swap();
                        matrix.show();
                        break;
                    case 3:
                        matrix.showMax();
                        break;
                    case 4:
                        matrix.show();
                        break;
                    case 5:
                        break;
                    default:
                        System.out.println("Incorrect num of the function.");
                }

            } while (choice != 5);
    }

    private void text() {

        System.out.println("Choose function:");
        System.out.println("1. Initialization of the matrix (default: random)");
        System.out.println("2. Swap lines with max and min elements");
        System.out.println("3. Number and  max element of lines with '0' on the diagonal");
        System.out.println("4. Show matrix");
        System.out.println("5. Exit");
        System.out.println("\nEnter a number of the function:");

    }
}
