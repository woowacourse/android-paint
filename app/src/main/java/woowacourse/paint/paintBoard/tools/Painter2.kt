package woowacourse.paint.paintBoard.tools

interface Painter2 {

    fun startPainting(pointX: Float, pointY: Float)

    fun drawPainting(pointX: Float, pointY: Float)

    fun finishPainting()
}