package com.example.onlinestore

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AlertDialog
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley

class HomeScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_screen)
       setTitle("Wellcome To HomeScreen")

        var brandListView = findViewById<ListView>(R.id.brandListView)

        var brandURL = "http://192.168.29.53/OnlineStoreApp/fatch_brand.php"

        var brandList = ArrayList<String>()
        var requestQ = Volley.newRequestQueue(HomeScreen@this)

        var jsonAR = JsonArrayRequest(Request.Method.GET,brandURL,null,Response.Listener { response ->

            for (jsonObject in 0.until(response.length())){
                brandList.add(response.getJSONObject(jsonObject).getString("brand"))

            }


            var brandsListAdapter = ArrayAdapter(HomeScreen@this,R.layout.brand_item_text_view,brandList)
            brandListView.adapter = brandsListAdapter

        },Response.ErrorListener { error ->
            val dialogBuilder = AlertDialog.Builder(this)
            dialogBuilder.setTitle("Message")
            dialogBuilder.setMessage(error.message)
            dialogBuilder.create().show()

        })
        requestQ.add(jsonAR)

        brandListView.setOnItemClickListener { parent, view, position, id ->
            val tappedBrand = brandList.get(position)
            val intent = Intent(HomeScreen@this,FetchEProductsActivity::class.java)
            intent.putExtra("BRAND",tappedBrand)
            startActivity(intent)
        }
    }
}