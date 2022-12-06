package com.app.flipprteachear.home.pojo

import java.io.Serializable
import java.util.ArrayList

data class PojoTeacherClass(
    val details: ArrayList<Detail>?=null,
    val message: String?=null
): Serializable