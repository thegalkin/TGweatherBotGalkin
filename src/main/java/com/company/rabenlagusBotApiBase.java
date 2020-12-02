package com.company;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class rabenlagusBotApiBase extends TelegramLongPollingBot {
    @Override
    public void onUpdateReceived(Update update) {
        if(update.hasMessage()){
            Message message = update.getMessage();


            if(message.hasText()){


                    SendMessage sendMessageRequest = new SendMessage();
                    sendMessageRequest.setChatId(message.getChatId().toString());
                    sendMessageRequest.setText("you said: " + message.getText());
                    try {
                        execute(sendMessageRequest);
                    } catch (TelegramApiException e) {

                    }
            }
        }

    }


    @Override
    public String getBotUsername() {
        return "theGalkinWeatherJava";
    }

    @Override
    public String getBotToken() {
        return "1479239978:AAEjMg5eddM2789iUphXxVrZaZmlDMvaLRs";
    }
}
