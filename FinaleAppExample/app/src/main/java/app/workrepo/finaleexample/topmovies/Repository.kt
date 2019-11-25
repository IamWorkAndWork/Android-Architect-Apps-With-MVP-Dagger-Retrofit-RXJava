package app.workrepo.finaleexample.topmovies

import app.workrepo.finaleexample.apiModel.Result
import io.reactivex.Observable

interface Repository {

    fun getResultsFromMemory(): Observable<Result>

    fun getResultsFromNetwork(): Observable<Result?>?

    fun getCountriesFromMemory(): Observable<String>

    fun getCountriesFromNetwork(): Observable<String?>?

    fun getCountryData(): Observable<String>

    fun getResultData(): Observable<Result>

}