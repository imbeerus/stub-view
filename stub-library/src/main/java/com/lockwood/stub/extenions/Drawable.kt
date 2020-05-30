package com.lockwood.stub.extenions

import android.content.res.Resources
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.annotation.ColorInt
import androidx.core.graphics.drawable.DrawableCompat
import androidx.core.graphics.drawable.toBitmap

internal fun Drawable.scale(
    res: Resources,
    width: Int,
    height: Int
): BitmapDrawable {
    val scaledBitmap = toBitmap(width, height)
    return BitmapDrawable(res, scaledBitmap)
}

internal fun Drawable.tint(@ColorInt tint: Int): Drawable {
    val wrappedDrawable = DrawableCompat.wrap(this)
    val mutableDrawable = wrappedDrawable.mutate()
    DrawableCompat.setTint(mutableDrawable, tint)
    return mutableDrawable
}
