package com.thecode.dagger_hilt_mvvm.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.thecode.dagger_hilt_mvvm.R
import com.thecode.dagger_hilt_mvvm.model.Blog
import kotlinx.android.synthetic.main.item_blog.view.*

class BlogAdapter(private val listener: BlogItemListener) : RecyclerView.Adapter<BlogViewHolder>() {

    interface BlogItemListener {
        fun onClickedBlog(blogTitle: CharSequence)
    }

    private val items = ArrayList<Blog>()
    private lateinit var item: Blog

    fun setItems(items: ArrayList<Blog>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BlogViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_blog,parent,false)
        return BlogViewHolder(view, listener)
    }

    override fun getItemCount(): Int = items.size


    override fun onBindViewHolder(holder: BlogViewHolder, position: Int) {
        holder.textTitle.text = items[position].title
        holder.textDescription.text = items[position].body
        Glide.with(holder.itemLayout).load(items[position].image)
            .placeholder(R.drawable.placeholder)
            .error(R.drawable.placeholder)
            .apply(RequestOptions().centerCrop())
            .into(holder.image)
        item = items[position]

    }

}

class BlogViewHolder(itemView: View, private val listener: BlogAdapter.BlogItemListener) : RecyclerView.ViewHolder(itemView),
    View.OnClickListener {

    val itemLayout: ConstraintLayout = itemView.blog_layout
    val textTitle: TextView = itemView.text_title
    val textDescription: TextView = itemView.text_description
    val image: AppCompatImageView = itemView.image
    init {
        itemLayout.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        listener.onClickedBlog(textTitle.text)
    }
}

