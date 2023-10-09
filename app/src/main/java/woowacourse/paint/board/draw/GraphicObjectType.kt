package woowacourse.paint.board.draw

enum class GraphicObjectType(val javaClassName: String) {
    LINE("Line"),
    ;

    companion object {
        fun getByJavaClassName(javaClassName: String): GraphicObjectType? =
            values().find { it.javaClassName == this.javaClass.name }
    }
}
