package com.app.flipprteachear.home.pojo.liveQuesPojo

import java.io.Serializable

data class AnswerDetail(
    val casestudy_ques_id: String,
    val question_img: String,
    val questions_all_types_id: String,
    val right_txt_ans1: String,
    val right_txt_ans_id: String,
    val types: String
): Serializable