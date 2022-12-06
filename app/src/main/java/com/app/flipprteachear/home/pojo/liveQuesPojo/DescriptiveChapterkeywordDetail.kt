package com.app.flipprteachear.home.pojo.liveQuesPojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class DescriptiveChapterkeywordDetail : Serializable {
    @SerializedName("chapter_id")
    @Expose
    var chapterId: String? = null

    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("chapter_keyword")
    @Expose
    var chapterKeyword: String? = null
}