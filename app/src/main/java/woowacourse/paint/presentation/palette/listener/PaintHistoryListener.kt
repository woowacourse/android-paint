package woowacourse.paint.presentation.palette.listener

interface PaintHistoryListener {
    fun onClear()

    fun onUndo()

    fun onRedo()
}
