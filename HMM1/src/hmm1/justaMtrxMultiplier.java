/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hmm1;

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
public class justaMtrxMultiplier {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            // TODO code application logic here
            BufferedReader br = null;
//            br = new BufferedReader(new FileReader("F:\\KTH\\Study Period 1\\AI\\HMM1 Data\\sample_00.in"));
            br = new BufferedReader(new InputStreamReader(System.in));
            String line = new String();
            Double[][] txnMatrix = null;
            Double[][] emsnMatrix = null;
            Double[][] statDistrb = null;
            int txnX = 0, txnY = 0, emsX = 0, emsY = 0, statX = 0, statY = 0;

            int counter = 0;

            //Initial setups
            while ((line = br.readLine()) != null) {
//                System.out.println(line);
                String[] dimNData = line.split("\\s+");
                Integer rowCount = Integer.parseInt(dimNData[0]);
                Integer colCount = Integer.parseInt(dimNData[1]);
                Double[][] array = new Double[rowCount][colCount];

                for (int i = 0; i < rowCount.intValue(); i++) {
                    for (int j = 0; j < colCount.intValue(); j++) {
                        array[i][j] = Double.parseDouble(dimNData[j + colCount * i + 2]);
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
            double[][] firstResult = new double[statX][txnY];

            for (int i = 0; i < statX; i++) {
                for (int j = 0; j < txnY; j++) {
                    for (int k = 0; k < statY; k++) {
                        firstResult[i][j] += statDistrb[i][k] * txnMatrix[k][j];
                    }
                }
            }

            double[][] finalResult = new double[statX][emsY];
            for (int i = 0; i < statX; i++) {
                for (int j = 0; j < emsY; j++) {
                    for (int k = 0; k < txnY; k++) {
                        finalResult[i][j] += firstResult[i][k] * emsnMatrix[k][j];
                    }
                }
            }
            System.out.print(statX + " " + emsY);
            for (int i = 0; i < emsY; i++) {
                System.out.print(" " + Math.round(finalResult[0][i] * Math.pow(10, 2)) / Math.pow(10, 2));
            }
            System.out.println("");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(justaMtrxMultiplier.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(justaMtrxMultiplier.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
