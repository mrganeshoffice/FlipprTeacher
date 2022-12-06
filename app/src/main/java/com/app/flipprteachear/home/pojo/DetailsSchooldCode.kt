package com.app.flipprteachear.home.pojo

import com.google.gson.annotations.SerializedName

data class DetailsSchooldCode(
    @SerializedName("user_id"            ) var userId            : String? = null,
    @SerializedName("first_name"         ) var firstName         : String? = null,
    @SerializedName("active_status"      ) var activeStatus      : String? = null,
    @SerializedName("last_name"          ) var lastName          : String? = null,
    @SerializedName("dob"                ) var dob               : String? = null,
    @SerializedName("image"              ) var image             : String? = null,
    @SerializedName("short_des"          ) var shortDes          : String? = null,
    @SerializedName("username"           ) var username          : String? = null,
    @SerializedName("password"           ) var password          : String? = null,
    @SerializedName("mobile"             ) var mobile            : String? = null,
    @SerializedName("email"              ) var email             : String? = null,
    @SerializedName("class_code"         ) var classCode         : String? = null,
    @SerializedName("device_token"       ) var deviceToken       : String? = null,
    @SerializedName("registration_date"  ) var registrationDate  : String? = null,
    @SerializedName("email_varification" ) var emailVarification : String? = null,
    @SerializedName("parent_id"          ) var parentId          : String? = null,
    @SerializedName("parent_code"        ) var parentCode        : String? = null,
    @SerializedName("class_id"           ) var classId           : String? = null,
    @SerializedName("gender_type"        ) var genderType        : String? = null,
    @SerializedName("account_type"       ) var accountType       : String? = null,
    @SerializedName("device_type"        ) var deviceType        : String? = null
) {

}
