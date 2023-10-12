package woowacourse.paint.model.drawingEngine

import android.graphics.Canvas
import android.graphics.Path

interface PathDrawingEngine : DrawingEngine {

    val path: Path

    override fun draw(canvas: Canvas)

    override fun draw(pointX: Float, pointY: Float)

    fun moveTo(pointX: Float, pointY: Float)

    fun quadTo(pointX: Float, pointY: Float)
}
