package woowacourse.paint

import org.junit.Assert.assertEquals
import org.junit.Test
import woowacourse.paint.model.BrushColor
import woowacourse.paint.model.DrawingPoint

class DrawingViewModelTest {
    @Test
    fun `stroke keeps the color and width selected when it started`() {
        val viewModel = DrawingViewModel()
        viewModel.changeBrushWidth(12f)
        viewModel.startStroke(DrawingPoint(0.25f, 0.5f))

        viewModel.selectColor(BrushColor.BLUE)
        viewModel.changeBrushWidth(24f)
        viewModel.finishStroke()

        val stroke = viewModel.state.value.strokes.single()
        assertEquals(BrushColor.RED, stroke.color)
        assertEquals(12f, stroke.widthDp)
    }
}
