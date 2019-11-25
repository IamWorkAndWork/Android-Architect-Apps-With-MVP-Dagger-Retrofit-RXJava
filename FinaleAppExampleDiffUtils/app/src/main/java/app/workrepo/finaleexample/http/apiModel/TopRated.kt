package app.workrepo.finaleexample.http.apiModel


import com.google.gson.annotations.SerializedName

data class TopRated(
    @SerializedName("page")
    var page: Int?,
    @SerializedName("results")
    var results: MutableList<Result?>?,
    @SerializedName("total_pages")
    var totalPages: Int?,
    @SerializedName("total_results")
    var totalResults: Int?
)