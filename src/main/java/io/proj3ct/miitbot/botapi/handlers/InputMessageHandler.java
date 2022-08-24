package io.proj3ct.miitbot.botapi.handlers;

import io.proj3ct.miitbot.constrants.BotState;
import io.proj3ct.miitbot.test.PartialBotMethodFacade;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

/**Обработчик сообщений
 */
public interface InputMessageHandler {
    PartialBotMethodFacade<?> handle(Message message);
    BotState getHandlerName();
}
