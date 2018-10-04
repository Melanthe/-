package task4;

import common.MyExceptions;
import jregex.PatternSyntaxException;

import java.io.IOException;

public class Task4 {
    public static void main(String[] args) {

        try {

            JavaCode jc = new JavaCode();

            jc.writeText();
            jc.deleteComments();
            jc.printResult();

        } catch (MyExceptions e) {
            System.out.println(e);

        } catch (IOException e) {
            System.out.println("Problems with the file!");

        } catch (PatternSyntaxException e) {
            System.out.println("Syntax error in the pattern!");

        } catch (IllegalStateException e) {
            System.out.println("Invalid match!");

        } catch (Exception e) {
            System.out.println("Error!");
        }
    }
}

