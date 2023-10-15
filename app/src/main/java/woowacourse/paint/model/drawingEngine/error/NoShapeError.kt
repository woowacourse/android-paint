package woowacourse.paint.model.drawingEngine.error

class NoShapeError(override val message: String = "도형이 존재하지 않습니다.") : Throwable(message)
