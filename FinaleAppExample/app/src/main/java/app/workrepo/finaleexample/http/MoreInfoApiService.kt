package app.workrepo.finaleexample.http

import app.workrepo.finaleexample.apiModel.OmdbApi
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface MoreInfoApiService {

    @GET("/")
    fun getCountry(@Query("t") title: String): Observable<OmdbApi>
}