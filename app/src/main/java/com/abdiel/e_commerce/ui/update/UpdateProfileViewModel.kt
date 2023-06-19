package com.abdiel.e_commerce.ui.update

import com.abdiel.e_commerce.api.ApiService
import com.abdiel.e_commerce.base.viewModel.BaseViewModel
import com.abdiel.e_commerce.session.Session
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UpdateProfileViewModel @Inject constructor(
    private val apiService: ApiService,
    private val gson: Gson,
    private val session: Session
) : BaseViewModel() {
}