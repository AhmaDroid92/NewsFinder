package entities

import com.google.gson.annotations.SerializedName

data class NewsApiResponse(
    @SerializedName(value = "author")
    val author: String? = null,

    @SerializedName(value = "title")
    val title: String? = null,

    @SerializedName(value = "description")
    val description: String? = null,

    @SerializedName(value = "source")
    val source: SourceResponse? = null,

    @SerializedName(value = "url")
    val url: String? = null,

    @SerializedName(value = "urlToImage")
    val urlToImage: String? = null,

    @SerializedName(value = "publishedAt")
    val publishedAt: String? = null,

    @SerializedName(value = "content")
    val content: String? = null
)

data class SourceResponse(
    @SerializedName("id")
    val id: String? = null,

    @SerializedName("name")
    val name: String? = null
)
