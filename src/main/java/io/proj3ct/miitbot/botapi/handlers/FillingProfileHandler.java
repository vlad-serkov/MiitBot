package io.proj3ct.miitbot.botapi.handlers;

import io.proj3ct.miitbot.constrants.UserState;
import io.proj3ct.miitbot.cache.UserDataCache;
import io.proj3ct.miitbot.constrants.BotState;
import io.proj3ct.miitbot.dto.UserProfileData;
import io.proj3ct.miitbot.service.ReplyMessagesService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;


/**
 * Формирует анкету пользователя.
 */

@Slf4j
@Component
@AllArgsConstructor
public class FillingProfileHandler implements InputMessageHandler {
    private UserDataCache userDataCache;
    private ReplyMessagesService messagesService;

    @Override
    public SendMessage handle(Message message) {

        return processUsersInput(message);
    }

    @Override
    public BotState getHandlerName() {
        return BotState.PROFILE_FILLING;
    }

    private SendMessage processUsersInput(Message inputMsg) {
        String usersAnswer = inputMsg.getText();
        Long userId = inputMsg.getFrom().getId();
        long chatId = inputMsg.getChatId();

        UserProfileData profileData = userDataCache.getUserProfileData(userId);
        UserState userState = userDataCache.getUsersCurrentUserState(userId);

        SendMessage replyToUser = null;

        if (userState.equals(UserState.ASK_NAME)) {

            userDataCache.setUsersCurrentUserState(userId, UserState.ASK_AGE);

            return messagesService.getReplyMessage(chatId, UserState.ASK_NAME);
        }
        if (userState.equals(UserState.ASK_AGE)) {

            profileData.setName(usersAnswer);
            userDataCache.saveUserProfileData(userId, profileData);

            userDataCache.setUsersCurrentUserState(userId, UserState.ASK_MATPOMOCH);

            return  messagesService.getReplyMessage(chatId, UserState.ASK_AGE);
        }

        if (userState.equals(UserState.ASK_MATPOMOCH)) {

            profileData.setAge(Integer.parseInt(usersAnswer));
            userDataCache.saveUserProfileData(userId, profileData);

            replyToUser = messagesService.getReplyMessage(chatId, UserState.ASK_MATPOMOCH);
            userDataCache.setUsersCurrentUserState(userId, UserState.ASK_INSTITUTE);
            replyToUser.setReplyMarkup(getButtonsMarkup());
            return replyToUser;

        }
        if (userState.equals(UserState.ASK_INSTITUTE)) {
            profileData.setTypeMat(usersAnswer);
            userDataCache.saveUserProfileData(userId, profileData);
            userDataCache.setUsersCurrentUserState(userId, UserState.FINISH);

            return messagesService.getReplyMessage(chatId, UserState.ASK_INSTITUTE);
        }
        if (userState.equals(UserState.FINISH)) {

            profileData.setInstitute(usersAnswer);
            userDataCache.saveUserProfileData(userId, profileData);
            userDataCache.setUsersCurrentUserState(userId, UserState.ASK_NAME);
            userDataCache.setUsersCurrentBotState(userId, BotState.SHOW_MENU);

            return new SendMessage(String.valueOf(chatId), String.format("%s %s", "Данные по вашей анкете", profileData));
        }

        return replyToUser;
    }

    private InlineKeyboardMarkup getButtonsMarkup() {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        InlineKeyboardButton button_1 = new InlineKeyboardButton();
        button_1.setText("1 Сирота");
        InlineKeyboardButton button_2 = new InlineKeyboardButton();
        button_2.setText("2 Чечня");
        InlineKeyboardButton button_3 = new InlineKeyboardButton();
        button_3.setText("3 Пизда");
        InlineKeyboardButton button_4 = new InlineKeyboardButton();
        button_4.setText("4 Афганистан");
        InlineKeyboardButton button_5 = new InlineKeyboardButton();
        button_5.setText("5");
        InlineKeyboardButton button_6 = new InlineKeyboardButton();
        button_6.setText("6");
        InlineKeyboardButton button_7 = new InlineKeyboardButton();
        button_7.setText("7");
        InlineKeyboardButton button_8 = new InlineKeyboardButton();
        button_8.setText("8");
        InlineKeyboardButton button_9 = new InlineKeyboardButton();
        button_9.setText("9");
        InlineKeyboardButton button_10 = new InlineKeyboardButton();
        button_10.setText("10");
        InlineKeyboardButton button_11 = new InlineKeyboardButton();
        button_11.setText("11");
        InlineKeyboardButton button_12 = new InlineKeyboardButton();
        button_12.setText("12");


        //Every button must have callBackData, or else not work !
        button_1.setCallbackData("button_1");
        button_2.setCallbackData("button_2");
        button_3.setCallbackData("button_3");
        button_4.setCallbackData("button_4");
        button_5.setCallbackData("button_5");
        button_6.setCallbackData("button_6");
        button_7.setCallbackData("button_7");
        button_8.setCallbackData("button_8");
        button_9.setCallbackData("button_9");
        button_10.setCallbackData("button_10");
        button_11.setCallbackData("button_11");
        button_12.setCallbackData("button_12");


        List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
        keyboardButtonsRow1.add(button_1);

        List<InlineKeyboardButton> keyboardButtonsRow2 = new ArrayList<>();
        keyboardButtonsRow2.add(button_2);
        List<InlineKeyboardButton> keyboardButtonsRow3 = new ArrayList<>();
        keyboardButtonsRow3.add(button_3);
        List<InlineKeyboardButton> keyboardButtonsRow4 = new ArrayList<>();
        keyboardButtonsRow4.add(button_4);
        List<InlineKeyboardButton> keyboardButtonsRow5 = new ArrayList<>();
        keyboardButtonsRow5.add(button_5);
        List<InlineKeyboardButton> keyboardButtonsRow6 = new ArrayList<>();
        keyboardButtonsRow6.add(button_6);
        List<InlineKeyboardButton> keyboardButtonsRow7 = new ArrayList<>();
        keyboardButtonsRow7.add(button_7);
        List<InlineKeyboardButton> keyboardButtonsRow8 = new ArrayList<>();
        keyboardButtonsRow8.add(button_8);
        List<InlineKeyboardButton> keyboardButtonsRow9 = new ArrayList<>();
        keyboardButtonsRow9.add(button_9);
        List<InlineKeyboardButton> keyboardButtonsRow10 = new ArrayList<>();
        keyboardButtonsRow10.add(button_10);
        List<InlineKeyboardButton> keyboardButtonsRow11 = new ArrayList<>();
        keyboardButtonsRow11.add(button_11);
        List<InlineKeyboardButton> keyboardButtonsRow12 = new ArrayList<>();
        keyboardButtonsRow12.add(button_12);

        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(keyboardButtonsRow1);
        rowList.add(keyboardButtonsRow2);
        rowList.add(keyboardButtonsRow3);
        rowList.add(keyboardButtonsRow4);
        rowList.add(keyboardButtonsRow5);
        rowList.add(keyboardButtonsRow6);
        rowList.add(keyboardButtonsRow7);
        rowList.add(keyboardButtonsRow8);
        rowList.add(keyboardButtonsRow9);
        rowList.add(keyboardButtonsRow10);
        rowList.add(keyboardButtonsRow11);
        rowList.add(keyboardButtonsRow12);

        inlineKeyboardMarkup.setKeyboard(rowList);

        return inlineKeyboardMarkup;
    }


}



