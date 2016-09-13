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
            br = new BufferedReader(new FileReader("F:\\KTH\\Study Period 1\\AI\\HMM3 Data\\sample2.txt"));
//            br = new BufferedReader(new InputStreamReader(System.in));
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
//            double[][] firstResult = new double[statX][txnY];
//            for (int i = 0; i < statX; i++) {
//                for (int j = 0; j < txnY; j++) {
//                    for (int k = 0; k < statY; k++) {
//                        firstResult[i][j] += statDistrb[i][k] * txnMatrix[k][j];
//                    }
//                }
//            }
            double[][] gammaCurr = new double[obsMat.length][txnX];
            int[][] idxCurr = new int[obsMat.length - 1][txnX];
            int t = 0;
            // Initialize alphaPrev[i]
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
            System.out.println("Here");
            //Move to the next in the observation sequence ?

//            for (t = 1; t < obsMat.length; t++) {
//                for (int i = 0; i < txnX; i++) {
//                    alphaCurr[t][i] = 0;
//                    for (int j = 0; j < txnX; j++) {
//                        alphaCurr[t][i] = alphaCurr[t][i] + alphaCurr[t - 1][j] * txnMatrix[j][i];
//                    }
//                    alphaCurr[t][i] = alphaCurr[t][i] * emsnMatrix[i][obsMat[t]];
//                }
//
//            }
//            double sum = 0d;
//            for (int i = 0; i < txnX; i++) {
//                sum = sum + alphaCurr[alphaCurr.length - 1][i];
//            }
//            System.out.println(Math.round(sum * Math.pow(10, 6)) / Math.pow(10, 6));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(HMM3.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(HMM3.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
