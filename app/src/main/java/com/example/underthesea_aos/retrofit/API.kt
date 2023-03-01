package com.example.underthesea_aos.retrofit

import com.example.underthesea_aos.kakaoLogIn.KakaoToken
import com.example.underthesea_aos.record.RecordInfo
import com.example.underthesea_aos.record.RecordResponse
import com.example.underthesea_aos.user.KakaoResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

public interface API {
    //kakao login
    @POST("login/kakao")
    fun postKakaoLoginResponse(@Body token: KakaoToken): Call<KakaoResponse>
    
    //post records
    @POST("records")
    fun postRecordsResponse(@Body record: RecordInfo): Call<RecordResponse>

    //get records
    @GET("records")
    fun getRecordsResponse(@Body record: RecordInfo): Call<String>
}