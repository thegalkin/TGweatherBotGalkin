package com.company;

import java.io.*;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.nio.file.Files;
import java.util.Map;

public class Users {
    static String usersFileName = "users.txt";




    public static String isInstance(String userID) throws IOException {
        HashMap<String, String> usersHash = read();
            //this IF returns user location(Str null) if this user exists
            // else it returns literally null instead of location
            if(usersHash.getOrDefault(userID, "not found") != "not found"){
                //if found returns user location
                return usersHash.get(userID);
            }else{
            //if userId not found
            write(String.format("%s:%s", userID, "null"));
            return null;
            }
    }



    //кейсы 1. человек отправляет старт, ему ставится нулевая локация, он отправляет локацию,мы обновляем строку с айди и локацией на новую, обновляем таблицу
//    2. человек уже в файле с локцией - он отправляет новую локицю - надо заменить старую строку с айди и локацией на новую, обновляем таблицу
    public static void writeLocation(String userID, String latitude, String longitude) throws IOException {

        HashMap<String, String> tempUserFile = read();
        tempUserFile.remove(userID);
        tempUserFile.put(userID, String.format("%s,%s",latitude,longitude));
        rewrite(tempUserFile);

    }
    public static HashMap<String, String> read() throws IOException {
        HashMap<String, String> usersHash = new HashMap<>();
        List<String> usersList = Files.readAllLines(Path.of(usersFileName));
        if (usersList.stream().count() != 0) {

            for (String line : usersList) {
                String localUserId = line.substring(0, line.indexOf(":")); //may be bugs here
                String localUserLocation = line.substring(line.indexOf(":") + 1);
//                System.out.printf("UserId: %s\nLocation: %s\nWere generated\n", localUserId, localUserLocation);
                usersHash.put(localUserId, localUserLocation);
            }

        }
        return usersHash;

    }
    public static void rewrite(HashMap<String, String> mapToWrite) throws IOException {
        File usersFile = new File(usersFileName);
        usersFile.delete();

        Files.createFile(Path.of(usersFileName));
        usersFile = new File(usersFileName);
        FileWriter fw = new FileWriter(usersFile, true);

        for (Map.Entry<String, String> entry: mapToWrite.entrySet()){
            fw.write(entry.getKey() + ":" + entry.getValue() + "\n");
        }
        fw.close();
    }
    public static void write(String string) throws IOException {
        File usersFile = new File(usersFileName);
        FileWriter fw = new FileWriter(usersFile, true);
        fw.write(string);
        fw.close();
    }
    public class users{
        public String User;
    }
}
