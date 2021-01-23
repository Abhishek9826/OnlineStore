package com.example.onlinestore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley

class FetchEProductsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fetch_e_products)

        var productsRV = findViewById<RecyclerView>(R.id.productRv)
        val selectedBrand: String? = intent.getStringExtra("BRAND")

        findViewById<TextView>(R.id.txtbrandName).text = "Product of $selectedBrand"

        var productList = ArrayList<EProduct>()
       val productURL = "http://192.168.29.53/OnlineStoreApp/fatch_eproducts.php?brand=$selectedBrand"
        val requestQ = Volley.newRequestQueue(FetchEProductsActivity@this)
        val jsonAR = JsonArrayRequest(Request.Method.GET,productURL,null, {
            response ->
            for(productjoIndex in 0.until(response.length())){
                productList.add(EProduct(response.getJSONObject(productjoIndex).getInt("id"),
                        response.getJSONObject(productjoIndex).getString("name"),
                        response.getJSONObject(productjoIndex).getInt("price"),
                        response.getJSONObject(productjoIndex).getString("picture")))
            }

            val pAdapter = EProductAdapter(FetchEProductsActivity@this,productList)

            productsRV.layoutManager = LinearLayoutManager(FetchEProductsActivity@this)
            productsRV.adapter = pAdapter
        }, { error ->
            val dialogBuilder = AlertDialog.Builder(this)
            dialogBuilder.setTitle("Message")
            dialogBuilder.setMessage(error.message)
            dialogBuilder.create().show()
        })

        requestQ.add(jsonAR)




    }
}