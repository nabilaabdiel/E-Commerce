package com.abdiel.e_commerce.ui.login

import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.abdiel.e_commerce.R
import com.abdiel.e_commerce.base.activity.BaseActivity
import com.abdiel.e_commerce.databinding.ActivityLoginBinding
import com.abdiel.e_commerce.ui.home.HomeActivity
import com.crocodic.core.api.ApiStatus
import com.crocodic.core.extension.isEmptyRequired
import com.crocodic.core.extension.openActivity
import com.crocodic.core.extension.textOf
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginActivity : BaseActivity<ActivityLoginBinding, LoginViewModel>(R.layout.activity_login) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.btnLogin.setOnClickListener {
            if (binding.etPhoneNumber.isEmptyRequired(R.string.label_must_fill) ||
                binding.etPassword.isEmptyRequired(R.string.label_must_fill)
            ) {
                return@setOnClickListener
            }

            val phoneNumber = binding.etPhoneNumber.textOf()
            val password = binding.etPassword.textOf()

            viewModel.login(phoneNumber, password)
        }

        lifecycleScope.launch {
            viewModel.apiResponse.collect {
                when (it.status) {
                    ApiStatus.LOADING -> loadingDialog.show("Logging in...")
                    ApiStatus.SUCCESS -> {
                        loadingDialog.dismiss()
                        openActivity<HomeActivity>()
                        finish()
                    }
                    else -> loadingDialog.setResponse(it.message ?: return@collect)
                }
            }
        }
    }
}