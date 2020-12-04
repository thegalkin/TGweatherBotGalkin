package com.company;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;


import java.io.IOException;


public class Main {
    //secret

    public static void main(String[] args) throws IOException, TelegramApiException {

        apiBase();
        System.out.println("Main");
    }
    //secret
    public static String weatherToken(){
        return "5ceac6d2df7a14bffac5f541c8b422a6&units";
    }


    public static void apiBase() throws TelegramApiException {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        try {

            telegramBotsApi.registerBot(new BotUsage());
        } catch (TelegramApiException ignored) {

        }

    }


}
