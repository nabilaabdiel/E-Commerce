package com.abdiel.e_commerce.ui.login

import androidx.lifecycle.viewModelScope
import com.abdiel.e_commerce.api.ApiService
import com.abdiel.e_commerce.base.viewModel.BaseViewModel
import com.abdiel.e_commerce.data.constant.Const
import com.abdiel.e_commerce.session.Session
import com.crocodic.core.api.ApiObserver
import com.crocodic.core.api.ApiResponse
import com.crocodic.core.data.CoreSession
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.json.JSONObject
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val apiService: ApiService,
    private val gson: Gson,
    private val sessionApp: Session,
    private val coreSession: CoreSession
) : BaseViewModel() {

    fun login(phone_number: String, password: String) = viewModelScope.launch {
        _apiResponse.send(ApiResponse().responseLoading())
        ApiObserver(
            { apiService.login(phone_number, password) }, false, object : ApiObserver.ResponseListener {
                override suspend fun onSuccess(response: JSONObject) {

                    coreSession.setValue(Const.USER.PHONE_NUMBER, phone_number)
                    coreSession.setValue(Const.USER.PASSWORD, password)
                    coreSession.setValue(Const.USER.PROFILE, "success")
                    coreSession.setValue(Const.TOKEN.PREF_TOKEN, response.getString("token"))

                    _apiResponse.send(ApiResponse().responseSuccess("Success"))
                }

                override suspend fun onError(response: ApiResponse) {
                    val rawData = response.rawResponse
                    val rawJSON = JSONObject(rawData)
                    val info = rawJSON.getString("info")
                    response.message = info
                    super.onError(response)
                }
            })
    }
}