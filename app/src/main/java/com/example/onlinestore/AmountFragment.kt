package com.example.onlinestore

import android.app.DialogFragment
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley


class AmountFragment : DialogFragment(){


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

      var fragmentView =    inflater.inflate(R.layout.fragment_amount2, container, false)
      var edtEnterAmount =  fragmentView.findViewById<EditText>(R.id.edtEnterAmount)
        var btnAddToCart = fragmentView.findViewById<ImageView>(R.id.btnAddToCart)

        btnAddToCart.setOnClickListener {
            var ptoURL = "http://192.168.29.53/OnlineStoreApp/insert_temporary_order.php?email=${P.email}&product_id=${P.addToCartProductID}&amount=${edtEnterAmount.text.toString()}"
            var requestQ = Volley.newRequestQueue(activity)
            val  stringRequest = StringRequest(Request.Method.GET,ptoURL,Response.Listener {
                    response ->
             var intent = Intent(activity,CartProductsActivity::class.java)
                startActivity(intent)


            },Response.ErrorListener {error ->
                val dialogBuilder = AlertDialog.Builder(activity)
                dialogBuilder.setTitle("Message")
                dialogBuilder.setMessage("error")
                dialogBuilder.create().show()
            })
            requestQ.add(stringRequest)

        }


      return fragmentView
    }


    }
