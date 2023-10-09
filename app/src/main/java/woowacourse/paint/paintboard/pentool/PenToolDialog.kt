package woowacourse.paint.paintboard.pentool

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import com.google.android.material.slider.RangeSlider
import woowacourse.paint.R
import woowacourse.paint.databinding.DialogPenToolBinding
import woowacourse.paint.paintboard.PaintBoard.Companion.DEFAULT_BRUSH_WIDTH
import woowacourse.paint.paintboard.pentool.PenColors.Companion.DEFAULT_COLOR_POSITION

class PenToolDialog(
    private val coordinateY: Int,
    private val getPenColor: (colorId: Int) -> Unit,
    private val getPenWidth: (width: Float) -> Unit,
) : DialogFragment() {
    private lateinit var binding: DialogPenToolBinding
    private lateinit var adapter: PenColorAdapter
    private val penColors = PenColors()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = DialogPenToolBinding.inflate(LayoutInflater.from(context))
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initAdapter()
    }

    private fun initView() {
        setDialogSize()
        setCoordinateY()
        dialog?.window?.setBackgroundDrawableResource(R.drawable.rect_radius_12)
        binding.rsPenToolWidth.valueFrom = 0f
        binding.rsPenToolWidth.valueTo = 200f
        binding.rsPenToolWidth.stepSize = 1f
        binding.rsPenToolWidth.values = listOf(DEFAULT_BRUSH_WIDTH)
        setPenWidth()
    }

    private fun setPenWidth() {
        binding.rsPenToolWidth.addOnChangeListener(
            RangeSlider.OnChangeListener { _, value, _ ->
                getPenWidth(value)
            },
        )
    }

    private fun setDialogSize() {
        val windowManager: WindowManager =
            dialog?.context?.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val width: Int = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            (windowManager.currentWindowMetrics.bounds.width() * 0.9).toInt()
        } else {
            (windowManager.defaultDisplay.width * 0.9).toInt()
        }
        dialog?.window?.setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    private fun setCoordinateY() {
        val window: Window = requireNotNull(dialog?.window) { "PenToolDialog의 window가 null입니다" }
        window.setGravity(Gravity.TOP)
        val params: WindowManager.LayoutParams = window.attributes.apply { y = coordinateY }
        window.attributes = params
    }

    private fun initAdapter() {
        adapter = PenColorAdapter { selectColor(it) }
        binding.rvPenToolColors.adapter = adapter
        adapter.submitList(penColors.selectColor(DEFAULT_COLOR_POSITION))
    }

    private fun selectColor(position: Int) {
        adapter.submitList(penColors.selectColor(position))
        getPenColor(penColors.currentPenColor)
    }
}
