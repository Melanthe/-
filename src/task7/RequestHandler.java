package task7;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import static java.time.format.FormatStyle.SHORT;

public class RequestHandler {

    private Companies companies;
    private List<Company> resultOfSearching;
    private List<String> requests;


    public RequestHandler() throws IOException {

        companies = new Companies();
        companies.fillBase();
        resultOfSearching = new ArrayList<>();
        requests = new ArrayList<>();
    }

    public void getRequests() throws NumberFormatException, IOException {


    }



  /*  private void choice1() {

        System.out.println("Enter short name for searching:");
        String tmp = scanner.nextLine().trim();
        requests.add(tmp);
        resultOfSearching = companies.findByShortName(tmp);
    }

    private void choice2() {

        System.out.println("Enter branch for searching:");
        String tmp = scanner.nextLine().trim();
        requests.add(tmp);
        resultOfSearching = companies.findByBranch(tmp);
    }

    private void choice3() {

        System.out.println("Enter activity for searching:");
        String tmp = scanner.nextLine().trim();
        requests.add(tmp);
        resultOfSearching = companies.findByActivity(tmp);
    }

    private void choice5() {

        System.out.println("Enter from and to number of employees: ");
        requests.add(scanner.nextLine());
        requests.add(scanner.nextLine());

        int from = Integer.parseInt(requests.get(0));
        int to = Integer.parseInt(requests.get(1));

        if (!checkNumbers(from, to)) {
            System.out.println("Incorrect input! From must be less or equal to.");
            menuFlag = false;
            return;
        }

        resultOfSearching = companies.findByEmployees(from, to);
    }

    private void writeToJason(List<Company> list) throws FileNotFoundException {

        try (PrintWriter pw = new PrintWriter("output/result6.json")) {

            Gson gs = new GsonBuilder().setPrettyPrinting().create();
            gs.toJson(list, pw);
        }
    }

    private void writeToXml(List<Company> list) throws FileNotFoundException {

        StringBuilder sb = new StringBuilder();
        PrintWriter pw = new PrintWriter("output/result6.xml");

        try (pw) {

            pw.println("<Companies>\n");

            for (Company item : list) {

                sb.append("<Company>\n\t");
                sb.append("<name> ").append(item.getName()).append(" </name>\n\t");
                sb.append("<shortName> ").append(item.getShortName()).append(" </shortName>\n\t");
                sb.append("<dateUpdate> ").append(item.getDateUpdate()).append(" </dateUpdate>\n\t");
                sb.append("<address> ").append(item.getAddress()).append(" </address>\n\t");
                sb.append("<countEmployees> ").append(item.getCountEmployees()).append(" </countEmployees>\n\t");
                sb.append("<dateFoundation> ").append(item.getDateFoundation()).append(" </dateFoundation>\n\t");
                sb.append("<auditor> ").append(item.getAuditor()).append(" </auditor>\n\t");
                sb.append("<phone> ").append(item.getPhone()).append(" </phone>\n\t");
                sb.append("<email> ").append(item.getEmail()).append(" </email>\n\t");
                sb.append("<branch> ").append(item.getBranch()).append(" </branch>\n\t");
                sb.append("<activity> ").append(item.getActivity()).append(" </activity>\n\t");
                sb.append("<link> ").append(item.getLink()).append(" </link>\n");
                sb.append("</Company>\n\n");

                pw.println(sb.toString());
                sb.delete(0, sb.length());
            }

            pw.println("</Companies>\n");
        }
    }

    private boolean checkNumbers(int from, int to) {

        return (from < to || from == to);
    }

    private void writeReport() throws IOException {

        StringBuilder sb = new StringBuilder();
        String size = Integer.toString(resultOfSearching.size());

        try (FileWriter writer = new FileWriter("output/report6.txt", true)) {

            writer
                    .write("Time:\n\r" + LocalDateTime.now()
                            .format(DateTimeFormatter.ofLocalizedDateTime(SHORT, SHORT)));

            if (requests.size() == 1) {
                sb
                        .append("\n\rRequest:\n\r").append(requests.get(0))
                        .append("\n\rNumber of found companies:\r")
                        .append(size).append("\n\r");

                writer.write(sb.toString());

            } else {
                sb
                        .append("\n\rRequest:\n\rFrom: ").append(requests.get(0))
                        .append("\rTo: ").append(requests.get(1))
                        .append("\n\rNumber of found companies:\r")
                        .append(size).append("\n\r");

                writer.write(sb.toString());
            }

            resultOfSearching.clear();
            requests.clear();
        }
    } */
}
