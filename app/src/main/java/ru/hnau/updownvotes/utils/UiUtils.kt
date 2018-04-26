package ru.hnau.updownvotes.utils

import android.content.Context
import android.graphics.Color
import android.graphics.Path
import android.graphics.Rect
import android.graphics.RectF
import android.graphics.drawable.Drawable
import android.util.DisplayMetrics
import android.view.View
import android.view.ViewGroup


object UiUtils {

    const val WRAP_CONTENT = ViewGroup.LayoutParams.WRAP_CONTENT
    const val MATCH_PARENT = ViewGroup.LayoutParams.MATCH_PARENT

    private var pxDpK: Float? = null

    private fun getPxDpK(context: Context?): Float? {
        var pxDpK = this.pxDpK
        if (pxDpK == null) {
            context ?: return 0f
            val densityDpi = context.resources?.displayMetrics?.densityDpi?.toFloat() ?: return 0f
            pxDpK = DisplayMetrics.DENSITY_DEFAULT / densityDpi
            this.pxDpK = pxDpK
        }
        return pxDpK
    }

    fun pxToDp(px: Float, context: Context?): Float {
        val pxDpK = getPxDpK(context) ?: return 0f
        return px * pxDpK
    }

    fun dpToPx(dp: Float, context: Context?): Float {
        val pxDpK = getPxDpK(context) ?: return 0f
        return dp / pxDpK
    }

    fun getDefaultSize(size: Int, measureSpec: Int): Int {
        var result = size
        val specMode = View.MeasureSpec.getMode(measureSpec)
        val specSize = View.MeasureSpec.getSize(measureSpec)

        when (specMode) {
            View.MeasureSpec.AT_MOST -> result = Math.min(size, specSize)
            View.MeasureSpec.UNSPECIFIED -> result = size
            View.MeasureSpec.EXACTLY -> result = specSize
        }
        return result
    }

    fun getMaxSize(size: Int, measureSpec: Int): Int {
        val specMode = View.MeasureSpec.getMode(measureSpec)
        return if (specMode == View.MeasureSpec.UNSPECIFIED) {
            size
        } else View.MeasureSpec.getSize(measureSpec)
    }


    fun calcCardPath(rect: Rect, cardCornersRadius: Int) =
            calcCardPath(rect.left.toFloat(), rect.top.toFloat(), rect.width().toFloat(), rect.height().toFloat(), cardCornersRadius.toFloat())

    fun calcCardPath(cardLeft: Int, cardTop: Int, cardWidth: Int, cardHeight: Int, cardCornersRadius: Int) =
            calcCardPath(cardLeft.toFloat(), cardTop.toFloat(), cardWidth.toFloat(), cardHeight.toFloat(), cardCornersRadius.toFloat())

    fun calcRoundSidesPath(rect: Rect) =
            calcRoundSidesPath(rect.left, rect.top, rect.width(), rect.height())

    fun calcRoundSidesPath(left: Int, top: Int, width: Int, height: Int) =
            calcCardPath(left, top, width, height, Math.min(width, height) / 2)

    fun calcCardPath(cardLeft: Float, cardTop: Float, cardWidth: Float, cardHeight: Float, cardCornersRadius: Float): Path {
        val circleLeft = cardLeft + cardCornersRadius * 2
        val circleTop = cardTop + cardCornersRadius * 2
        val circleBottom = cardTop + cardHeight - cardCornersRadius * 2
        val circleRight = cardLeft + cardWidth - cardCornersRadius * 2

        val cardPath = Path()
        cardPath.arcTo(RectF(cardLeft, cardTop, circleLeft, circleTop), 180f, 90f)
        cardPath.arcTo(RectF(circleRight, cardTop, cardLeft + cardWidth, circleTop), 270f, 90f)
        cardPath.arcTo(RectF(circleRight, circleBottom, cardLeft + cardWidth, cardTop + cardHeight), 0f, 90f)
        cardPath.arcTo(RectF(cardLeft, circleBottom, circleLeft, cardTop + cardHeight), 90f, 90f)
        cardPath.lineTo(cardLeft, cardTop + cardCornersRadius)

        return cardPath
    }

    fun getColorInterTwoColors(c1: Int, c2: Int, pos: Float): Int {
        //Смешение цветов в пропорции зависящей от pos
        val r1 = Color.red(c1)
        val g1 = Color.green(c1)
        val b1 = Color.blue(c1)
        val a1 = Color.alpha(c1)

        val r2 = Color.red(c2)
        val g2 = Color.green(c2)
        val b2 = Color.blue(c2)
        val a2 = Color.alpha(c2)

        val r = r1 + ((r2 - r1) * pos).toInt()
        val g = g1 + ((g2 - g1) * pos).toInt()
        val b = b1 + ((b2 - b1) * pos).toInt()
        val a = a1 + ((a2 - a1) * pos).toInt()

        return Color.argb(a, r, g, b)
    }

    fun setColorAlpha(color: Int, alpha: Float) =
            Color.argb((Color.alpha(color) * alpha).toInt(), Color.red(color), Color.green(color), Color.blue(color))


}