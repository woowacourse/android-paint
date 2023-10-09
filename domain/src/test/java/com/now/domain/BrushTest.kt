package com.now.domain

import junit.framework.TestCase.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Test

class BrushTest {
    @Test
    fun `Brush의 색상을 변경했을 때 두께와 타입은 유지된다`() {
        // given
        val originBrush = Brush(BrushType.PEN, BrushColor.RED, BrushWidth(10f))

        // when
        val newBrush = originBrush.changeColor(BrushColor.BLUE)

        // then
        assertNotEquals(originBrush.brushColor, newBrush.brushColor)
        assertEquals(originBrush.brushType, newBrush.brushType)
        assertEquals(originBrush.brushWidth, newBrush.brushWidth)
    }

    @Test
    fun `Brush의 색상을 변경할 수 있다`() {
        // given
        val originBrush = Brush(BrushType.PEN, BrushColor.RED, BrushWidth(10f))

        // when
        val expected = BrushColor.BLUE
        val newBrush = originBrush.changeColor(expected)

        // then
        assertNotEquals(originBrush.brushColor, newBrush.brushColor)
        assertEquals(expected, newBrush.brushColor)
    }

    @Test
    fun `Brush의 두께를 변경할 수 있다`() {
        // given
        val originBrush = Brush(BrushType.PEN, BrushColor.RED, BrushWidth(10f))

        // when
        val expected = BrushWidth(50f)
        val newBrush = originBrush.changeWidth(expected)

        // then
        assertNotEquals(originBrush.brushWidth, newBrush.brushWidth)
        assertEquals(expected, newBrush.brushWidth)
    }

    @Test
    fun `Brush의 두께를 변경했을 때 색상과 타입은 유지된다`() {
        // given
        val originBrush = Brush(BrushType.PEN, BrushColor.RED, BrushWidth(10f))

        // when
        val newBrush = originBrush.changeWidth(BrushWidth(50f))

        // then
        assertEquals(originBrush.brushType, newBrush.brushType)
        assertEquals(originBrush.brushColor, newBrush.brushColor)
        assertNotEquals(originBrush.brushWidth, newBrush.brushWidth)
    }

    @Test
    fun `Brush의 default 값은 펜타입, 붉은색 색상, 두께는 5f다`() {
        // given
        val brush = Brush.fromDefault()

        // when & then
        assertEquals(BrushType.PEN, brush.brushType)
        assertEquals(BrushColor.RED, brush.brushColor)
        assertEquals(BrushWidth(5f), brush.brushWidth)
    }

    @Test
    fun `Brush의 Type을 변경할 수 있다`() {
        // given
        val originBrush = Brush(BrushType.PEN, BrushColor.RED, BrushWidth(10f))

        // when
        val expected = BrushType.ERASER
        val newBrush = originBrush.changeType(expected)

        // then
        assertEquals(expected, newBrush.brushType)
    }

    @Test
    fun `Brush의 Type을 변경해도 색상과 두께는 유지된다`() {
        // given
        val originBrush = Brush(BrushType.PEN, BrushColor.RED, BrushWidth(10f))

        // when
        val newBrush = originBrush.changeType(BrushType.ERASER)

        // then
        assertNotEquals(newBrush.brushType, originBrush.brushType)
        assertEquals(newBrush.brushColor, originBrush.brushColor)
        assertEquals(newBrush.brushWidth, originBrush.brushWidth)
    }
}
