package com.creatare.cvsccapp.ui.extension

import android.content.Context
import android.widget.Toast

fun Context.toast(throwable: Throwable) =
    throwable.message?.let { toast(it) }
        ?: toast("Unknown error")

fun Context.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}