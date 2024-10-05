package woowacourse.paint.model.rectangle

data class RectangleVertex(
    var startX: Float = 0f,
    var startY: Float = 0f,
    var endX: Float = 0f,
    var endY: Float = 0f,
) {
    fun changeVertex(
        startX: Float = this.startX,
        startY: Float = this.startY,
        endX: Float = this.endX,
        endY: Float = this.endY,
    ) {
        this.startX = startX
        this.startY = startY
        this.endX = endX
        this.endY = endY
    }
}
