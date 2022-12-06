package com.app.flipprteachear.home.view.viewModel


import com.app.flipprteachear.home.pojo.liveModel.PojoTeacherCreateLive
import com.app.flipprteachear.home.pojo.liveQuesPojo.PojMcqQues
import com.app.flipprteachear.home.pojo.topicDetail.*
import com.app.flipprteachear.home.pojo.userDetail.PojoUserDetail
import com.app.flipprteachear.retroFitClasses.ApiInterface2
import java.util.HashMap


class LiveRepostitory(var api: ApiInterface2) {


    suspend fun getLiveQues1(token: String, model: ModelLiveDetail): PojMcqQues {
        return api.getLiveQues1(token, model)
    }
    suspend fun getTeacherCreateLive(token: String, model: ModelTeacherCreateLiveSession): PojoTeacherCreateLive {
        return api.teacher_create_live_session(token, model)
    }
    suspend fun get_users_details(token: String, model: HashMap<String, String>): PojoUserDetail {
        return api.get_users_details(token, model)
    }

}