package io.proj3ct.miitbot.botapi;

import io.proj3ct.miitbot.buttons.ButtonContext;
import io.proj3ct.miitbot.cache.UserDataCache;
import io.proj3ct.miitbot.constrants.BotState;
import io.proj3ct.miitbot.constrants.AskState;
import io.proj3ct.miitbot.test.PartialBotMethodFacade;
import io.proj3ct.miitbot.test.SendMessageFacade;
import io.proj3ct.miitbot.word.WordService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * @author Sergei Viacheslaev
 */
@Service
@Slf4j
@AllArgsConstructor
public class TelegramFacade {

    private BotStateContext botStateContext;
    private UserDataCache userDataCache;
    private ButtonContext buttonContext;

    private WordService wordService;


    public PartialBotMethodFacade<?> handleUpdate(Update update) {

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
            return handleInputMessage(message);
        }
        throw new RuntimeException("lol");
    }

    private PartialBotMethodFacade<?> handleInputMessage(Message message) {
        String inputMsg = message.getText();
        Long userId = message.getFrom().getId();
        switch (inputMsg) {
            case "/start":
                userDataCache.setUsersCurrentBotState(userId, BotState.SHOW_MENU);
                break;
            case "/make":
                userDataCache.setUsersCurrentUserState(userId, AskState.ASK_FULL_NAME);
                userDataCache.setUsersCurrentBotState(userId, BotState.PROFILE_FILLING);
                break;
            case "/stop":
                userDataCache.setUsersCurrentUserState(userId, AskState.ASK_FULL_NAME);
                userDataCache.setUsersCurrentBotState(userId, BotState.SHOW_MENU);
                break;
            default:
                if (userDataCache.getUsersCurrentBotState(userId) != BotState.PROFILE_FILLING)
                    userDataCache.setUsersCurrentBotState(userId, BotState.SHOW_MENU);
        }

        return botStateContext.processInputMessage(userDataCache.getUsersCurrentBotState(userId), message);
    }


    private PartialBotMethodFacade<?> processCallbackQuery(CallbackQuery buttonQuery) {
        final Long userId = buttonQuery.getFrom().getId();
        return new SendMessageFacade(buttonContext.processInputMessage(buttonQuery.getData(), userId));


    }

}
