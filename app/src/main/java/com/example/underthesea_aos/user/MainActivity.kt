package com.example.underthesea_aos.user

import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.underthesea_aos.R
import com.example.underthesea_aos.googleLogin.SecondActivity
import com.example.underthesea_aos.kakaoLogIn.KakaoToken
import com.example.underthesea_aos.retrofit.RetrofitBuilder
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.AuthErrorCause
import com.kakao.sdk.user.UserApiClient
import kotlinx.android.synthetic.main.activity_signin.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var auth : FirebaseAuth
    private lateinit var googleSignInClient : GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)

        /*
        // 로그인 정보 확인
        UserApiClient.instance.accessTokenInfo { tokenInfo, error ->
            if (error != null) {
                Toast.makeText(this, "토큰 정보 보기 실패", Toast.LENGTH_SHORT).show()
            }
            else if (tokenInfo != null) {
                Toast.makeText(this, "토큰 정보 보기 성공", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, SecondActivity::class.java)
                startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
                finish()
            }
        }
         */

        //백엔드와의 통신 성공 or 실패
        fun Login(token: KakaoToken){
            Log.d("Response: ", "ok")
            val call = RetrofitBuilder.api.getKakaoLoginResponse(token)
            //비동기 방식의 통신
            call.enqueue(object : Callback<String> {
                //통신 성공
                override fun onResponse(call: Call<String>, response: Response<String>) {
                    //응답 성공
                    if(response.isSuccessful()){
                        Log.d("Response: ", response.body().toString())
                    }
                    //응답 실패
                    else{
                        Log.d("Response: ", "failure")
                    }
                }
                //통신 실패
                override fun onFailure(call: Call<String>, t: Throwable) {
                    Log.d("Connection Failure", t.localizedMessage)
                }
            })
        }

        //accesstoken(, refreshtoken) 발급 과정 성공 or 실패
        val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
            //발급 실패
            if (error != null) {
                when {
                    error.toString() == AuthErrorCause.AccessDenied.toString() -> {
                        Toast.makeText(this, "접근이 거부 됨(동의 취소)", Toast.LENGTH_SHORT).show()
                    }
                    error.toString() == AuthErrorCause.InvalidClient.toString() -> {
                        Toast.makeText(this, "유효하지 않은 앱", Toast.LENGTH_SHORT).show()
                    }
                    error.toString() == AuthErrorCause.InvalidGrant.toString() -> {
                        Toast.makeText(this, "인증 수단이 유효하지 않아 인증할 수 없는 상태", Toast.LENGTH_SHORT).show()
                    }
                    error.toString() == AuthErrorCause.InvalidRequest.toString() -> {
                        Toast.makeText(this, "요청 파라미터 오류", Toast.LENGTH_SHORT).show()
                    }
                    error.toString() == AuthErrorCause.InvalidScope.toString() -> {
                        Toast.makeText(this, "유효하지 않은 scope ID", Toast.LENGTH_SHORT).show()
                    }
                    error.toString() == AuthErrorCause.Misconfigured.toString() -> {
                        Toast.makeText(this, "설정이 올바르지 않음(android key hash)", Toast.LENGTH_SHORT).show()
                    }
                    error.toString() == AuthErrorCause.ServerError.toString() -> {
                        Toast.makeText(this, "서버 내부 에러", Toast.LENGTH_SHORT).show()
                    }
                    error.toString() == AuthErrorCause.Unauthorized.toString() -> {
                        Toast.makeText(this, "앱이 요청 권한이 없음", Toast.LENGTH_SHORT).show()
                    }
                    else -> { // Unknown
                        Toast.makeText(this, "기타 에러", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            //발급 성공
            else if (token != null) {
                Log.d(ContentValues.TAG, "token : ${token}")

                Toast.makeText(this, "로그인에 성공하였습니다.", Toast.LENGTH_SHORT).show()
                //val intent = Intent(this, SecondActivity::class.java)
                //startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
                //finish()

                val kakaoToken = KakaoToken()
                kakaoToken.accessToken = token.accessToken.toString()
                kakaoToken.refreshToken = token.refreshToken.toString()
                kakaoToken.accessExpiration = token.accessTokenExpiresAt.toString()
                kakaoToken.refreshExpiration = token.refreshTokenExpiresAt.toString()
                Login(kakaoToken)
            }
        }

        //로그인 버튼 클릭 동작
        val kakao_login_button = findViewById<ImageButton>(R.id.kakao_login_button) // 로그인 버튼
        kakao_login_button.setOnClickListener {
            if(UserApiClient.instance.isKakaoTalkLoginAvailable(this)){
                UserApiClient.instance.loginWithKakaoTalk(this, callback = callback)
            }else{
                UserApiClient.instance.loginWithKakaoAccount(this, callback = callback)
            }
        }
        
        
        //google
        auth = FirebaseAuth.getInstance()
//        Log.d("auth: ", auth.toString())

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
//        Log.d("gso: ", gso.toString())

        googleSignInClient = GoogleSignIn.getClient(this, gso)

        findViewById<Button>(R.id.SignInBtn).setOnClickListener {
            signInGoogle()
        }
    }

    private fun signInGoogle() {
        val signInIntent = googleSignInClient.signInIntent
        launcher.launch(signInIntent)
    }

    private val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
//                    Log.d("task: ", task.toString())
            handleResults(task)
        }
    }

    private fun handleResults(task: Task<GoogleSignInAccount>) {
        if (task.isSuccessful) {
            val account: GoogleSignInAccount? = task.result
//            Log.d("account: ", account.toString())
            if (account != null) {
                updateUI(account)
            }
        } else {
            Toast.makeText(this, task.exception.toString(), Toast.LENGTH_SHORT).show()
        }
    }

    private fun updateUI(account: GoogleSignInAccount) {
        val credential = GoogleAuthProvider.getCredential((account).idToken, null)
//        Log.d("idToken: ", (account).idToken.toString())
        auth.signInWithCredential(credential).addOnCompleteListener {
            if (it.isSuccessful) {//로그인 성공 시
                val intent: Intent = Intent(this, SecondActivity::class.java)
                intent.putExtra("email", account.email)
                intent.putExtra("name", account.displayName)
                startActivity(intent)
//                Log.d("loginSuccess","login")
            } else {//로그인 실패 시
                Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
            }
        }
//        Log.d("email:::", account?.email.toString())
//        Log.d("name:::", account?.displayName.toString())
//        Log.d("pic:::", account?.photoUrl.toString())
    }

//    private fun GoogleLoginInfo(task: Task<GoogleSignInAccount>) {
//        val googleInfo = GoogleInfo()
//        val account: GoogleSignInAccount? = task.result
//    }
}