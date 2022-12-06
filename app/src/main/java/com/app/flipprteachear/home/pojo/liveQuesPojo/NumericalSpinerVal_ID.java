package com.app.flipprteachear.home.pojo.liveQuesPojo;

public class NumericalSpinerVal_ID {

    String id;
    String value;
    String Right;

    public String getWrong() {
        return wrong;
    }

    public void setWrong(String wrong) {
        this.wrong = wrong;
    }

    String wrong;
    public NumericalSpinerVal_ID(String id, String value, String Right, String wrong){
        this.id= id;
        this. value = value;
        this.Right = Right;
        this.wrong = wrong;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getRight() {
        return Right;
    }

    public void setRight(String right) {
        Right = right;
    }


}
