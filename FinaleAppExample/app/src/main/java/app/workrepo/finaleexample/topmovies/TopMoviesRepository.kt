package app.workrepo.finaleexample.topmovies

import app.workrepo.finaleexample.apiModel.OmdbApi
import app.workrepo.finaleexample.apiModel.Result
import app.workrepo.finaleexample.apiModel.TopRated
import app.workrepo.finaleexample.http.MoreInfoApiService
import app.workrepo.finaleexample.http.MovieApiService
import io.reactivex.Observable

class TopMoviesRepository : Repository {

    private var movieApiService: MovieApiService
    private var moreInfoApiService: MoreInfoApiService
    private var countries: MutableList<String>? = arrayListOf()
    private var results: MutableList<Result>? = arrayListOf()
    private var timestamp: Long = 0
    private val STALE_MS = 20 * 1000 // Data is stale after 20 seconds
        .toLong()

    constructor(movieApiService: MovieApiService, moreInfoApiService: MoreInfoApiService) {
        this.movieApiService = movieApiService
        this.moreInfoApiService = moreInfoApiService
        this.timestamp = System.currentTimeMillis()
    }

    fun isUpToDate(): Boolean {
        return System.currentTimeMillis() - timestamp < STALE_MS
    }

    override fun getResultsFromMemory(): Observable<Result> {
        if (isUpToDate()) {
            return Observable.fromIterable(results)
        } else {
            timestamp = System.currentTimeMillis()
            results?.clear()
            return Observable.empty()
        }
    }

    override fun getResultsFromNetwork(): Observable<Result?>? {
        val topRatedObservable =
            movieApiService.getTopRatedMovies(1).concatWith(movieApiService.getTopRatedMovies(2))
                .concatWith(movieApiService.getTopRatedMovies(3))

        return topRatedObservable.concatMap { topRated: TopRated ->
            Observable.fromIterable(topRated.results)
        }.doOnNext { result: Result? ->
            if (result != null) {
                results?.add(result)
            }
        }
    }

    override fun getCountriesFromMemory(): Observable<String> {
        if (isUpToDate()) {
            return Observable.fromIterable(countries)
        } else {
            timestamp = System.currentTimeMillis()
            countries?.clear()
            return Observable.empty()
        }
    }

    override fun getCountriesFromNetwork(): Observable<String?>? {

//        Log.e("print","call getCountriesFromNetwork")

        return getResultsFromNetwork()?.concatMap { result: Result ->
            result.title?.let { moreInfoApiService.getCountry(it) }
        }?.concatMap { result: OmdbApi ->
            Observable.just(result.country)
        }
            ?.doOnNext { result: String? ->
                result?.let { countries?.add(it) }
            }
    }

    override fun getCountryData(): Observable<String> {
        return getCountriesFromMemory()?.switchIfEmpty(getCountriesFromNetwork())
    }

    override fun getResultData(): Observable<Result> {
        return getResultsFromMemory().switchIfEmpty(getResultsFromNetwork())
    }
}