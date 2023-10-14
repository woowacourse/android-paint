package woowacourse.paint.paintBoard.painter

interface Painter {

    fun startPainting(pointX: Float, pointY: Float)

    fun drawPainting(pointX: Float, pointY: Float)

    fun finishPainting()
}