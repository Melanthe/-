package task7;

import common.MyException;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.time.format.FormatStyle.SHORT;

public class RequestHandler {

    private Companies companies;
    private List<String> requests;
    private List<Company> resultOfSearching;

    private List<String> inputInfo;
    private List<String> outputFields;

    public static List<String> exceptions;

    public RequestHandler() throws IOException {

        companies = new Companies();
        companies.fillBase();
        requests = new ArrayList<>();
        resultOfSearching = new ArrayList<>();
        inputInfo = new ArrayList<>();
        outputFields = new ArrayList<>();
        exceptions = new ArrayList<>();
    }

    private void getRequests() throws FileNotFoundException {

        Scanner sc = new Scanner(new File("input/input7.txt"));
        try (sc) {

            while (sc.hasNextLine()) {
                requests.add(sc.nextLine().trim());
            }
        }
    }

    public void requestsHandling()
            throws IOException {

        String key;
        FileWriter writer = new FileWriter("output/report7.txt", true);
        int count = 1;

        deleteLastResults();
        getRequests();

        writer
                .write(LocalDateTime.now()
                        .format(DateTimeFormatter.ofLocalizedDateTime(SHORT, SHORT)) + "\n\t");

        for (String item : requests) {

            try {

                exceptions.clear();

                Matcher matcher = checkRequest(item);
                key = parseRequests(matcher);
                chooseMethod(key);

                writeToJson(resultOfSearching, count);
                writeToXml(resultOfSearching, count);


            } catch (MyException e) {
                exceptions.add(e.toString());
            } catch (NumberFormatException e) {
                exceptions.add(e.getClass().getName() + " " + e.getMessage());
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                exceptions.add(e.getClass().getName() + " " + e.getMessage());
            } catch (FileNotFoundException e) {
                exceptions.add(e.getClass().getName() + " " + e.getMessage());
            } finally {

                writeLogFile(count, writer);
                writer.write("\t");

                count++;

                outputFields.clear();
                inputInfo.clear();
                resultOfSearching.clear();
            }
        }

            writer.write("\n");
            writer.close();
    }

    private Matcher checkRequest(String item) throws MyException {

        String pattern = "^(?:(?i)SELECT\\s+(.+)\\s+FROM\\s+(?-i)dataBase(?i)\\s+WHERE (.+))$";

        Pattern pt = Pattern.compile(pattern);
        Matcher matcher = pt.matcher(item);

        if (!matcher.matches()) {
            throw new MyException("Request's format error!");
        }

        return matcher;
    }

    private String parseRequests(Matcher matcher) throws MyException {

        String key;
        String fields = matcher.group(1).trim();
        String command = matcher.group(2).trim();

        if (!fields.equalsIgnoreCase("*")) {
            Scanner outFields = new Scanner(fields).useDelimiter(",");
            try (outFields) {

                while (outFields.hasNext()) {

                    outputFields.add(outFields.next().trim());
                }
            }
        } else {
            outputFields.add("*");
        }

        Scanner commandParser = new Scanner(command).useDelimiter("[\\s=']+");
        try (commandParser) {
            String tmp;

            key = commandParser.next();
            tmp = commandParser.next();
            if (tmp.equalsIgnoreCase("between")) {

                inputInfo.add(commandParser.next());
                if (!commandParser.next().equalsIgnoreCase("and")) {
                    throw new MyException("Format error!");
                }
                inputInfo.add(commandParser.next());

            } else {

                while (commandParser.hasNext()) {
                    tmp = tmp.concat(" " + commandParser.next());
                }
                inputInfo.add(tmp);
            }
        }
        return key;
    }

    private void chooseMethod(String key) throws MyException {

        switch (key.toLowerCase()) {

            case "countemployees":
                int from = Integer.parseInt(inputInfo.get(0));
                int to = Integer.parseInt(inputInfo.get(1));
                if (!checkNumbers(from, to)) {
                    throw new MyException("Incorrect number of employees");
                }
                resultOfSearching = companies.findByEmployees(from, to);
                break;
            case "shortname":
                resultOfSearching = companies.findByShortName(inputInfo.get(0));
                break;
            case "activity":
                resultOfSearching = companies.findByActivity(inputInfo.get(0));
                break;
            default:
                throw new MyException("Incorrect request!");
        }
    }

    private boolean checkNumbers(int from, int to) {

        return (from < to || from == to);
    }

    private String makeFilePath(int count, String expansion) {

        StringBuilder sb = new StringBuilder("output/");
        sb.append("result7.");
        sb.append(count).append(".").append(expansion);
        return sb.toString();
    }

    private String getterName(String field) {

        String first = field.substring(0, 1).toUpperCase();
        field = first + field.substring(1);
        return new StringBuilder("get").append(field).toString();
    }

    private String xmlToString(Company company)
            throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {

        StringBuilder sb = new StringBuilder();

        if (outputFields.get(0) == "*") {
            sb = xmlAllToString(company, sb);

        } else {
            for (String field : outputFields) {

                sb.append("<").append(field).append(">");
                sb.append(Company.class.getDeclaredMethod(getterName(field)).invoke(company));
                sb.append("</").append(field).append(">\n\t");
            }
        }
        return sb.toString();
    }

    private StringBuilder xmlAllToString(Company item, StringBuilder sb) {

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

        return sb;
    }

    private String jsonToString(Company company)
            throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {

        StringBuilder sb = new StringBuilder();
        int len = outputFields.size();
        int last = 1;

        if (outputFields.get(0) == "*") {
            sb = jsonAllToString(company, sb);

        } else {
            for (String field : outputFields) {

                sb.append("\t\t\"").append(field).append("\": \"");
                sb.append(Company.class.getDeclaredMethod(getterName(field)).invoke(company));
                if (last == len) {
                    sb.append("\"\n");
                } else {
                    sb.append("\",\n");
                }

                last++;
            }
        }
        return sb.toString();
    }

    private StringBuilder jsonAllToString(Company item, StringBuilder sb) {

        sb.append("\t\t\"name\": \"").append(item.getName()).append("\",\n\t\t");
        sb.append("\"shortName\": \"").append(item.getShortName()).append("\",\n\t\t");
        sb.append("\"dateUpdate\": \"").append(item.getDateUpdate()).append("\",\n\t\t");
        sb.append("\"address\": \"").append(item.getAddress()).append("\",\n\t\t");
        sb.append("\"countEmployees\": \"").append(item.getCountEmployees()).append("\",\n\t\t");
        sb.append("\"dateFoundation\": \"").append(item.getDateFoundation()).append("\",\n\t\t");
        sb.append("\"auditor\": \"").append(item.getAuditor()).append("\",\n\t\t");
        sb.append("\"phone\": \"").append(item.getPhone()).append("\",\n\t\t");
        sb.append("\"email\": \"").append(item.getEmail()).append("\",\n\t\t");
        sb.append("\"branch\": \"").append(item.getBranch()).append("\",\n\t\t");
        sb.append("\"activity\": \"").append(item.getActivity()).append("\",\n\t\t");
        sb.append("\"link\": \"").append(item.getLink()).append("\",\n\t\t");

        return sb;
    }

    private void writeToJson(List<Company> list, int count)
            throws FileNotFoundException, NoSuchMethodException,
            IllegalAccessException, InvocationTargetException {

        StringBuilder sb = new StringBuilder();
        PrintWriter pw = new PrintWriter(makeFilePath(count, "json"));
        int len = list.size();
        int last = 1;

        try (pw) {

            pw.println("[");

            for (Company item : list) {

                sb.append("\t{\n");
                sb.append(jsonToString(item));
                if (last == len) {
                    sb.append("\t}\n");
                } else {
                    sb.append("\t},\n");
                }

                pw.println(sb.toString());
                sb.delete(0, sb.length());
                last++;
            }

            pw.println("]");
        }
    }

    private void writeToXml(List<Company> list, int count)
            throws FileNotFoundException, NoSuchMethodException,
            IllegalAccessException, InvocationTargetException {

        StringBuilder sb = new StringBuilder();
        PrintWriter pw = new PrintWriter(makeFilePath(count, "xml"));

        try (pw) {

            pw.println("<Companies>\n");

            for (Company item : list) {

                sb.append("<Company>\n\t");
                sb.append(xmlToString(item));
                sb.append("</Company>\n\n");

                pw.println(sb.toString());
                sb.delete(0, sb.length());
            }

            pw.println("</Companies>\n");
        }
    }

    private void deleteLastResults() {

        int count = 1;

        while (true) {

            File fileJson = new File(makeFilePath(count, "json"));
            File fileXml = new File(makeFilePath(count, "xml"));

            if (fileJson.exists() || fileXml.exists()) {

                fileJson.delete();
                fileXml.delete();

                count++;

            } else {
                break;
            }
        }
    }

    private void writeLogFile(int count, FileWriter writer) throws IOException {

        StringBuilder sb = new StringBuilder("  Request ");
        String sizeOfResult = Integer.toString(resultOfSearching.size());
        int sizeOfErrors = exceptions.size();

        sb.append(count);

        if (sizeOfErrors == 0) {

            sb.append(": success. Number of companies found: ");
            sb.append(sizeOfResult);
            writer.write(sb.toString());
        } else {

            sb.append(": defeat. Exceptions: ");
            for (String error : exceptions) {
                sb.append(error);
            }
            writer.write(sb.toString());
        }

        writer.write("\n");
    }
}
