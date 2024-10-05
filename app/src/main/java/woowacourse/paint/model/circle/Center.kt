package woowacourse.paint.model.circle

data class Center(
    private var _x: Float = 0f,
    private var _y: Float = 0f,
) {
    val x get() = _x
    val y get() = _y

    fun changeProperty(
        x: Float,
        y: Float,
    ) {
        this._x = x
        this._y = y
    }
}
