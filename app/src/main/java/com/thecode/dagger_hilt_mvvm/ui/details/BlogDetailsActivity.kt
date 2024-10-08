package com.thecode.dagger_hilt_mvvm.ui.details

import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.thecode.dagger_hilt_mvvm.R
import com.thecode.dagger_hilt_mvvm.databinding.ActivityBlogDetailsBinding
import com.thecode.dagger_hilt_mvvm.model.Blog
import com.thecode.dagger_hilt_mvvm.ui.main.MainActivity.Companion.BLOG_UI_MODEL

class BlogDetailsActivity : AppCompatActivity() {

    private var binding: ActivityBlogDetailsBinding? = null
    private var blog: Blog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBlogDetailsBinding.inflate(layoutInflater)

        blog = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.extras?.getParcelable(BLOG_UI_MODEL, Blog::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.extras?.getParcelable(BLOG_UI_MODEL)
        }

        initViews()
        setContentView(binding?.root)
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    private fun initViews() {
        binding?.apply {
            Glide.with(this@BlogDetailsActivity).load(blog?.image)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.placeholder)
                .apply(RequestOptions().centerCrop())
                .into(imageHeader)

            blog?.apply {
                titleTextView.text = title
                bodyTextView.text = body
                catgoryTextView.text = category
            }
        }
    }
}