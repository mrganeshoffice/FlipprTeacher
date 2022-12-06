package com.app.flipprteachear.home

import com.app.flipprteachear.home.pojo.topicDetail.TopicDetail

interface ForLivePageChange {
    fun golivePage(topicDetail: TopicDetail)
    fun endlivePage()
    fun checkBoxUpdate(s: String, schoolCourseStructureId: String)
}