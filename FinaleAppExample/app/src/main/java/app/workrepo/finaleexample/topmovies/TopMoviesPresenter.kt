package app.workrepo.finaleexample.topmovies

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class TopMoviesPresenter(private var model: TopMoviesActivityMVP.Model) :
    TopMoviesActivityMVP.Presenter {

    private lateinit var view: TopMoviesActivityMVP.View
    private lateinit var subscription: Disposable

    override fun loadData() {
        subscription = model.result()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                view?.let { view ->
                    view.updateData(it)
                }
            }, { t->
                t.printStackTrace()
                view?.let {
                    it.showSnackBar("Error getting movies : "+t)
                }
            })
    }

    override fun rxUnsubscribe() {
        subscription?.let {
            it.dispose()
        }
    }

    override fun setView(view: TopMoviesActivityMVP.View) {
        this.view = view
    }
}