
package com.app.flipprteachear.LoginRegistration.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PojoSignUp {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("detail")
    @Expose
    private List<Detail_sigh> detail = null;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Detail_sigh> getDetail() {
        return detail;
    }

    public void setDetail(List<Detail_sigh> detail) {
        this.detail = detail;
    }

}
