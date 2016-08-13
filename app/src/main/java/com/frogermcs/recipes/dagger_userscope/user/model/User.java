package com.frogermcs.recipes.dagger_userscope.user.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.frogermcs.recipes.dagger_userscope.api.response.UserResponse;

import rx.functions.Func1;

/**
 * Created by froger_mcs on 09/08/16.
 */

public class User implements Parcelable {
    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        public User[] newArray(int size) {
            return new User[size];
        }
    };
    public String login;
    public long id;
    public String url;
    public String email;

    public User() {
    }

    protected User(Parcel in) {
        this.login = in.readString();
        this.id = in.readLong();
        this.url = in.readString();
        this.email = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.login);
        dest.writeLong(this.id);
        dest.writeString(this.url);
        dest.writeString(this.email);
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", id=" + id +
                ", url='" + url + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public static Func1<UserResponse, User> UserResponseToUser() {
        return new Func1<UserResponse, User>() {
            @Override
            public User call(UserResponse userResponse) {
                User user = new User();
                user.login = userResponse.login;
                user.id = userResponse.id;
                user.url = userResponse.url;
                user.email = userResponse.email;
                return user;
            }
        };
    }
}