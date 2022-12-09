package io.proj3ct.miitbot.service;

import io.proj3ct.miitbot.constrants.AskState;
import io.proj3ct.miitbot.dto.UserProfileData;
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

    public SendMessageFacade getReplyMessage(long chatId, AskState askState, UserProfileData profileData) {
        return new SendMessageFacade(new SendMessage(String.valueOf(chatId), String.format(askState.getMessage(),
                profileData.getFullName(),
                profileData.getDateOfBirthday(),
                profileData.getTypeMat().getText(),
                profileData.getInstitute(),
                profileData.getGroup(),
                profileData.getCourseNumber(),
                profileData.getAddress(),
                profileData.getPhoneNumber(),
                profileData.getSerialPassport(),
                profileData.getPassportIssued(),
                profileData.getInn(),
                profileData.getBankBook(),
                profileData.getBankBIK(),
                profileData.getUnionCard()
                )));
    }

}
