package com.lockwood.stub.extenions

import android.graphics.drawable.Drawable
import android.os.Build
import android.widget.TextView

internal val TextView.drawables: Array<Drawable?>
    get() {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            compoundDrawablesRelative
        } else {
            compoundDrawables
        }
    }

internal fun TextView.setDrawables(
    top: Drawable? = null,
    bottom: Drawable? = null
) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
        setCompoundDrawablesRelativeWithIntrinsicBounds(null, top, null, bottom)
    } else {
        setCompoundDrawablesWithIntrinsicBounds(null, top, null, bottom)
    }
}