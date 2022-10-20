package entities

import com.google.gson.annotations.SerializedName

data class NewsDataWrapperResponse(

    @SerializedName(value = "status")
    val status: String? = null,

    @SerializedName("results")
    val newsList: List<NewsDataResponse>? = null
)