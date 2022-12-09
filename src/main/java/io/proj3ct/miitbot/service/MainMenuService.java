package io.proj3ct.miitbot.service;


import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardRemove;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardRemove;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

/**
 * Управляет отображением главного меню в чате.
 *
 * @author Sergei Viacheslaev
 */
@Service
public class MainMenuService {

    public SendMessage getMainMenuMessage(final long chatId, final String textMessage) {
        return createMessageWithKeyboard(chatId, textMessage, getMainMenuKeyboard());
    }

    private ReplyKeyboardRemove getMainMenuKeyboard() {

        final ReplyKeyboardRemove ReplyKeyboardRemove = new ReplyKeyboardRemove();
        ReplyKeyboardRemove.setSelective(true);


        List<KeyboardRow> keyboard = new ArrayList<>();

        KeyboardRow row1 = new KeyboardRow();
        row1.add(new KeyboardButton("/make"));
        keyboard.add(row1);
        ReplyKeyboardRemove.setRemoveKeyboard(true);
        return ReplyKeyboardRemove;
    }

    private SendMessage createMessageWithKeyboard(final long chatId,
                                                  String textMessage,
                                                  final ReplyKeyboardRemove ReplyKeyboardRemove) {
        final SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(chatId);
        sendMessage.setText(textMessage);
        if (ReplyKeyboardRemove != null) {
            sendMessage.setReplyMarkup(ReplyKeyboardRemove);
        }
        return sendMessage;
    }
}
