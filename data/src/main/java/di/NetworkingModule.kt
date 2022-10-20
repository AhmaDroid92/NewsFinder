package di

import com.ahmedroid.data.BuildConfig
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import network.NewsApiService
import network.NewsDataService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkingModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        return OkHttpClient.Builder().apply {
            addInterceptor(loggingInterceptor)
            addInterceptor(
                Interceptor { chain ->
                    val builder = chain.request().newBuilder()
                    builder.header("X-Api-Key", "e6ae6a83e0214b319a12478a9d029408")
                    return@Interceptor chain.proceed(builder.build())
                }
            )
        }.build()
    }

    @Singleton
    @Provides
    @Named("NewsApiClient")
    fun provideNewsApiRetrofitClient(
        gson: Gson, okHttpClient: OkHttpClient,
    ): Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.NEWS_API_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    @Singleton
    @Provides
    @Named("NewsDataClient")
    fun provideNewsDataRetrofitClient(
        gson: Gson, okHttpClient: OkHttpClient,
    ): Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.NEWS_DATA_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    fun provideNewsApiService(@Named("NewsApiClient") retrofit: Retrofit): NewsApiService =
        retrofit.create(NewsApiService::class.java)

    @Provides
    fun provideNewsDataService(@Named("NewsDataClient") retrofit: Retrofit): NewsDataService =
        retrofit.create(NewsDataService::class.java)
}
