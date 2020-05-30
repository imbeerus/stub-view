/*
 * Copyright (C) 2020  Ivan Zinovyev (https://github.com/lndmflngs)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.lockwood.stub

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import com.lockwood.drawable.transformation.SizeTransformation
import com.lockwood.drawable.transformation.TintTransformation
import com.lockwood.drawable.transformation.extenions.buildDrawable
import com.lockwood.stub.DrawablePosition.TOP
import com.lockwood.stub.extenions.drawables
import com.lockwood.stub.extenions.fetchAttrs
import com.lockwood.stub.extenions.getDrawableCompat
import com.lockwood.stub.extenions.setDrawables

open class StubView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = android.R.attr.textViewStyle
) : AppCompatTextView(context, attrs, defStyleAttr) {

    companion object {

        private val TAG = StubView::class.java.simpleName

        const val DEFAULT_DRAWABLE_POSITION = TOP

        const val DEFAULT_DRAWABLE_SIZE = 0

        const val DEFAULT_DRAWABLE_TINT_COLOR = -1
    }

    var drawable: Drawable?
        get() {
            val index = drawablePosition
            return drawables[index]
        }
        set(value) {
            stubDrawable = value
            updateDrawable()
        }

    var drawablePosition = DEFAULT_DRAWABLE_POSITION
        set(value) {
            field = value
            updateDrawable()
        }

    var drawableTint = DEFAULT_DRAWABLE_TINT_COLOR
        set(value) {
            field = value
            updateDrawable()
        }

    var drawableCustomSize = DEFAULT_DRAWABLE_SIZE
        set(value) {
            field = value
            updateDrawable()
        }

    private var stubDrawable: Drawable? = null

    // fetch attrs and update compound drawables
    init {
        fetchAttrs(
            context,
            R.styleable.StubView,
            set = attrs,
            defStyleAttr = defStyleAttr
        ) {
            drawablePosition = getInt(
                R.styleable.StubView_drawablePosition,
                DEFAULT_DRAWABLE_POSITION
            )
            drawableCustomSize = getDimensionPixelSize(
                R.styleable.StubView_drawableSize,
                DEFAULT_DRAWABLE_SIZE
            )
            drawableTint = getResourceId(
                R.styleable.StubView_drawableTint,
                DEFAULT_DRAWABLE_TINT_COLOR
            )

            stubDrawable = getDrawableCompat(context, R.styleable.StubView_drawable)
        }

        drawable = stubDrawable
    }

    private fun updateDrawable() {
        val resultDrawable = transformDrawable(stubDrawable)
        val currentDrawableTop = drawablePosition == TOP

        if (currentDrawableTop) {
            setDrawables(top = resultDrawable)
        } else {
            setDrawables(bottom = resultDrawable)
        }
    }

    private fun transformDrawable(drawable: Drawable?): Drawable? {
        if (drawable == null) {
            return null
        }

        return buildDrawable(context, drawable) {
            append(TintTransformation(drawableTint))
            append(SizeTransformation(drawableCustomSize))
        }
    }

}