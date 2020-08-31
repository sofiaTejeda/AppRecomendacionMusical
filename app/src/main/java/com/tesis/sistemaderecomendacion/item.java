package com.tesis.sistemaderecomendacion;

import android.net.Uri;

import java.net.URI;
import java.util.Optional;

public class item {
    int background;
    String backgroundString;
    String profileName;
    int profilePhoto;
    String nbFollowers;

    public item() {
    }

    public String getBackgroundString() {
        return backgroundString;
    }

    public void setBackgroundString(String backgroundString) {
        this.backgroundString = backgroundString;
    }

    public item(int background, String profileName, int profilePhoto, String nbFollowers) {
        this.background = background;
        this.profileName = profileName;
        this.profilePhoto = profilePhoto;
        this.nbFollowers = nbFollowers;
    }

    public int getBackground() {
        return background;
    }

    public String getProfileName() {
        return profileName;
    }

    public int getProfilePhoto() {
        return profilePhoto;
    }

    public String getNbFollowers() {
        return nbFollowers;
    }

    public void setBackground(int background) {
        this.background = background;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    public void setProfilePhoto(int profilePhoto) {
        this.profilePhoto = profilePhoto;
    }

    public void setNbFollowers(String nbFollowers) {
        this.nbFollowers = nbFollowers;
    }
}

