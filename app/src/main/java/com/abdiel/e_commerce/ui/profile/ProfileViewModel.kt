package com.abdiel.e_commerce.ui.profile

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
class ProfileViewModel @Inject constructor(
    private val apiService: ApiService,
    private val gson: Gson,
    private val session: Session
) : BaseViewModel() {

    fun logout() = viewModelScope.launch {
        ApiObserver(
            { apiService.logout() }, false, object : ApiObserver.ResponseListener {
                override suspend fun onSuccess(response: JSONObject) {
                    //delete account
                    session.clearUser()

                    session.setValue(Const.USER.PHONE_NUMBER, "")
                    session.setValue(Const.USER.PASSWORD, "")
                    session.setValue(Const.USER.PROFILE, "")
                    session.setValue(Const.TOKEN.PREF_TOKEN, "")
                    _apiResponse.send(ApiResponse().responseSuccess())
                }
            })
    }
}