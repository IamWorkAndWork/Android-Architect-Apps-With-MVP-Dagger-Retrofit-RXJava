package app.workrepo.finaleexample.topmovies

import app.workrepo.finaleexample.http.apiModel.Result

interface TopMoviesActivityMVP {


    interface View {

        fun updateData(result: MutableList<Result?>?)
        fun showSnackBar(s: String)
        fun setLoading(b: Boolean)
        fun getPage(): Int
        fun setPage(page: Int)

    }

    interface Presenter {

        fun loadData()

        fun detachView()

        fun attachView(view: TopMoviesActivityMVP.View)

        fun updatePage()

    }


}