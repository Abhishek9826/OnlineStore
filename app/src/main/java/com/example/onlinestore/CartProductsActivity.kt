package com.example.onlinestore

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import java.util.*

class CartProductsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart_products)

        var cartProductURL = "http://192.168.29.53/OnlineStoreApp/fatch_temporary_order.php?email=${P.email}"
        var cartProductsList = ArrayList<String>()
        var requestQ = Volley.newRequestQueue(CartProductsActivity@this)
        var jsonAR = JsonArrayRequest(Request.Method.GET, cartProductURL, null,
                Response.Listener { response ->
                    for (joIndex in 0.until(response.length())) {
                        cartProductsList.add("${response.getJSONObject(joIndex).getInt("id")} \n ${response.getJSONObject(joIndex).getString("name")} \n ${response.getJSONObject(joIndex).getInt("price")}  \n ${response.getJSONObject(joIndex).getInt("amount")}")


                    }
                    var cartProductsAdapter = ArrayAdapter(CartProductsActivity@this,android.R.layout.simple_list_item_1,cartProductsList)
                     findViewById<ListView>(R.id.cartProductListView).adapter= cartProductsAdapter


                }, Response.ErrorListener { error ->
            val dialogBuilder = AlertDialog.Builder(this)
            dialogBuilder.setTitle("Message")
            dialogBuilder.setMessage("Something Wrong")
            dialogBuilder.create().show()

        })
        requestQ.add(jsonAR)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.cart_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.continueShoppingItem){
            var intent = Intent(CartProductsActivity@this,HomeScreen::class.java)
            startActivity(intent)
        }else if(item.itemId == R.id.declineOrderItem){

            var deleteURL = "http://192.168.29.53/OnlineStoreApp/decline_order.php?email=${P.email}"

            var requestq = Volley.newRequestQueue(CartProductsActivity@this)
            var stringRequest = StringRequest(Request.Method.GET,deleteURL,Response.Listener { response ->


                var intent = Intent(this,HomeScreen::class.java)
                startActivity(intent)
            },Response.ErrorListener { error ->
                Toast.makeText(CartProductsActivity@this,"Something is wrong",Toast.LENGTH_SHORT).show()

            })
            requestq.add(stringRequest)

        }else if(item.itemId == R.id.varifyOrderItem){

            var varifyOrderURL = "http://192.168.29.53/OnlineStoreApp/varify_order.php"
            var requestQueue = Volley.newRequestQueue(CartProductsActivity@this)
            var stringRequest = StringRequest(Request.Method.GET,varifyOrderURL,Response.Listener {
                response ->
                Toast.makeText(CartProductsActivity@this,"Varifyed",Toast.LENGTH_SHORT).show()


            },Response.ErrorListener {
                error->
                Toast.makeText(CartProductsActivity@this,"!!!!!!!!!!!!",Toast.LENGTH_SHORT).show()
            })
            requestQueue.add(stringRequest)



        }
        return super.onOptionsItemSelected(item)
    }

}