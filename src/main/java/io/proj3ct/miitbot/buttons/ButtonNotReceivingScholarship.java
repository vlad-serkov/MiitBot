package io.proj3ct.miitbot.buttons;

import io.proj3ct.miitbot.cache.UserDataCache;
import io.proj3ct.miitbot.constrants.Buttons;
import io.proj3ct.miitbot.constrants.AskState;
import io.proj3ct.miitbot.dto.UserProfileData;
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
public class ButtonNotReceivingScholarship implements CallBackHandler, MatType {
    int serial = 13;
    @Autowired
    UserDataCache userDataCache;
    Buttons button = Buttons.BUTTON_NOT_RECEIVING_SCHOLARSHIP;
    InlineKeyboardButton keyboardButton;

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
    public SendMessage handle(Long userId) {
        final UserProfileData  userProfileData= userDataCache.getUserProfileData(userId);
        userProfileData.setTypeMat(button);
        userDataCache.saveUserProfileData(userId, userProfileData);
        userDataCache.setUsersCurrentUserState(userId, AskState.ASK_GROUP);
        return new SendMessage(String.valueOf(userId), AskState.ASK_INSTITUTE.getMessage());
    }
}
