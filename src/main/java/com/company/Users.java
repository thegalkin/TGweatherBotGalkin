package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class Users {

    private static File usersFile = new File("users.txt");




    public static void read(){

        FileReader fileReader = null;
        try {
            fileReader = new FileReader(usersFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int lines = 0;
//        while (fileReader.read() != null){
//
//        }


    }
    public static void write(){

    }
}
