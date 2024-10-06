package woowacourse.paint.brush.draw

class Drawn {
    val draws: MutableList<Draw> = mutableListOf()

    fun add(draw: Draw){
        draws.add(draw)
    }
}
