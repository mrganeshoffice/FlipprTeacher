package com.app.flipprteachear.home.view.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.flipprteachear.home.pojo.liveModel.PojoTeacherCreateLive
import com.app.flipprteachear.home.pojo.liveQuesPojo.PojMcqQues
import com.app.flipprteachear.home.pojo.topicDetail.*
import com.app.flipprteachear.home.pojo.userDetail.PojoUserDetail
import com.app.flipprteachear.retroFitClasses.ApiInterface2
import com.app.flipprteachear.retroFitClasses.Retrofit
import com.hubwallet.utillss.ErrorResponse
import com.hubwallet.utillss.ResultWrapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.FileNotFoundException
import java.io.IOException
import java.util.HashMap

class LiveViewModel: ViewModel() {
    lateinit var repostitory: LiveRepostitory
    var api = Retrofit.getRetrofit().create(ApiInterface2::class.java)
    init {
        repostitory = LiveRepostitory(api)
    }
    private val liveDataLiveQues= MutableLiveData<ResultWrapper<PojMcqQues>>()

     fun getLiveQues(token: String, model: ModelLiveDetail): MutableLiveData<ResultWrapper<PojMcqQues>> {
        viewModelScope.launch(Dispatchers.IO){
            try {
                liveDataLiveQues.postValue(ResultWrapper.Loading)
                val response = repostitory.getLiveQues1(token, model)
                liveDataLiveQues.postValue(ResultWrapper.Success(response))

            }catch (e : Exception){
                e.printStackTrace()

                if(e is IOException)  liveDataLiveQues.postValue(ResultWrapper.NetworkError)  else liveDataLiveQues.postValue(
                    ResultWrapper.GenericError(
                    ErrorResponse(-1, e.message)
                ))
                if(e is FileNotFoundException) liveDataLiveQues.postValue(ResultWrapper.NetworkError)

            }
        }
        return liveDataLiveQues
    }

    private val liveDataCreateLive= MutableLiveData<ResultWrapper<PojoTeacherCreateLive>>()
    fun getCreateLive(token: String, model: ModelTeacherCreateLiveSession): MutableLiveData<ResultWrapper<PojoTeacherCreateLive>> {
        viewModelScope.launch(Dispatchers.IO){
            try {
                liveDataCreateLive.postValue(ResultWrapper.Loading)
                val response = repostitory.getTeacherCreateLive(token, model)
                liveDataCreateLive.postValue(ResultWrapper.Success(response))

            }catch (e : Exception){
                e.printStackTrace()

                if(e is IOException)  liveDataCreateLive.postValue(ResultWrapper.NetworkError)  else liveDataCreateLive.postValue(
                    ResultWrapper.GenericError(
                    ErrorResponse(-1, e.message)
                ))
                if(e is FileNotFoundException) liveDataCreateLive.postValue(ResultWrapper.NetworkError)

            }
        }
        return liveDataCreateLive
    }
    private val liveDataUserDetail= MutableLiveData<ResultWrapper<PojoUserDetail>>()
    fun get_users_details(token: String, userID: HashMap<String, String>): MutableLiveData<ResultWrapper<PojoUserDetail> > {
        viewModelScope.launch(Dispatchers.IO){
            try {
                liveDataUserDetail.postValue(ResultWrapper.Loading)
                val response = repostitory.get_users_details(token, userID)
                liveDataUserDetail.postValue(ResultWrapper.Success(response))

            }catch (e : Exception){
                e.printStackTrace()

                if(e is IOException)  liveDataUserDetail.postValue(ResultWrapper.NetworkError)  else liveDataUserDetail.postValue(ResultWrapper.GenericError(
                    ErrorResponse(-1, e.message)
                ))
                if(e is FileNotFoundException) liveDataUserDetail.postValue(ResultWrapper.NetworkError)

            }
        }
        return liveDataUserDetail
    }
}