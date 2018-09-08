package task1;

import common.MyExceptions;

import java.util.Scanner;
import common.MyExceptions;

public class Taylor {

    public void sum(){

        int k;
        double x, eps, stSum,  sum = 1, prev = 1;

        Scanner sc = new Scanner(System.in);

        try {

            System.out.println("Input k and x:");
            k = Integer.parseInt(sc.next());
            x = Double.parseDouble(sc.next());

            if (k <= 0) {
                throw new MyExceptions("Accuracy must be greater than 0!");
            }

            eps = Math.pow(10, -k);

            for(int i = 1; Math.abs(prev) >= eps; ++i){
                prev *= x / i;
                sum += prev;
            }

            stSum = Math.exp(x);

            System.out.printf("Result: %.3f \n", sum);
            System.out.printf("The result of the standard function: %.3f", stSum);

        }
        catch(MyExceptions e){
            System.out.println(e);
        }
        catch(NumberFormatException e){
            System.out.println("Invalid input!");
        }
        catch(Exception e){
            System.out.println("Error!");
            }
    }
}
