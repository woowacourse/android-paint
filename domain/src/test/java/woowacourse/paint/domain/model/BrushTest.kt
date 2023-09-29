package woowacourse.paint.domain.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BrushTest {

    @Test
    fun `붓 두께를 10에서 20으로 변경하면 붓 두께만 변경된다`() {
        // given
        val brush = Brush(BrushColor.RED, BrushWidth(10f))

        // when
        val actual = brush.changeWidth(BrushWidth(20f))

        // then
        val expected = Brush(BrushColor.RED, BrushWidth(20f))

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `붓 색깔이 빨강에서 파랑으로 변경하면 붓 색깔만 변경된다`() {
        // given
        val brush = Brush(BrushColor.RED, BrushWidth(10f))

        // when
        val actual = brush.changeColor(BrushColor.BLUE)

        // then
        val expected = Brush(BrushColor.BLUE, BrushWidth(10f))

        assertThat(actual).isEqualTo(expected)
    }
}
