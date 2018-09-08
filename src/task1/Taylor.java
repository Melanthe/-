package task1;

import common.MyExceptions;

import java.util.Scanner;

public class Taylor {

    private int k;
    private double x;
    private  double eps;
    private  double sum;
    private  double stSum;

    public void init() throws MyExceptions {

        Scanner sc = new Scanner(System.in);

        System.out.println("Input k and x:");

        k = Integer.parseInt(sc.next());
        x = Double.parseDouble(sc.next());

        if (k <= 0) {
            throw new MyExceptions("Accuracy must be greater than 0!");
        }

        eps = Math.pow(10, -k);
        sum = 1;
    }

    public void sum() {

        double prev = 1;

        for (int i = 1; Math.abs(prev) >= eps; ++i) {
            prev *= x / i;
            sum += prev;
        }
    }

    public void standartSum() {

        stSum = Math.exp(x);
    }

    public void show() {

        System.out.printf("Result: %.3f \n", sum);
        System.out.printf("The result of the standard function: %.3f", stSum);
    }
}
