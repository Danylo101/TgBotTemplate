package com.example.template.bot.handlers.commands;

import com.example.template.bot.handlers.RequestHandler;
import com.example.template.bot.model.UserRequest;
import com.example.template.bot.util.Sender;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class Help extends RequestCommandHandler implements RequestHandler, ICommand {
    final String helpMessage;
    Sender sender;

    public Help(List<ICommand> commandsList, Sender sender) {
        this.sender = sender;
        this.helpMessage = buildHelpMessage(commandsList);
    }

    @Override
    public String getCommandName() {
        return "/help";
    }

    @Override
    public String getCommandDescription() {
        return "List of available commands.";
    }

    @Override
    public boolean isApplicable(UserRequest request) {
        return isCommand(request.getUpdate(), getCommandName());
    }

    @Override
    public void handle(UserRequest request) {
        sender.execute(SendMessage.builder()
                .chatId(request.getChatId())
                .text(helpMessage)
                .build());
    }

    private String buildHelpMessage(List<ICommand> commands) {
        StringBuilder sb = new StringBuilder("Available commands:\n\n");
        for (ICommand command : commands) {
            sb.append(command.getCommandName())
                    .append(" — ")
                    .append(command.getCommandDescription())
                    .append("\n\n");
        }

        sb.append(getCommandName())
                .append(" — ")
                .append(getCommandDescription())
                .append("\n");
        return sb.toString();
    }
}
