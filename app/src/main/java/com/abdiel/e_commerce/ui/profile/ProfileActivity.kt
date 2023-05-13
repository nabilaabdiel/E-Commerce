package com.abdiel.e_commerce.ui.profile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.abdiel.e_commerce.R
import com.abdiel.e_commerce.base.activity.BaseActivity
import com.abdiel.e_commerce.databinding.ActivityHomeBinding
import com.abdiel.e_commerce.databinding.ActivityProfileBinding
import com.abdiel.e_commerce.session.Session
import com.abdiel.e_commerce.ui.home.HomeViewModel
import com.abdiel.e_commerce.ui.login.LoginActivity
import com.abdiel.e_commerce.ui.update.UpdateProfileActivity
import com.crocodic.core.api.ApiStatus
import com.crocodic.core.extension.createIntent
import com.crocodic.core.extension.openActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class ProfileActivity : BaseActivity<ActivityProfileBinding, ProfileViewModel>(R.layout.activity_profile) {

    @Inject
    lateinit var session: Session

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getUser()

        //btn logout
        binding.btnLogout.setOnClickListener {
            viewModel.logout()
        }

        observe()

        //btn edit profile
        binding.btnEditProfile.setOnClickListener {

            //get data
            activityLauncher.launch(createIntent<UpdateProfileActivity>()) {
                getUser()
            }
        }
    }

    //data user
    private fun getUser() {
        val users = session.getUser()
        binding.data = users
        Log.d("apa?", "user:$users")
    }

    private fun observe() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.apiResponse.collect { logout ->
                        if (logout.status == ApiStatus.SUCCESS) {
                            openActivity<LoginActivity>()
                            finishAffinity()
                        }
                    }
                }
            }
        }
    }
}