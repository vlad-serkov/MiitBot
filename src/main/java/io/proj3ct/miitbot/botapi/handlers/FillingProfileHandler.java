package io.proj3ct.miitbot.botapi.handlers.fillingprofile;

import io.proj3ct.miitbot.constrants.BotState;
import io.proj3ct.miitbot.botapi.InputMessageHandler;
import io.proj3ct.miitbot.cache.UserDataCache;
import io.proj3ct.miitbot.constrants.FillingProfileState;
import io.proj3ct.miitbot.dto.UserProfileData;
import io.proj3ct.miitbot.service.ReplyMessagesService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;


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
    public FillingProfileState getHandlerName() {
        return FillingProfileState.PROFILE_FILLING;
    }

    private SendMessage processUsersInput(Message inputMsg) {
        String usersAnswer = inputMsg.getText();
        Long userId = inputMsg.getFrom().getId();
        long chatId = inputMsg.getChatId();

        UserProfileData profileData = userDataCache.getUserProfileData(userId);
        BotState botState = userDataCache.getUsersCurrentBotState(userId);

        SendMessage replyToUser = null;

        if (botState.equals(BotState.ASK_NAME)) {
            replyToUser = messagesService.getReplyMessage(chatId, BotState.ASK_NAME);
            userDataCache.setUsersCurrentBotState(userId, BotState.ASK_AGE);
        }

        if (botState.equals(BotState.ASK_AGE)) {
            profileData.setName(usersAnswer);
            replyToUser = messagesService.getReplyMessage(chatId, BotState.ASK_AGE);
            userDataCache.setUsersCurrentProfileFillingState(userId, FillingProfileState.PROFILE_FILED);

        }
//
//        if (botState.equals(BotState.ASK_GENDER)) {
//            replyToUser = messagesService.getReplyMessage(chatId, BotState.ASK_GENDER);
//            profileData.setAge(Integer.parseInt(usersAnswer));
//            userDataCache.setUsersCurrentBotState(userId, BotState.ASK_NUMBER);
//        }
//
//        if (botState.equals(BotState.ASK_NUMBER)) {
//            replyToUser = messagesService.getReplyMessage(chatId, BotState.ASK_NUMBER);
//            profileData.setGender(usersAnswer);
//            userDataCache.setUsersCurrentBotState(userId, BotState.ASK_COLOR);
//        }
//
//        if (botState.equals(BotState.ASK_COLOR)) {
//            replyToUser = messagesService.getReplyMessage(chatId, BotState.ASK_COLOR);
//            profileData.setNumber(Integer.parseInt(usersAnswer));
//            userDataCache.setUsersCurrentBotState(userId, BotState.ASK_MOVIE);
//        }
//
//        if (botState.equals(BotState.ASK_MOVIE)) {
//            replyToUser = messagesService.getReplyMessage(chatId, BotState.ASK_MOVIE);
//            profileData.setColor(usersAnswer);
//            userDataCache.setUsersCurrentBotState(userId, BotState.ASK_SONG);
//        }
//
//        if (botState.equals(BotState.ASK_SONG)) {
//            replyToUser = messagesService.getReplyMessage(chatId, BotState.ASK_SONG);
//            profileData.setMovie(usersAnswer);
//            userDataCache.setUsersCurrentProfileFillingState(userId, FillingProfileState.PROFILE_FILED);
//        }
//
        if (userDataCache.getUsersCurrentProfileFillingState(userId) == FillingProfileState.PROFILE_FILED) {
            profileData.setAge(Integer.parseInt(usersAnswer));
            userDataCache.setUsersCurrentBotState(userId, BotState.ASK_NAME);
            replyToUser = new SendMessage(String.valueOf(chatId), String.format("%s %s", "Данные по вашей анкете", profileData));
        }
        userDataCache.saveUserProfileData(userId, profileData);

        return replyToUser;
    }


}



