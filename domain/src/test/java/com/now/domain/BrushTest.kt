package com.now.domain

import junit.framework.TestCase.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Test

class BrushTest {
    @Test
    fun `Brush의 색상을 변경했을 때 두께는 유지된다`() {
        // given
        val originBrush = Brush(BrushColor.RED, BrushWidth(10f))

        // when
        val newBrush = originBrush.changeColor(BrushColor.BLUE)

        // then
        assertNotEquals(originBrush.brushColor, newBrush.brushColor)
        assertEquals(originBrush.brushWidth, newBrush.brushWidth)
    }

    @Test
    fun `Brush의 두께를 변경했을 때 색상은 유지된다`() {
        // given
        val originBrush = Brush(BrushColor.RED, BrushWidth(10f))

        // when
        val newBrush = originBrush.changeWidth(BrushWidth(50f))

        // then
        assertNotEquals(originBrush.brushWidth, newBrush.brushWidth)
        assertEquals(originBrush.brushColor, newBrush.brushColor)
    }

    @Test
    fun `Brush의 default 값은 붉은색 색상에 두께는 1f다`() {
        // given
        val brush = Brush.fromDefault()

        // when & then
        assertEquals(brush.brushColor, BrushColor.RED)
        assertEquals(brush.brushWidth, BrushWidth(1f))
    }
}
