
package com.app.flipprteachear.LoginRegistration.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Detail {

    @SerializedName("parent_code_id")
    @Expose
    private String parentCodeId;
    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("phone")
    @Expose
    private String phone;

    public String getParentCodeId() {
        return parentCodeId;
    }

    public void setParentCodeId(String parentCodeId) {
        this.parentCodeId = parentCodeId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}
