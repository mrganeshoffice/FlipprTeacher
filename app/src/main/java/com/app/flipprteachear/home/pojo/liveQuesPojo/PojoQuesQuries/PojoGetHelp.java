
package com.app.flipprteachear.home.pojo.liveQuesPojo.PojoQuesQuries;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PojoGetHelp {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("details")
    @Expose
    private List<Detail_h> details = null;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Detail_h> getDetails() {
        return details;
    }

    public void setDetails(List<Detail_h> details) {
        this.details = details;
    }
    public class Detail_h {

        @SerializedName("help_query_types_id")
        @Expose
        private String helpQueryTypesId;
        @SerializedName("query_name")
        @Expose
        private String queryName;
        @SerializedName("isSelected")
        @Expose
        private Boolean isSelected;

        public String getHelpQueryTypesId() {
            return helpQueryTypesId;
        }

        public void setHelpQueryTypesId(String helpQueryTypesId) {
            this.helpQueryTypesId = helpQueryTypesId;
        }

        public String getQueryName() {
            return queryName;
        }

        public void setQueryName(String queryName) {
            this.queryName = queryName;
        }

        public Boolean getSelected() {
            return isSelected;
        }

        public void setSelected(Boolean selected) {
            isSelected = selected;
        }
    }
}
