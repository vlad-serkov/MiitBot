package io.proj3ct.miitbot.cache;

import io.proj3ct.miitbot.constrants.UserState;
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
    private Map<Long, UserState> usersBotStates = new HashMap<>();
    private Map<Long, UserProfileData> usersProfileData = new HashMap<>();

    private Map<Long, BotState> usersFilingProfileState = new HashMap<>();

    @Override
    public void setUsersCurrentUserState(Long userId, UserState userState) {
        usersBotStates.put(userId, userState);
    }
    @Override
    public UserState getUsersCurrentUserState(Long userId) {
        UserState userState = usersBotStates.get(userId);
        if (userState == null) {
            return UserState.ASK_NAME;
        }
        return userState;
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
