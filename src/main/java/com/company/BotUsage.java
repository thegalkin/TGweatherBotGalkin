package com.company;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Location;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;
import java.util.HashMap;

public class BotUsage extends TelegramLongPollingBot {
    //Func where all command handling happens
    @Override
    public void onUpdateReceived(Update update) {
        if(update.hasMessage()){
            Message message = update.getMessage();
            Boolean messageUnderstandable = false;

            if (message.hasText()){

                String userID = message.getChatId().toString();
                String messageText = message.getText();

                System.out.println(messageText);
                    if (message.getText().equals("/start")) {
                        messageUnderstandable = true;
                        try {
                            if (Users.isInstance(userID) != null) {
                                //if user is already in our userBase
                                sendMessageHandler(message, "You've been already registered!");

                            } else {
                                //if user is new to us
                                Users.write(userID + ":" + "null" + "\n");
                                sendMessageHandler(message, "Hello, please send me the location!");
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if(message.getText().equals("/subscribe")) {
                            messageUnderstandable = true;
                        try {

                            if (Users.isInstance(userID) != null && Users.isInstance(userID) != "null") {
                                LocalCron.subscribe(userID);
                                sendMessageHandler(message, "You've been successfully subscribed!");
                            }

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if(message.getText().equals("/unsubscribe")) {
                        messageUnderstandable = true;
                        try {

                            if (Users.isInstance(userID) != null && Users.isInstance(userID) != "null") {
                                LocalCron.unsubscribe(userID);
                                sendMessageHandler(message, "You've been successfully unsubscribed!");
                            }

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if(!messageUnderstandable) {
                        sendMessageHandler(message, "I don't understand you!");
                    }






            }

            if(message.hasLocation()) {
                //Cutting out location for user
                Location location = message.getLocation();
                String longitude = location.getLongitude().toString();
                String latitude = location.getLatitude().toString();
                String userID = message.getChatId().toString();

                //getting forecast for this call
                String forecast = null;
                try {
                    forecast = getWeather.getForecast(longitude, latitude, Main.weatherToken());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (forecast != null) {
                    sendMessageHandler(message, forecast);
                }
                try {
                    Users.writeLocation(userID, latitude, longitude);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.printf("https://yandex.ru/maps/?pt=%s,%s&z=10&l=map%n", longitude, latitude);



            }

        }

    }
    //message sender
    public void sendMessageHandler(Message messageLocal, String messageText){
        SendMessage sendMessageRequest = new SendMessage();
        sendMessageRequest.setChatId(messageLocal.getChatId().toString());
        sendMessageRequest.setText(messageText);
        try {
            execute(sendMessageRequest);
        } catch (TelegramApiException ignored) {}
    }
    //when called - sends a message with actual forecast
    public void subscriptionTime(String userID) throws IOException {

        HashMap<String, String> usersHash = Users.read();

        String coordinatesGlued = usersHash.get(userID);
        String latitude = coordinatesGlued.substring(0,coordinatesGlued.indexOf(","));
        String longitude = coordinatesGlued.substring(coordinatesGlued.indexOf(",")+1);

        String forecast = getWeather.getForecast(longitude,latitude,Main.weatherToken());


        //I had to not use the func because I don't have a message to rely on here
        SendMessage sendMessageRequest = new SendMessage();
        sendMessageRequest.setChatId(userID);
        sendMessageRequest.setText("Forecast, you've subscribed for:\n"+forecast);
        try {
            execute(sendMessageRequest);
        } catch (TelegramApiException ignored) {}

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
