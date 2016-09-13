/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hmm4;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import static java.lang.Math.log;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rohin
 */
public class HMM4 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            // TODO code application logic here
            BufferedReader br = null;
            br = new BufferedReader(new FileReader("F:\\KTH\\Study Period 1\\AI\\HMM4 Data\\hmm4_01.in"));
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


            int iters = 0;
            int maxIters = 10;
            double oldLogProb = -999999999;
            double[][] alphaCurr = new double[obsMat.length][txnX];
            int t = 0;
            //Alpha pass

            //scaling matrix
            double c[] = new double[obsMat.length];

            while (iters < maxIters) {

//                t = 0;
                c[0] = 0;
                for (int i = 0; i < txnX; i++) {
                    alphaCurr[0][i] = statDistrb[statX - 1][i] * emsnMatrix[i][obsMat[0]];
                    c[0] += alphaCurr[0][i];
                }
                //scale the alpha
                c[0]=1/c[0];
                for (int i = 0; i < txnX; i++) {
                    alphaCurr[0][i] *= c[0];
                }
                //Move to the next in the observation sequence?
                //compute alpha_t[i]
                for (t = 1; t < obsMat.length; t++) {
                    c[t] = 0;
                    for (int i = 0; i < txnX; i++) {
                        alphaCurr[t][i] = 0;
                        for (int j = 0; j < txnX; j++) {
                            alphaCurr[t][i] += alphaCurr[t - 1][j] * txnMatrix[j][i];
                        }
                        alphaCurr[t][i] *= emsnMatrix[i][obsMat[t]];
                        c[t] += alphaCurr[t][i];
                    }

                    //scale alpha_t[i]
                    c[t] = 1 / c[t];
                    for (int i = 0; i < txnX; i++) {
                        alphaCurr[t][i] *= c[t];
                    }

                }

                //Beta Pass
                double[][] betaCurr = new double[obsMat.length][txnX];
                for (int i = 0; i < txnX; i++) {
                    betaCurr[obsMat.length - 1][i] = c[obsMat.length - 1];
                }

                for (t = obsMat.length - 2; t >= 0; t--) {
                    for (int i = 0; i < txnX; i++) {
                        betaCurr[t][i] = 0;
                        for (int j = 0; j < txnX; j++) {
                            betaCurr[t][i] += txnMatrix[i][j] * emsnMatrix[j][obsMat[t + 1]] * betaCurr[t + 1][j];
                        }
                        betaCurr[t][i] *= c[t];
                    }
                }

                double denom = 0;
                double numer = 0;
                double[][][] digamma = new double[obsMat.length][txnX][txnX];
                double[][] gamma = new double[obsMat.length][txnX];
                //Compute gamma and digamma
                for (t = 0; t < obsMat.length; t++) {
                    if (t < obsMat.length - 1) {
                        denom = 0;
                        for (int i = 0; i < txnX; i++) {
                            for (int j = 0; j < txnX; j++) {
                                denom += (alphaCurr[t][i] * txnMatrix[i][j] * emsnMatrix[j][obsMat[t + 1]] * betaCurr[t + 1][j]);
                            }
                        }

                        for (int i = 0; i < txnX; i++) {
                            gamma[t][i] = 0;
                            for (int j = 0; j < txnX; j++) {
                                digamma[t][i][j] = (alphaCurr[t][i] * txnMatrix[i][j] * emsnMatrix[j][obsMat[t + 1]] * betaCurr[t + 1][j]) / denom;
                                gamma[t][i] += digamma[t][i][j];
                            }
                        }
                    } else {
                        denom = 0;
                        for (int i = 0; i < txnX; i++) {
                            denom += alphaCurr[t][i];
                        }
                        for (int i = 0; i < txnX; i++) {
                            gamma[t][i] = alphaCurr[t][i] / denom;
                        }
                    }

                }

                //re-estimate pi
                for (int i = 0; i < txnX; i++) {
                    statDistrb[statX - 1][i] = gamma[0][i];
                }

                //re-estimate A
                for (int i = 0; i < txnX; i++) {
                    for (int j = 0; j < txnX; j++) {
                        numer = 0;
                        denom = 0;
                        for (t = 0; t < obsMat.length - 1; t++) {
                            numer += digamma[t][i][j];
                            denom += gamma[t][i];
                        }
                        txnMatrix[i][j] = numer / denom;
                    }
                }

                //re-estimate B
                for (int i = 0; i < txnX; i++) {
                    for (int j = 0; j < emsY; j++) {
                        numer = 0;
                        denom = 0;
                        for (t = 0; t < obsMat.length; t++) {
                            if (obsMat[t] == j) {
                                numer += gamma[t][i];
                            }
                            denom += gamma[t][i];
                        }
                        emsnMatrix[i][j] = numer / denom;
                    }
                }

                double logProb = 0;
                for (int i = 0; i < obsMat.length; i++) {
                    logProb += log(c[i]);
                }

                logProb = -logProb;

                iters++;
                if (iters < maxIters && logProb > oldLogProb) {
                    oldLogProb = logProb;
                } else {
                    System.out.println("iters" + iters+" oldLogProb:"+oldLogProb);
                    System.out.print(txnX + " " + txnY + " ");
                    for (int i = 0; i < txnX; i++) {
                        for (int j = 0; j < txnY; j++) {
                            System.out.print(Math.round(txnMatrix[i][j] * Math.pow(10, 6)) / Math.pow(10, 6) + " ");
//                              System.out.print(txnMatrix[i][j]+" ");
                        }
                    }
                    System.out.println("");
                    System.out.print(txnX + " " + emsY + " ");
                    for (int i = 0; i < txnX; i++) {
                        for (int j = 0; j < emsY; j++) {
//                            System.out.print(emsnMatrix[i][j]+" ");
                            System.out.print(Math.round(emsnMatrix[i][j] * Math.pow(10, 6)) / Math.pow(10, 6) + " ");
                        }
                    }

                    System.exit(0);
                }
            }
//Math.round(txnMatrix[i][j] * Math.pow(10, 6)) / Math.pow(10, 6)
//            double sum = 0d;
//            for (int i = 0; i < txnX; i++) {
//                sum = sum + alphaCurr[alphaCurr.length - 1][i];
//            }
//            System.out.println(Math.round(sum * Math.pow(10, 6)) / Math.pow(10, 6));

        } catch (FileNotFoundException ex) {
            Logger.getLogger(HMM4.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(HMM4.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
