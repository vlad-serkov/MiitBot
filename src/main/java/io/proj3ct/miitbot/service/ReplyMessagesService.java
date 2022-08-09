package io.proj3ct.miitbot.service;

import io.proj3ct.miitbot.constrants.UserState;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;


/**
 * Формирует готовые ответные сообщения в чат.
 */
@Service
public class ReplyMessagesService {

    public SendMessage getReplyMessage(long chatId, UserState userState) {
        return new SendMessage(String.valueOf(chatId), userState.getMessage());
    }

}
