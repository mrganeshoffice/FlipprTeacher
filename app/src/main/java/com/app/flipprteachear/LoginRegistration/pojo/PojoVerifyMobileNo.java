
package com.app.flipprteachear.LoginRegistration.pojo;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class PojoVerifyMobileNo {

    @SerializedName("request_id")
    @Expose
    private String requestId;
    @SerializedName("type")
    @Expose
    private String type;

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
