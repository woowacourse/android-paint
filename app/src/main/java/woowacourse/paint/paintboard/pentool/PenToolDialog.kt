package woowacourse.paint.paintboard.pentool

import android.app.Dialog
import android.content.Context
import android.os.Build
import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import com.google.android.material.slider.RangeSlider
import woowacourse.paint.R
import woowacourse.paint.databinding.DialogPenToolBinding
import woowacourse.paint.paintboard.pentool.PenColors.Companion.DEFAULT_COLOR_POSITION

class PenToolDialog(
    context: Context,
    private val getSelectedColor: (colorId: Int) -> Unit,
    private val getWidth: (width: Float) -> Unit,
) {
    private val dialog = Dialog(context)
    private val binding = DialogPenToolBinding.inflate(LayoutInflater.from(context))
    private lateinit var adapter: PenColorAdapter
    private val penColors = PenColors()

    init {
        initAdapter()
        initView()
    }

    fun show() {
        dialog.show()
    }

    private fun initView() {
        setSize()
        dialog.window?.setBackgroundDrawableResource(R.drawable.rect_radius_12)
        dialog.setContentView(binding.root)
        binding.rsPenToolWidth.valueFrom = 0f
        binding.rsPenToolWidth.valueTo = 200f
        binding.rsPenToolWidth.stepSize = 1f
        setPenWidth()
    }

    private fun setPenWidth() {
        binding.rsPenToolWidth.addOnChangeListener(
            RangeSlider.OnChangeListener { _, value, _ ->
                getWidth(value)
            },
        )
    }

    private fun setSize() {
        val windowManager: WindowManager =
            dialog.context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val width: Int = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            (windowManager.currentWindowMetrics.bounds.width() * 0.9).toInt()
        } else {
            (windowManager.defaultDisplay.width * 0.9).toInt()
        }
        dialog.window?.setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    fun setPosY(posY: Int) {
        val window: Window = requireNotNull(dialog.window) { "PenToolDialog의 window가 null입니다" }
        window.setGravity(Gravity.TOP)
        val params: WindowManager.LayoutParams = window.attributes.apply { y = posY }
        window.attributes = params
    }

    private fun initAdapter() {
        adapter = PenColorAdapter { selectColor(it) }
        binding.rvPenToolColors.adapter = adapter
        adapter.submitList(penColors.selectColor(DEFAULT_COLOR_POSITION))
    }

    private fun selectColor(position: Int) {
        adapter.submitList(penColors.selectColor(position))
        getSelectedColor(penColors.currentPenColor)
    }
}
