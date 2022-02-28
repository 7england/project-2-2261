package edu.umsl;
import java.util.Scanner;

public class connectFour {
    static int turnNo = 0; //turn number rotates between players
    static String t; //red or yellow
    static int disk; //player number


    public static void main(String[] args) {
        int[][] m = new int[6][6]; //
        Scanner input = new Scanner(System.in);
        int column;

        System.out.println("There are two colors: red and yellow.\n" +
                "Use 0-5 to pick which column you want to drop your token in.");

        //until there is a winner up turn count and make odd turns red and even turns yellow
        do {
            if (turnNo % 2 == 0) {
                t = "Red";
                disk = 1;
            } else {
                t = "Yellow";
                disk = 2;
            }
            displayBoard(m);
            System.out.println("Pick a column using characters 0-5 to drop Player " +disk +"'s token:\n\n");
            column = input.nextInt();
            turnNo++;

            for (int i = m.length - 1; i >= 0; i--) {
                if (m[i][column] == 0) {
                    m[i][column] = disk;
                    break;
                }
                else if (i == 0) {
                    System.out.println("That column is full. Pick a different column.\n");
                    turnNo++;
                }
                displayBoard(m);
                checkBoard(m);
            }

        } while (!checkBoard(m) && turnNo <= 42);

        if (checkBoard(m)){
            displayBoard(m);
            System.out.println("The " +t + " player won the game! Congrats!!!!\n");
            System.out.println("Do you want to play a new game? 1 for yes:\n");
            char y = input.next().charAt(0);
            if (y == '1') {
                displayBoard(m);
                turnNo = 2;
            }
        }
        if (!checkBoard(m)) {
            displayBoard(m);
            System.out.println("No one won. Sorry!\n");
            System.out.println("Do you want to play a new game? 1 for yes:\n");
            char y = input.next().charAt(0);
            if (y == '1') {
                displayBoard(m);
                turnNo = 2;
            }
        }
    }

    public static void displayBoard(int[][] m) {
        int i, j;
        char c;
        for (i = 0; i < m.length; i++) {
            System.out.print("|");
            for (j = 0; j < m[i].length; j++) {
                if (m[i][j] == 1) {
                    c = 'R';
                } else if (m[i][j] == 2) {
                    c = 'Y';
                } else {
                    c = ' ';
                }
                System.out.print(c + "|");
            }
            System.out.println();
        }
        System.out.println();
    }



    public static boolean checkBoard(int[][] m) {
        checkV(m);
        checkH(m);
        checkD(m);
        return checkV(m) || checkH(m) || checkD(m);
    }

    public static boolean checkV(int[][] m) {
        for (int i = 0; i < m[0].length; i++) {
            int start = m[0][i];
            int count = 1;
            for (int j = 1; j < m.length; j++) {
                if (start == m[j][i] && start != 0) {
                    count++;
                }
                else {
                    start = m[j][i];
                    count = 1;
                }

                if (count == 4) {
                    return true;
                }
            }
        }
        return false;

    }

    public static boolean checkH(int[][] m) {
        for (int i = 1; i < m.length; i++) {
            int start = m[i][0];
            int count = 1;
            for (int j = 1; j < m[i].length; j++) {
                if (start == m[i][j] && start != 0) {
                    count++;
                } else {
                    start = m[i][j];
                    count = 1;
                }

                if (count == 4) {
                    return true;
                }
            }
        }
        return false;

    }

    public static boolean checkD(int[][] m) {
        for (int i = 0; i < m.length; i++) {
            int start = m[i][0];
            int c = 1;
            for (int j = i+1, k = 1; j < m.length && k < m[0].length; j++, k++) {
                if (start == m[j][k] && start != 0) {
                    c++;
                } else {
                    start = m[j][k];
                    c = 1;
                }
                if (c == 4) {
                    return true;
                }
            }
        }

        for (int i = 0; i < m[0].length; i++) {
            int start = m[0][i];
            int count = 1;
            for (int j = i+1, k = 1; j < m[0].length && k < m.length; j++, k++) {
                if (start == m[k][j] && start != 0) {
                    count++;
                } else {
                    start = m[k][j];
                    count = 1;
                }

                if (count == 4) {
                    return true;
                }
            }
        }

        for (int i = 0; i < m[0].length; i++) {
            int start = m[0][i];
            int count = 1;
            for (int j = 1, k = i-1; j < m.length && k >= 0; j++, k--) {
                if (start == m[j][k] && start != 0) {
                    count++;
                } else {
                    start = m[j][k];
                    count = 1;
                }
                if (count == 4) {
                    return true;
                }
            }
        }

        for (int i = 0; i < m.length; i++) {
            int start = m[i][m[i].length-1];
            int count = 1;
            for (int j = i+1, k = m[0].length-2; j < m.length && k >= 0; j++, k--) {
                if (start == m[j][k] && start != 0) {
                    count++;
                } else {
                    start = m[j][k];
                    count = 1;
                }
                if (count == 4) {
                    return true;
                }
            }
        }
        return false;

    }
}