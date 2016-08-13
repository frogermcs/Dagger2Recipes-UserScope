package com.frogermcs.recipes.dagger_userscope.user;

import android.content.SharedPreferences;
import android.text.TextUtils;

import com.frogermcs.recipes.dagger_userscope.user.model.User;
import com.google.gson.Gson;

/**
 * Created by froger_mcs on 28/07/16.
 */

public class UserDataStore {
    private static final String KEY_SERIALIZED_USER = "serialized_user";

    private SharedPreferences sharedPreferences;
    private Gson gson;

    public UserDataStore(SharedPreferences sharedPreferences, Gson gson) {
        this.sharedPreferences = sharedPreferences;
        this.gson = gson;
    }

    public void createUser(User user) {
        String serializedUser = gson.toJson(user);
        sharedPreferences.edit().putString(KEY_SERIALIZED_USER, serializedUser).apply();
    }

    public void clearUser() {
        sharedPreferences.edit().remove(KEY_SERIALIZED_USER).apply();
    }

    public User getUser() {
        String serializedUser = sharedPreferences.getString(KEY_SERIALIZED_USER, null);
        if (!TextUtils.isEmpty(serializedUser)) {
            return gson.fromJson(serializedUser, User.class);
        }

        return null;
    }
}
