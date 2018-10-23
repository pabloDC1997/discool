package br.com.pablocouto.discool.model.user;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by pablo.couto on 06/12/2017.
 */

public class Profile implements Serializable {

    @SerializedName("id_user")
    private String id;

    @SerializedName("email")
    private String email;

    @SerializedName("password")
    private String password;

    @SerializedName("name")
    private String name;

    @SerializedName("phone")
    private String phone;

    public Profile(){
        this(null,null);
    }

    public Profile(String id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }

    public Profile(String email, String password) {
        this.id = null;
        this.email = email;
        this.password = password;
    }

    public Profile(String id, String email, String password, String name, String phone) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.phone = phone;
    }

    public Profile(String email, String password, String name, String phone) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return
                " {" +
                    " id = "         +   this.id +
                    " email = "      +   this.email +
                    " password = "   +   this.password +
                " }";
    }
}
