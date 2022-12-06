package com.app.flipprteachear.home.pojo;

import java.util.ArrayList;

public class RoomsForDb {

    private String liveCode;
    private Boolean nextTapped;
    private String schoolClassCourseID ;
    private String schoolCourseStructureID ;
    private Boolean startStatus ;
    private String teacherId ;
    private ArrayList<String> users ;
    private ArrayList<String> submittedBy ;

    private String level;
    private String subtopics;
    private String dimension;
    private Integer currentQuestionIndex;
    private Integer nextTappedCount;
    private String chapter_name;
    private String topic_name;

    public RoomsForDb(){}

    public RoomsForDb(String liveCode, Boolean nextTapped, String schoolClassCourseID, String schoolCourseStructureID, Boolean startStatus, String teacherId, ArrayList<String> users, String chapter_name, String topic_name) {
        this.liveCode = liveCode;
        this.nextTapped = nextTapped;
        this.schoolClassCourseID = schoolClassCourseID;
        this.schoolCourseStructureID = schoolCourseStructureID;
        this.startStatus = startStatus;
        this.teacherId = teacherId;
        this.users = users;
        this.topic_name = topic_name;
        this.chapter_name = chapter_name;
    }
    public RoomsForDb(String liveCode, Boolean nextTapped, String schoolClassCourseID, String schoolCourseStructureID, Boolean startStatus, String teacherId, ArrayList<String> users, ArrayList<String> submittedBy, String level,String dimension,String subtopics , int currentQuestionIndex, int nextTappedCount, String topic_name, String chapter_name) {
        this.liveCode = liveCode;
        this.nextTapped = nextTapped;
        this.schoolClassCourseID = schoolClassCourseID;
        this.schoolCourseStructureID = schoolCourseStructureID;
        this.startStatus = startStatus;
        this.teacherId = teacherId;
        this.users = users;
        this.submittedBy = submittedBy;
        this.currentQuestionIndex = currentQuestionIndex;
        this.level = level;
        this.dimension = dimension;
        this.subtopics = subtopics;
        this.nextTappedCount = nextTappedCount;
        this.topic_name = topic_name;
        this.chapter_name = chapter_name;
    }
    public String getLiveCode() {
        return liveCode;
    }

    public void setLiveCode(String liveCode) {
        this.liveCode = liveCode;
    }

    public Boolean getNextTapped() {
        return nextTapped;
    }

    public void setNextTapped(Boolean nextTapped) {
        this.nextTapped = nextTapped;
    }

    public String getSchoolClassCourseID() {
        return schoolClassCourseID;
    }

    public void setSchoolClassCourseID(String schoolClassCourseID) {
        this.schoolClassCourseID = schoolClassCourseID;
    }

    public String getSchoolCourseStructureID() {
        return schoolCourseStructureID;
    }

    public void setSchoolCourseStructureID(String schoolCourseStructureID) {
        this.schoolCourseStructureID = schoolCourseStructureID;
    }

    public Boolean getStartStatus() {
        return startStatus;
    }

    public void setStartStatus(Boolean startStatus) {
        this.startStatus = startStatus;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public ArrayList<String> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<String> users) {
        this.users = users;
    }


    public ArrayList<String> getSubmittedBy() {
        return submittedBy;
    }

    public void setSubmittedBy(ArrayList<String> submittedBy) {
        this.submittedBy = submittedBy;
    }
    public Integer getCurrentQuestionIndex() {
        return currentQuestionIndex;
    }

    public void setCurrentQuestionIndex(Integer currentQuestionIndex) {
        this.currentQuestionIndex = currentQuestionIndex;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getSubtopics() {
        return subtopics;
    }

    public void setSubtopics(String subtopics) {
        this.subtopics = subtopics;
    }

    public String getDimension() {
        return dimension;
    }

    public void setDimension(String dimension) {
        this.dimension = dimension;
    }


    public Integer getNextTappedCount() {
        return nextTappedCount;
    }

    public void setNextTappedCount(Integer nextTappedCount) {
        this.nextTappedCount = nextTappedCount;
    }

    public String getTopic_name() {
        return topic_name;
    }

    public void setTopic_name(String topic_name) {
        this.topic_name = topic_name;
    }

    public String getChapter_name() {
        return chapter_name;
    }

    public void setChapter_name(String chapter_name) {
        this.chapter_name = chapter_name;
    }
}
