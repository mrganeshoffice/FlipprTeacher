
package com.app.flipprteachear.LoginRegistration.pojo.generateCode;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class PojoGenrateCode {

    @SerializedName("detail")
    @Expose
    private List<Detail> detail = null;

    public List<Detail> getDetail() {
        return detail;
    }

    public void setDetail(List<Detail> detail) {
        this.detail = detail;
    }

}
