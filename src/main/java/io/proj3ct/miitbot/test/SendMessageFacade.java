package io.proj3ct.miitbot.test;

import lombok.AllArgsConstructor;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@AllArgsConstructor
public class SendMessageFacade implements PartialBotMethodFacade<SendMessage>{
    //private String filename;
    private SendMessage message;
    @Override
    public SendMessage getInstance() {
        return message;
    }

    @Override
    public String getName() {
        return "SendMessage";
    }

    @Override
    public String getPath() {
        throw new UnsupportedOperationException("Not supported");
    }
}
