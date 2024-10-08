package com.thecode.dagger_hilt_mvvm.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.thecode.dagger_hilt_mvvm.R
import com.thecode.dagger_hilt_mvvm.databinding.ItemBlogBinding
import com.thecode.dagger_hilt_mvvm.model.Blog

class BlogAdapter(private val onBlogClicked: (blog: Blog) -> Unit) :
    RecyclerView.Adapter<BlogAdapter.BlogViewHolder>() {

    private val items = ArrayList<Blog>()
    private lateinit var blog: Blog
    private lateinit var binding: ItemBlogBinding

    fun setItems(items: List<Blog>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BlogViewHolder {
        binding = ItemBlogBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BlogViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size


    override fun onBindViewHolder(holder: BlogViewHolder, position: Int) {
        blog = items[position]
        val blog = items[position]
        holder.apply {
            textTitle.text = blog.title
            textDescription.text = blog.body
            itemLayout.setOnClickListener {
                onBlogClicked(blog)
            }

            Glide.with(itemLayout).load(blog.image)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.placeholder)
                .apply(RequestOptions().centerCrop())
                .into(image)
        }
    }


    class BlogViewHolder(binding: ItemBlogBinding) : RecyclerView.ViewHolder(binding.root) {
        val itemLayout: ConstraintLayout = binding.blogLayout
        val textTitle: TextView = binding.textTitle
        val textDescription: TextView = binding.textDescription
        val image: AppCompatImageView = binding.image

    }
}

