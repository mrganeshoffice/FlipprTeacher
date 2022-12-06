package com.app.flipprteachear.home.pojo

import com.google.gson.annotations.SerializedName

data class SchoolCode(
    @SerializedName("details" ) var details : DetailsSchooldCode? = DetailsSchooldCode(),
    @SerializedName("message" ) var message : String?  = null
)
