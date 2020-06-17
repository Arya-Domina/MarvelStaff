package com.example.marvelstaff.ui

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.example.marvelstaff.R
import kotlinx.android.synthetic.main.error_view.view.*

class ErrorView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0, defStyleRes: Int = 0
) : LinearLayout(context, attrs, defStyleAttr, defStyleRes) {
    init {
        LayoutInflater.from(context).inflate(R.layout.error_view, this, true)
    }

    fun setOnRetry(action: () -> Unit) {
        error_retry.setOnClickListener {
            action.invoke()
        }
    }
}