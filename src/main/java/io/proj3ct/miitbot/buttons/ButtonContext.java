package io.proj3ct.miitbot.buttons;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ButtonContext {
    private final Map<String, CallBackHandler> callBackHandler = new HashMap<>();

    public ButtonContext(List<CallBackHandler> callBackHandler) {
        callBackHandler.forEach(handler -> this.callBackHandler.put(handler.getButtonCallbackData(), handler));
    }

    public SendMessage  processInputMessage(String callback, Long userId) {
        CallBackHandler currentMessageHandler = findMessageHandler(callback);
        return currentMessageHandler.handle(userId);
    }

    private CallBackHandler findMessageHandler(String callback) {
        return callBackHandler.get(callback);
    }
}
