package com.example.underthesea_aos.retrofit

import com.example.underthesea_aos.kakaoLogIn.KakaoToken
import com.example.underthesea_aos.record.RecordInfo
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

public interface API {
    //kakao login

    @POST("login/kakao")
    fun getKakaoLoginResponse(@Body token: KakaoToken): Call<KakaoResponse>
    
    //post records
    @POST("records")
    fun postRecordsResponse(@Body record: RecordInfo): Call<String>

    //get records
    @GET("records")
    fun getRecordsResponse(@Body record: RecordInfo): Call<String>
}