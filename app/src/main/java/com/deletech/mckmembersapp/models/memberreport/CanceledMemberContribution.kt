package com.deletech.mckmembersapp.models.memberreport
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
class CanceledMemberContribution {
    @SerializedName("canceled_contribution")
    @Expose
    var canceledContribution:Double? = 0.00
}