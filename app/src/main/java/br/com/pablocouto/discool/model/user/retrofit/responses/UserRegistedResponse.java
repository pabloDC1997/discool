package br.com.pablocouto.discool.model.user.retrofit.responses;

import com.google.gson.annotations.SerializedName;

/**
 * Created by pablo.couto on 19/12/2017.
 */

public class UserRegistedResponse {

    @SerializedName("cadastro")
    private boolean cadastro;

    @SerializedName("msg")
    private String  msg;

    @SerializedName("ex")
    private String  ex;

    public UserRegistedResponse() {
        this(false,null,null);
    }

    public UserRegistedResponse(boolean cadastro, String msg, String ex) {
        this.cadastro = cadastro;
        this.msg = msg;
        this.ex = ex;
    }

    public boolean isCadastro() {
        return cadastro;
    }

    public void setCadastro(boolean cadastro) {
        this.cadastro = cadastro;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getEx() {
        return ex;
    }

    public void setEx(String ex) {
        this.ex = ex;
    }
}
