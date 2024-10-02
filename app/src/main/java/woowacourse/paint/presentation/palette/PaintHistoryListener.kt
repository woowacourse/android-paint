package woowacourse.paint.presentation.palette

interface PaintHistoryListener {
    fun onEmpty()

    fun onUndo()

    fun onRedo()
}
