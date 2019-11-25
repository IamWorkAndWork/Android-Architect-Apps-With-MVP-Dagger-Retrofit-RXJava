package app.workrepo.myapplication.http.Model


import com.google.gson.annotations.SerializedName

data class ReportItem(
    @SerializedName("app")
    var app: String?,
    @SerializedName("channel_id")
    var channelId: String?,
    @SerializedName("channel_name")
    var channelName: String?,
    @SerializedName("channel_type")
    var channelType: String?,
    @SerializedName("date_report")
    var dateReport: String?,
    @SerializedName("dupdate")
    var dupdate: String?,
    @SerializedName("id")
    var id: String?,
    @SerializedName("message")
    var message: String?,
    @SerializedName("more")
    var more: String?,
    @SerializedName("more2")
    var more2: String?,
    @SerializedName("username")
    var username: String?
)