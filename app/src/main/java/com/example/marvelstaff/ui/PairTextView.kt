package com.example.marvelstaff.ui

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.annotation.StringRes
import com.example.marvelstaff.R
import kotlinx.android.synthetic.main.pair_text_view.view.*

class PairTextView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0, defStyleRes: Int = 0
) : LinearLayout(context, attrs, defStyleAttr, defStyleRes) {

    constructor(context: Context, @StringRes denotationRes: Int?, value: String?) : this(context) {
        denotationRes?.let { denotation_text.setText(denotationRes) }
        if (value != null)
            value_text.text = value
        else value_text.hint =
            denotation_text.text.takeIf { !it.isNullOrEmpty() }?.let { "No $it" } ?: "nothing"
    }

    init {
        LayoutInflater.from(context).inflate(R.layout.pair_text_view, this, true)

        attrs?.let {
            val typedArray = context.obtainStyledAttributes(
                it, R.styleable.PairTextView, 0, 0
            )
            val title = resources.getText(
                typedArray
                    .getResourceId(R.styleable.PairTextView_denotation, R.string.denotation)
            )

            denotation_text.text = title
            value_text.hint =
                "No $title"

            typedArray.recycle()
        }
    }

    var value: String? = value_text.text.toString()
        set(value) {
            field = value
            value_text.text = value
        }

}
