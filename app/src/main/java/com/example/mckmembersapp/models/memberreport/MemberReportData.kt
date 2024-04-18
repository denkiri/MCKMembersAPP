package com.example.mckmembersapp.models.memberreport
import com.example.mckmembersapp.models.contribution.Contribution
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class MemberReportData {
    @SerializedName("error")
    @Expose
    var error = false
    @SerializedName("status")
    @Expose
    var status:Int? = 0
    @SerializedName("contribution")
    @Expose
    var contribution: List<Contribution>? = null
    @SerializedName("dayContribution")
    @Expose
    var dayContribution: DayMemberContribution? = null

    @SerializedName("monthContribution")
    @Expose
    var monthContribution: MonthMemberContribution? = null

    @SerializedName("yearContribution")
    @Expose
    var yearContribution: YearMemberContribution? = null

    @SerializedName("canceledContribution")
    @Expose
    var canceledContribution: CanceledMemberContribution? = null
    @SerializedName("message")
    @Expose
    var message: String? = null
}