package com.abdiel.e_commerce.ui.cart

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.abdiel.e_commerce.R
import com.abdiel.e_commerce.base.activity.BaseActivity
import com.abdiel.e_commerce.databinding.ActivityProfileBinding
import com.abdiel.e_commerce.ui.profile.ProfileViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CartActivity : BaseActivity<ActivityProfileBinding, ProfileViewModel>(R.layout.activity_cart) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}