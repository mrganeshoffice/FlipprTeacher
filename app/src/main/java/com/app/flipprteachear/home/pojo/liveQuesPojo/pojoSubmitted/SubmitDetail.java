
package com.app.flipprteachear.home.pojo.liveQuesPojo.pojoSubmitted;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SubmitDetail {

    @SerializedName("user_flip_data_id")
    @Expose
    private String userFlipDataId;
    @SerializedName("stu_submit_question_id")
    @Expose
    private String stuSubmitQuestionId;
    @SerializedName("true_false_action")
    @Expose
    private String trueFalseAction;
    @SerializedName("mcq_action")
    @Expose
    private String mcqAction;
    @SerializedName("arrange_rowid")
    @Expose
    private String arrangeRowid;
    @SerializedName("fillup_user_action")
    @Expose
    private String fillupUserAction;
    @SerializedName("image_user_action")
    @Expose
    private String imageUserAction;
    @SerializedName("des_visual_action")
    @Expose
    private String desVisualAction;
    @SerializedName("des_ans")
    @Expose
    private String desAns;
    @SerializedName("des_ansmcq_action")
    @Expose
    private String desAnsmcqAction;
    @SerializedName("n_inter_action")
    @Expose
    private String nInterAction;
    @SerializedName("n_visual_action")
    @Expose
    private String nVisualAction;
    @SerializedName("n_application_action")
    @Expose
    private String nApplicationAction;
    @SerializedName("case_study_ques")
    @Expose
    private String caseStudyQues;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("ques_detail")
    @Expose
    private QuesDetail quesDetail;

    public String getUserFlipDataId() {
        return userFlipDataId;
    }

    public void setUserFlipDataId(String userFlipDataId) {
        this.userFlipDataId = userFlipDataId;
    }

    public String getStuSubmitQuestionId() {
        return stuSubmitQuestionId;
    }

    public void setStuSubmitQuestionId(String stuSubmitQuestionId) {
        this.stuSubmitQuestionId = stuSubmitQuestionId;
    }

    public String getTrueFalseAction() {
        return trueFalseAction;
    }

    public void setTrueFalseAction(String trueFalseAction) {
        this.trueFalseAction = trueFalseAction;
    }

    public String getMcqAction() {
        return mcqAction;
    }

    public void setMcqAction(String mcqAction) {
        this.mcqAction = mcqAction;
    }

    public String getArrangeRowid() {
        return arrangeRowid;
    }

    public void setArrangeRowid(String arrangeRowid) {
        this.arrangeRowid = arrangeRowid;
    }

    public String getFillupUserAction() {
        return fillupUserAction;
    }

    public void setFillupUserAction(String fillupUserAction) {
        this.fillupUserAction = fillupUserAction;
    }

    public String getImageUserAction() {
        return imageUserAction;
    }

    public void setImageUserAction(String imageUserAction) {
        this.imageUserAction = imageUserAction;
    }

    public String getDesVisualAction() {
        return desVisualAction;
    }

    public void setDesVisualAction(String desVisualAction) {
        this.desVisualAction = desVisualAction;
    }

    public String getDesAns() {
        return desAns;
    }

    public void setDesAns(String desAns) {
        this.desAns = desAns;
    }

    public String getDesAnsmcqAction() {
        return desAnsmcqAction;
    }

    public void setDesAnsmcqAction(String desAnsmcqAction) {
        this.desAnsmcqAction = desAnsmcqAction;
    }

    public String getnInterAction() {
        return nInterAction;
    }

    public void setnInterAction(String nInterAction) {
        this.nInterAction = nInterAction;
    }

    public String getnVisualAction() {
        return nVisualAction;
    }

    public void setnVisualAction(String nVisualAction) {
        this.nVisualAction = nVisualAction;
    }

    public String getnApplicationAction() {
        return nApplicationAction;
    }

    public void setnApplicationAction(String nApplicationAction) {
        this.nApplicationAction = nApplicationAction;
    }

    public String getCaseStudyQues() {
        return caseStudyQues;
    }

    public void setCaseStudyQues(String caseStudyQues) {
        this.caseStudyQues = caseStudyQues;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public QuesDetail getQuesDetail() {
        return quesDetail;
    }

    public void setQuesDetail(QuesDetail quesDetail) {
        this.quesDetail = quesDetail;
    }

}
