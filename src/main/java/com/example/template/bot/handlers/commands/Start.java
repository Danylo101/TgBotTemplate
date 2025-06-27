package com.example.template.bot.handlers.commands;


import com.example.template.bot.handlers.RequestHandler;
import com.example.template.bot.model.UserRequest;
import com.example.template.bot.util.Sender;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class Start extends RequestCommandHandler implements RequestHandler, ICommand {
    Sender sender;

    @Override
    public boolean isApplicable(UserRequest request) {
        return isCommand(request.getUpdate(), getCommandName());
    }

    @Override
    public String getCommandName() {
        return "/start";
    }

    @Override
    public String getCommandDescription() {
        return "bla-bla-bla";
    }

    @Override
    public void handle(UserRequest request) {
        sender.execute(SendMessage.builder()
                .text("This is start message")
                .chatId(request.getChatId())
                .build());
    }
}
