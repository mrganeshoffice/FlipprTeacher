package com.app.flipprteachear.home.pojo;

public class ModelCheckedActivities {
    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    private boolean isSelected;

    public boolean isSelected() {
        return isSelected;
    }

    public String getActivities_s() {
        return activities_s;
    }
    public String getIds() { return ids; }

    private String activities_s;
    private String ids;

    public ModelCheckedActivities(boolean isSelected, String activities_s, String ids) {
        this.isSelected = isSelected;
        this.activities_s = activities_s;
        this.ids = ids;
    }


}
