package woowacourse.paint.model

class ColorPalette {
    val red = Color("Red", "#FF0000")
    val orange = Color("Orange", "#FFA500")
    val yellow = Color("Yellow", "#FFFF00")
    val green = Color("Green", "#00FF00")
    val blue = Color("Blue", "#0000FF")

    val colors =
        listOf(
            red,
            orange,
            yellow,
            green,
            blue,
        )
}
