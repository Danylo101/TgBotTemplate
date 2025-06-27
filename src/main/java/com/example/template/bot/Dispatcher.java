package com.example.template.bot;


import com.example.template.bot.handlers.RequestHandler;
import com.example.template.bot.model.UserRequest;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class Dispatcher {
    List<RequestHandler> handlers;

    public void dispatch(UserRequest userRequest) {
        for (RequestHandler userRequestHandler : handlers) {
            if (userRequestHandler.isApplicable(userRequest)) {
                userRequestHandler.handle(userRequest);

                System.out.println(userRequestHandler); // TODO FOR TEST
            }
        }
    }
}
