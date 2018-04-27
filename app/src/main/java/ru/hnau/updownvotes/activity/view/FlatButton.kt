package ru.hnau.updownvotes.activity.view

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.support.v4.content.ContextCompat
import android.text.TextUtils
import android.util.TypedValue
import android.view.Gravity
import android.widget.TextView
import ru.hnau.updownvotes.R
import ru.hnau.updownvotes.utils.UiUtils

/**
 *
 * Простая текстовая кнопка
 *
 */

@SuppressLint("ViewConstructor")
class FlatButton(
        context: Context,
        titleResId: Int,
        onClick: () -> Unit
) : TextView(context) {

    private val BG_COLOR = ContextCompat.getColor(context, R.color.bg)
    private val BG_COLOR_PRESSED = ContextCompat.getColor(context, R.color.bg_dark)

    private val bgPaint = Paint()

    init {
        text = context.getString(titleResId)
        setTextColor(ContextCompat.getColor(context, R.color.fg))
        gravity = Gravity.CENTER
        maxLines = 1
        minLines = 1
        val paddingH = UiUtils.dpToPx(32f, context).toInt()
        val paddingV = UiUtils.dpToPx(16f, context).toInt()
        setPadding(paddingH, paddingV, paddingH, paddingV)
        setTextSize(TypedValue.COMPLEX_UNIT_SP, 16f)
        setAllCaps(true)
        ellipsize = TextUtils.TruncateAt.END
        setOnClickListener { onClick() }
    }

    override fun draw(canvas: Canvas) {
        val color = if (isPressed) BG_COLOR_PRESSED else BG_COLOR
        bgPaint.color = color
        canvas.drawRect(0f, 0f, width.toFloat(), height.toFloat(), bgPaint)

        super.draw(canvas)
    }

    // Вызывается когда нажали отпустили и т.п.
    override fun drawableStateChanged() {
        super.drawableStateChanged()
        invalidate()
    }

}