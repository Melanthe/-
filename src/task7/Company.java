package task7;

public class Company {

    private String name;
    private String shortName;
    private String dateUpdate;
    private String address;
    private int countEmployees;
    private String dateFoundation;
    private String auditor;
    private String phone;
    private String email;
    private String branch;
    private String activity;
    private String link;

    public Company() {}

    public Company(String name, String shortName, String dateUpdate, String address,
                   String dateFoundation, int countEmployees, String auditor, String phone,
                   String email, String branch, String activity, String link) {

        this.name = name;
        this.shortName = shortName;
        this.dateUpdate = dateUpdate;
        this.address = address;
        this.dateFoundation = dateFoundation;
        this.countEmployees = countEmployees;
        this.auditor = auditor;
        this.phone = phone;
        this.email = email;
        this.branch = branch;
        this.activity = activity;
        this.link = link;
    }

    public String getShortName() {
        return shortName;
    }

    public String getDateFoundation() {
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

    public String getName() {
        return name;
    }

    public String getDateUpdate() {
        return dateUpdate;
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
}
