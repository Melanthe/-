package task2;

import java.util.Scanner;

public class Matrix {

    private int matrix[][];
    private int lines;
    private int column;

    public Matrix() {

        lines = column = 4;
        matrix = new int[lines][column];

        randomInput();
    }

    public Matrix(int lines, int column) {

        this.lines = lines;
        this.column = column;
        matrix = new int[lines][column];

        randomInput();
    }

    private void randomInput() {

        for (int i = 0; i < lines; ++i) {
            for (int j = 0; j < column; ++j) {

                matrix[i][j] = (int) (Math.random() * 100);

            }
        }
    }

    public void inputElements() {

        Scanner sc = new Scanner(System.in);

        for (int i = 0; i < lines; ++i) {
            for (int j = 0; j < column; ++j) {

                matrix[i][j] = sc.nextInt();
            }
        }
    }

    public void show() {

        for (int i = 0; i < lines; ++i) {
            for (int j = 0; j < column; ++j) {

                System.out.print(matrix[i][j] + " ");
            }

            System.out.println();
        }

        System.out.println();
    }

    public void swap() {

        int max = max();
        int min = min();

        if (max != min) {

            for (int i = 0; i < column; ++i) {

                int x = matrix[max][i];
                int y = matrix[min][i];

                if (x != y) {

                    matrix[max][i] = y;
                    matrix[min][i] = x;
                }
            }
        }
    }

    private int max() {

        int maxLine = 0;
        int max = matrix[0][0];

        for (int i = 0; i < lines; ++i) {
            for (int j = 0; j < column; ++j) {

                if (matrix[i][j] > max) {

                    max = matrix[i][j];
                    maxLine = i;
                }
            }
        }

        return maxLine;
    }

    private int min() {

        int minLine = 0;
        int min = matrix[0][0];

        for (int i = 0; i < lines; ++i) {
            for (int j = 0; j < column; ++j) {

                if (matrix[i][j] < min) {

                    min = matrix[i][j];
                    minLine = i;
                }
            }
        }

        return minLine;
    }

    public int maxLineElement(int line) {

        int max = matrix[line][0];

        for (int i = 0; i < column; ++i) {

            if (matrix[line][i] > max) {
                max = matrix[line][i];
            }
        }

        return max;
    }

    public void showMax() {

        boolean flag = false;

        System.out.println("Number and  max element of lines with '0' on the diagonal: ");

        for (int i = 0; (i < lines) && (i < column); ++i) {

            if (matrix[i][i] == 0) {

                System.out.print((i + 1) + " " + maxLineElement(i) + '\n');
                flag = true;
            }
        }

        if (flag == false) {

            System.out.println("No such lines.");
        }

        System.out.println();
    }
}
