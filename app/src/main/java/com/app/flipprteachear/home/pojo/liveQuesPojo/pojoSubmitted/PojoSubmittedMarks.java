
package com.app.flipprteachear.home.pojo.liveQuesPojo.pojoSubmitted;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class PojoSubmittedMarks {

    @SerializedName("question_details")
    @Expose
    private List<QuestionDetail> questionDetails = null;

    public List<QuestionDetail> getQuestionDetails() {
        return questionDetails;
    }

    public void setQuestionDetails(List<QuestionDetail> questionDetails) {
        this.questionDetails = questionDetails;
    }

}
