package com.app.flipprteachear.LoginRegistration.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PojoLogin {

    @SerializedName("login")
    @Expose
    private String login;
    @SerializedName("detail")
    @Expose
    private List<Detail> detail = null;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
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
        @SerializedName("grade")
        @Expose
        private String grade;
        @SerializedName("board")
        @Expose
        private String board;
        @SerializedName("mobile")
        @Expose
        private String mobile;
        @SerializedName("father_name")
        @Expose
        private String fatherName;
        @SerializedName("mother_name")
        @Expose
        private String motherName;
        @SerializedName("father_mobile")
        @Expose
        private String fatherMobile;
        @SerializedName("mother_mobile")
        @Expose
        private String motherMobile;
        @SerializedName("father_email")
        @Expose
        private String fatherEmail;
        @SerializedName("mother_email")
        @Expose
        private String motherEmail;
        @SerializedName("device_token")
        @Expose
        private String deviceToken;

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

        public String getGrade() {
            return grade;
        }

        public void setGrade(String grade) {
            this.grade = grade;
        }

        public String getBoard() {
            return board;
        }

        public void setBoard(String board) {
            this.board = board;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getFatherName() {
            return fatherName;
        }

        public void setFatherName(String fatherName) {
            this.fatherName = fatherName;
        }

        public String getMotherName() {
            return motherName;
        }

        public void setMotherName(String motherName) {
            this.motherName = motherName;
        }

        public String getFatherMobile() {
            return fatherMobile;
        }

        public void setFatherMobile(String fatherMobile) {
            this.fatherMobile = fatherMobile;
        }

        public String getMotherMobile() {
            return motherMobile;
        }

        public void setMotherMobile(String motherMobile) {
            this.motherMobile = motherMobile;
        }

        public String getFatherEmail() {
            return fatherEmail;
        }

        public void setFatherEmail(String fatherEmail) {
            this.fatherEmail = fatherEmail;
        }

        public String getMotherEmail() {
            return motherEmail;
        }

        public void setMotherEmail(String motherEmail) {
            this.motherEmail = motherEmail;
        }

        public String getDeviceToken() {
            return deviceToken;
        }

        public void setDeviceToken(String deviceToken) {
            this.deviceToken = deviceToken;
        }

    }
}