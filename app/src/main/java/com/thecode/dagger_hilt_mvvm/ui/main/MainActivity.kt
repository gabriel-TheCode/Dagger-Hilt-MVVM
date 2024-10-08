package com.thecode.dagger_hilt_mvvm.ui.main

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.thecode.dagger_hilt_mvvm.databinding.ActivityMainBinding
import com.thecode.dagger_hilt_mvvm.model.Blog
import com.thecode.dagger_hilt_mvvm.ui.details.BlogDetailsActivity
import com.thecode.dagger_hilt_mvvm.ui.main.MainActivity.Companion.BLOG_UI_MODEL
import com.thecode.dagger_hilt_mvvm.util.DataState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels()
    private lateinit var adapter: BlogAdapter
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setupRecyclerView()
        subscribeObservers()
        viewModel.setStateEvent(MainStateEvent.GetBlogEvents)

        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.setStateEvent(MainStateEvent.GetBlogEvents)
        }

        setContentView(binding.root)

    }

    private fun subscribeObservers() {
        viewModel.dataState.observe(this) { dataState ->
            when (dataState) {
                is DataState.Success -> {
                    displayLoading(false)
                    populateRecyclerView(dataState.data)
                }

                is DataState.Loading -> {
                    displayLoading(true)
                }

                is DataState.Error -> {
                    displayLoading(false)
                    displayError(dataState.exception.message)
                }
            }
        }
    }


    private fun displayError(message: String?) {
        if (message.isNullOrEmpty()) {
            Toast.makeText(this, "Unknown error", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(this, message, Toast.LENGTH_LONG).show()
        }
    }

    private fun displayLoading(isLoading: Boolean) {
        binding.swipeRefreshLayout.isRefreshing = isLoading
    }

    private fun populateRecyclerView(blogs: List<Blog>) {
        if (blogs.isNotEmpty()) adapter.setItems(blogs)
    }

    private fun setupRecyclerView() {
        adapter = BlogAdapter(onBlogClicked = {
            openBlogDetails(it)
        })
        binding.blogRecyclerview.layoutManager = LinearLayoutManager(this)
        binding.blogRecyclerview.adapter = adapter
    }

    private fun openBlogDetails(blog: Blog) {
        val intent = Intent(this, BlogDetailsActivity::class.java)
        intent.putExtra(BLOG_UI_MODEL, blog)
        this.startActivity(intent)
    }

    object Companion {
        const val BLOG_UI_MODEL = "PHOTO_UI_MODEL"
    }
}