package com.abdiel.e_commerce.ui.update

import android.os.Bundle
import com.abdiel.e_commerce.R
import com.abdiel.e_commerce.base.activity.BaseActivity
import com.abdiel.e_commerce.databinding.ActivityUpdateProfileBinding
import com.abdiel.e_commerce.session.Session
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class UpdateProfileActivity : BaseActivity<ActivityUpdateProfileBinding, UpdateProfileViewModel>(R.layout.activity_update_profile) {

    @Inject
    lateinit var session: Session

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getUser()
    }

    //data user
    private fun getUser() {
        val users = session.getUser()
        binding.user = users
    }
}