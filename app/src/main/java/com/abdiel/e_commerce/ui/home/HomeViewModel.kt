package com.abdiel.e_commerce.ui.home

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.abdiel.e_commerce.api.ApiService
import com.abdiel.e_commerce.base.viewModel.BaseViewModel
import com.abdiel.e_commerce.data.product.Product
import com.abdiel.e_commerce.data.user.User
import com.abdiel.e_commerce.session.Session
import com.crocodic.core.api.ApiCode
import com.crocodic.core.api.ApiObserver
import com.crocodic.core.api.ApiResponse
import com.crocodic.core.data.CoreSession
import com.crocodic.core.extension.toList
import com.crocodic.core.extension.toObject
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import org.json.JSONObject
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val apiService: ApiService,
    private val gson: Gson,
    private val session: Session
) : BaseViewModel() {

    private val _listAllProduct = MutableSharedFlow<List<Product>>()
    val listAllProduct = _listAllProduct.asSharedFlow()

    fun getAllProduct() = viewModelScope.launch {
        ApiObserver(
            { apiService.getAllProduct() }, false, object : ApiObserver.ResponseListener {
                override suspend fun onSuccess(response: JSONObject) {
                    val data = response.getJSONArray(ApiCode.DATA).toList<Product>(gson)
                    _listAllProduct.emit(data)
                }
            })
    }

    fun getProfile() = viewModelScope.launch {
        ApiObserver(
            { apiService.getProfile() }, false, object : ApiObserver.ResponseListener {
                override suspend fun onSuccess(response: JSONObject) {
                    val data = response.getJSONObject(ApiCode.DATA).toObject<User>(gson)
                    session.saveUser(data)
                    _apiResponse.send(ApiResponse().responseSuccess())
                }
            })
    }
}