package io.proj3ct.miitbot.service;

import io.proj3ct.miitbot.botapi.TelegramFacade;
import io.proj3ct.miitbot.config.BotConfig;
import io.proj3ct.miitbot.test.PartialBotMethodFacade;
import io.proj3ct.miitbot.test.SendDocumentFacade;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;

@Service
@Setter
@Slf4j
public class TelegramBot extends TelegramLongPollingBot {
    private final BotConfig botConfig;
    private final TelegramFacade telegramFacade;


    public TelegramBot(BotConfig botConfig, TelegramFacade telegramFacade) {
        this.botConfig = botConfig;
        this.telegramFacade = telegramFacade;
        final ArrayList<BotCommand> commandList = new ArrayList<>();
        commandList.add(new BotCommand("/start", "Запустить бота"));
        commandList.add(new BotCommand("/make","Начать заполнение заявления"));
        commandList.add(new BotCommand("/stop","Прекратить заполнение"));
        try {
            this.execute(new SetMyCommands(commandList, new BotCommandScopeDefault(),null));
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
        }

    }
    @Override
    public String getBotUsername() {
        return botConfig.getBotName();
    }

    @Override
    public String getBotToken() {
        return botConfig.getToken();
    }

    @Override
    @SneakyThrows
    public void onUpdateReceived(Update update) {
        PartialBotMethodFacade<?> replyMessageToUser = telegramFacade.handleUpdate(update);
        if (replyMessageToUser.getName().equals("SendDocument")) {
            sendDocument((SendDocumentFacade) replyMessageToUser);
        }
        if (replyMessageToUser.getName().equals("SendMessage")) {
            sendMessage((SendMessage) replyMessageToUser.getInstance());
        }
    }

    public void sendDocument(SendDocumentFacade sendDocumentFacade) throws TelegramApiException {
        SendDocument sendDocument = sendDocumentFacade.getInstance();
        execute(sendDocument);
        execute(new SendMessage(sendDocument.getChatId(), sendDocumentFacade.getPath()));
    }

    public void sendMessage(SendMessage message) throws TelegramApiException {
        execute(message);
    }

}
