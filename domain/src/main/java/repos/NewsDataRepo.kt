package repos

import com.ahmedroid.domain.entity.NewsEntity
import mapper.toDomainObject
import network.NewsDataService
import utils.NetworkHelper
import java.lang.Exception
import javax.inject.Inject

class NewsDataRepo @Inject constructor(
    private val newsApiService: NewsDataService,
    private val networkHelper: NetworkHelper
) {

    suspend fun getNewsWithFilters(filters: Map<String, String>): List<NewsEntity> {
        if (networkHelper.isNetworkConnected()) {
            newsApiService.getNewsWithFilters(filters).apply {
                return if (this.isSuccessful) {
                    this.body()?.newsList?.map { it.toDomainObject() } ?: listOf()
                } else {
                    throw Exception(this.message())
                }
            }
        } else {
            throw Exception("No Internet Connection")
        }
    }
}
