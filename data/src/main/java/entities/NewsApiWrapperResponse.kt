package entities

import com.google.gson.annotations.SerializedName

data class NewsApiWrapperResponse(
    @SerializedName(value = "status")
    val status: String? = null,

    @SerializedName(value = "totalResults")
    val totalResults : Int? = null,

    @SerializedName("articles")
    val newsList: List<NewsApiResponse>? = null
)
