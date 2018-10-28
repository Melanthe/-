package task6;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

public class Companies {

    private Set<Company> companies;

    public Companies() {

        companies = new HashSet<>();
    }

    public void fillBase() throws FileNotFoundException {

        try (Scanner fields = new Scanner(new File("input/input6.csv")).useDelimiter(";")) {

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
}
