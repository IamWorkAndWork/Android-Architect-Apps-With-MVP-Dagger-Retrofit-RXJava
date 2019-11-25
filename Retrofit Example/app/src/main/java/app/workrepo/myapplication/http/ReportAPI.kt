package app.workrepo.myapplication.http

import app.workrepo.myapplication.http.Model.Report
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET

interface ReportAPI {

    @GET("webservice/service_report/rest/service_report/getAllReports")
    fun getReport(): Call<Report>

    @GET("webservice/service_report/rest/service_report/getAllReports")
    fun getReportRx(): Observable<Report>

    @GET("webservice/service_report/rest/service_report/getAllReports")
    suspend fun getReportCoroutine(): Report

}