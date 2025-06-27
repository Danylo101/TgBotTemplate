package com.example.template.bot.handlers;


import com.example.template.bot.model.UserRequest;

public interface RequestHandler {
    boolean isApplicable(UserRequest request);
    void handle(UserRequest request);

}
