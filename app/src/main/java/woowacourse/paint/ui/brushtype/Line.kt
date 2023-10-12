package woowacourse.paint.ui.brushtype

import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import com.example.domain.BrushType.LINE

class Line : BrushType {
    override var type = LINE

    override val path = Path()
    override val paint = Paint().apply {
        isAntiAlias = true
        this.color = Color.BLACK
        style = Paint.Style.STROKE
        strokeCap = Paint.Cap.ROUND
        strokeJoin = Paint.Join.ROUND
        strokeWidth = 0f
        xfermode = null
    }

    override fun startDrawing(pointX: Float, pointY: Float) {
        path.moveTo(pointX, pointY)
    }

    override fun moveDrawing(pointX: Float, pointY: Float) {
        path.lineTo(pointX, pointY)
    }

    fun doActionUp(pointX: Float, pointY: Float) {
        path.lineTo(pointX, pointY)
    }
}
