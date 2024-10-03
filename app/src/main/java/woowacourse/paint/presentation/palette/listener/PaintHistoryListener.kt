package woowacourse.paint.presentation.palette.listener

interface PaintHistoryListener {
    fun onEmpty()

    fun onUndo()

    fun onRedo()
}
