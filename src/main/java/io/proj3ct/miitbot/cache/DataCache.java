package io.proj3ct.miitbot.cache;


import io.proj3ct.miitbot.constrants.AskState;
import io.proj3ct.miitbot.constrants.BotState;
import io.proj3ct.miitbot.dto.UserProfileData;

public interface DataCache {


    void setUsersCurrentUserState(Long userId, AskState askState);

    AskState getUsersCurrentUserState(Long userId);

    UserProfileData getUserProfileData(Long userId);

    void saveUserProfileData(Long userId, UserProfileData userProfileData);
    void setUsersCurrentBotState(Long userId, BotState botState);

    BotState getUsersCurrentBotState(Long userId);
}
