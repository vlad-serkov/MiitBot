package io.proj3ct.miitbot.test;

import io.proj3ct.miitbot.dto.UserProfileData;
import io.proj3ct.miitbot.word.WordService;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.objects.InputFile;

public class SendDocumentFacade implements PartialBotMethodFacade<SendDocument>{
    private final SendDocument document;
    private final String message;

    public SendDocumentFacade( String chatId, UserProfileData userProfile, WordService wordService) {
        this.message = userProfile.getTypeMat().getMessage();
        this.document = new SendDocument(chatId, wordService.getInputFile(chatId, userProfile.getTypeMat().getPath(), userProfile));
    }

    @Override
    public SendDocument getInstance() {
        return document;
    }

    @Override
    public String getName() {
        return "SendDocument";
    }

    @Override
    public String getPath() {
        return message;
    }
}
