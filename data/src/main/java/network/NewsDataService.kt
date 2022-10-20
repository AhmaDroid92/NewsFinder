package network

import entities.NewsDataWrapperResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface NewsDataService {
    @GET("api/1/news")
    suspend fun getNewsWithFilters(
        @QueryMap queries: Map<String, String>? = null,
        @Query("apikey") apiKey: String = "pub_1508e9913474fcb85d810a8f1126e1a43e04",
    ): Response<NewsDataWrapperResponse>
}