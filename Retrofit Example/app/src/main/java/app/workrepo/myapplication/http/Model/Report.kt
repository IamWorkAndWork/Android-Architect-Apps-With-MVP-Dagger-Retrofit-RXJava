package app.workrepo.myapplication.http.Model


import com.google.gson.annotations.SerializedName

data class Report(
    @SerializedName("items")
    var items: List<ReportItem?>?
)