package io.proj3ct.miitbot.googlesheets;

import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.ValueRange;
import io.proj3ct.miitbot.dto.UserProfileData;
import io.proj3ct.miitbot.service.PhoneNumberService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

@Service
@AllArgsConstructor
public class GoogleSheetService {
    GoogleSheetConfig googleSheetConfig;
    Sheets sheets;
    PhoneNumberService phoneNumberService;
    public void saveUser(UserProfileData userProfileData) {
        ValueRange appendBody = new ValueRange().setValues(Collections.singletonList(
           Arrays.asList(
                   userProfileData.getFullName(),
                   userProfileData.getTypeMat().getText(),
                   userProfileData.getInstitute(),
                   userProfileData.getCourseNumber(),
                   userProfileData.getGroup(),
                   userProfileData.getAddress(),
                   userProfileData.getPhoneNumber(),
                   userProfileData.getSerialPassport(),
                   userProfileData.getPassportIssued(),
                   String.valueOf(userProfileData.getDateOfBirthday()),
                   userProfileData.getInn(),
                   userProfileData.getBankBook(),
                   userProfileData.getBankBIK()
           )
        ));

        try {
            sheets.spreadsheets().values()
                    .append(googleSheetConfig.getSpreadsheetId(), "A1:M1", appendBody)
                    .setValueInputOption("USER_ENTERED")
                    .setInsertDataOption("INSERT_ROWS")
                    .setIncludeValuesInResponse(true)
                    .execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
