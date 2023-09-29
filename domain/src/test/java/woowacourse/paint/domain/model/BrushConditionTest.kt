package woowacourse.paint.domain.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BrushConditionTest {

    @Test
    fun `붓 두께를 10에서 20으로 변경하면 붓 두께만 변경된다`() {
        // given
        val brushCondition = BrushCondition(BrushColor.RED, BrushWidth(10f))

        // when
        val actual = brushCondition.changeWidth(BrushWidth(20f))

        // then
        val expected = BrushCondition(BrushColor.RED, BrushWidth(20f))

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `붓 색깔이 빨강에서 파랑으로 변경하면 붓 색깔만 변경된다`() {
        // given
        val brushCondition = BrushCondition(BrushColor.RED, BrushWidth(10f))

        // when
        val actual = brushCondition.changeColor(BrushColor.BLUE)

        // then
        val expected = BrushCondition(BrushColor.BLUE, BrushWidth(10f))

        assertThat(actual).isEqualTo(expected)
    }
}
