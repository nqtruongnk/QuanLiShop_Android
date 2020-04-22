package nqtruongnk.nhatky.quanlishop

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.UserHandle
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        auth = FirebaseAuth.getInstance()
        btnRegister.setOnClickListener {
            val intent: Intent= Intent(this, Register::class.java)
            startActivity(intent)

        }

        btnSingIn.setOnClickListener(){
            signUpUser()
        }
    }

    private fun signUpUser(){
        if (edtUsername.text.toString().isEmpty()){
            edtUsername.error = "Plesase Enter Email!"
            edtUsername.requestFocus()
            return
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(edtUsername.text.toString()).matches()){
            edtUsername.error = "Plesase Enter valid Email!"
            edtUsername.requestFocus()
            return
        }

        if (edtPassword.text.toString().isEmpty()){
            edtPassword.error = "Plesase Enter Password!"
            edtPassword.requestFocus()
            return
        }

        auth.createUserWithEmailAndPassword(edtUsername.text.toString(), edtPassword.text.toString())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    startActivity(Intent(this,Main2Activity::class.java))
                    finish()
                } else {
                   Toast.makeText(baseContext, "Sign Up failed. Try again after some time.",
                        Toast.LENGTH_SHORT).show()
                }

                // ...
            }

    }









}
