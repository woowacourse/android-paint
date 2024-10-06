package woowacourse.paint.brush.draw

class Drawn {
    private val draws: MutableList<Draw> = mutableListOf()

    fun add(draw: Draw){
        draws.add(draw)
    }
}
