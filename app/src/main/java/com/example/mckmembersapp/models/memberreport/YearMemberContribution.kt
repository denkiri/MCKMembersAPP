package com.example.mckmembersapp.models.memberreport
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
class YearMemberContribution {
    @SerializedName("year_contribution")
    @Expose
    var yearContribution:Double? = 0.00
}