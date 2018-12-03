package test;

import java.util.Scanner;

public class Test {
    public static void main(String[] args) {

        Scanner sc = new Scanner("name,address,phone FROM dataBase WHERE countEmployees BETWEEN 1000 and 4000").useDelimiter(",\\s?");

        while(sc.hasNext()) {
            String tmp = sc.next();
            if (!tmp.contains("FROM")) {
                System.out.println(tmp);
            }
            if(!sc.hasNext()) {
                break;
            }
        }

        sc.useDelimiter(" \\s");
        System.out.println(sc.next());
    }
}
