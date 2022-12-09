package io.proj3ct.miitbot.word;

import java.io.*;

import io.proj3ct.miitbot.dto.UserProfileData;
import io.proj3ct.miitbot.service.PhoneNumberService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hwpf.HWPFDocument;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.InputFile;

@Slf4j
@Service
@AllArgsConstructor
public class WordService {

    private PhoneNumberService phoneNumberService;

    public InputFile getInputFile(String chatId, String path, UserProfileData profileData) {

        try (InputStream inputStream = getInputStream(path, profileData)) {
            return new InputFile(inputStream, path.substring(0, path.length()-4)+ chatId+".doc");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    private InputStream getInputStream(String path, UserProfileData profileData) {
        try (ByteArrayOutputStream b = new ByteArrayOutputStream()) {
            final HWPFDocument document = getHWPFDocument(path, profileData);
            document.write(b);
            return new ByteArrayInputStream(b.toByteArray());
        } catch (IOException e) {
            log.error("Error getInputStream {}",e.getMessage());
            throw new RuntimeException(e);
        }

    }

    private HWPFDocument getHWPFDocument(String pathName, UserProfileData profileData) {
        try (FileInputStream fis = new FileInputStream(pathName)) {
            return updateHWPFDocument(new HWPFDocument(fis), profileData);
        } catch (Exception ex) {
            log.error("Error template!");
        }

        throw new IllegalStateException("HWPFDocument not found: " + pathName);
    }

    private HWPFDocument updateHWPFDocument(HWPFDocument document, UserProfileData profileData) {
        try {
            document.getRange().replaceText("$ФИО", profileData.getFullName());
            document.getRange().replaceText("$ГРУППА", profileData.getGroup());
            document.getRange().replaceText("$КУРС", String.valueOf(profileData.getCourseNumber()));
            document.getRange().replaceText("$АДРЕС", profileData.getAddress());
            document.getRange().replaceText("$ИНН", profileData.getInn());
            document.getRange().replaceText("$СЕРИЯ_НОМЕР", profileData.getSerialPassport());
            document.getRange().replaceText("$ДАТА_РОЖДЕНИЯ", String.valueOf(profileData.getDateOfBirthday()));
            document.getRange().replaceText("$НОМЕР_ПРОФ_БИЛЕТА", profileData.getUnionCard());
            document.getRange().replaceText("$ЛИЦЕВОЙ_СЧЁТ", profileData.getBankBook());
            document.getRange().replaceText("$БИК_БАНКА", profileData.getBankBIK());
            document.getRange().replaceText("$ТЕЛЕФОН", phoneNumberService.formatNumber(profileData.getPhoneNumber()));
            document.getRange().replaceText("$ВЫДАН", profileData.getPassportIssued());

            return document;
        } catch (Exception ex) {
            log.error("Error replaceText!");

        }
        throw new IllegalStateException();
    }

}
