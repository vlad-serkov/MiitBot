package io.proj3ct.miitbot.botapi;

import io.proj3ct.miitbot.botapi.handlers.InputMessageHandler;
import io.proj3ct.miitbot.constrants.BotState;
import io.proj3ct.miitbot.test.PartialBotMethodFacade;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Defines message handlers for each state.
 */
@Component
public class BotStateContext {
    private final Map<BotState, InputMessageHandler> messageHandlers;

    public BotStateContext(List<InputMessageHandler> messageHandlers) {
        this.messageHandlers = new HashMap<>();
        messageHandlers.forEach(handler -> this.messageHandlers.put(handler.getHandlerName(), handler));
    }

    public PartialBotMethodFacade<?> processInputMessage(BotState currentState, Message message) {
        InputMessageHandler currentMessageHandler = findMessageHandler(currentState);
        return currentMessageHandler.handle(message);
    }

    private InputMessageHandler findMessageHandler(BotState currentState) {
        return messageHandlers.get(currentState);
    }

}





