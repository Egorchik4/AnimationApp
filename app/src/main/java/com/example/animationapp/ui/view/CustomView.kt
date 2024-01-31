package com.example.animationapp.ui.view

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat
import com.example.animationapp.R

class CustomView @JvmOverloads constructor(
	context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

	private val paintCursor: Paint = Paint()
	private val paintBaraban: Paint = Paint()
	private val paintText: Paint = Paint()
	private val colors = listOf(
		ContextCompat.getColor(context, R.color.red),
		ContextCompat.getColor(context, R.color.orange),
		ContextCompat.getColor(context, R.color.yellow),
		ContextCompat.getColor(context, R.color.green),
		ContextCompat.getColor(context, R.color.cyan),
		ContextCompat.getColor(context, R.color.blue),
		ContextCompat.getColor(context, R.color.violet)
	)
	private var startAngle = 0f
	private val sweepAngle = 360f / colors.size
	private var animateStep = 0
	private var getPixelX = 0f
	private var getPixelY = 0f
	private var bitmap: Bitmap? = null
	private var text: String? = null

	init {
		paintCursor.color = Color.BLACK
		paintCursor.strokeWidth = 5f
		paintCursor.style = Paint.Style.FILL

		paintBaraban.color = Color.RED
		paintBaraban.strokeWidth = 50f
		paintBaraban.style = Paint.Style.FILL

		paintText.color = Color.BLACK
		paintText.textSize = 120f
		paintText.textAlign = Paint.Align.CENTER
	}

	override fun onDraw(canvas: Canvas) {
		super.onDraw(canvas)
		drawCircle(canvas)
		drawCursor(canvas)
		if (bitmap != null) {
			bitmap?.let {
				val cx: Float = (width - it.width shr 1).toFloat()
				val cy: Float = (height - it.height shr 1).toFloat()
				canvas.drawBitmap(it, cx, cy, null)
			}
		} else if (text != null) {
			text?.let {
				val xPos = width / 2f
				val yPos = height / 2f - (paintText.descent() + paintText.ascent()) / 2
				canvas.drawText(it, xPos, yPos, paintText)
			}
		}
	}

	private fun drawCircle(canvas: Canvas) {
		val centerX = width / 2f
		val centerY = height / 2f
		val radius = width.coerceAtMost(height) / 2f
		for (i in colors.indices) {
			paintBaraban.color = colors[i]
			canvas.drawArc(
				centerX - radius,
				centerY - radius,
				centerX + radius,
				centerY + radius,
				startAngle + animateStep + i * sweepAngle,
				sweepAngle,
				true,
				paintBaraban
			)
		}
	}

	private fun drawCursor(canvas: Canvas) {
		val centerX = width / 2f
		val centerY = height / 2f
		val startY = centerY - width.coerceAtMost(height) / 2f
		getPixelX = centerX
		getPixelY = startY
		canvas.drawRect(centerX - 20f, startY + 20f, centerX + 20f, centerY + 20f, paintCursor)
	}

	fun rotateBaraban(step: Int) {
		animateStep += step
		invalidate()
	}

	fun getColorOfCursor(): Int {
		val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
		this.draw(Canvas(bitmap))
		return bitmap.getPixel(getPixelX.toInt(), getPixelY.toInt())
	}

	fun showPicture(bitmap: Bitmap) {
		this.bitmap = bitmap
		this.text = null
		invalidate()
	}

	fun showText(text: String) {
		this.bitmap = null
		this.text = text
		invalidate()
	}

	fun reset() {
		startAngle = 0f
		animateStep = 0
		this.bitmap = null
		this.text = null
		invalidate()
	}
}