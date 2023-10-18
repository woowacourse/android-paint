package woowacourse.paint.model.drawingEngine.error

class AccessToEmptyDrawingEnginesError(override val message: String = "DrawingEngines의 요소가 비어 있습니다.") :
    Throwable(message)
