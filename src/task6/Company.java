package task6;

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

                }catch (NumberFormatException e) {}
            }

            return res;
        }
    }

    public String getShortName() {

        return shortTitle;
    }

    public int[] getDateFoundation() {
        return dateFoundation;
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

    @Override
    public boolean equals(Object company) {

        if (this == company) {
            return true;
        }

        if (company == null || (company.getClass() != this.getClass())) {
            return false;
        }

        return (name.equalsIgnoreCase(((Company)company).name));
    }

    @Override
    public int hashCode() {

        int result = 0;
        int length = name.length();

        for(int i = 0; i < length; ++i) {
            result += name.charAt(i);
        }

        return result;
    }
}
