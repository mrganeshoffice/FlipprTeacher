package com.app.flipprteachear.home.pojo.liveModel

data class Details(
    val dimensions: List<Dimension>,
    val levels: List<Level>?=null,
    val live_code: String?=null,
    val students: List<Student>?=null,
    val sub_topics: List<SubTopic>?=null
)