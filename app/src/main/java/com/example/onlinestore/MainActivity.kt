package com.example.onlinestore

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var loginEmail = findViewById<EditText>(R.id.edtEmail)
        var loginPassword = findViewById<EditText>(R.id.edtPassword)
        findViewById<Button>(R.id.btnSignUpActivity).setOnClickListener {
            val intent = Intent(MainActivity@this,SignUpActivity::class.java)

            startActivity(intent)
        }

        findViewById<Button>(R.id.btnLogin).setOnClickListener {

            val loginURL = " http://192.168.29.53/OnlineStoreApp/login_app_user.php?email=" + loginEmail.text.toString() + "&password=" + loginPassword.text.toString()

            val loginRequestQ = Volley.newRequestQueue(MainActivity@this)
            val  stringRequest = StringRequest(Request.Method.GET,loginURL,Response.Listener { response ->

               if (response.equals("The user does exist")){
                   P.email= loginEmail.text.toString()
                   Toast.makeText(MainActivity@this,response,Toast.LENGTH_LONG).show()

                   val homeIntent = Intent(MainActivity@this,HomeScreen::class.java)
                   startActivity(homeIntent)
               }else{
                   val dialogBuilder = AlertDialog.Builder(this)
                   dialogBuilder.setTitle("Message")
                   dialogBuilder.setMessage(response)
                   dialogBuilder.create().show()
               }



            }, Response.ErrorListener { error ->

                val dialogBuilder = AlertDialog.Builder(this)
                dialogBuilder.setTitle("Message")
                dialogBuilder.setMessage(error.message)
                dialogBuilder.create().show()


            })
            loginRequestQ.add(stringRequest )


        }


    }
}