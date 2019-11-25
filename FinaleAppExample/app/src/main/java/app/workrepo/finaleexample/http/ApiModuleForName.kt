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
import java.util.concurrent.TimeUnit
import javax.inject.Named


@Module
class ApiModuleForName {

//    https://api.themoviedb.org/3/movie/top_rated?api_key=60a8582ce80d789855094e2f628bb855&language=en-US&page=1

    val BASE_URL = "https://api.themoviedb.org/3/movie/"
    val API_KEY = "60a8582ce80d789855094e2f628bb855"

    @Provides
    fun privideClient(
        interceptor: HttpLoggingInterceptor,
        interceptorChain: Interceptor
    ): OkHttpClient {
        val httpClient = OkHttpClient.Builder()
            .addNetworkInterceptor(interceptor)
            .addInterceptor(interceptorChain)

        httpClient.connectTimeout(60, TimeUnit.SECONDS)
        httpClient.readTimeout(60, TimeUnit.SECONDS)

        val client = httpClient.build()
        return client
    }

    @Provides
    fun provideInterceptor(): HttpLoggingInterceptor {
        val intercepto = HttpLoggingInterceptor()
        intercepto.level = HttpLoggingInterceptor.Level.BASIC
        return intercepto
    }

    @Provides
    fun provideInterceptorChain(): Interceptor {
        var interceptor = object : Interceptor {
            override fun intercept(chain: Interceptor.Chain): Response {
                var request = chain.request()

//                val requestWithHeaders = request.newBuilder().header("Authorization", "My_API_KEY")
//                    .build()

                val url = request.url.newBuilder().addQueryParameter("api_key", API_KEY).build()
                request = request.newBuilder()
//                    .header("Authorization", "My_API_KEY")
                    .url(url).build()
                return chain.proceed(request)
            }
        }
        return interceptor
    }

    @Provides
    @Named("retrofitApi")
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .client(client)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun MovieApiService(@Named("retrofitApi") retrofit: Retrofit): MovieApiService {
        return retrofit.create(MovieApiService::class.java)
    }

}