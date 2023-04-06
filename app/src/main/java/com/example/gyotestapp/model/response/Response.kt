package com.example.gyotestapp.model.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
data class
MealsCategoriesResponse( val categories: List<MealResponse>) : Parcelable
@Parcelize
data class MealResponse(
    @SerializedName("strCategory")  val name: String,
    @SerializedName("strCategoryDescription")  val description: String,
    @SerializedName("strCategoryThumb")  val imageUrl: String,
    @SerializedName("idCategory")  val id: String
) : Parcelable