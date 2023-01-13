package com.example.underthesea_aos.kakaoLogIn

class KakaoToken {
    var accessToken: String? = null
        get() { return field }
        set(value) { field = value }
    var refreshToken: String? = null
        get() { return field }
        set(value) { field = value }
    var accessExpiration: String? = null
        get() { return field }
        set(value) { field = value }
    var refreshExpiration: String? = null
        get() { return field }
        set(value) { field = value }
}