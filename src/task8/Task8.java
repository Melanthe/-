package task8;

import java.util.Scanner;

public class Task8 {

    public static void main(String[] args) {

        try {

            Scanner sc = new Scanner(System.in);
            Array arr;
            int size;

            System.out.println("Input a size of array:");
            size = sc.nextInt();
            arr = new Array(size);

            System.out.println("Origin array:\nSize: " + size);
            arr.showArray();
            arr.sortArray();
            arr.showArray();

        } catch (NumberFormatException e) {
            System.out.println("Incorrect input!");
        } catch (InterruptedException e) {
            System.out.println("Thread problem!");
        }
    }
}
