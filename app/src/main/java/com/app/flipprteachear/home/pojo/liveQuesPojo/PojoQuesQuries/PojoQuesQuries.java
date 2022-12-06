
package com.app.flipprteachear.home.pojo.liveQuesPojo.PojoQuesQuries;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PojoQuesQuries {

    @SerializedName("details")
    @Expose
    private List<Detail> details = null;
    @SerializedName("message")
    @Expose
    private String message;

    public List<Detail> getDetails() {
        return details;
    }

    public void setDetails(List<Detail> details) {
        this.details = details;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
