package app.workrepo.myapplication

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import app.workrepo.myapplication.http.Model.Report
import app.workrepo.myapplication.http.ReportAPI
import app.workrepo.myapplication.root.App
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject


class MainActivity : AppCompatActivity() {

    private var callAPI: Disposable? = null
    @Inject
    lateinit var reportAPI: ReportAPI

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        (application as App).getComponent().inject(this)


//        callWithRetrofitAsync()
//
//        callWithRX()
//
//        callWithCoroutine()

        callWithRxAdvance()


    }

    private fun callWithRxAdvance() {

        reportAPI.getReportRx().flatMap {
            Observable.fromCallable {
                it.items
            }
        }.flatMap {
            Observable.just(it.map {
                it?.channelName
            })
        }
//            .filter {
//                true
//            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.d("print", "body data callWithRxAdvance = " + it.toString())

            }, {

            }, {

            })


    }

    private fun callWithCoroutine() {

        val data = CoroutineScope(Dispatchers.Main).launch {

            try {

                //way1
//                val data = withContext(Dispatchers.IO) {
//                    reportAPI.getReportCoroutine()
//                }

                //way2
//                val data = reportAPI.getReportCoroutine()

                //way3
                val dataCoroutine = async {
                    reportAPI.getReportCoroutine()
                }
                val data = dataCoroutine.await()

                Log.e("print", "body data callWithCoroutine = " + data.items?.size)
            } catch (er: Exception) {
                Log.e("print", "callWithCoroutine error = " + er);
            }
        }

    }

    private fun callWithRetrofitAsync() {

        val call = reportAPI.getReport()
        call.enqueue(object : Callback<Report> {
            override fun onFailure(call: Call<Report>, t: Throwable) {

                Log.e("print", "error report api = " + t)

            }

            override fun onResponse(call: Call<Report>, response: Response<Report>) {

                if (response.isSuccessful) {
                    val reportList = response.body()?.items
                    Log.e(
                        "print",
                        "body data callWithRetrofitAsync = " + response.body()?.items?.size
                    )
                } else {
                    Log.e("print", "body failed = " + response.raw())
                }

            }
        })

    }

    private fun callWithRX() {

        callAPI = reportAPI.getReportRx()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({

                it.items?.let {
                    Log.e("print", "body data callWithRX = " + it.size)
                }

            }, { er ->
                Log.e("print", "error report getReportReact = " + er)

            })

    }

    override fun onDestroy() {
        super.onDestroy()
        callAPI?.dispose()
    }
}
