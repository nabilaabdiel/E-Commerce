package com.abdiel.e_commerce.data.slider


import com.google.gson.annotations.SerializedName

data class ImageSlider(
    @SerializedName("created_at")
    val createdAt: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("image")
    val image: String?,
    @SerializedName("product_id")
    val productId: Int?,
    @SerializedName("updated_at")
    val updatedAt: String?
)