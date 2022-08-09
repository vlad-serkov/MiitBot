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


/**
 * Формирует анкету пользователя.
 */

@Slf4j
@Component
@AllArgsConstructor
public class FiledProfileHandler implements InputMessageHandler {
    private UserDataCache userDataCache;
    private ReplyMessagesService messagesService;

    @Override
    public SendMessage handle(Message message) {

        return processUsersInput(message);
    }

    @Override
    public BotState getHandlerName() {
        return BotState.SHOW_MENU;
    }

    private SendMessage processUsersInput(Message inputMsg) {
        Long userId = inputMsg.getFrom().getId();
        return new SendMessage(String.valueOf(userId), "OKEY");
    }


}



