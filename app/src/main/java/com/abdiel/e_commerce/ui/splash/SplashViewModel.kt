package com.abdiel.e_commerce.ui.splash

import androidx.lifecycle.viewModelScope
import com.abdiel.e_commerce.base.viewModel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(): BaseViewModel() {
    fun splash(done: (Boolean) -> Unit) = viewModelScope.launch {
        delay(1500)
        done (true)
    }
}