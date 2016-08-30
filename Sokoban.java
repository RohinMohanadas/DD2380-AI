/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rohin.kth;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rohin
 */
public class Sokoban {

    public int playerX = 0;
    public int playerY = 0;
    static ArrayList<String> ar = new ArrayList<String>();
    static String[] Map;
    private static Character[][] Maps;
    static Integer maxLength = 0;
    static Integer maxHeight = 0;
    static ArrayList<Character> output = new ArrayList<Character>();
    static Integer playerlocx = 0;
    static Integer playerlocy = 0;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

//        File inFile = null;
//        if (0 < args.length) {
//            inFile = new File(args[0]);
//        } else {
//            inFile = new File("F:\\KTH\\Sokoban\\00_sample.in");
//        }
        BufferedReader br = null;

        try {

            String sCurrentLine;

            br = new BufferedReader(new InputStreamReader(System.in));
//            br = new BufferedReader(new FileReader(inFile));
            while ((sCurrentLine = br.readLine()) != null) {
                ar.add(sCurrentLine);
            }

            Map = ar.toArray(new String[ar.size()]);
            Boolean flag = FALSE;
//        System.out.println("Map.length");
            for (int i = 0; i < Map.length; i++) {
                // Use this to draw the map in output
//                System.out.println(Map[i]);
                if (Map[i].contains("+")) {
                    playerlocx = Map[i].indexOf("+");
                    playerlocy = i;
                    flag = TRUE;
//                    System.exit(0);
                }
                if (Map[i].contains("@")) {
                    playerlocx = Map[i].indexOf("@");
                    playerlocy = i;
                }
                if (Map[i].length() > maxLength) {
                    maxLength = Map[i].length();
                }
            }
            if (flag == TRUE) {
                System.exit(0);
            }
            maxHeight = Map.length;
//Debugging code
//            System.out.println("Location of player is : " + playerlocx.toString() + "," + playerlocy.toString());
//            System.out.println("Dimensions of the maze: " + maxHeight + "X" + maxLength);
            Maps = new Character[maxLength][maxHeight];
            for (int i = 0; i < maxLength; i++) {
                for (int j = 0; j < maxHeight; j++) {
                    Maps[i][j] = 'C'; //clear map
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Sokoban.class.getName()).log(Level.SEVERE, null, ex);
        }
        {
            //Calling the recursive function to find the solution.
            doTheChacha(playerlocx, playerlocy);
            System.out.println("no path");
            System.exit(0);
        }
    }

    public void setCoordinates(Integer x, Integer y) {
        playerX = x;
        playerY = y;
    }

    /* For debugging purposes
    
########
#   # .#
#   $$.#
####   #
   #@ ##
   ####
    
     */
    public static void doTheChacha(Integer x, Integer y) {
        Maps[x][y] = 'V';
        //Right
        if (x < maxLength - 1) {
            if ((Map[y].charAt(x + 1) == ' ' || Map[y].charAt(x + 1) == '.') && Maps[x + 1][y] != 'V') {
                if (Map[y].charAt(x + 1) == '.') {
//                    System.out.println('R');
                    output.add('R');
                    System.out.println(output.toString().replace(",", "").replace("[", "").replace("]", ""));
                    System.exit(0);
                } else {
//                    System.out.println('R');
                    output.add('R');
//                    Maps[x + 1][y] = 'V';
                    doTheChacha(x + 1, y);
                }
            } else if (Map[y].charAt(x + 1) == '#' || Map[y].charAt(x + 1) == '$' || Maps[x + 1][y] == 'F') {
//                if (!output.isEmpty()) {
//                    output.remove(output.size() - 1);
//                }
            }
        }
//        if (!output.isEmpty()) {
//            output.remove(output.size() - 1);
//        }
        //DOWN
        if (y < maxHeight - 1) {
            if ((Map[y + 1].charAt(x) == ' ' || Map[y + 1].charAt(x) == '.') && Maps[x][y + 1] != 'V') {
                if (Map[y + 1].charAt(x) == '.') {
//                    System.out.println('D');
                    output.add('D');
                    System.out.println(output.toString().replace(",", "").replace("[", "").replace("]", ""));
                    System.exit(0);
                } else {
//                    System.out.println('D');
                    output.add('D');
//                    Maps[x][y + 1] = 'V';
                    doTheChacha(x, y + 1);
                }
            } else if (Map[y + 1].charAt(x) == '#' || Map[y + 1].charAt(x) == '$' || Maps[x][y + 1] == 'F') {

            }
        }
//        if (!output.isEmpty()) {
//            output.remove(output.size() - 1);
//        }
        //LEFT
        if (x > 1) {
            if ((Map[y].charAt(x - 1) == ' ' || Map[y].charAt(x - 1) == '.') && Maps[x - 1][y] != 'V') {
                if (Map[y].charAt(x - 1) == '.') {
//                    System.out.println('L');
                    output.add('L');
                    System.out.println(output.toString().replace(",", "").replace("[", "").replace("]", ""));
                    System.exit(0);
                } else {
//                    System.out.println('L');
                    output.add('L');
//                    Maps[x - 1][y] = 'V';
                    doTheChacha(x - 1, y);
                }
            } else if (Map[y].charAt(x - 1) == '#' || Map[y].charAt(x - 1) == '$' || Maps[x - 1][y] == 'F') {

            }
        }
//        if (!output.isEmpty()) {
//            output.remove(output.size() - 1);
//        }
        //UP
        if (y > 1) {
            if ((Map[y - 1].charAt(x) == ' ' || Map[y - 1].charAt(x) == '.') && Maps[x][y - 1] != 'V') {
                if (Map[y - 1].charAt(x) == '.') {
//                    System.out.println('U');
                    output.add('U');
                    System.out.println(output.toString().replace(",", "").replace("[", "").replace("]", ""));
                    System.exit(0);
                } else {
//                    System.out.println('U');
                    output.add('U');
//                    Maps[x][y - 1] = 'V';
                    doTheChacha(x, y - 1);
                }
            } else if (Map[y - 1].charAt(x) == '#' || Map[y - 1].charAt(x) == '$' || Maps[x][y - 1] == 'F') {

            }

//            if (!output.isEmpty()) {
//                output.remove(output.size() - 1);
//            }
        }
        if (!output.isEmpty()) {
            output.remove(output.size() - 1);
        }
        //System.out.println(Maps[x][y + 1] + "x,y+1" + Maps[x + 1][y] + "x+1,y" + Maps[x - 1][y] + "x-1,y" + Maps[x][y - 1] + "x,y-1");
        if (Maps[x][y + 1] == 'V' && Maps[x + 1][y] == 'V' && Maps[x - 1][y] == 'V' && Maps[x][y - 1] == 'V') {
            //System.out.println(Maps[x][y + 1] + "x,y+1" + Maps[x + 1][y] + "x+1,y" + Maps[x - 1][y] + "x-1,y" + Maps[x][y - 1] + "x,y-1");
            Maps[x][y] = 'F';

        }

        if (Maps[x][y + 1] == 'F' && Maps[x + 1][y] == 'F' && Maps[x - 1][y] == 'F' && Maps[x][y - 1] == 'F') {
            System.out.println(Maps[x][y + 1] + "x,y+1" + Maps[x + 1][y] + "x+1,y" + Maps[x - 1][y] + "x-1,y" + Maps[x][y - 1] + "x,y-1");
            System.out.println("No Path");
            System.exit(0);
        }

        return;
    }
}
