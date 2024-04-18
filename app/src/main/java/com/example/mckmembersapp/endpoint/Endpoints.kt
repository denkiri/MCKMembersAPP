package com.example.mckmembersapp.endpoint
import com.example.mckmembersapp.models.auth.ProfileData
import com.example.mckmembersapp.models.memberreport.MemberReportData
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import javax.inject.Singleton
@Singleton
interface Endpoints {
    @FormUrlEncoded
    @POST("Auth/signinMember.php")
    fun signIn(@Field("mobile_number") mobileNumber: String?, @Field("password") password: String?): Call<ProfileData>
    @FormUrlEncoded
    @POST("member/report.php")
    fun memberReport(@Field("member_id") memberId: String?,@Field("church_id") churchId: String?): Call<MemberReportData>
    @FormUrlEncoded
    @POST("member/customMemberReport.php")
    fun customMemberReport(@Field("member_id") memberId: String?,@Field("day") day: String?,@Field("month") month: String?,@Field("year") year: String?,@Field("church_id") churchId: String?): Call<MemberReportData>
}