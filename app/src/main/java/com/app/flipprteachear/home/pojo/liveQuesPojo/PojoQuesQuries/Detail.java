
package com.app.flipprteachear.home.pojo.liveQuesPojo.PojoQuesQuries;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Detail {

    @SerializedName("ques_query_types_id")
    @Expose
    private String quesQueryTypesId;
    @SerializedName("query_name")
    @Expose
    private String queryName;
    @SerializedName("isSelected")
    @Expose
    private Boolean isSelected;
    public String getQuesQueryTypesId() {
        return quesQueryTypesId;
    }

    public void setQuesQueryTypesId(String quesQueryTypesId) {
        this.quesQueryTypesId = quesQueryTypesId;
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
