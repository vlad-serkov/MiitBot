package io.proj3ct.miitbot.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

/**
 * Данные анкеты пользователя
 */
@Getter
@Setter
@RequiredArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserProfileData {
    String name;
    int age;
    String typeMat;
    String institute;
}
