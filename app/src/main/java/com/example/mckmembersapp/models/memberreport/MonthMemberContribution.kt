package com.example.mckmembersapp.models.memberreport
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
class MonthMemberContribution {
    @SerializedName("month_contribution")
    @Expose
    var monthContribution:Double? = 0.00
}