package io.proj3ct.miitbot.botapi.handlers;


import io.proj3ct.miitbot.cache.UserDataCache;
import io.proj3ct.miitbot.constrants.BotState;
import io.proj3ct.miitbot.service.MainMenuService;
import io.proj3ct.miitbot.service.ReplyMessagesService;
import io.proj3ct.miitbot.test.PartialBotMethodFacade;
import io.proj3ct.miitbot.test.SendMessageFacade;
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
public class MainMenuHandler implements InputMessageHandler {
    private UserDataCache userDataCache;
    private ReplyMessagesService messagesService;
    private MainMenuService mainMenuService;

    @Override
    public PartialBotMethodFacade<?> handle(Message message) {
        return new SendMessageFacade(mainMenuService.getMainMenuMessage(message.getChatId(), "Главное меню (тут нужно что-то написать)"));
    }

    @Override
    public BotState getHandlerName() {
        return BotState.SHOW_MENU;
    }




}



