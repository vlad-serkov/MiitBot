package io.proj3ct.miitbot.botapi;

import io.proj3ct.miitbot.cache.UserDataCache;
import io.proj3ct.miitbot.constrants.BotState;
import io.proj3ct.miitbot.constrants.UserState;
import io.proj3ct.miitbot.dto.UserProfileData;
import io.proj3ct.miitbot.service.MainMenuService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.io.Serializable;

/**
 * @author Sergei Viacheslaev
 */
@Service
@Slf4j
@AllArgsConstructor
public class TelegramFacade {
    private BotStateContext botStateContext;
    private UserDataCache userDataCache;

    private MainMenuService mainMenuService;

    public BotApiMethod<?> handleUpdate(Update update) {
        SendMessage replyMessage = null;
        if (update.hasCallbackQuery()) {
            CallbackQuery callbackQuery = update.getCallbackQuery();
            log.info("New callbackQuery from User: {}, userId: {}, with data: {}", update.getCallbackQuery().getFrom().getUserName(),
                    callbackQuery.getFrom().getId(), update.getCallbackQuery().getData());
            return processCallbackQuery(callbackQuery);
        }

        Message message = update.getMessage();
        if (message != null && message.hasText()) {
            log.info("New message from User:{}, chatId: {},  with text: {}",
                    message.getFrom().getUserName(), message.getChatId(), message.getText());
            replyMessage = handleInputMessage(message);
        }

        return replyMessage;
    }

    private SendMessage handleInputMessage(Message message) {
        String inputMsg = message.getText();
        Long userId = message.getFrom().getId();
        switch (inputMsg) {
            case "/start":
                userDataCache.setUsersCurrentBotState(userId,BotState.PROFILE_FILLING);
                userDataCache.setUsersCurrentUserState(userId, UserState.ASK_NAME);
                break;
            case "/data":
                userDataCache.setUsersCurrentBotState(userId,BotState.PROFILE_FILED);
                break;
            default:
                if (userDataCache.getUsersCurrentBotState(userId)!=BotState.PROFILE_FILLING)
                    userDataCache.setUsersCurrentBotState(userId,BotState.SHOW_MENU);
        }

        return botStateContext.processInputMessage(userDataCache.getUsersCurrentBotState(userId), message);
    }


    private BotApiMethod<?> processCallbackQuery(CallbackQuery buttonQuery) {
        final long chatId = buttonQuery.getMessage().getChatId();
        final Long userId = buttonQuery.getFrom().getId();
        BotApiMethod<?> callBackAnswer = mainMenuService.getMainMenuMessage(chatId, "Воспользуйтесь главным меню");

        //From Gender choose buttons
        if (buttonQuery.getData().equals("button_1")) {
            UserProfileData userProfileData = userDataCache.getUserProfileData(userId);
            userProfileData.setTypeMat("CИРОТА");
            userDataCache.saveUserProfileData(userId, userProfileData);
            userDataCache.setUsersCurrentUserState(userId, UserState.FINISH);
            return new SendMessage(String.valueOf(chatId), UserState.ASK_INSTITUTE.getMessage());
        } else if (buttonQuery.getData().equals("button_2")) {
            UserProfileData userProfileData = userDataCache.getUserProfileData(userId);
            userProfileData.setTypeMat("ИНВАЛИД");
            userDataCache.saveUserProfileData(userId, userProfileData);
            userDataCache.setUsersCurrentUserState(userId, UserState.FINISH);
            return new SendMessage(String.valueOf(chatId), UserState.ASK_INSTITUTE.getMessage());
        }  else if (buttonQuery.getData().equals("button_3")) {
            UserProfileData userProfileData = userDataCache.getUserProfileData(userId);
            userProfileData.setTypeMat("ывжлыфдв");
            userDataCache.saveUserProfileData(userId, userProfileData);
            userDataCache.setUsersCurrentUserState(userId, UserState.FINISH);
            return new SendMessage(String.valueOf(chatId), UserState.ASK_INSTITUTE.getMessage());
        } else {
            userDataCache.setUsersCurrentBotState(userId, BotState.SHOW_MENU);
        }


        return callBackAnswer;


    }


    private AnswerCallbackQuery sendAnswerCallbackQuery(String text, boolean alert, CallbackQuery callbackquery) {
        AnswerCallbackQuery answerCallbackQuery = new AnswerCallbackQuery();
        answerCallbackQuery.setCallbackQueryId(callbackquery.getId());
        answerCallbackQuery.setShowAlert(alert);
        answerCallbackQuery.setText(text);
        return answerCallbackQuery;
    }




}
