package com.abdiel.e_commerce.ui.checkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.abdiel.e_commerce.R
import dagger.hilt.android.AndroidEntryPoint

class CheckoutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout)
    }
}