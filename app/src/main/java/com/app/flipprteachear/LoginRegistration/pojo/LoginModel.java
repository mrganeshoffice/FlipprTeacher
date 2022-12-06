package com.app.flipprteachear.LoginRegistration.pojo;

public class LoginModel {
    String mobile, device;
    public LoginModel(String mobile, String device){
        this.mobile = mobile;
        this.device = device;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }
}
