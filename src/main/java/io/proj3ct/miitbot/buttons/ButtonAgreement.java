package io.proj3ct.miitbot.buttons;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.List;

public class ButtonAgreement implements CallBackHandler , MatType{
    @Override
    public List<InlineKeyboardButton> getKeyboardButton() {
        return null;
    }

    @Override
    public String getButtonCallbackData() {
        return null;
    }

    @Override
    public SendMessage handle(final Long userId) {
        return null;
    }

    @Override
    public int getSerial() {
        return 0;
    }
}
