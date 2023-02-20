package io.proj3ct.miitbot.botapi.handlers;

import io.proj3ct.miitbot.buttons.CallBackHandler;
import io.proj3ct.miitbot.buttons.CheckButton;
import io.proj3ct.miitbot.buttons.MatType;
import io.proj3ct.miitbot.constrants.AskState;
import io.proj3ct.miitbot.cache.UserDataCache;
import io.proj3ct.miitbot.constrants.BotState;
import io.proj3ct.miitbot.dto.UserProfileData;
import io.proj3ct.miitbot.googlesheets.GoogleSheetService;
import io.proj3ct.miitbot.service.ReplyMessagesService;
import io.proj3ct.miitbot.test.PartialBotMethodFacade;
import io.proj3ct.miitbot.test.SendDocumentFacade;
import io.proj3ct.miitbot.test.SendMessageFacade;
import io.proj3ct.miitbot.validator.*;
import io.proj3ct.miitbot.word.WordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


/**
 * Формирует анкету пользователя.
 */

@Slf4j
@Component
public class FillingProfileHandler implements InputMessageHandler {
    private UserDataCache userDataCache;
    private ReplyMessagesService messagesService;

    private List<MatType> callBackHandlers;
    private WordService wordService;
    GoogleSheetService googleSheetService;
    SimpleDateFormat formater;
    private List<CheckButton> callBackHandlersCheck;

    public FillingProfileHandler(UserDataCache userDataCache,
                                 ReplyMessagesService messagesService,
                                 List<MatType> callBackHandlers, WordService wordService,
                                 GoogleSheetService googleSheetService,
                                 final List<CheckButton> callBackHandlersCheck) {
        this.userDataCache = userDataCache;
        this.messagesService = messagesService;
        this.callBackHandlers = callBackHandlers;
        this.wordService = wordService;
        this.googleSheetService = googleSheetService;
        this.callBackHandlersCheck = callBackHandlersCheck;
        this.formater = new SimpleDateFormat("dd.MM.yyyy");
    }

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

            try {
                profileData.setDateOfBirthday(formater.parse(usersAnswer));
            } catch (ParseException e) {
                return new SendMessageFacade(new SendMessage(String.valueOf(chatId), "Данные введены не верно, попробуйте еще раз"));
            }
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
            try {
                InstituteValidator.validate(usersAnswer);
            } catch (InstituteValidator.IllegalInstituteException e) {
                return new SendMessageFacade(new SendMessage(String.valueOf(chatId), e.getMessage()));
            }

            profileData.setInstitute(usersAnswer);
            userDataCache.saveUserProfileData(userId, profileData);
            userDataCache.setUsersCurrentUserState(userId, AskState.ASK_COURSE_NUMBER);

            return messagesService.getReplyMessage(chatId, AskState.ASK_GROUP);
        }
        if (askState.equals(AskState.ASK_COURSE_NUMBER)) {
            try {
                GroupValidator.validate(usersAnswer);
            } catch (GroupValidator.IllegalGroupException e) {
                return new SendMessageFacade(new SendMessage(String.valueOf(chatId), e.getMessage()));
            }
            profileData.setGroup(usersAnswer);
            userDataCache.saveUserProfileData(userId, profileData);
            userDataCache.setUsersCurrentUserState(userId, AskState.ASK_ADDRESS);

            return messagesService.getReplyMessage(chatId, AskState.ASK_COURSE_NUMBER);
        }
        if (askState.equals(AskState.ASK_ADDRESS)) {
            try {
                CourseValidator.validate(usersAnswer);
            } catch (CourseValidator.IllegalCourseException e) {
                return new SendMessageFacade(new SendMessage(String.valueOf(chatId), e.getMessage()));
            }
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
            try {
                PhoneNumberValidator.validate(usersAnswer);
            } catch (PhoneNumberValidator.IllegalPhoneNumberException e) {
                return new SendMessageFacade(new SendMessage(String.valueOf(chatId), e.getMessage()));
            }

            profileData.setPhoneNumber(usersAnswer);
            userDataCache.saveUserProfileData(userId, profileData);
            userDataCache.setUsersCurrentUserState(userId, AskState.ASK_PASSPORT_ISSUED);

            return messagesService.getReplyMessage(chatId, AskState.ASK_SERIAL_OF_PASSPORT);
        }
        if (askState.equals(AskState.ASK_PASSPORT_ISSUED)) {
            try {
                SerialPassportValidator.validate(usersAnswer);
            } catch (SerialPassportValidator.IllegalSerialPassportException e) {
                return new SendMessageFacade(new SendMessage(String.valueOf(chatId), e.getMessage()));
            }
            profileData.setSerialPassport(usersAnswer);
            userDataCache.saveUserProfileData(userId, profileData);
            userDataCache.setUsersCurrentUserState(userId, AskState.ASK_PASSPORT_DATE);

            return messagesService.getReplyMessage(chatId, AskState.ASK_PASSPORT_ISSUED);
        }
        if (askState.equals(AskState.ASK_PASSPORT_DATE)) {
            profileData.setPassportIssued(usersAnswer);
            userDataCache.saveUserProfileData(userId, profileData);
            userDataCache.setUsersCurrentUserState(userId, AskState.ASK_INN);

            return messagesService.getReplyMessage(chatId, AskState.ASK_PASSPORT_DATE);
        }
        if (askState.equals(AskState.ASK_INN)) {
            profileData.setPassportIssued(profileData.getPassportIssued() + " " + usersAnswer);
            userDataCache.saveUserProfileData(userId, profileData);
            userDataCache.setUsersCurrentUserState(userId, AskState.ASK_BANK_BOOK);

            return messagesService.getReplyMessage(chatId, AskState.ASK_INN);
        }
        if (askState.equals(AskState.ASK_BANK_BOOK)) {
            try {
                InnValidator.validate(usersAnswer);
            } catch (InnValidator.IllegalInnException e) {
                return new SendMessageFacade(new SendMessage(String.valueOf(chatId), e.getMessage()));
            }
            profileData.setInn(usersAnswer);
            userDataCache.saveUserProfileData(userId, profileData);
            userDataCache.setUsersCurrentUserState(userId, AskState.ASK_BANK_BIK);

            return messagesService.getReplyMessage(chatId, AskState.ASK_BANK_BOOK);
        }
        if (askState.equals(AskState.ASK_BANK_BIK)) {
            try {
                BankBookValidator.validate(usersAnswer);
            } catch (BankBookValidator.IllegalBankBookException e) {
                return new SendMessageFacade(new SendMessage(String.valueOf(chatId), e.getMessage()));
            }
            profileData.setBankBook(usersAnswer);
            userDataCache.saveUserProfileData(userId, profileData);
            userDataCache.setUsersCurrentUserState(userId, AskState.ASK_UNION_CARD);

            return messagesService.getReplyMessage(chatId, AskState.ASK_BANK_BIK);
        }
        if (askState.equals(AskState.ASK_UNION_CARD)) {
            try {
                BankBIKValidator.validate(usersAnswer);
            } catch (BankBIKValidator.IllegalBankBIKException e) {
                return new SendMessageFacade(new SendMessage(String.valueOf(chatId), e.getMessage()));
            }
            profileData.setBankBIK(usersAnswer);
            userDataCache.saveUserProfileData(userId, profileData);
            userDataCache.setUsersCurrentUserState(userId, AskState.ASK_ALL);
            return messagesService.getReplyMessage(chatId, AskState.ASK_UNION_CARD);
        }
        if (askState.equals(AskState.ASK_ALL)) {
            profileData.setUnionCard(usersAnswer);
            userDataCache.saveUserProfileData(userId, profileData);
            userDataCache.setUsersCurrentUserState(userId, AskState.FINISH);
            return messagesService.getReplyMessage(chatId, AskState.ASK_ALL, profileData);
        }

        if (askState.equals(AskState.FINISH)) {
            userDataCache.saveUserProfileData(userId, profileData);
            userDataCache.setUsersCurrentUserState(userId, AskState.ASK_FULL_NAME);
            userDataCache.setUsersCurrentBotState(userId, BotState.SHOW_MENU);
            try {
                googleSheetService.saveUser(profileData);
            } catch (Exception e) {
                log.info("Error saving user into GoogleSheet");
            }
            return new SendDocumentFacade(String.valueOf(chatId), profileData, wordService);
        }

        return null;
    }

    private InlineKeyboardMarkup getButtonsMarkup() {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        callBackHandlers.stream().map(matType -> (CallBackHandler) matType).sorted(Comparator.comparingInt(CallBackHandler::getSerial)).forEach(callBackHandler -> rowList.add(callBackHandler.getKeyboardButton()));
        inlineKeyboardMarkup.setKeyboard(rowList);
        return inlineKeyboardMarkup;
    }

    private InlineKeyboardMarkup getButtonsMarkupForCheck() {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        callBackHandlersCheck.stream().map(matType -> (CallBackHandler) matType).sorted(Comparator.comparingInt(CallBackHandler::getSerial)).forEach(callBackHandler -> rowList.add(callBackHandler.getKeyboardButton()));
        inlineKeyboardMarkup.setKeyboard(rowList);
        return inlineKeyboardMarkup;
    }


}



