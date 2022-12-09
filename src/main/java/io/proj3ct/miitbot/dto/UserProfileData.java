package io.proj3ct.miitbot.dto;

import io.proj3ct.miitbot.constrants.Buttons;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Данные анкеты пользователя
 */
@Getter
@Setter
@RequiredArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserProfileData {
    String fullName;
    Buttons typeMat;
    String institute;
    String address;
    int courseNumber;
    String group;
    String phoneNumber;
    String serialPassport;
    String passportIssued;
    Date dateOfBirthday;
    String inn;
    String bankBook;
    String bankBIK;
    String unionCard;

    public String getDateOfBirthday() {
        String pattern = "dd.MM.yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(dateOfBirthday);
        return date;
    }
}
