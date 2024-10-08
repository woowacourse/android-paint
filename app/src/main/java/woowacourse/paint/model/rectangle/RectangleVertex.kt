package woowacourse.paint.model.rectangle

data class RectangleVertex(
    private var _startX: Float = 0f,
    private var _startY: Float = 0f,
    private var _endX: Float = 0f,
    private var _endY: Float = 0f,
) {
    val startX get() = _startX
    val startY get() = _startY
    val endX get() = _endX
    val endY get() = _endY

    fun changeVertex(
        startX: Float = this.startX,
        startY: Float = this.startY,
        endX: Float = this.endX,
        endY: Float = this.endY,
    ) {
        this._startX = startX
        this._startY = startY
        this._endX = endX
        this._endY = endY
    }
}
