package task6;

import java.util.Scanner;

public class Menu {

    private boolean code;

    public Menu() {
        code = true;
    }

    public void menu() throws NumberFormatException {

        String answer;
        int choice;

        try (Scanner scanner = new Scanner(System.in)) {

            do {

                text();

                do {
                    System.out.println("\nInput the number of the request: ");
                    choice = scanner.nextInt();

                    choice(choice);

                } while (!code);


                System.out.println("\nDo you want to continue (yes\\no): ");
                answer = scanner.next();

            } while (answer.equalsIgnoreCase("yes"));
        }
    }

    private void text() {

        System.out.printf("%40s", "Choose a request:\n\n");
        System.out.println("1. Find a company by short name.");
        System.out.println("2. Select companies by industry.");
        System.out.println("3. Select companies by activity.");
        System.out.println("4. Select companies by date of establishment in a certain interval (from and to).");
        System.out.println("5. Select companies by the number of employees in a certain period (from and to).");
    }

    private void choice(int num) {

        code = true;

        switch (num) {

            case 1: {

                break;
            }
            case 2: {

                break;
            }
            case 3: {

                break;
            }
            case 4: {

                break;
            }
            case 5: {

                break;
            }
            default:
                System.out.println("\nIncorrect number, try again!");
                code = false;
                break;
        }
    }
}
