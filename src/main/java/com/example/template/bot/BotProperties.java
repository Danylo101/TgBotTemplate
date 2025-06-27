package com.example.template.bot;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Data
@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BotProperties {
    String botToken = "6969028939:AAG-r0xVuymIWJ970nNqJP0dW5CMcNZFyyY"; // test
}
