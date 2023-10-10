package woowacourse.paint.model.shape

sealed interface Shape {

    fun move(pointX: Float, pointY: Float)
}
