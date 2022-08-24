package io.proj3ct.miitbot.botapi.handlers;

import io.proj3ct.miitbot.buttons.CallBackHandler;
import io.proj3ct.miitbot.constrants.AskState;
import io.proj3ct.miitbot.cache.UserDataCache;
import io.proj3ct.miitbot.constrants.BotState;
import io.proj3ct.miitbot.dto.UserProfileData;
import io.proj3ct.miitbot.googlesheets.GoogleSheetService;
import io.proj3ct.miitbot.service.ReplyMessagesService;
import io.proj3ct.miitbot.test.PartialBotMethodFacade;
import io.proj3ct.miitbot.test.SendDocumentFacade;
import io.proj3ct.miitbot.test.SendMessageFacade;
import io.proj3ct.miitbot.word.WordService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


/**
 * Формирует анкету пользователя.
 */

@Slf4j
@Component
@AllArgsConstructor
public class FillingProfileHandler implements InputMessageHandler {
    private UserDataCache userDataCache;
    private ReplyMessagesService messagesService;

    private List<CallBackHandler> callBackHandlers;
    private WordService wordService;
    GoogleSheetService googleSheetService;

    @Override
    public PartialBotMethodFacade<?> handle(Message message) {
        return processUsersInput(message);
    }

    @Override
    public BotState getHandlerName() {
        return BotState.PROFILE_FILLING;
    }

    private PartialBotMethodFacade<?> processUsersInput(Message inputMsg) {
        String usersAnswer = inputMsg.getText();
        Long userId = inputMsg.getFrom().getId();
        long chatId = inputMsg.getChatId();

        UserProfileData profileData = userDataCache.getUserProfileData(userId);
        AskState askState = userDataCache.getUsersCurrentUserState(userId);



        if (askState.equals(AskState.ASK_FULL_NAME)) {

            userDataCache.setUsersCurrentUserState(userId, AskState.ASK_DATE_OF_BIRTHDAY);

            return messagesService.getReplyMessage(chatId, AskState.ASK_FULL_NAME);
        }
        if (askState.equals(AskState.ASK_DATE_OF_BIRTHDAY)) {

            profileData.setFullName(usersAnswer);
            userDataCache.saveUserProfileData(userId, profileData);

            userDataCache.setUsersCurrentUserState(userId, AskState.ASK_MATPOMOCH);

            return  messagesService.getReplyMessage(chatId, AskState.ASK_DATE_OF_BIRTHDAY);
        }

        if (askState.equals(AskState.ASK_MATPOMOCH)) {

            profileData.setDateOfBirthday(Date.valueOf(usersAnswer));
            userDataCache.saveUserProfileData(userId, profileData);
            userDataCache.setUsersCurrentUserState(userId, AskState.ASK_INSTITUTE);

            SendMessage replyToUser = messagesService.getReplyMessage(chatId, AskState.ASK_MATPOMOCH).getInstance();
            replyToUser.setReplyMarkup(getButtonsMarkup());
            return new SendMessageFacade(replyToUser);

        }
        if (askState.equals(AskState.ASK_INSTITUTE)) {
            userDataCache.saveUserProfileData(userId, profileData);
            userDataCache.setUsersCurrentUserState(userId, AskState.ASK_GROUP);

            return messagesService.getReplyMessage(chatId, AskState.ASK_INSTITUTE);
        }

        if (askState.equals(AskState.ASK_GROUP)) {
            profileData.setInstitute(usersAnswer);
            userDataCache.saveUserProfileData(userId, profileData);
            userDataCache.setUsersCurrentUserState(userId, AskState.ASK_COURSE_NUMBER);

            return messagesService.getReplyMessage(chatId, AskState.ASK_GROUP);
        }
        if (askState.equals(AskState.ASK_COURSE_NUMBER)) {
            profileData.setGroup(usersAnswer);
            userDataCache.saveUserProfileData(userId, profileData);
            userDataCache.setUsersCurrentUserState(userId, AskState.ASK_ADDRESS);

            return messagesService.getReplyMessage(chatId, AskState.ASK_COURSE_NUMBER);
        }
        if (askState.equals(AskState.ASK_ADDRESS)) {
            profileData.setCourseNumber(Integer.parseInt(usersAnswer));
            userDataCache.saveUserProfileData(userId, profileData);
            userDataCache.setUsersCurrentUserState(userId, AskState.ASK_PHONE_NUMBER);

            return messagesService.getReplyMessage(chatId, AskState.ASK_ADDRESS);
        }
        if (askState.equals(AskState.ASK_PHONE_NUMBER)) {
            profileData.setAddress(usersAnswer);
            userDataCache.saveUserProfileData(userId, profileData);
            userDataCache.setUsersCurrentUserState(userId, AskState.ASK_SERIAL_OF_PASSPORT);

            return messagesService.getReplyMessage(chatId, AskState.ASK_PHONE_NUMBER);
        }
        if (askState.equals(AskState.ASK_SERIAL_OF_PASSPORT)) {
            profileData.setPhoneNumber(usersAnswer);
            userDataCache.saveUserProfileData(userId, profileData);
            userDataCache.setUsersCurrentUserState(userId, AskState.ASK_PASSPORT_ISSUED);

            return messagesService.getReplyMessage(chatId, AskState.ASK_SERIAL_OF_PASSPORT);
        }
        if (askState.equals(AskState.ASK_PASSPORT_ISSUED)) {
            profileData.setSerialPassport(usersAnswer);
            userDataCache.saveUserProfileData(userId, profileData);
            userDataCache.setUsersCurrentUserState(userId, AskState.ASK_INN);

            return messagesService.getReplyMessage(chatId, AskState.ASK_PASSPORT_ISSUED);
        }
        if (askState.equals(AskState.ASK_INN)) {
            profileData.setPassportIssued(usersAnswer);
            userDataCache.saveUserProfileData(userId, profileData);
            userDataCache.setUsersCurrentUserState(userId, AskState.ASK_BANK_BOOK);

            return messagesService.getReplyMessage(chatId, AskState.ASK_INN);
        }
        if (askState.equals(AskState.ASK_BANK_BOOK)) {
            profileData.setInn(usersAnswer);
            userDataCache.saveUserProfileData(userId, profileData);
            userDataCache.setUsersCurrentUserState(userId, AskState.ASK_BANK_BIK);

            return messagesService.getReplyMessage(chatId, AskState.ASK_BANK_BOOK);
        }
        if (askState.equals(AskState.ASK_BANK_BIK)) {
            profileData.setBankBook(usersAnswer);
            userDataCache.saveUserProfileData(userId, profileData);
            userDataCache.setUsersCurrentUserState(userId, AskState.ASK_UNION_CARD);

            return messagesService.getReplyMessage(chatId, AskState.ASK_BANK_BIK);
        }
        if (askState.equals(AskState.ASK_UNION_CARD)) {
            profileData.setBankBIK(usersAnswer);
            userDataCache.saveUserProfileData(userId, profileData);
            userDataCache.setUsersCurrentUserState(userId, AskState.FINISH);
            return messagesService.getReplyMessage(chatId, AskState.ASK_UNION_CARD);
        }



        if (askState.equals(AskState.FINISH)) {

            profileData.setUnionCard(usersAnswer);
            userDataCache.saveUserProfileData(userId, profileData);
            userDataCache.setUsersCurrentUserState(userId, AskState.ASK_FULL_NAME);
            userDataCache.setUsersCurrentBotState(userId, BotState.SHOW_MENU);

            return new SendDocumentFacade(String.valueOf(chatId), profileData, wordService);
        }

        return null;
    }

    private InlineKeyboardMarkup getButtonsMarkup() {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        callBackHandlers.stream().sorted(Comparator.comparingInt(CallBackHandler::getSerial)).forEach(callBackHandler -> rowList.add(callBackHandler.getKeyboardButton()));
        inlineKeyboardMarkup.setKeyboard(rowList);
        return inlineKeyboardMarkup;
    }


}



