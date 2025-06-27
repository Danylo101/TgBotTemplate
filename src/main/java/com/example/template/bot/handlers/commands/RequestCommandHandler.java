package com.example.template.bot.handlers.commands;

import com.example.template.bot.model.UserRequest;
import org.telegram.telegrambots.meta.api.objects.Update;

public class RequestCommandHandler {
    public boolean isCommand(Update update, String command) {
        return update.hasMessage()
                && update.getMessage().hasText()
                && update.getMessage().isCommand()
                && update.getMessage().getText().equals(command);
    }

    public boolean isTextMessage(UserRequest request) {
        return request.getUpdate().hasMessage()
                && request.getUpdate().getMessage().hasText();
    }

    public boolean isUserPrivateChat(UserRequest request) {
        return request.getUpdate().hasMessage()
                && request.getUpdate().getMessage().getChat().isUserChat();
    }
}
