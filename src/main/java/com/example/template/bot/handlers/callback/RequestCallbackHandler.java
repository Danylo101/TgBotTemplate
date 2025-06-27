package com.example.template.bot.handlers.callback;


import com.example.template.bot.model.UserRequest;

public class RequestCallbackHandler {
    public boolean isCallbackRequest(UserRequest request) {
        return request.getUpdate().hasCallbackQuery();
    }
}
