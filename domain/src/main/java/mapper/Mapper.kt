package mapper

import com.ahmedroid.domain.entity.NewsEntity
import entities.NewsApiResponse
import entities.NewsDataResponse
import utils.toFormattedDate

fun NewsApiResponse.toDomainObject() = NewsEntity(
    title = title ?: "",
    author = author ?: "",
    imageUrl = urlToImage ?: "",
    newsUrl = url ?: "",
    publicationDate = publishedAt?.toFormattedDate("yyyy-MM-dd'T'HH:mm:ss'Z'") ?: ""
)

fun NewsDataResponse.toDomainObject() = NewsEntity(
    title = title ?: "",
    author = creator?.firstOrNull() ?: "",
    imageUrl = imageUrl ?: "",
    newsUrl = link ?: "",
    publicationDate = pubDate?.toFormattedDate("yyyy-MM-dd HH:mm:ss") ?: ""
)