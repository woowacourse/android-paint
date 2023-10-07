package woowacourse.paint.customview.brush

import android.graphics.Color
import android.graphics.Paint

sealed class Brush : BrushSetting {
    val paint get() = paintInstance

    companion object {
        val paintInstance = Paint().apply {
            color = Color.RED
        }
    }
}

object Pen : Brush() {
    override fun setStyle() {
        paint.apply {
            style = Paint.Style.STROKE
            strokeCap = Paint.Cap.ROUND
            strokeJoin = Paint.Join.ROUND
        }
    }
}

object Rectangle : Brush() {
    override fun setStyle() {
        paint.style = Paint.Style.FILL
    }
}

object Circle : Brush() {
    override fun setStyle() {
        paint.style = Paint.Style.FILL
    }
}

interface BrushSetting {
    fun setStyle()
}
