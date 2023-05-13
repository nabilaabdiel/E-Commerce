package com.abdiel.e_commerce.ui.cart

import com.abdiel.e_commerce.api.ApiService
import com.abdiel.e_commerce.base.viewModel.BaseViewModel
import com.crocodic.core.data.CoreSession
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val apiService: ApiService,
    private val gson: Gson,
    private val coreSession: CoreSession
) : BaseViewModel() {
}