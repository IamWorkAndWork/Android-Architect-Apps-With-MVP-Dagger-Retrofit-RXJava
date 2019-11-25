package app.workrepo.finaleexample.topmovies

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import app.workrepo.finaleexample.R
import app.workrepo.finaleexample.root.App
import kotlinx.android.synthetic.main.activity_top_movies.*
import javax.inject.Inject

class TopMoviesActivity : AppCompatActivity(), TopMoviesActivityMVP.View {

    private val TAG by lazy {
       "TopMoviesActivity"
    }

    @Inject
    lateinit var presenter: TopMoviesActivityMVP.Presenter

    var resultList: MutableList<ViewModel>? = arrayListOf()
    lateinit var adapter: ListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_top_movies)

        (application as App).getComponent().inject(this)

        adapter = ListAdapter(resultList!!)

        val mLayoutManager = LinearLayoutManager(this)
        val dividerItemDecoration = DividerItemDecoration(this, mLayoutManager.orientation)

        recView.apply {
            this.adapter = this@TopMoviesActivity.adapter
            addItemDecoration(dividerItemDecoration)
            itemAnimator = DefaultItemAnimator()
            setHasFixedSize(true)
            this.layoutManager = mLayoutManager
        }

    }

    override fun onStart() {
        super.onStart()

        presenter.setView(this)
        presenter.loadData()

    }

    override fun onStop() {
        super.onStop()

        presenter.rxUnsubscribe()
        resultList?.clear()
        adapter.notifyDataSetChanged()

    }

    override fun updateData(viewModel: ViewModel) {

        Log.e(TAG, "update data = " + viewModel.toString())
        resultList?.add(viewModel)
        adapter?.notifyDataSetChanged()

    }

    override fun showSnackBar(s: String) {
        Log.e(TAG, "showSnackBar data = " + s.toString())
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show()

    }
}
