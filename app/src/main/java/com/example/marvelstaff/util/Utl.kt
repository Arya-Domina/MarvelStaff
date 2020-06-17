package com.example.marvelstaff.util

import android.widget.ImageView
import android.widget.LinearLayout
import androidx.annotation.StringRes
import com.example.marvelstaff.R
import com.example.marvelstaff.ui.PairTextView
import com.squareup.picasso.Picasso

fun String.addSizeSmall(): String {
    return addSize(Constants.SIZE_SMALL)
}

fun String.addSizeMedium(): String {
    return addSize(Constants.SIZE_MEDIUM)
}

fun String.addSize(size: String): String {
    return (substringBeforeLast('.') +
            replaceBeforeLast('.', size))
}

fun ImageView.loadThumbnail(url: String?) {
    Picasso.get().load(url).fit()
        .placeholder(R.drawable.ic_placeholder).error(R.drawable.ic_error)
        .into(this)
}

fun LinearLayout.addPair(@StringRes denotationRes: Int, value: String?) {
    value?.let {
        this.addView(
            PairTextView(
                context, denotationRes, value
            )
        )
    }
}

fun LinearLayout.addPair(@StringRes denotationRes: Int, value: Int?) {
    if (value != null && value != 0) {
        this.addPair(denotationRes, value.toString())
    }
}