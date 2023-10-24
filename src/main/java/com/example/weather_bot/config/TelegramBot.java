package com.example.weather_bot.config;

import com.example.weather_bot.config.entity.User;
import com.example.weather_bot.config.service.WeatherJsonService;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Slf4j
@Getter
@Component
public class TelegramBot extends TelegramLongPollingBot {

    private Message requestMessage = new Message();
    private final SendMessage response = new SendMessage();

    private final String botUsername;
    private final String botToken;
    @Autowired
    WeatherJsonService weatherJsonService;

    public TelegramBot(
            TelegramBotsApi telegramBotsApi,
            @Value("${telegram-bot.name}") String botUsername,
            @Value("${telegram-bot.token}") String botToken) throws TelegramApiException {
        this.botUsername = botUsername;
        this.botToken = botToken;

        telegramBotsApi.registerBot(this);
    }

    @SneakyThrows
    @Override
    public void onUpdateReceived(Update request) {
        requestMessage = request.getMessage();
        response.setChatId(requestMessage.getChatId().toString());
        weatherJsonService.getJsonWeather();
        var entity = new User(
                0, requestMessage.getChat().getUserName(),
                requestMessage.getText());

        if (request.hasMessage() && requestMessage.hasText())
            log.info("Working onUpdateReceived, request text[{}]", request.getMessage().getText());

        if (requestMessage.getText().equals("/start"))
            defaultMsg(response, "Напишите команду для показа списка мыслей: \n " + "/idea - показать мысли");
        else if (requestMessage.getText().equals("/idea"))

        log.info("Working, text[{}]", requestMessage.getText());

        if (requestMessage.getText().startsWith("/")) {
            entity.setStartWord("команда: ");
        } else {
            entity.setStartWord("мысль: ");
        }
    }
    private void defaultMsg(SendMessage response, String msg) throws TelegramApiException {
        response.setText(msg);
        execute(response);
    }
}
