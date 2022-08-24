package io.proj3ct.miitbot.buttons;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.List;

public interface CallBackHandler {
    List<InlineKeyboardButton> getKeyboardButton();
    String getButtonCallbackData();
    SendMessage handle(Long userId);
    
    int getSerial();
}
