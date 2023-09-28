package woowacourse.paint.domain.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LineTest {
    @Test
    fun `선 두께를 10에서 20으로 변경하면 선 두께만 변경된다`() {
        // given
        val line = Line(LineColor.RED, LineWidth(10f))

        // when
        val actual = line.changeWidth(LineWidth(20f))

        // then
        val expected = Line(LineColor.RED, LineWidth(20f))

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `선 색깔이 빨강에서 파랑으로 변경하면 선 색깔만 변경된다`() {
        // given
        val line = Line(LineColor.RED, LineWidth(10f))

        // when
        val actual = line.changeColor(LineColor.BLUE)

        // then
        val expected = Line(LineColor.BLUE, LineWidth(10f))

        assertThat(actual).isEqualTo(expected)
    }
}
