package task4;

import common.MyExceptions;

public class Task4 {
    public static void main(String[] args) {

        try {

            JavaCode jc = new JavaCode();

            jc.writeText();

        } catch (MyExceptions e) {
            System.out.println(e);

        } catch (Exception e) {
            System.out.println("Error!");
        }
    }
}
