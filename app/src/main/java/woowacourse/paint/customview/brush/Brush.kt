package woowacourse.paint.customview.brush

import android.graphics.Color
import android.graphics.Paint

sealed class Brush : BrushSetting {
    val paint = Paint().apply {
        color = Color.RED
    }

    fun onActionDown() {
        when (this) {
            is Pen -> {}
            is Rectangle, is Circle -> {}
        }
    }

    fun onActionMove(brush: Brush) {
        when (this) {
            is Pen -> {}
            is Rectangle -> {}
            is Circle -> {}
        }
    }

    fun onActionOn(brush: Brush) {
        when (this) {
            is Pen -> {}
            is Rectangle -> {}
            is Circle -> {}
        }
    }
}

object Pen : Brush() {
    override fun setStyle(paintColor: Int) {
        paint.apply {
            paint.color = paintColor
            style = Paint.Style.STROKE
            strokeCap = Paint.Cap.ROUND
            strokeJoin = Paint.Join.ROUND
        }
    }
}

object Rectangle : Brush() {
    override fun setStyle(paintColor: Int) {
        paint.style = Paint.Style.FILL
        paint.color = paintColor
    }
}

object Circle : Brush() {
    override fun setStyle(paintColor: Int) {
        paint.color = paintColor
        paint.style = Paint.Style.FILL
    }
}

interface BrushSetting {
    fun setStyle(paintColor: Int = Color.RED)
}
