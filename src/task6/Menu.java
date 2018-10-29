package task6;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
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

    public void menu()

            throws
            NumberFormatException, IOException,
            ParserConfigurationException, TransformerException {

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

    private void choice(int num)

            throws
            IOException, ParserConfigurationException, TransformerException {

        code = true;
        String key;
        List<Company> res;

        switch (num) {

            case 1: {

                System.out.println("Enter short name for searching:");
                key = scanner.nextLine().trim();

                res = companies.findByShortName(key);
                writeToJason(res);
                writeToXml(res);

                writer.write("\nRequest:\n" + key);
                writer.write("\nNumber of found companies:\n" + res.size() + "\n");

                res.clear();

                break;
            }
            case 2: {

                System.out.println("Enter branch for searching:");
                key = scanner.nextLine().trim();

                res = companies.findByBranch(key);
                writeToJason(res);
                writeToXml(res);

                writer.write("\nRequest:\n" + key);
                writer.write("\nNumber of found companies:\n" + res.size() + "\n");

                res.clear();

                break;
            }
            case 3: {

                System.out.println("Enter activity for searching:");
                key = scanner.nextLine().trim();

                res = companies.findByActivity(key);
                writeToJason(res);
                writeToXml(res);

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

    private void writeToJason(List<Company> list) throws FileNotFoundException {

        try (PrintWriter pw = new PrintWriter("output/result6.json")) {

            Gson gs = new GsonBuilder().setPrettyPrinting().create();
            gs.toJson(list, pw);
        }
    }

    private void writeToXml(List<Company> list)
            throws ParserConfigurationException, TransformerException {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.newDocument();

        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty(OutputKeys.METHOD, "xml");
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");

        Element root = document.createElement("Companies");

        document.appendChild(root);
        for (Company item : list) {

            root.appendChild(makeCompanyNode(item, document));
        }

        File file = new File("output/result6.xml");
        transformer.transform(new DOMSource(document), new StreamResult(file));
    }

    private Node makeCompanyNode(Company company, Document document) {

        Element node = document.createElement(company.getName());

        node.appendChild(makeFieldNode(document, "name", company.getName()));
        node.appendChild(makeFieldNode(document, "short name", company.getShortName()));
        node.appendChild(makeFieldNode(document, "date update", company.getDateUpdate()));
        node.appendChild(makeFieldNode(document, "address", company.getAddress()));
        node.appendChild(makeFieldNode(document, "date foundation", company.getDateFoundation()));
        node.appendChild(makeFieldNode(document, "count of employees",
                Integer.toString(company.getCountEmployees())));
        node.appendChild(makeFieldNode(document, "auditor", company.getAuditor()));
        node.appendChild(makeFieldNode(document, "phone", company.getPhone()));
        node.appendChild(makeFieldNode(document, "email", company.getEmail()));
        node.appendChild(makeFieldNode(document, "branch", company.getBranch()));
        node.appendChild(makeFieldNode(document, "activity", company.getActivity()));
        node.appendChild(makeFieldNode(document, "link", company.getLink()));

        return node;

    }

    private Node makeFieldNode(Document document, String name, String value) {

        Element title = document.createElement(name);
        Element field = document.createElement(value);

        title.appendChild(field);

        return title;
    }
}
