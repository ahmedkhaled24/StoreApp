package com.example.storeapp.view.ui.adapters.products

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.storeapp.R
import com.example.storeapp.model.custom.ProductData
import com.squareup.picasso.Picasso

class ProductsAdapter(private val listener : ClickOnItemProduct) :
    RecyclerView.Adapter<ProductsAdapter.ProductsViewHolder>() {

    private var data: MutableList<ProductData> = ArrayList()
    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsViewHolder {
        context = parent.context
        val itemView: View = LayoutInflater.from(parent.context).inflate(R.layout.product_item, parent, false)
        return ProductsViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) {

        Picasso.get().load(data[position].image).into(holder.imageProduct)
        holder.title.text = data[position].title
        holder.price.text = context.getString(R.string.price, data[position].price.toString())

        holder.parentItem.setOnClickListener{
            listener.clickOnParentItem(data[position].id)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun setData(data: MutableList<ProductData>) {
        this.data = data
    }

    class ProductsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            var parentItem : LinearLayout = itemView.findViewById(R.id.parentItem)
            var imageProduct : ImageView = itemView.findViewById(R.id.product_iv)
            var title : TextView = itemView.findViewById(R.id.title_tv)
            var price : TextView = itemView.findViewById(R.id.price_tv)
    }

}

interface ClickOnItemProduct {
    fun clickOnParentItem(id: Int)
}