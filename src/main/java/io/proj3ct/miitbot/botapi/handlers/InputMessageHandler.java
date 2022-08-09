package io.proj3ct.miitbot.botapi;

import io.proj3ct.miitbot.constrants.BotState;
import io.proj3ct.miitbot.constrants.FillingProfileState;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

/**Обработчик сообщений
 */
public interface InputMessageHandler {
    SendMessage handle(Message message);
    FillingProfileState getHandlerName();
}
