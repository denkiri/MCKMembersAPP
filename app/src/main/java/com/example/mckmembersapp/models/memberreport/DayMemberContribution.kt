package com.example.mckmembersapp.models.memberreport

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class DayMemberContribution {
    @SerializedName("day_contribution")
    @Expose
    var dayContribution:Double?= 0.00
}