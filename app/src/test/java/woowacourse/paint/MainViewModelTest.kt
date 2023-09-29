package woowacourse.paint

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.assertj.core.api.Assertions.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import woowacourse.paint.domain.model.Brush
import woowacourse.paint.domain.model.BrushColor
import woowacourse.paint.domain.model.BrushWidth
import woowacourse.paint.presentation.ui.main.MainViewModel
import woowacourse.paint.presentation.ui.model.BrushColorModel
import woowacourse.paint.presentation.ui.model.toPresentation

class MainViewModelTest {

    private lateinit var viewModel: MainViewModel

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
        viewModel = MainViewModel()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `기본 붓은 색은 빨강이고 두께는 30이다`() {
        // given
        // when
        val actual = viewModel.brush.value

        // then
        val expected = Brush(BrushColor.RED, BrushWidth(30f)).toPresentation()

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `붓 색을 주황으로 바꾸면 주황으로 바뀐다`() {
        // given
        // when
        viewModel.changeLineColor(BrushColorModel.ORANGE)

        // then
        val actual = viewModel.brush.value
        val expected = Brush(BrushColor.ORANGE, BrushWidth(30f)).toPresentation()

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `붓 두께를 50으로 바꾸면 50으로 바뀐다`() {
        // given
        // when
        viewModel.changeLineWidth(50f)

        // then
        val actual = viewModel.brush.value
        val expected = Brush(BrushColor.RED, BrushWidth(50f)).toPresentation()

        assertThat(actual).isEqualTo(expected)
    }
}
