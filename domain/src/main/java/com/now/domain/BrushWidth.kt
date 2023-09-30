package com.now.domain

@JvmInline
value class BrushWidth(val width: Float) {
    init {
        require(width in 0f..100f) { ERROR_WIDTH }
    }

    companion object {
        const val ERROR_WIDTH = "유효하지 않은 굵기입니다."
    }
}
