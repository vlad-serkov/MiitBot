package io.proj3ct.miitbot.cache;


import io.proj3ct.miitbot.constrants.UserState;
import io.proj3ct.miitbot.constrants.BotState;
import io.proj3ct.miitbot.dto.UserProfileData;

public interface DataCache {


    void setUsersCurrentUserState(Long userId, UserState userState);

    UserState getUsersCurrentUserState(Long userId);

    UserProfileData getUserProfileData(Long userId);

    void saveUserProfileData(Long userId, UserProfileData userProfileData);
    void setUsersCurrentBotState(Long userId, BotState botState);

    BotState getUsersCurrentBotState(Long userId);
}
