package io.proj3ct.miitbot.service;

import io.proj3ct.miitbot.botapi.TelegramFacade;
import io.proj3ct.miitbot.config.BotConfig;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;

@Service
@Setter
@AllArgsConstructor
@Slf4j
public class TelegramBot extends TelegramLongPollingBot {
    private final BotConfig botConfig;
    private final TelegramFacade telegramFacade;


//    public TelegramBot(BotConfig botConfig) {
//        this.botConfig = botConfig;
//        final ArrayList<BotCommand> commandList = new ArrayList<>();
//        commandList.add(new BotCommand("/start", "СТАРТ"));
//        commandList.add(new BotCommand("/mydata", "МОИ ДАННЫЕ"));
//        commandList.add(new BotCommand("/help","ПОМОЩЬ"));
//        commandList.add(new BotCommand("/hel","ПОМОЩЬ"));
//        try {
//            this.execute(new SetMyCommands(commandList, new BotCommandScopeDefault(),null));
//        } catch (TelegramApiException e) {
//
//        }
//    }

    @Override
    public String getBotUsername() {
        return botConfig.getBotName();
    }

    @Override
    public String getBotToken() {
        return botConfig.getToken();
    }

    @Override
    public void onUpdateReceived(Update update) {
        BotApiMethod<?> replyMessageToUser = telegramFacade.handleUpdate(update);
        try {
            log.info("TelegramBot______________");
            execute(replyMessageToUser);
        } catch (TelegramApiException e) {
            log.error("oops {}", e.getMessage());
        }
    }
}
