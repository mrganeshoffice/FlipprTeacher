
package com.app.flipprteachear.LoginRegistration.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PojoULoginList {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("detail")
    @Expose
    private List<Detail> detail = null;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Detail> getDetail() {
        return detail;
    }

    public void setDetail(List<Detail> detail) {
        this.detail = detail;
    }
    public class Detail {

        @SerializedName("user_id")
        @Expose
        private String userId;
        @SerializedName("first_name")
        @Expose
        private String firstName;
        @SerializedName("last_name")
        @Expose
        private String lastName;
        @SerializedName("image")
        @Expose
        private String image;
        @SerializedName("username")
        @Expose
        private String username;
        @SerializedName("password")
        @Expose
        private String password;
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
        @SerializedName("class_name")
        @Expose
        private String className;
        @SerializedName("academy_name")
        @Expose
        private String academyName;
        @SerializedName("token")
        @Expose
        private String token;




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

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
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

        public String getClassName() {
            return className;
        }

        public void setClassName(String className) {
            this.className = className;
        }

        public String getAcademyName() {
            return academyName;
        }

        public void setAcademyName(String academyName) {
            this.academyName = academyName;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }


    }

}
