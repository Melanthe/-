package task7;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class Companies {

    private Set<Company> companies;

    public Companies() {

        companies = new HashSet<>();
    }

    public void fillBase() throws FileNotFoundException {

        Scanner fields = new Scanner(new File("input/input6.csv")).useDelimiter(";");

        try (fields) {

            while (fields.hasNextLine()) {

                try {

                    companies.add(new Company(
                            fields.next(), fields.next(), fields.next(),
                            fields.next(), fields.next(), fields.nextInt(),
                            fields.next(), fields.next(), fields.next(),
                            fields.next(), fields.next(), fields.next()));

                } catch (NumberFormatException e) {
                }

                fields.nextLine();
            }
        }
    }

    public List<Company> findByShortName(String shortName) {

        return (companies
                .stream()
                .filter(item -> item.getShortName().equalsIgnoreCase(shortName))
                .collect(Collectors.toList()));
    }

    public List<Company> findByBranch(String branch) {

        return (companies
                .stream()
                .filter(item -> item.getBranch().equalsIgnoreCase(branch))
                .collect(Collectors.toList()));
    }

    public List<Company> findByActivity(String activity) {

        return (companies
                .stream()
                .filter(item -> item.getActivity().equalsIgnoreCase(activity))
                .collect(Collectors.toList()));
    }

    public List<Company> findByDateFoundation(String from, String to) {

        return (companies
                .stream()
                .filter(item -> checkDates(from, to, item))
                .collect(Collectors.toList()));
    }

    private boolean checkDates(String from, String to, Company item) {

        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

        try {
            Date dateFrom = sdf.parse(from);
            Date dateTo = sdf.parse(to);
            Date current = sdf.parse(item.getDateFoundation());

            if ((current.before(dateTo) || current.equals(dateTo)) &&
                    (current.after(dateFrom) || current.equals(dateFrom))) {
                return true;
            } else {
                return false;
            }

        } catch (ParseException e) {
            System.out.println("Parse date error!");
            return false;
        }
    }

    public List<Company> findByEmployees(int from, int to) {

        return (companies
                .stream()
                .filter(item -> checkNumber(from, to, item))
                .collect(Collectors.toList()));
    }

    private boolean checkNumber(int from, int to, Company item) {

        int count = item.getCountEmployees();
        return (count >= from && count <= to);
    }
}
