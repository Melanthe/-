package task8;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;
import java.util.Scanner;

public class Array {

    Integer[] array;

    public Array(int size) {

        array = new Integer[size];

        for (int i = 0; i < array.length; ++i) {
            array[i] = new Random().nextInt(1000) - 500;
        }
    }

    public Array() {
        this(0);
    }

    public void showArray() {

        System.out.println();
        for (int item: array) {
            System.out.print(item + " ");
        }
        System.out.println("\n");
    }

    public void sortArray() throws NumberFormatException, InterruptedException {

        menu();
        int key = getRequest();
        Sort sort = new Sort(key, array);
        sort.start();

        if (sort.isAlive()) {
            sort.join();
        }
    }

    private int getRequest() throws NumberFormatException {

        Scanner sc = new Scanner(System.in);
        return sc.nextInt();
    }

    private void menu() {

        System.out.println("Choose a number or request:");
        System.out.println("1. Sort in ascending order of values.");
        System.out.println("2. Sort in descending order of values.");
        System.out.println("3. Sort in ascending order of digits.");
        System.out.println("4. Sort in descending order of digits.");
    }
}

class Sort extends Thread
{
    Integer[] arr;
    int way;

    public Sort(int way, Integer[] arr) {

        this.way = way;
        this.arr = arr;
    }

    public Sort() {
        this(1, null);
    }

    @Override
    public void run() throws NumberFormatException {

        switch (way) {

            case 1:
                Arrays.sort(arr);
                break;
            case 2:
                Arrays.asList(arr).sort(new Comparator<Integer>() {

                    @Override
                    public int compare(Integer x, Integer y) {
                        return (y - x);
                    }
                });
                break;
            case 3:
                Arrays.asList(arr).sort(new Comparator<Integer>() {

                    @Override
                    public int compare(Integer x, Integer y) {
                        return compareNumberOfDigits(x, y);
                    }
                });
                break;
            case 4:
                Arrays.asList(arr).sort(new Comparator<Integer>() {

                    @Override
                    public int compare(Integer x, Integer y) {
                        return compareNumberOfDigits(y, x);
                    }
                });
                break;
            default:
                throw new NumberFormatException();
        }
    }

    private int digitsNumber (Integer obj) {

        int count = 0;

        while (obj != 0) {

            obj /= 10;
            count++;
        }

        return count;
    }

    private int compareNumberOfDigits(Integer x, Integer y) {

        int sizeX = digitsNumber(x);
        int sizeY = digitsNumber(y);

        if (sizeX == sizeY) {
            return (x - y);
        }

        return (sizeX - sizeY);
    }
}
