package br.com.pablocouto.discool.model.user.retrofit.responses;


import com.google.gson.annotations.SerializedName;

import br.com.pablocouto.discool.model.user.Profile;

/**
 * Created by pablo.couto on 07/12/2017.
 */

public class UserAuthenticated {

    @SerializedName("profile")
    private Profile profile;

    @SerializedName("status")
    private boolean status;

    @SerializedName("msg")
    private String msg;

    public UserAuthenticated(Profile profile, boolean status, String msg) {
        this.profile = profile;
        this.status = status;
        this.msg = msg;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }


}
