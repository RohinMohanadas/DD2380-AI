/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hmm3;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rohin
 */
public class HMM3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            // TODO code application logic here
            BufferedReader br = null;
//            br = new BufferedReader(new FileReader("F:\\KTH\\Study Period 1\\AI\\HMM3 Data\\hmm3_01.in"));
            br = new BufferedReader(new InputStreamReader(System.in));
            String line = new String();
            Double[][] txnMatrix = null;
            Double[][] emsnMatrix = null;
            Double[][] statDistrb = null;
            int txnX = 0, txnY = 0, emsX = 0, emsY = 0, statX = 0, statY = 0;
            int[] obsMat = null;

            int counter = 0;

            //Initial setups
            while ((line = br.readLine()) != null) {
//                System.out.println(line);
                String[] dimNData = line.split("\\s+");
                Integer rowCount = Integer.parseInt(dimNData[0]);
                Integer colCount = Integer.parseInt(dimNData[1]);
                Double[][] array = new Double[rowCount][colCount];

                if (counter < 3) {
                    for (int i = 0; i < rowCount.intValue(); i++) {
                        for (int j = 0; j < colCount.intValue(); j++) {
                            array[i][j] = Double.parseDouble(dimNData[j + colCount * i + 2]);
                        }
                    }
                } else {
                    //Emission sequence Matrix / Obs sequence.
                    obsMat = new int[rowCount];
                    for (int i = 1; i <= rowCount; i++) {
                        obsMat[i - 1] = Integer.parseInt(dimNData[i]);
                    }
                }

                switch (++counter) {
                    case 1:
                        txnMatrix = new Double[rowCount][colCount];
                        txnMatrix = array;
                        txnX = rowCount;
                        txnY = colCount;
                        break;
                    case 2:
                        emsnMatrix = new Double[rowCount][colCount];
                        emsnMatrix = array;
                        emsX = rowCount;
                        emsY = colCount;

                        break;
                    case 3:
                        statDistrb = new Double[rowCount][colCount];
                        statDistrb = array;
                        statX = rowCount;
                        statY = colCount;
                        break;

                    default:
                        break;

                }

            }

            //Work begins here
            double[][] gammaCurr = new double[obsMat.length][txnX];
            int[][] idxCurr = new int[obsMat.length - 1][txnX];
            int t = 0;
            // Initialize Gamma[i]
            for (int i = 0; i < txnX; i++) {
                gammaCurr[0][i] = statDistrb[statX - 1][i] * emsnMatrix[i][obsMat[t]];
            }

            for (t = 1; t < obsMat.length; t++) {
                for (int i = 0; i < txnX; i++) {
                    double max = 0d;
                    int idx = 0;
                    double temp = 0d;
                    for (int j = 0; j < txnX; j++) {
                        temp = txnMatrix[j][i] * gammaCurr[t - 1][j] * emsnMatrix[i][obsMat[t]];
                        if (max < temp) {
                            max = temp;
                            idx = j;
                        }
                    }
                    gammaCurr[t][i] = max;
                    idxCurr[t - 1][i] = idx;
                }
            }

            //Begin Backtracking for a solution
            double max = 0;
            int index = 0;
            int[] displayArray = new int[obsMat.length];
            for (int x = 0; x < txnX; x++) {
                if (gammaCurr[obsMat.length - 1][x] > max) {
                    max = gammaCurr[obsMat.length - 1][x];
                    index = x;
                }
            }
            displayArray[0] = index;
            int ctr = 1;

            for (int j = (obsMat.length) - 2; j >= 0; j--) {
                index = idxCurr[j][index];
                displayArray[ctr++] = index;
            }

            for (int i = obsMat.length - 1; i >= 0; i--) {
                System.out.print(displayArray[i] + " ");
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(HMM3.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(HMM3.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
