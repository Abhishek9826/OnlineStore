package com.example.onlinestore

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class EProductAdapter(var context: Context, var arrayList: ArrayList<EProduct>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val productView = LayoutInflater.from(context).inflate(R.layout.e_product_row,parent,false)

        return ProductViewHolder(productView)

    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        (holder as ProductViewHolder).initializeRowUIComponents(arrayList.get(position).id,arrayList.get(position).name,arrayList.get(position).price,arrayList.get(position).picture)


    }


    override fun getItemCount(): Int {

return arrayList.size

    }

    inner class ProductViewHolder(pView: View): RecyclerView.ViewHolder(pView){

fun initializeRowUIComponents(id: Int, name:String, price: Int, picName:String){


    itemView.findViewById<TextView>(R.id.txtID).text = id.toString()
    itemView.findViewById<TextView>(R.id.txtName).text = name
    itemView.findViewById<TextView>(R.id.txtPrice).text = price.toString()

    var picUrl = "http://192.168.29.53/OnlineStoreApp/osimages/"
    picUrl = picUrl.replace(" ","%20")
    Picasso.get().load(picUrl+picName).into(itemView.findViewById<ImageView>(R.id.imgProduct))

    itemView.findViewById<ImageView>(R.id.imgAdd).setOnClickListener {

        P.addToCartProductID = id
        var amountFragment= AmountFragment()
        var fragmentManager = (itemView.context  as Activity).fragmentManager
        amountFragment.show(fragmentManager,"TAG")

    }

}

    }
}