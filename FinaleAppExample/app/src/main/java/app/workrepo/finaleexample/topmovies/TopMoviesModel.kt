package app.workrepo.finaleexample.topmovies

import app.workrepo.finaleexample.apiModel.Result
import io.reactivex.Observable
import io.reactivex.functions.BiFunction

class TopMoviesModel(private var repository: Repository) : TopMoviesActivityMVP.Model {

    override fun result(): Observable<ViewModel> {
        return Observable.zip(
            repository.getResultData(),
            repository.getCountryData(),
            object : BiFunction<Result, String, ViewModel> {
                override fun apply(t1: Result, t2: String): ViewModel {
                    return ViewModel(t1.title, t2)
                }
            }
        )
    }

}