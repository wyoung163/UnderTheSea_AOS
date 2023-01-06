package com.example.underthesea_aos.retrofit

import com.example.underthesea_aos.kakaoLogIn.KakaoToken
import retrofit2.Call
import retrofit2.http.*

public interface API {
    //kakao login
    @POST("kakao")
    fun getKakaoLoginResponse(@Body token: KakaoToken): Call<String>
}