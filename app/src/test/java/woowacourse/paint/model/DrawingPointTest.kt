package woowacourse.paint.model

import org.junit.Assert.assertEquals
import org.junit.Test

class DrawingPointTest {
    @Test
    fun `canvas position is normalized and clipped to canvas bounds`() {
        assertEquals(
            DrawingPoint(0.5f, 1f),
            DrawingPoint.fromCanvasPosition(50f, 120f, 100f, 100f),
        )
    }
}
