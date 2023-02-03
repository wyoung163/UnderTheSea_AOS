package com.example.underthesea_aos.googleLogin

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.underthesea_aos.R
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.firebase.auth.FirebaseAuth
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.internal.StringResourceValueReader
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.functions.FirebaseFunctions
import com.google.firebase.functions.FirebaseFunctionsException
import org.json.JSONObject

class MainActivity :AppCompatActivity(){
    private lateinit var auth : FirebaseAuth
    private lateinit var googleSignInClient : GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_googlelogin)

        auth = FirebaseAuth.getInstance()
        Log.d("auth: ", auth.toString())

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        Log.d("gso: ", gso.toString())

        googleSignInClient = GoogleSignIn.getClient(this, gso)

        findViewById<Button>(R.id.SignInBtn).setOnClickListener {
            signInGoogle()
        }
    }

    private fun signInGoogle(){
        val signInIntent = googleSignInClient.signInIntent
        launcher.launch(signInIntent)
    }

    private val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        result ->
                if(result.resultCode == Activity.RESULT_OK){
                    val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
                    Log.d("task: ", task.toString())
                    handleResults(task)
                }
    }

    private fun handleResults(task: Task<GoogleSignInAccount>) {
        if(task.isSuccessful){
            val account : GoogleSignInAccount? = task.result
            Log.d("account: ", account.toString())
            if(account != null){
                updateUI(account)
            }
        }else{
            Toast.makeText(this, task.exception.toString(), Toast.LENGTH_SHORT).show()
        }
    }

    private fun updateUI(account: GoogleSignInAccount) {
        val credential = GoogleAuthProvider.getCredential((account).idToken, null)
        Log.d("idToken: ", (account).idToken.toString())
        auth.signInWithCredential(credential).addOnCompleteListener{
            if(it.isSuccessful){//로그인 성공 시
                val intent : Intent = Intent(this,HomeActivity::class.java)
                intent.putExtra("email",account.email)
                intent.putExtra("name",account.displayName)
                startActivity(intent)
                Log.d("loginSuccess","login")
            }else{//로그인 실패 시
                Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun onClickCreateJwt(){
        val text: String = JSONObject()
            .put("expireSeconds", 60*60*24)
            .put("did","my_did")
            .put("muid","my_muid")
            .toString()

        val data = hashMapOf(
            "text" to text
        )

        FirebaseFunctions.getInstance()
            .getHttpsCallable("createJwtToken")
            .call(data)
            .addOnCompleteListener{ task ->
                if (task.isSuccessful){
                    val dataString = task.result?.data?.toString()
                    Log.d("dataString","dataString")
                    val dataMap = task.result?.data as? Map<String, Object>;
                    Log.d("dataMap","dataMap")
                    if(dataMap != null){
                        if((dataMap["code"] as? Int) == 0){
                            val resultMap = dataMap["result"] as? Map<String, Object>
                            val token = resultMap?.get("token") as? String
                            val exp = resultMap?.get("exp") as? Int
                            Log.d("getToken","token= $token\nexp=$exp")
                        }
                    }
                }else{
                    val e = task.exception
                    if(e is FirebaseFunctionsException){
                        val code = e.code
                        val details = e.details
                    }
                }
            }
    }
}