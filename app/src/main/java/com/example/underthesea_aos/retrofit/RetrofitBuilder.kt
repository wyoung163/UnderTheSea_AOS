package com.example.underthesea_aos.retrofit

import android.os.Handler
import android.os.Looper
import com.example.underthesea_aos.user.MainActivity
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException


object RetrofitBuilder {
    var api: API

    init{
        val retrofit = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:3000/") //요청 보내는 api 서버 url
            .client(okHttpClient(AppInterceptor())) // okHttpClient를 Retrofit 빌더에 추가
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        api = retrofit.create(API::class.java)
    }

    fun okHttpClient(interceptor: AppInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(interceptor) // okHttp에 인터셉터 추가
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }).build()
    }

    class AppInterceptor : Interceptor {
        var jwtToken = ""
        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain) : Response = with(chain) {
            val mHandler = Handler(Looper.getMainLooper())
            mHandler.postDelayed(Runnable {
                jwtToken = MainActivity().jwtToken // ViewModel에서 지정한 key로 JWT 토큰을 가져온다.
            }, 0)
            val newRequest = request().newBuilder()
                .addHeader("Authorization", jwtToken) // 헤더에 authorization라는 key로 JWT 를 넣어준다.
                .build()
            proceed(newRequest)
        }
    }
}

/*
    addConverterFactory(GsonConverterFactory.create()): 데이터 파싱하는 converter, Json을 Gson 형태로 변환
 */