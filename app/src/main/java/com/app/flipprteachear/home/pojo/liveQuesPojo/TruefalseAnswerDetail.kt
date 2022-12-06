package com.app.flipprteachear.home.pojo.liveQuesPojo

import java.io.Serializable

data class TruefalseAnswerDetail(
    val questions_all_types_id: String,
    val right_truefalse_ans_id: String,
    val right_txt_ans: String,
    val tf_type: String,
    val truefalse_img: String
): Serializable