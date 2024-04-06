package com.thecode.dagger_hilt_mvvm.ui.activities.details

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.thecode.dagger_hilt_mvvm.R
import com.thecode.dagger_hilt_mvvm.model.Blog
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
import kotlinx.android.synthetic.main.activity_details.*

@AndroidEntryPoint
class DetailsActivity : AppCompatActivity() {

    companion object {
        private const val blog = "blog"

        fun getDetailsIntent(context: Context, blogTitle: CharSequence) : Intent {
            return Intent(context, DetailsActivity::class.java).apply {
                putExtra(blog, blogTitle)
            }
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        setIntent()
    }

    private fun setIntent() {
        text_title.text = intent.getCharSequenceExtra(blog)
    }

}