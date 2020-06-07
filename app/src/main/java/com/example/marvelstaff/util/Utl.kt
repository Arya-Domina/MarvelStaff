package com.example.marvelstaff.util

fun String.addSizeSmall(): String {
    return addSize(Constants.SIZE_SMALL)
}

fun String.addSizeMedium(): String {
    return addSize(Constants.SIZE_MEDIUM)
}

fun String.addSize(size: String): String {
    return (substringBeforeLast('.') +
            replaceBeforeLast('.', size))
        .also {
            Logger.log("Util", "addSize: $it")
        }
}