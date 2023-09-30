package com.now.domain

@JvmInline
value class BrushWidth(val width: Int) {
    init {
        require(width in 0..100) { ERROR_WIDTH }
    }

    companion object {
        const val ERROR_WIDTH = "유효하지 않은 굵기입니다."
    }
}
