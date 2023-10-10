package woowacourse.paint.domain.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BrushTest {

    @Test
    fun `붓 두께를 10에서 20으로 변경하면 붓 두께만 변경된다`() {
        // given
        val brush = BrushFixture.getBrush(brushWidth = BrushWidth(10f))

        // when
        val actual = brush.changeWidth(BrushWidth(20f))

        // then
        val expected = BrushFixture.getBrush(brushWidth = BrushWidth(20f))

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `붓 색깔이 빨강에서 파랑으로 변경하면 붓 색깔만 변경된다`() {
        // given
        val brush = BrushFixture.getBrush(brushColor = BrushColor.RED)

        // when
        val actual = brush.changeColor(BrushColor.BLUE)

        // then
        val expected = BrushFixture.getBrush(brushColor = BrushColor.BLUE)

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `붓 종류를 팬에서 원으로 변경하면 붓 종류만 원으로 변경된다`() {
        // given
        val brush = BrushFixture.getBrush(brushType = BrushType.LINE)

        // when
        val actual = brush.changeType(type = BrushType.CIRCLE)

        // then
        val expected = BrushFixture.getBrush(brushType = BrushType.CIRCLE)

        assertThat(actual).isEqualTo(expected)
    }
}
