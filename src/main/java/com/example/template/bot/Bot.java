package com.example.template.bot;


import com.example.template.bot.model.UserRequest;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.longpolling.interfaces.LongPollingUpdateConsumer;
import org.telegram.telegrambots.longpolling.starter.SpringLongPollingBot;
import org.telegram.telegrambots.longpolling.util.LongPollingSingleThreadUpdateConsumer;
import org.telegram.telegrambots.meta.api.objects.Update;


@Slf4j
@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class Bot implements SpringLongPollingBot, LongPollingSingleThreadUpdateConsumer {
    BotProperties botProperties;
    Dispatcher dispatcher;

    @Override
    public void consume(Update update) {
        Long chatId = null;
        if (update.hasMessage() && update.getMessage().hasText()) {
            chatId = update.getMessage().getChatId();
        } else if (update.hasCallbackQuery()) {
            chatId = update.getCallbackQuery().getMessage().getChatId();
        } 

        if (chatId != null) {
            UserRequest userRequest = UserRequest.builder()
                    .update(update)
                    .chatId(chatId)
                    .build();
            dispatcher.dispatch(userRequest);
        } else {
            log.warn("Unsupported update type: {}", update);
        }

    }

    @Override
    public String getBotToken() {
        return botProperties.getBotToken();
    }

    @Override
    public LongPollingUpdateConsumer getUpdatesConsumer() {
        return this;
    }
}
