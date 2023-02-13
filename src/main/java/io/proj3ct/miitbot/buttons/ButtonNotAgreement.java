package io.proj3ct.miitbot.buttons;

import io.proj3ct.miitbot.cache.UserDataCache;
import io.proj3ct.miitbot.constrants.AskState;
import io.proj3ct.miitbot.constrants.Buttons;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.List;
@Component
@RequiredArgsConstructor
@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ButtonNotAgreement implements CallBackHandler , CheckButton{

    int serial = 12;
    @Autowired
    private UserDataCache userDataCache;
    private Buttons button = Buttons.BUTTON_NOT_AGREEMENT;
    private InlineKeyboardButton keyboardButton;

    @PostConstruct
    void init() {
        keyboardButton = new InlineKeyboardButton();
        keyboardButton.setText(button.getText());
        keyboardButton.setCallbackData(button.getCallback());
    }

    @Override
    public List<InlineKeyboardButton> getKeyboardButton() {
        return Collections.singletonList(keyboardButton);
    }

    @Override
    public String getButtonCallbackData() {
        return button.getCallback();
    }

    @Override
    public SendMessage handle(final Long userId) {
        userDataCache.setUsersCurrentUserState(userId, AskState.ASK_FULL_NAME);
        return new SendMessage(String.valueOf(userId), AskState.ASK_FULL_NAME.getMessage());
    }

    @Override
    public int getSerial() {
        return serial;
    }
}
