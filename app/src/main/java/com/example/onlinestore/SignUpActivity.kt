package com.example.onlinestore

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.Person
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        var Password = findViewById<EditText>(R.id.sign_up_layout_password)
        var confirmPassword = findViewById<EditText>(R.id.sign_up_layout_edtconfirmPassword)
        var Email = findViewById<EditText>(R.id.sign_up_layout_edtemail)
        var Username = findViewById<EditText>(R.id.sign_up_layout_edtusername)


        findViewById<Button>(R.id.btnLoginActivity).setOnClickListener {
            val intent = Intent(SignUpActivity@this,MainActivity::class.java)

            startActivity(intent)
        }


        findViewById<Button>(R.id.btnSignup).setOnClickListener {
            if (Password.text.toString().equals(confirmPassword.text.toString())){


                val signUpURL = "http://192.168.29.53/OnlineStoreApp/join_new_user.php?email=" + Email.text.toString() +
                        "&username=" + Username.text.toString() +
                        "&password=" + Password.text.toString()
                val requestQ = Volley.newRequestQueue(SignUpActivity@this)
                val  stringRequest = StringRequest(Request.Method.GET,signUpURL,Response.Listener { response ->

                   if (response.equals("A user with this Email already exists")){
                       val dialogBuilder = AlertDialog.Builder(this)
                       dialogBuilder.setTitle("Message")
                       dialogBuilder.setMessage(response)
                       dialogBuilder.create().show()

                   }else{
//                       val dialogBuilder = AlertDialog.Builder(this)
//                       dialogBuilder.setTitle("Message")
//                       dialogBuilder.setMessage(response)
//                       dialogBuilder.create().show()

                       P.email = Email.text.toString()

    Toast.makeText(SignUpActivity@this,response,Toast.LENGTH_SHORT).show()
                       val homeIntent = Intent(SignUpActivity@this,HomeScreen::class.java)
                       startActivity(homeIntent)

                   }



                },Response.ErrorListener {error ->

                    val dialogBuilder = AlertDialog.Builder(this)
                    dialogBuilder.setTitle("Message")
                    dialogBuilder.setMessage(error.message)
                    dialogBuilder.create().show()


                })
                requestQ.add(stringRequest)
            }else{
                val dialogBuilder = AlertDialog.Builder(this)
                dialogBuilder.setTitle("Message")
                dialogBuilder.setMessage("Password Mismatch")
                dialogBuilder.create().show()
            }

        }
    }
}