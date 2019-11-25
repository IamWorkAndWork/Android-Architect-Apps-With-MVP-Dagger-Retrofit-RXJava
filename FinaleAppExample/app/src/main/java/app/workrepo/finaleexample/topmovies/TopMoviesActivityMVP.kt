package app.workrepo.finaleexample.topmovies

import io.reactivex.Observable

interface TopMoviesActivityMVP {


    interface View {

        fun updateData(viewModel: ViewModel)

        fun showSnackBar(s: String)

    }

    interface Presenter {

        fun loadData()

        fun rxUnsubscribe()

        fun setView(view: TopMoviesActivityMVP.View)

    }

    interface Model {

        fun result(): Observable<ViewModel>

    }

}