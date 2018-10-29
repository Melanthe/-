package task6;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Company {

    private String name;
    private String shortTitle;
    private int[] dateUpdate;
    private String address;
    private int[] dateFoundation;
    private int countEmployees;
    private String auditor;
    private String phone;
    private String email;
    private String branch;
    private String activity;
    private String link;

    public Company() {

        dateUpdate = new int[3];
        dateFoundation = new int[3];
    }

    public Company(String name, String shortTitle, String dateUpdate, String address,
                   String dateFoundation, int countEmployees, String auditor, String phone,
                   String email, String branch, String activity, String link) {

        this.name = name;
        this.shortTitle = shortTitle;
        this.dateUpdate = formatDate(dateUpdate);
        this.address = address;
        this.dateFoundation = formatDate(dateFoundation);
        this.countEmployees = countEmployees;
        this.auditor = auditor;
        this.phone = phone;
        this.email = email;
        this.branch = branch;
        this.activity = activity;
        this.link = link;
    }

    private int[] formatDate(String date) {

        try (Scanner sc = new Scanner(date).useDelimiter("\\.")) {

            int[] res = new int[3];

            for (int i = 0; i < 3; ++i) {

                try {

                    res[i] = sc.nextInt();

                } catch (InputMismatchException e) {
                }
            }

            return res;
        }
    }

    public String getShortName() {

        return shortTitle;
    }

    public String getDateFoundation() {
        return dateToString(dateFoundation);
    }

    public int getCountEmployees() {
        return countEmployees;
    }

    public String getBranch() {
        return branch;
    }

    public String getActivity() {
        return activity;
    }

    public String getName() {
        return name;
    }

    public String getDateUpdate() {
        return dateToString(dateUpdate);
    }

    public String getAddress() {
        return address;
    }

    public String getAuditor() {
        return auditor;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getLink() {
        return link;
    }

    @Override
    public boolean equals(Object company) {

        if (this == company) {
            return true;
        }

        if (company == null || (company.getClass() != this.getClass())) {
            return false;
        }

        return (name.equalsIgnoreCase(((Company) company).name));
    }

    @Override
    public int hashCode() {

        int result = 0;
        int length = name.length();

        for (int i = 0; i < length; ++i) {
            result += name.charAt(i);
        }

        return result;
    }

    private String dateToString(int[] date) {

        StringBuilder res = new StringBuilder(date[0]);
        res.append(".").append(date[1]).append(".").append(date[2]);

        return res.toString();
    }
}
