
package com.app.flipprteachear.LoginRegistration.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GenrateParentCodePojo {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("detail")
    @Expose
    private List<Detail> detail = null;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Detail> getDetail() {
        return detail;
    }

    public void setDetail(List<Detail> detail) {
        this.detail = detail;
    }

}
