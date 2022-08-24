package io.proj3ct.miitbot.service;

import io.proj3ct.miitbot.constrants.AskState;
import io.proj3ct.miitbot.test.SendMessageFacade;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;


/**
 * Формирует готовые ответные сообщения в чат.
 */
@Service
public class ReplyMessagesService {

    public SendMessageFacade getReplyMessage(long chatId, AskState askState) {
        return new SendMessageFacade(new SendMessage(String.valueOf(chatId), askState.getMessage()));
    }

}
