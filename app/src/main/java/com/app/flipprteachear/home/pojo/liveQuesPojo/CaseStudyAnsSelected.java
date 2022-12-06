package com.app.flipprteachear.home.pojo.liveQuesPojo;

public class CaseStudyAnsSelected {
    String quesPosition;
    String quesAnsMarks;
    String noOfRightAns;
    String rightOrWrong;
    String selectedQesIds;

    public CaseStudyAnsSelected(String quesPosition, String quesAnsMarks, String noOfRightAns, String rightOrWrong, String selectedQesIds) {
        this.quesPosition = quesPosition;
        this.quesAnsMarks = quesAnsMarks;
        this.noOfRightAns = noOfRightAns;
        this.rightOrWrong = rightOrWrong;
        this.selectedQesIds = selectedQesIds;
    }
    public String getQuesPosition() {
        return quesPosition;
    }

    public String getQuesAnsMarks() {
        return quesAnsMarks;
    }
    public String getNoOfRightAns() {
        return noOfRightAns;
    }

    public String getRightOrWrong() {
        return rightOrWrong;
    }
    public String getSelectedQesIds() {
        return selectedQesIds;
    }
}
