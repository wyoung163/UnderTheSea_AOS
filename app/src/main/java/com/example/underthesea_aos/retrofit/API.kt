package com.example.underthesea_aos.retrofit

import com.example.underthesea_aos.kakaoLogIn.KakaoToken
import com.example.underthesea_aos.plan.GetPlanRes
import com.example.underthesea_aos.record.PostRecordRes
import com.example.underthesea_aos.record.RecordInfo
import com.example.underthesea_aos.user.KakaoResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

public interface API {
    //kakao login
    @POST("login/kakao")
    fun postKakaoLoginResponse(@Body token: KakaoToken): Call<KakaoResponse>
    
    //post records
    @POST("records")
    fun postRecordsResponse(
        //@Header("Authorization") jwtToken: String,
        @Body record: RecordInfo
    ): Call<PostRecordRes>

    //get records
    @GET("records")
    fun getRecordsResponse(
        //@Header("Authorization") jwtToken: String,
        @Query("date") date: String
    ): Call<String>

    //get plans
    @GET("plans")
    fun getPlansResponse(
        //@Header("Authorization") jwtToken: String,
        @Query("date") date: String
    ): Call<List<GetPlanRes>>

    //get plan
    @GET("plan")
    fun getPlanResponse(
        //@Header("Authorization") jwtToken: String,
        @Query("plan_id") plan_id: Long
    ): Call<GetPlanRes>
}