package network

import entities.NewsApiWrapperResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface NewsApiService {
    // TODO convert to querymap
    @GET("v2/top-headlines")
    suspend fun getNewsWithFilters(
        @QueryMap queries: Map<String, String>? = null
    ): Response<NewsApiWrapperResponse>
}
