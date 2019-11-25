package app.workrepo.finaleexample.http

import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import javax.inject.Named
import javax.inject.Singleton

@Module
class ApiModuleForInfo {


    @Provides
    @Singleton
    @Named("baseOmdb")
    fun BASE_URLI(): String = "https://www.omdbapi.com"

    val API_KEY = "4f1016c2"

    @Provides
    @Named("clientOmdb")
    fun provideClient(): OkHttpClient? {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder().addInterceptor(interceptor)
            .addInterceptor(object : Interceptor {
                @Throws(IOException::class)
                override fun intercept(chain: Interceptor.Chain): Response {
                    var request = chain.request()
                    val url = request.url.newBuilder().addQueryParameter(
                        "apikey",
                        API_KEY
                    ).build()
                    request = request.newBuilder().url(url).build()
                    return chain.proceed(request)
                }
            }).build()
    }

    @Provides
    @Named("retrofitObdb")
    fun provideRetrofit(@Named("baseOmdb") baseURL: String?, @Named("clientOmdb") client: OkHttpClient?): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseURL)
            .client(client)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideApiService(@Named("retrofitObdb") retrofit: Retrofit): MoreInfoApiService {
        return retrofit.create(
            MoreInfoApiService::class.java
        )
    }

}