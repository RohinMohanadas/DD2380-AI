
//import static java.lang.Math.random;
import static java.lang.Math.log;
import java.util.Random;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Rohin
 */
public class HMM {

    static final int COUNT_STATE = 5;
    private int obsLength = 0;
    private Integer[] obsMat = null;
    private double[][] txnMatrix = new double[COUNT_STATE][COUNT_STATE];
    private double[][] emsnMatrix = new double[COUNT_STATE][Constants.COUNT_MOVE];
    private double[][] statDistrb = new double[1][COUNT_STATE];

    public HMM(Bird currBird) {
        obsLength = currBird.getSeqLength();
//        System.err.println("this bird's Seq length : " + obsLength);
        obsMat = new Integer[obsLength];
        //populate the values into a local observation array. Neat.. :P
        for (int i = 0; i < obsLength; i++) {
            obsMat[i] = currBird.getObservation(i);
        }
    }

    public int takeADecision() {
        Random rand = new Random();
        //Initialize the HMM parameters

        //Init 1:  InitialStatematrix
        double rownSum = 0;
        double rowadjSum = 0; //this is used to make sure that the row is stochastic.
        int idx = 0;
        for (int i = 0; i < COUNT_STATE; i++) {
            statDistrb[0][i] = rand.nextInt((10 - 1) + 1) + 1;
            rownSum += statDistrb[0][i];
        }
        //scale it down
        for (int i = 0; i < COUNT_STATE; i++) {
            statDistrb[0][i] = statDistrb[0][i] / rownSum;
            rowadjSum += statDistrb[0][i];
        }
        // adjustment
        idx = rand.nextInt(((COUNT_STATE - 1) - 0) + 1);
        statDistrb[0][idx] += (1 - rowadjSum);
        rownSum = 0;
        rowadjSum = 0;

        //Init 2:  Txnmatrix initialization
        for (int i = 0; i < COUNT_STATE; i++) {
            for (int j = 0; j < COUNT_STATE; j++) {
                txnMatrix[i][j] = rand.nextInt((10 - 1) + 1) + 1;
                rownSum += txnMatrix[i][j];
            }
            for (int j = 0; j < COUNT_STATE; j++) {
                txnMatrix[i][j] = txnMatrix[i][j] / rownSum;
                rowadjSum += txnMatrix[i][j];
            }
            idx = rand.nextInt(((COUNT_STATE - 1) - 0) + 1);
            txnMatrix[i][idx] += rowadjSum;
        }

        //Init 3: emsMatrix initialization
        for (int i = 0; i < COUNT_STATE; i++) {
            for (int j = 0; j < Constants.COUNT_MOVE; j++) {
                emsnMatrix[i][j] = rand.nextInt((10 - 1) + 1) + 1;
                rownSum += emsnMatrix[i][j];
            }
            for (int j = 0; j < Constants.COUNT_MOVE; j++) {
                emsnMatrix[i][j] = emsnMatrix[i][j] / rownSum;
                rowadjSum += emsnMatrix[i][j];
            }
            idx = rand.nextInt(((COUNT_STATE - 1) - 0) + 1);
            emsnMatrix[i][idx] += rowadjSum;
        }

//        System.err.println("Initial : Init Matrix");
//        for (int i = 0; i < COUNT_STATE; i++) {
//            System.err.print(statDistrb[0][i] + " ");
//        }
//
//        System.err.println("Initial : Txn Matrix");
//        for (int i = 0; i < COUNT_STATE; i++) {
//            for (int j = 0; j < COUNT_STATE; j++) {
//                System.err.print(txnMatrix[i][j] + " ");
//            }
//            System.err.println();
//        }
//
//        System.err.println("Initial : Emission Matrix");
//        for (int i = 0; i < COUNT_STATE; i++) {
//            for (int j = 0; j < Constants.COUNT_MOVE; j++) {
//                System.err.print(emsnMatrix[i][j] + " ");
//            }
//            System.err.println();
//        }
        int iters = 0;
        int maxIters = 500;
        double oldLogProb = -999999999;
        double[][] alphaCurr = new double[obsMat.length][COUNT_STATE];
        int t = 0;
        //Alpha pass

        //scaling matrix
        double c[] = new double[obsMat.length];
        //Ennal pinne thodanguvalle?
        while (iters < maxIters) {

//                t = 0;
            c[0] = 0;
            for (int i = 0; i < COUNT_STATE; i++) {
                alphaCurr[0][i] = statDistrb[0][i] * emsnMatrix[i][obsMat[0]];
                c[0] += alphaCurr[0][i];
            }
            //scale the alpha
            c[0] = 1 / c[0];
            for (int i = 0; i < COUNT_STATE; i++) {
                alphaCurr[0][i] *= c[0];
            }
            //Move to the next in the observation sequence?
            //compute alpha_t[i]
            for (t = 1; t < obsMat.length; t++) {
                c[t] = 0;
                for (int i = 0; i < COUNT_STATE; i++) {
                    alphaCurr[t][i] = 0;
                    for (int j = 0; j < COUNT_STATE; j++) {
                        alphaCurr[t][i] += (alphaCurr[t - 1][j] * txnMatrix[j][i]);
                    }
//                    System.err.println("i:  "+i+"t: "+t);
                    try {
                        alphaCurr[t][i] *= emsnMatrix[i][obsMat[t]];
                    } catch(Exception e){
//                        System.err.println("i:  "+i+"t: "+t+"obsMat[t]"+obsMat[t]);
                        throw e;
                    }

                    c[t] += alphaCurr[t][i];
                }

                //scale alpha_t[i]
                c[t] = 1 / c[t];
                for (int i = 0; i < COUNT_STATE; i++) {
                    alphaCurr[t][i] *= c[t];
                }

            }

            //Beta Pass
            double[][] betaCurr = new double[obsMat.length][COUNT_STATE];
            for (int i = 0; i < COUNT_STATE; i++) {
                betaCurr[obsMat.length - 1][i] = c[obsMat.length - 1];
            }

            for (t = obsMat.length - 2; t >= 0; t--) {
                for (int i = 0; i < COUNT_STATE; i++) {
                    betaCurr[t][i] = 0;
                    for (int j = 0; j < COUNT_STATE; j++) {
                        betaCurr[t][i] += (txnMatrix[i][j] * emsnMatrix[j][obsMat[t + 1]] * betaCurr[t + 1][j]);
                    }
                    betaCurr[t][i] *= c[t];
                }
            }

            double denom = 0;
            double numer = 0;
            double[][][] digamma = new double[obsMat.length][COUNT_STATE][COUNT_STATE];
            double[][] gamma = new double[obsMat.length][COUNT_STATE];
            //Compute gamma and digamma
            for (t = 0; t < obsMat.length - 1; t++) {
//                    if (t < obsMat.length - 1) {
                denom = 0;
                for (int i = 0; i < COUNT_STATE; i++) {
                    for (int j = 0; j < COUNT_STATE; j++) {
                        denom += (alphaCurr[t][i] * txnMatrix[i][j] * emsnMatrix[j][obsMat[t + 1]] * betaCurr[t + 1][j]);
                    }
                }

                for (int i = 0; i < COUNT_STATE; i++) {
                    gamma[t][i] = 0;
                    for (int j = 0; j < COUNT_STATE; j++) {
                        digamma[t][i][j] = (alphaCurr[t][i] * txnMatrix[i][j] * emsnMatrix[j][obsMat[t + 1]] * betaCurr[t + 1][j]) / denom;
                        gamma[t][i] += digamma[t][i][j];
                    }
                }
            }

            denom = 0;
            for (int i = 0; i < COUNT_STATE; i++) {
                denom += alphaCurr[obsMat.length - 1][i];
            }
            for (int i = 0; i < COUNT_STATE; i++) {
                gamma[t][i] = alphaCurr[obsMat.length - 1][i] / denom;
            }

            //re-estimate pi
            for (int i = 0; i < COUNT_STATE; i++) {
                statDistrb[0][i] = gamma[0][i];
            }

            //re-estimate A
            for (int i = 0; i < COUNT_STATE; i++) {
                for (int j = 0; j < COUNT_STATE; j++) {
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
            for (int i = 0; i < COUNT_STATE; i++) {
                for (int j = 0; j < Constants.COUNT_MOVE; j++) {
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
//                System.err.println("iters" + iters + " oldLogProb:" + oldLogProb);
//                    System.out.print(COUNT_STATE + " " + txnY + " ");
//                for (int i = 0; i < COUNT_STATE; i++) {
//                    for (int j = 0; j < COUNT_STATE; j++) {
//                        System.out.print(Math.round(txnMatrix[i][j] * Math.pow(10, 6)) / Math.pow(10, 6) + " ");
////                            System.out.print(txnMatrix[i][j] + " ");
//                    }
//                }
//                System.out.println("");
////                    System.out.print(COUNT_STATE + " " + emsY + " ");
//                for (int i = 0; i < COUNT_STATE; i++) {
//                    for (int j = 0; j < Constants.COUNT_MOVE; j++) {
////                            System.out.print(emsnMatrix[i][j] + " ");
//                        System.out.print(Math.round(emsnMatrix[i][j] * Math.pow(10, 6)) / Math.pow(10, 6) + " ");
//                    }
//                }
//                System.err.println("Init Matrix");
//                for (int i = 0; i < COUNT_STATE; i++) {
//                    System.err.print(statDistrb[0][i] + " ");
//                }
//
//                System.err.println("Txn Matrix");
//                for (int i = 0; i < COUNT_STATE; i++) {
//                    for (int j = 0; j < COUNT_STATE; j++) {
//                        System.err.print(txnMatrix[i][j] + " ");
//                    }
//                    System.err.println();
//                }
//
//                System.err.println("Emission Matrix");
//                for (int i = 0; i < COUNT_STATE; i++) {
//                    for (int j = 0; j < Constants.COUNT_MOVE; j++) {
//                        System.err.print(emsnMatrix[i][j] + " ");
//                    }
//                    System.err.println();
//                }
                double sum = 0;
                double maxprob = 0;
                int possState = 0;
//                Integer[] obsStateNew = new Integer[obsLength + 1];
//                obsStateNew = obsMat;
                for (int i = 0; i < Constants.COUNT_MOVE; i++) {
//                    obsStateNew[obsLength] = i;
                    for (int j = 0; j < COUNT_STATE; j++) {
                        sum += alphaCurr[obsLength - 1][j] * emsnMatrix[j][i];
                    }
                    if (maxprob < sum) {
                        maxprob = sum;
                        possState = i;
                    }
                    sum = 0;
                }

//                System.err.println("Max prob is: " + maxprob + " which corresponds to possState :" + possState);
                return possState;
            }
        }
        return -1;
    }
}
