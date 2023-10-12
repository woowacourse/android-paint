package woowacourse.paint.model.drawingEngine

import android.graphics.Path

interface PathDrawingEngine : DrawingEngine {

    val path: Path

    fun moveTo(pointX: Float, pointY: Float)

    fun quadTo(pointX: Float, pointY: Float)
}
