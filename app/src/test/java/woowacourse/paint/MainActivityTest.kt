package woowacourse.paint

import android.os.SystemClock
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import androidx.core.view.get
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.google.android.material.slider.Slider
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotSame
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import woowacourse.paint.databinding.ActivityMainBinding
import woowacourse.paint.view.MainActivity
import woowacourse.paint.view.PaintView
import woowacourse.paint.view.PaintViewModel
import woowacourse.paint.view.model.mapper.DrawingsMapper.toDomain
import woowacourse.paint.view.model.pen.EllipsePen
import woowacourse.paint.view.model.pen.RectPen

@RunWith(RobolectricTestRunner::class)
class MainActivityTest {
    @get:Rule
    val activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun `PaintView를 드래그하면 PaintViewModel의 lines LiveData에 Inks 객체가 추가된다`() {
        activityScenarioRule.scenario.onActivity { activity ->
            // given
            val paintViewModel = ViewModelProvider(activity)[PaintViewModel::class.java]
            val paintView = activity.findViewById<PaintView>(R.id.main_paint_view)

            // when
            drag(paintView)

            // then
            val expected = 1
            val actual = paintViewModel.lines.getOrAwaitValue().value.size
            assertEquals(expected, actual)
        }
    }

    @Test
    fun `선을 선택하고 굵기를 30으로 조정하고 PaintView를 드래그하면 PaintViewModel의 lines LiveData에 굵기가 30인 Line이 추가된다`() {
        activityScenarioRule.scenario.onActivity { activity ->
            // given
            val paintViewModel = ViewModelProvider(activity)[PaintViewModel::class.java]
            val paintView = activity.findViewById<PaintView>(R.id.main_paint_view)
            activity.findViewById<Button>(R.id.main_line_pen_btn).callOnClick()
            activity.findViewById<Slider>(R.id.main_width_slider).value = 30F
            activity.executePendingDataBinding<ActivityMainBinding>()

            // when
            drag(paintView)

            // then
            val expected = 30F
            val actual = paintViewModel.lines.getOrAwaitValue().value[0].paint.strokeWidth
            assertEquals(expected, actual)
        }
    }

    @Test
    fun `선을 선택하고 색을 노란색으로 설정하고 PaintView를 드래그하면 PaintViewModel의 lines LiveData에 색이 노란색인 Line이 추가된다`() {
        activityScenarioRule.scenario.onActivity { activity ->
            // given
            val paintViewModel = ViewModelProvider(activity)[PaintViewModel::class.java]
            val paintView = activity.findViewById<PaintView>(R.id.main_paint_view)
            activity.findViewById<Button>(R.id.main_line_pen_btn).callOnClick()
            activity.findViewById<RecyclerView>(R.id.main_palettes)[2].callOnClick()
            activity.executePendingDataBinding<ActivityMainBinding>()

            // when
            drag(paintView)

            // then
            val expected = 0xFFFFFF00.toInt()
            val actual = paintViewModel.lines.getOrAwaitValue().value[0].paint.color
            assertEquals(expected, actual)
        }
    }

    @Test
    fun `사각형을 선택하면 PaintViewModel의 pen LiveData에 사각형 펜으로 변경된다`() {
        activityScenarioRule.scenario.onActivity { activity ->
            // given
            val paintViewModel = ViewModelProvider(activity)[PaintViewModel::class.java]

            // when
            activity.findViewById<Button>(R.id.main_rect_pen_btn).callOnClick()
            activity.executePendingDataBinding<ActivityMainBinding>()

            // then
            val expected = true
            val actual = paintViewModel.pen.getOrAwaitValue() is RectPen
            assertEquals(expected, actual)
        }
    }

    @Test
    fun `타원을 선택하면 PaintViewModel의 pen LiveData에 타원 펜으로 변경된다`() {
        activityScenarioRule.scenario.onActivity { activity ->
            // given
            val paintViewModel = ViewModelProvider(activity)[PaintViewModel::class.java]

            // when
            activity.findViewById<Button>(R.id.main_ellipse_pen_btn).callOnClick()
            activity.executePendingDataBinding<ActivityMainBinding>()

            // then
            val expected = true
            val actual = paintViewModel.pen.getOrAwaitValue() is EllipsePen
            assertEquals(expected, actual)
        }
    }

    @Test
    fun `전체 지우기를 선택하면 PaintViewModel의 lines LiveData Size가 0이 된다`() {
        activityScenarioRule.scenario.onActivity { activity ->
            // given
            val paintViewModel = ViewModelProvider(activity)[PaintViewModel::class.java]
            val paintView = activity.findViewById<PaintView>(R.id.main_paint_view)
            drag(paintView)

            // when
            activity.findViewById<Button>(R.id.main_clear_btn).callOnClick()

            // then
            val expected = 0
            val actual = paintViewModel.lines.getOrAwaitValue().value.size
            assertEquals(expected, actual)
        }
    }

    @Test
    fun `PaintView를 드래그하고 되돌리기를 선택하면 PaintViewModel의 lines LiveData가 드래그하기 이전 상태로 돌아간다`() {
        activityScenarioRule.scenario.onActivity { activity ->
            // given
            val paintViewModel = ViewModelProvider(activity)[PaintViewModel::class.java]
            val paintView = activity.findViewById<PaintView>(R.id.main_paint_view)
            drag(paintView)
            val expected = paintViewModel.lines.getOrAwaitValue().toDomain()
            drag(paintView)
            assertNotSame(expected, paintViewModel.lines.getOrAwaitValue().toDomain())

            // when
            activity.findViewById<Button>(R.id.main_undo_btn).callOnClick()

            // then
            val actual = paintViewModel.lines.getOrAwaitValue().toDomain()
            assertEquals(expected, actual)
        }
    }

    @Test
    fun `PaintView를 드래그하고 되돌리기 한 후 되돌리기 취소를 선택하면 PaintViewModel의 lines LiveData가 드래그 한 상태로 돌아간다`() {
        activityScenarioRule.scenario.onActivity { activity ->
            // given
            val paintViewModel = ViewModelProvider(activity)[PaintViewModel::class.java]
            val paintView = activity.findViewById<PaintView>(R.id.main_paint_view)
            drag(paintView)
            val expected = paintViewModel.lines.getOrAwaitValue().toDomain()
            activity.findViewById<Button>(R.id.main_undo_btn).callOnClick()

            // when
            activity.findViewById<Button>(R.id.main_redo_btn).callOnClick()

            // then
            val actual = paintViewModel.lines.getOrAwaitValue().toDomain()
            assertEquals(expected, actual)
        }
    }

    private fun drag(view: View) {
        val downTime = SystemClock.uptimeMillis()
        view.dispatchTouchEvent(
            MotionEvent.obtain(downTime, downTime, MotionEvent.ACTION_DOWN, 10F, 10F, 0)
        )
        view.dispatchTouchEvent(
            MotionEvent.obtain(downTime, downTime, MotionEvent.ACTION_MOVE, 15F, 10F, 0)
        )
        view.dispatchTouchEvent(
            MotionEvent.obtain(downTime, downTime, MotionEvent.ACTION_UP, 10F, 10F, 0)
        )
    }
}
