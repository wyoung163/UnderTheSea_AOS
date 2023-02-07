package com.example.underthesea_aos.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {
    var api: API
    init{
        val retrofit = Retrofit.Builder()
            .baseUrl("http://localhost:8080/connection/") //요청 보내는 api 서버 url
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        api = retrofit.create(API::class.java)
    }
}

/*
    addConverterFactory(GsonConverterFactory.create()): 데이터 파싱하는 converter, Json을 Gson 형태로 변환
 */