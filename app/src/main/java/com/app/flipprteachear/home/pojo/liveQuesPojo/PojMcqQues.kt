package com.app.flipprteachear.home.pojo.liveQuesPojo

import java.io.Serializable

data class PojMcqQues(
    val details: List<Detail>?=null,
    val message: String
):Serializable