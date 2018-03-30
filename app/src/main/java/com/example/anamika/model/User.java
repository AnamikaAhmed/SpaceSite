package com.example.anamika.model;

import com.stfalcon.chatkit.commons.models.IUser;

/**
 * Created by tahmid on 29-Sep-17.
 */

public class User implements IUser {

    private String id, name, avatar;

    public User(String id, String name, String avatar) {
        this.id = id;
        this.name = name;
        this.avatar = avatar;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getAvatar() {
        return this.avatar;
    }
}
