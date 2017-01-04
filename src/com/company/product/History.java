package com.company.product;

import java.io.*;
import java.util.*;
/**
 * Created by loic on 29/12/16.
 */
public class History  {

    public final static String MSG_NEW_ROUND = "Manche Commence";
    public final static String MSG_END_GAME = "Partie finie";
    public final static String MSG_DRAW = "Egalité";


    private File file;
    private FileWriter writer;


    public History() {
        try {
            file = new File("log.txt");
            // creates the file
            file.createNewFile();
            // creates a FileWriter Object
            writer = new FileWriter(file, false); //overwrite
        } catch (IOException e){
            System.err.println("error write");
        }
    }

    public void save(String msg){
        try{
            writer.write(msg + "\n");
            writer.flush(); // important
        } catch (IOException e){
            System.err.println("error write");
        }
    }

    public void close(){
        try{
            writer.close();
        } catch (IOException e){
            System.err.println("error close");
        }
    }



}
