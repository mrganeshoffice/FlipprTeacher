package com.app.flipprteachear.home.pojo.flag

import com.google.gson.annotations.SerializedName

class QueryDetails(
    @SerializedName("ques_query_types_id") var quesQueryTypesId: String? = null,
    @SerializedName("query_name") var queryName: String? = null,
)