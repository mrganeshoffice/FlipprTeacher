
package com.app.flipprteachear.home.pojo.liveQuesPojo.pojoSubmitted;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class UserDetail {

    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("first_name")
    @Expose
    private String firstName;
    @SerializedName("active_status")
    @Expose
    private String activeStatus;
    @SerializedName("gender_type")
    @Expose
    private Object genderType;
    @SerializedName("last_name")
    @Expose
    private String lastName;
    @SerializedName("dob")
    @Expose
    private String dob;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("short_des")
    @Expose
    private Object shortDes;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("password")
    @Expose
    private Object password;
    @SerializedName("mobile")
    @Expose
    private String mobile;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("class_code")
    @Expose
    private String classCode;
    @SerializedName("device_token")
    @Expose
    private String deviceToken;
    @SerializedName("registration_date")
    @Expose
    private String registrationDate;
    @SerializedName("email_varification")
    @Expose
    private String emailVarification;
    @SerializedName("parent_id")
    @Expose
    private String parentId;
    @SerializedName("parent_code")
    @Expose
    private String parentCode;
    @SerializedName("class_id")
    @Expose
    private String classId;
    @SerializedName("account_type")
    @Expose
    private String accountType;
    @SerializedName("device_type")
    @Expose
    private String deviceType;
    @SerializedName("submit_detail")
    @Expose
    private SubmitDetail submitDetail;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getActiveStatus() {
        return activeStatus;
    }

    public void setActiveStatus(String activeStatus) {
        this.activeStatus = activeStatus;
    }

    public Object getGenderType() {
        return genderType;
    }

    public void setGenderType(Object genderType) {
        this.genderType = genderType;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Object getShortDes() {
        return shortDes;
    }

    public void setShortDes(Object shortDes) {
        this.shortDes = shortDes;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Object getPassword() {
        return password;
    }

    public void setPassword(Object password) {
        this.password = password;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getClassCode() {
        return classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }

    public String getDeviceToken() {
        return deviceToken;
    }

    public void setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getEmailVarification() {
        return emailVarification;
    }

    public void setEmailVarification(String emailVarification) {
        this.emailVarification = emailVarification;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public SubmitDetail getSubmitDetail() {
        return submitDetail;
    }

    public void setSubmitDetail(SubmitDetail submitDetail) {
        this.submitDetail = submitDetail;
    }

}
