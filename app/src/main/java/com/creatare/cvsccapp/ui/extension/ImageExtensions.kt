package com.creatare.cvsccapp.ui.extension

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.net.Uri
import androidx.core.content.FileProvider
import androidx.core.graphics.drawable.toBitmap
import java.io.File
import java.io.FileOutputStream

fun Context.shareImage(title: String, image: Drawable, filename: String, text: String) {
    val file = try {
        val outputFile = File(cacheDir, "$filename.png")
        val outPutStream = FileOutputStream(outputFile)
        image.toBitmap().compress(Bitmap.CompressFormat.PNG, 100, outPutStream)
        outPutStream.flush()
        outPutStream.close()
        outputFile
    } catch (e: Throwable) {
        return toast(e)
    }
    val uri = file.toUriCompat(this) // .toUriCompat(this)
    val shareIntent = Intent().apply {
        action = Intent.ACTION_SEND
        type = "image/png"
        putExtra(Intent.EXTRA_TEXT, text)
        putExtra(Intent.EXTRA_STREAM, uri)
    }
    startActivity(Intent.createChooser(shareIntent, title))
}

fun File.toUriCompat(context: Context): Uri {
    return FileProvider.getUriForFile(context, context.packageName + ".provider", this)
}
