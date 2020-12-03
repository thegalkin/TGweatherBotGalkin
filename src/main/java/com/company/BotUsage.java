package com.company;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Location;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;

public class BotUsage extends TelegramLongPollingBot {
    //Func where all command handling happens
    @Override
    public void onUpdateReceived(Update update) {
        if(update.hasMessage()){
            Message message = update.getMessage();


            if (message.hasText()){


//                if (message.getText() == "/start"){
//                    if (message.getChatId() instanceof )
//
//
//                }

                    SendMessage sendMessageRequest = new SendMessage();
                    sendMessageRequest.setChatId(message.getChatId().toString());
                    sendMessageRequest.setText("you said: " + message.getText());
                    System.out.println(message.getLocation());
                    try {
                        execute(sendMessageRequest);
                    } catch (TelegramApiException e) {

                    }
            }

            if(message.hasLocation()) {
                //Cutting out location for user
                Location location = message.getLocation();
                String longitude = location.getLongitude().toString();
                String latitude = location.getLatitude().toString();

                //getting forecast for this call
                String forecast = null;
                try {
                    forecast = getWeather.getForecast(longitude, latitude, Main.weatherToken());
                } catch (IOException e) {
                    e.printStackTrace();
                }

                SendMessage sendMessageRequest = new SendMessage();
                sendMessageRequest.setChatId(message.getChatId().toString());
                sendMessageRequest.setText(forecast);
                System.out.printf("https://yandex.ru/maps/?pt=%s,%s&z=10&l=map%n", latitude, longitude);
                try {
                    execute(sendMessageRequest);
                } catch (TelegramApiException ignored) {

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
