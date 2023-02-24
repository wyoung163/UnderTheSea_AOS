package com.example.underthesea_aos.retrofit

import com.example.underthesea_aos.kakaoLogIn.KakaoToken
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

public interface API {
    //kakao login
    @POST("login/kakao")
    fun getKakaoLoginResponse(@Body token: KakaoToken): Call<String>
}