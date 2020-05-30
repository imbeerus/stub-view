package com.lockwood.stubdemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.lockwood.stub.DrawablePosition
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    companion object {

        private const val MIN_DRAWABLE_SIZE = 24
        private const val MAX_DRAWABLE_SIZE = 256

        private val TINT_COLORS = arrayOf(
            R.color.primary,
            R.color.primaryDark,
            R.color.accent,
            android.R.color.darker_gray
        )
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupButtons()
    }

    private fun setupButtons() {
        button_position.setOnClickListener { changePosition() }
        button_size.setOnClickListener { changeSize() }
        button_color.setOnClickListener { changeColor() }
    }

    private fun changePosition() {
        val currentPosition = stub.drawablePosition
        stub.drawablePosition = if (currentPosition == DrawablePosition.TOP) {
            DrawablePosition.BOTTOM
        } else {
            DrawablePosition.TOP
        }
    }

    private fun changeSize() {
        val size = Random.nextInt(MIN_DRAWABLE_SIZE, MAX_DRAWABLE_SIZE)
        stub.drawableCustomSize = size
    }

    private fun changeColor() {
        val color = TINT_COLORS.random()
        stub.drawableTint = color
    }


}