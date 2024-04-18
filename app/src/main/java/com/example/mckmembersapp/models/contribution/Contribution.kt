package com.example.mckmembersapp.models.contribution
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
class Contribution {
    @SerializedName("contribution_id")
    @Expose
    var contributionId:Int? = 0

    @SerializedName("member_id")
    @Expose
    var memberId:Int? = 0

    @SerializedName("amount")
    @Expose
    var amount: Double? =0.00

    @SerializedName("church_id")
    @Expose
    var churchId:Int? = 0

    @SerializedName("receipt_number")
    @Expose
    var receiptNumber: String? = null

    @SerializedName("payment_type")
    @Expose
    var paymentType:Int? = 0

    @SerializedName("payment_status")
    @Expose
    var paymentStatus:Int? = 0

    @SerializedName("time")
    @Expose
    var time: String? = null

    @SerializedName("date")
    @Expose
    var date: String? = null

    @SerializedName("month")
    @Expose
    var month: String? = null

    @SerializedName("year")
    @Expose
    var year: String? = null

    @SerializedName("staff_id")
    @Expose
    var staffId:Int?= 0

    @SerializedName("status")
    @Expose
    var status: String? = null

    @SerializedName("type_value")
    @Expose
    var typeValue: String? = null

    @SerializedName("first_name")
    @Expose
    var firstName: String? = null

    @SerializedName("second_name")
    @Expose
    var secondName: String? = null

    @SerializedName("surname")
    @Expose
    var surname: String? = null
    @SerializedName("registration_id")
    @Expose
    var registrationId:Int = 0
    @SerializedName("staff_first_name")
    @Expose
    var staffFirstName: String? = null
    @SerializedName("staff_last_name")
    @Expose
    var staffLastName: String? = null
}