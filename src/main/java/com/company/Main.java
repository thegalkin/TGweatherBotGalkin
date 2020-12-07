package com.company;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;


import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;


public class Main {
    //secret

    public static void main(String[] args) throws IOException, TelegramApiException, InterruptedException {
        apiBase();
        while (true){
            clock();
            TimeUnit.SECONDS.sleep(59);
        }

    }
    //secret
    public static String weatherToken(){
        return "5ceac6d2df7a14bffac5f541c8b422a6&units";
    }


    private static void clock() throws IOException {
        System.out.println("Running clock");
        Date date = new Date();
        HashMap<String, String> cronHash = LocalCron.read();
        for(Map.Entry<String, String> entry : cronHash.entrySet()){
            System.out.println("User: " + entry.getKey() +"\nTime: " + entry.getValue() + "\n" + "Current time: " + date.getTime());
                int timePeriod = 60000;
//            if(60000 > (date.getTime() - Long.parseLong(entry.getValue())) && 0 < (date.getTime() - Long.parseLong(entry.getValue()))){
              if(timePeriod > ((date.getTime() - Long.parseLong(entry.getValue()))%timePeriod) && 0 < ((date.getTime() % Long.parseLong(entry.getValue()))%timePeriod)){
                System.out.println("starting the time");
                BotUsage botUsage = new BotUsage();
                botUsage.subscriptionTime(entry.getKey());
            }
        }

    }

    public static void apiBase() throws TelegramApiException {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        try {

            telegramBotsApi.registerBot(new BotUsage());


        } catch (TelegramApiException ignored) {

        }

    }


}
