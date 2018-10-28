package task6;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import static java.time.format.FormatStyle.SHORT;

public class Menu {

    private boolean code;
    private Companies companies;
    private FileWriter writer;
    private Scanner scanner;

    public Menu() throws IOException {

        code = true;
        companies = new Companies();
        writer = new FileWriter("output/report6.txt", true);
        scanner = new Scanner(System.in);
    }

    public void menu() throws NumberFormatException, IOException {

        String answer;
        int choice;

        companies.fillBase();
        writer.write("Time:\n" + LocalDateTime.now().format(DateTimeFormatter.ofLocalizedDateTime(SHORT, SHORT)));

        do {

            text();

            do {
                System.out.println("\nInput the number of the request: ");

                choice = Integer.parseInt(scanner.nextLine());

                choice(choice);

            } while (!code);


            System.out.println("\nDo you want to continue (yes\\no): ");

            answer = scanner.nextLine();

        } while (answer.equalsIgnoreCase("yes"));

        scanner.close();
        writer.write("\n");
        writer.close();
    }

    private void text() {

        System.out.printf("%40s", "Choose a request:\n\n");
        System.out.println("1. Find a company by short name.");
        System.out.println("2. Select companies by branch.");
        System.out.println("3. Select companies by activity.");
        System.out.println("4. Select companies by date of establishment in a certain interval (from and to).");
        System.out.println("5. Select companies by the number of employees in a certain period (from and to).");
    }

    private void choice(int num) throws IOException {

        code = true;
        String key;
        List<Company> res;

        switch (num) {

            case 1: {

                System.out.println("Enter short name for searching:");
                key = scanner.nextLine().trim();

                res = companies.findByShortName(key);
                writeResToJason(res);

                writer.write("\nRequest:\n" + key);
                writer.write("\nNumber of found companies:\n" + res.size() + "\n");

                res.clear();

                break;
            }
            case 2: {

                System.out.println("Enter branch for searching:");
                key = scanner.nextLine().trim();

                res = companies.findByBranch(key);
                writeResToJason(res);

                writer.write("\nRequest:\n" + key);
                writer.write("\nNumber of found companies:\n" + res.size() + "\n");

                res.clear();

                break;
            }
            case 3: {

                System.out.println("Enter activity for searching:");
                key = scanner.nextLine().trim();

                res = companies.findByActivity(key);
                writeResToJason(res);

                writer.write("\nRequest:\n" + key);
                writer.write("\nNumber of found companies:\n" + res.size() + "\n");

                res.clear();

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

    private void writeResToJason(List<Company> list) throws FileNotFoundException {

        try (PrintWriter pw = new PrintWriter("output/result6.json")) {

            Gson gs = new GsonBuilder().setPrettyPrinting().create();
            gs.toJson(list, pw);
        }
    }
}
