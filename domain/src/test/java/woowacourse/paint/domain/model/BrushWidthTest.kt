package woowacourse.paint.domain.model

import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class BrushWidthTest {

    @ValueSource(floats = [1f, 50f, 100f])
    @ParameterizedTest
    fun `붓 굵기는 1 이상 100 이하의 유리수이다`(value: Float) {
        // given
        assertDoesNotThrow { BrushWidth(value) }
    }

    @ValueSource(floats = [-1f, 100.1f, 1000f])
    @ParameterizedTest
    fun `붓 굵기는 1 미만이거나 100을 초과할 수 없다`(value: Float) {
        // given
        assertThrows<IllegalArgumentException> { BrushWidth(value) }
    }
}
