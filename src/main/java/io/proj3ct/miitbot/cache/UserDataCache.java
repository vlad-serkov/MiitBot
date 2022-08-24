package io.proj3ct.miitbot.cache;

import io.proj3ct.miitbot.constrants.AskState;
import io.proj3ct.miitbot.constrants.BotState;
import io.proj3ct.miitbot.dto.UserProfileData;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * In-memory cache.
 * usersBotStates: user_id and user's bot state
 * usersProfileData: user_id  and user's profile data.
 */

@Component
public class UserDataCache implements DataCache{
    private final Map<Long, AskState> usersBotStates = new HashMap<>();
    private final Map<Long, UserProfileData> usersProfileData = new HashMap<>();
    private final Map<Long, BotState> usersFilingProfileState = new HashMap<>();
    
    @Override
    public void setUsersCurrentUserState(Long userId, AskState askState) {
        usersBotStates.put(userId, askState);
    }
    @Override
    public AskState getUsersCurrentUserState(Long userId) {
        AskState askState = usersBotStates.get(userId);
        if (askState == null) {
            return AskState.ASK_FULL_NAME;
        }
        return askState;
    }
    @Override
    public UserProfileData getUserProfileData(Long userId) {
        UserProfileData userProfileData = usersProfileData.get(userId);
        if (userProfileData == null) {
            return new UserProfileData();
        }
        return userProfileData;
    }
    @Override
    public void saveUserProfileData(Long userId, UserProfileData userProfileData) {
        usersProfileData.put(userId, userProfileData);
    }
    @Override
    public void setUsersCurrentBotState(Long userId, BotState botState) {
        usersFilingProfileState.put(userId, botState);
    }
    @Override
    public BotState getUsersCurrentBotState(Long userId) {
        if (usersFilingProfileState.get(userId)==null){
            return BotState.SHOW_MENU;
        }
        return usersFilingProfileState.get(userId);
    }
}
