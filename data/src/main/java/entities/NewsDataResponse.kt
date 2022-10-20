package entities

import com.google.gson.annotations.SerializedName

data class NewsDataResponse(
    @SerializedName(value = "id")
    val id: String? = null,

    @SerializedName(value = "title")
    val title: String? = null,

    @SerializedName(value = "link")
    val link: String? = null,

    @SerializedName(value = "creator")
    val creator: List<String>? = null,

    @SerializedName(value = "content")
    val content: String? = null,

    @SerializedName(value = "pubDate")
    val pubDate: String? = null,

    @SerializedName(value = "image_url")
    val imageUrl: String? = null
)