package app.workrepo.finaleexample.http

import app.workrepo.finaleexample.apiModel.TopRated
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApiService {

    @GET("top_rated")
    fun getTopRatedMovies(@Query("page") page: Int): Observable<TopRated>

}