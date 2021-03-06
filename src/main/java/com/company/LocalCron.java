package com.company;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


//File Structure
//UserID:Time of subscription


public class LocalCron {
    public static String fileName = "LocalCron.txt";
    public static HashMap<String, String> read() throws IOException {
        List<String> cronLines = Files.readAllLines(Path.of(fileName));
        HashMap<String, String> cronHash = new HashMap<>();
        if (cronLines.stream().count() != 0) {

            for (String line : cronLines) {
                String localUserId = line.substring(0, line.indexOf(":")); //may be bugs here
                String localUserTime = line.substring(line.indexOf(":") + 1);
//                System.out.printf("UserId: %s\nTime: %s\nWere generated", localUserId, localUserTime);
                cronHash.put(localUserId, localUserTime);
            }

        }
        return cronHash;
    }

    public static String subscribe(String userId) throws IOException {
        File cronFile = new File(fileName);
        HashMap<String, String> cronFileHash = read();
        cronFile.delete();



        Date date = new Date();
        Files.createFile(Path.of(fileName));
        File cronFileNew = new File(fileName);
        FileWriter fw = new FileWriter(cronFile);

        if(cronFileHash.containsKey(userId)){
            //rewrite time for subscription, when user whants to change the time
            cronFileHash.replace(userId, "" + date.getTime());//the stupidest thing I've ever seen, .toString doesn't work so...
        }else{
            //write and user time in line
            cronFileHash.put(userId, "" + date.getTime());
        }

        for(Map.Entry<String, String> entry : cronFileHash.entrySet()){
            fw.write(entry.getKey() + ":" + entry.getValue() +"\n");
        }
        fw.close();
        return "Success";

    }
    public static String unsubscribe(String userId) throws IOException {
        File cronFile = new File(fileName);
        HashMap<String, String> cronFileHash = read();
        cronFile.delete();




        Files.createFile(Path.of(fileName));
        File cronFileNew = new File(fileName);
        FileWriter fw = new FileWriter(cronFile);

        if(cronFileHash.containsKey(userId)){
            //remove subscription if there is one
            cronFileHash.remove(userId);
        }else{

            return "Subscription not found";
        }

        for(Map.Entry<String, String> entry : cronFileHash.entrySet()){
            fw.write(entry.getKey() + ":" + entry.getValue() +"\n");
        }
        fw.close();
        return "Success";
    }

}
