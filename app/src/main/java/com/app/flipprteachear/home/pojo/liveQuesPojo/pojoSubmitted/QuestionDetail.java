
package com.app.flipprteachear.home.pojo.liveQuesPojo.pojoSubmitted;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class QuestionDetail {

    @SerializedName("stu_submit_question_id")
    @Expose
    private String stuSubmitQuestionId;
    @SerializedName("question_id")
    @Expose
    private String questionId;
    @SerializedName("student_id")
    @Expose
    private String studentId;
    @SerializedName("total_taken_time")
    @Expose
    private String totalTakenTime;
    @SerializedName("current_datetime")
    @Expose
    private String currentDatetime;
    @SerializedName("ans_status")
    @Expose
    private String ansStatus;
    @SerializedName("ans_points")
    @Expose
    private String ansPoints;
    @SerializedName("is_streak_active")
    @Expose
    private String isStreakActive;
    @SerializedName("is_streak_saver")
    @Expose
    private String isStreakSaver;
    @SerializedName("is_streak_saver_enabled")
    @Expose
    private String isStreakSaverEnabled;
    @SerializedName("is_double_confidence")
    @Expose
    private String isDoubleConfidence;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("level_id")
    @Expose
    private String levelId;
    @SerializedName("segment_id")
    @Expose
    private String segmentId;
    @SerializedName("dimension_id")
    @Expose
    private String dimensionId;
    @SerializedName("exam_importance_id")
    @Expose
    private String examImportanceId;
    @SerializedName("chapter_id")
    @Expose
    private String chapterId;
    @SerializedName("topic_id")
    @Expose
    private String topicId;
    @SerializedName("subtopic_id")
    @Expose
    private String subtopicId;
    @SerializedName("subject_id")
    @Expose
    private String subjectId;
    @SerializedName("cluster_id")
    @Expose
    private String clusterId;
    @SerializedName("course_id")
    @Expose
    private String courseId;
    @SerializedName("solution_opened_status")
    @Expose
    private String solutionOpenedStatus;
    @SerializedName("student_session_activity_id")
    @Expose
    private String studentSessionActivityId;
    @SerializedName("live_id")
    @Expose
    private String liveId;
    @SerializedName("submit_from")
    @Expose
    private String submitFrom;
    @SerializedName("user_detail")
    @Expose
    private List<UserDetail> userDetail = null;

    public String getStuSubmitQuestionId() {
        return stuSubmitQuestionId;
    }

    public void setStuSubmitQuestionId(String stuSubmitQuestionId) {
        this.stuSubmitQuestionId = stuSubmitQuestionId;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getTotalTakenTime() {
        return totalTakenTime;
    }

    public void setTotalTakenTime(String totalTakenTime) {
        this.totalTakenTime = totalTakenTime;
    }

    public String getCurrentDatetime() {
        return currentDatetime;
    }

    public void setCurrentDatetime(String currentDatetime) {
        this.currentDatetime = currentDatetime;
    }

    public String getAnsStatus() {
        return ansStatus;
    }

    public void setAnsStatus(String ansStatus) {
        this.ansStatus = ansStatus;
    }

    public String getAnsPoints() {
        return ansPoints;
    }

    public void setAnsPoints(String ansPoints) {
        this.ansPoints = ansPoints;
    }

    public String getIsStreakActive() {
        return isStreakActive;
    }

    public void setIsStreakActive(String isStreakActive) {
        this.isStreakActive = isStreakActive;
    }

    public String getIsStreakSaver() {
        return isStreakSaver;
    }

    public void setIsStreakSaver(String isStreakSaver) {
        this.isStreakSaver = isStreakSaver;
    }

    public String getIsStreakSaverEnabled() {
        return isStreakSaverEnabled;
    }

    public void setIsStreakSaverEnabled(String isStreakSaverEnabled) {
        this.isStreakSaverEnabled = isStreakSaverEnabled;
    }

    public String getIsDoubleConfidence() {
        return isDoubleConfidence;
    }

    public void setIsDoubleConfidence(String isDoubleConfidence) {
        this.isDoubleConfidence = isDoubleConfidence;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLevelId() {
        return levelId;
    }

    public void setLevelId(String levelId) {
        this.levelId = levelId;
    }

    public String getSegmentId() {
        return segmentId;
    }

    public void setSegmentId(String segmentId) {
        this.segmentId = segmentId;
    }

    public String getDimensionId() {
        return dimensionId;
    }

    public void setDimensionId(String dimensionId) {
        this.dimensionId = dimensionId;
    }

    public String getExamImportanceId() {
        return examImportanceId;
    }

    public void setExamImportanceId(String examImportanceId) {
        this.examImportanceId = examImportanceId;
    }

    public String getChapterId() {
        return chapterId;
    }

    public void setChapterId(String chapterId) {
        this.chapterId = chapterId;
    }

    public String getTopicId() {
        return topicId;
    }

    public void setTopicId(String topicId) {
        this.topicId = topicId;
    }

    public String getSubtopicId() {
        return subtopicId;
    }

    public void setSubtopicId(String subtopicId) {
        this.subtopicId = subtopicId;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public String getClusterId() {
        return clusterId;
    }

    public void setClusterId(String clusterId) {
        this.clusterId = clusterId;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getSolutionOpenedStatus() {
        return solutionOpenedStatus;
    }

    public void setSolutionOpenedStatus(String solutionOpenedStatus) {
        this.solutionOpenedStatus = solutionOpenedStatus;
    }

    public String getStudentSessionActivityId() {
        return studentSessionActivityId;
    }

    public void setStudentSessionActivityId(String studentSessionActivityId) {
        this.studentSessionActivityId = studentSessionActivityId;
    }

    public String getLiveId() {
        return liveId;
    }

    public void setLiveId(String liveId) {
        this.liveId = liveId;
    }

    public String getSubmitFrom() {
        return submitFrom;
    }

    public void setSubmitFrom(String submitFrom) {
        this.submitFrom = submitFrom;
    }

    public List<UserDetail> getUserDetail() {
        return userDetail;
    }

    public void setUserDetail(List<UserDetail> userDetail) {
        this.userDetail = userDetail;
    }

}
