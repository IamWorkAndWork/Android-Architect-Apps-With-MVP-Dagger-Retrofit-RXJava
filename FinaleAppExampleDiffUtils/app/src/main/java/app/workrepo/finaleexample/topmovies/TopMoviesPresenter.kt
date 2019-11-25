package app.workrepo.finaleexample.topmovies

import android.util.Log
import app.workrepo.finaleexample.http.MovieApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class TopMoviesPresenter(private var service: MovieApiService) :
    TopMoviesActivityMVP.Presenter {

    private lateinit var view: TopMoviesActivityMVP.View
    private val subscription: CompositeDisposable by lazy {
        CompositeDisposable()
    }

//    var page = 1;

    init {
//        view.setPage(1)
//        subscription = CompositeDisposable()
    }

    override fun loadData() {

        view?.let {
            view.setLoading(true)
        }

        Log.e("print","page = "+view.getPage())

        subscription.add(
            service.getTopRatedMovies(view.getPage())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnTerminate {
                    view.setLoading(false)
                    updatePage()
                }
                .subscribe({
                    view?.let { view ->
                        view.updateData(it.results)
                    }
                }, { t ->
                    t.printStackTrace()
                    view?.let {
                        it.showSnackBar("Error getting movies : " + t)
                    }
                })
        )
    }

    override fun detachView() {
        subscription?.let {
            it.clear()
        }
    }

    override fun attachView(view: TopMoviesActivityMVP.View) {
        this.view = view
    }

    override fun updatePage() {
        val page = view.getPage() + 1
        view.setPage(page)
    }
}