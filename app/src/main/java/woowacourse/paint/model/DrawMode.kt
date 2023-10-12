package woowacourse.paint.model

import woowacourse.paint.model.drawingEngine.DrawingEngine
import woowacourse.paint.model.drawingEngine.path.LineDrawingEngine
import woowacourse.paint.model.drawingEngine.path.PathEraserDrawingEngine
import woowacourse.paint.model.drawingEngine.shape.OvalDrawingEngine
import woowacourse.paint.model.drawingEngine.shape.RectangleDrawingEngine
import woowacourse.paint.model.pen.Pen

enum class DrawMode(val instantiation: (pen: Pen, pointX: Float, pointY: Float) -> DrawingEngine) {
    RECT(RectangleDrawingEngine::createInstance),
    OVAL(OvalDrawingEngine::createInstance),
    LINE(LineDrawingEngine::createInstance),
    ERASER(PathEraserDrawingEngine::createInstance),
    ;

    companion object {
        fun getDefaultDrawMode(): DrawMode = LINE
    }
}
