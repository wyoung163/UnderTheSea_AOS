package com.example.underthesea_aos.user

import android.app.Application
import android.content.ContentValues
import android.util.Log
import com.example.underthesea_aos.R
import com.kakao.sdk.common.KakaoSdk
import com.kakao.sdk.common.util.Utility

class GlobalApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        //플랫폼 등록을 위한 해시값
        Log.d(ContentValues.TAG, "keyhash : ${Utility.getKeyHash(this)}")
        // 네이티브 앱 키로 초기화
        KakaoSdk.init(this, getString(R.string.KAKAO_LOGIN_API_KEY))
    }
}