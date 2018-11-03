/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;


/**
 * This class provide tools for manipulating CSV files
 * @author Francois
 */
public class CSVElement {
    
    /**
     * @brief This function create a char array from a CSV file.
     * Size of the array must be specified
     * @throws FileNotFoundException
     * @param p_nbColumn : Column number (X)
     * @param p_nbLine : Line number (Y)
     * @param p_path : path to the level csv file
     * @return the CSV file as a {@code int[][]}
     */
    public static int[][] readCSVFile(int p_nbLine, int p_nbColumn, String p_path) throws FileNotFoundException{
        
        int[][] ar_Board = new int[p_nbColumn][p_nbLine];
        File myCSV = new File(p_path);
        BufferedReader br;
        
        try {
            br = new BufferedReader(new FileReader(myCSV));
            
            for (int line = 0; line < p_nbLine ; line++){
                for (int column = 0; column < p_nbColumn; column++){
                    char f_character;

                    try {
                        f_character = (char)br.read();
                        //If it's a Return or comma character from CSV, take the next one
                        if(f_character == (char)'\n' || f_character == ',')
                            f_character = (char)br.read();
                        
                        ar_Board[line][column] = Character.getNumericValue(f_character);
                    } catch (IOException ex) {
                        System.err.println(ex.toString());
                    }
                }
            }
        } catch (FileNotFoundException ex) {
                        System.err.println(ex.toString());
        }

        return ar_Board;
    }
    
            /**
         * @brief Ask the user to pick the level CSV file
         * @author Francois
         * @return String Absolute path to the CSV file
         */
        public static String pick_CSVLevel(){
            FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV File", "csv");
            JFileChooser chooser = new JFileChooser("./src/view/levels");
            chooser.setFileFilter(filter);
            
                int returnVal = chooser.showOpenDialog(null);
                if(returnVal == JFileChooser.APPROVE_OPTION) {
                    return chooser.getSelectedFile().getAbsolutePath();
                }
    
            return "";
        }
    
    // TEST ONLY
        /*
    public static void main (String[] args) throws IOException{
    String path_Mac = "/Users/benjamin/Dropbox/0-ECE/JAVA_POO/Projet_Sokoban/MySokoban/src/view/niveau_1.csv";
        
        int[][] laVar = readCSVFile(20,20, path_Mac);
        
        for (int line = 0; line < 20 ; line++){
            for (int column = 0; column < 20; column++){
                System.out.print(laVar[line][column]+" ");
            }
            System.out.println("");
        }
        
        
        //https://stackoverflow.com/questions/28405833/change-color-of-output-text-in-netbeans-ide-window-and-clear-the-output-area
        String ANSI_RESET = "\u001B[0m";
        String ANSI_RED = "\u001B[31m";
        System.out.println(ANSI_RED + "This text is red!" + ANSI_RESET + " texte noir");
    }*/
        
}
