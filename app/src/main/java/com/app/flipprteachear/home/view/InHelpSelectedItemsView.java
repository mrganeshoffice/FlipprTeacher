package com.app.flipprteachear.home.view;


import com.app.flipprteachear.home.pojo.liveQuesPojo.PojoQuesQuries.Detail;
import com.app.flipprteachear.home.pojo.liveQuesPojo.PojoQuesQuries.PojoGetHelp;

import java.util.List;

public interface InHelpSelectedItemsView {
    void getInSelectedItems(List<PojoGetHelp.Detail_h> mlist);
    void getInSelectedItemss(List<Detail> mlist);
}
