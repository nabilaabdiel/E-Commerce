package com.abdiel.e_commerce.ui.splash

import android.os.Bundle
import com.abdiel.e_commerce.R
import com.abdiel.e_commerce.base.activity.BaseActivity
import com.abdiel.e_commerce.data.constant.Const
import com.abdiel.e_commerce.databinding.ActivitySplashBinding
import com.abdiel.e_commerce.session.Session
import com.abdiel.e_commerce.ui.home.HomeActivity
import com.abdiel.e_commerce.ui.login.LoginActivity
import com.crocodic.core.data.CoreSession
import com.crocodic.core.extension.openActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SplashActivity : BaseActivity<ActivitySplashBinding, SplashViewModel>(R.layout.activity_splash) {

    @Inject
    lateinit var coreSession: CoreSession

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val userLogin = coreSession.getString(Const.USER.PROFILE)

        viewModel.splash {
            if (userLogin == "success") {
                openActivity<HomeActivity>()
            } else {
                openActivity<LoginActivity>()
            }
            finish()
        }
    }
}