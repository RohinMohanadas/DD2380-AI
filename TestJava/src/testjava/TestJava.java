/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testjava;

/**
 *
 * @author Rohin
 */
public class TestJava {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        int n = 0;
        int i = 0;
        double result = 0;
        double[] arr = new double[9];
        for (i = 1; i <= 40; i++) {
            n = (int) Math.pow(2, i);
            while (n > 9) {
                n /= 10;
            }
            switch (n) {
                case 1:
                    arr[0]++;
                    break;
                case 2:
                    arr[1]++;
                    break;
                case 3:
                    arr[2]++;
                    break;
                case 4:
                    arr[3]++;
                    break;
                case 5:
                    arr[4]++;
                    break;
                case 6:
                    arr[5]++;
                    break;
                case 7:
                    arr[6]++;
                    break;
                case 8:
                    arr[7]++;
                    break;
                case 9:
                    arr[8]++;
                    break;

            }

        }
        for (int j = 0; j < 9; j++) {
            result = (arr[j] / i) * 100;
            System.out.println("Stat for " + (j + 1) + " is :" + result);
        }
        return;
    }

}
