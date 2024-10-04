package woowacourse.paint.model.circle

data class Center(
    var x: Float = 0f,
    var y: Float = 0f,
) {
    fun changeProperty(
        x: Float,
        y: Float,
    ) {
        this.x = x
        this.y = y
    }
}
