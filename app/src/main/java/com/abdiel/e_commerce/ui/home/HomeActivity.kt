package com.abdiel.e_commerce.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.SearchView
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.abdiel.e_commerce.R
import com.abdiel.e_commerce.base.activity.BaseActivity
import com.abdiel.e_commerce.data.constant.Const
import com.abdiel.e_commerce.data.product.Product
import com.abdiel.e_commerce.databinding.ActivityHomeBinding
import com.abdiel.e_commerce.databinding.ItemHomeBinding
import com.abdiel.e_commerce.session.Session
import com.abdiel.e_commerce.ui.cart.CartActivity
import com.abdiel.e_commerce.ui.detail.DetailActivity
import com.abdiel.e_commerce.ui.profile.ProfileActivity
import com.crocodic.core.api.ApiStatus
import com.crocodic.core.base.adapter.CoreListAdapter
import com.crocodic.core.base.adapter.ReactiveListAdapter
import com.crocodic.core.extension.openActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class HomeActivity : BaseActivity<ActivityHomeBinding, HomeViewModel>(R.layout.activity_home),
    SearchView.OnQueryTextListener {

    @Inject
    lateinit var session: Session

    private var listProductHome = ArrayList<Product?>()
    private var listProduct = ArrayList<Product?>()

    private val adapter by lazy {
        ReactiveListAdapter<ItemHomeBinding, Product>(R.layout.item_home)
            .initItem { position, data ->
                openActivity<DetailActivity> {
                    putExtra(Const.LIST.LIST_PRODUCT, data)
                }
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        observe()

        viewModel.getProfile()

        binding.btnCart.setOnClickListener {
            openActivity<CartActivity>()
        }

        binding.ivProfile.setOnClickListener {
            openActivity<ProfileActivity>()
        }

        //Search
        binding.svHome.setOnQueryTextListener(this)

        binding.rvHome.adapter = adapter

        viewModel.getAllProduct()

    }
    private fun observe() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {

                launch {
                    viewModel.apiResponse.collect {
                        when (it.status) {
                            ApiStatus.SUCCESS -> {
                                getUser()
                            }
                            else -> loadingDialog.setResponse(it.message ?: return@collect)
                        }
                    }
                }

                launch {
                    viewModel.listAllProduct.collect {
                        listProductHome.clear()
                        listProductHome.addAll(it)
                        adapter.submitList(listProductHome)
                        binding.swipeRefresh.isRefreshing = false
//                        binding.tvEmpty.isVisible = it.isEmpty()
                    }
                }
            }
        }
    }

    //data user
    private fun getUser() {
        val users = session.getUser()
        binding.data = users
    }

//    private fun getAll() {
//        viewModel.getAllProduct()
//    }

    //Search-end
    override fun onQueryTextSubmit(p0: String?): Boolean {
        return true
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onQueryTextChange(text: String?): Boolean {
        if (text.isNullOrEmpty()) {
            listProduct.clear()
            binding.rvHome.adapter?.notifyDataSetChanged()
            listProduct.addAll(listProductHome)
            binding.rvHome.adapter?.notifyItemInserted(0)
//            adapter.submitList(listProduct)
//            binding.tvEmptyRecycle.isVisible = listProduct.isEmpty()

        } else {

            val filter = listProductHome.filter { it?.name?.contains("$text", true) == true }
            listProduct.clear()
            filter.forEach {
                listProduct.add(it)
            }
//            listProduct.addAll(filter)
            binding.rvHome.adapter?.notifyDataSetChanged()
            binding.rvHome.adapter?.notifyItemInserted(0)

            adapter.submitList(listProduct)
//            binding.tvEmptyRecycle.isVisible = listProduct.isEmpty()

        }
        if (listProduct.isEmpty()) {
            binding.tvEmptyRecycle.visibility = View.VISIBLE
        } else {
            binding.tvEmptyRecycle.visibility = View.GONE
        }
        return false
    }
}