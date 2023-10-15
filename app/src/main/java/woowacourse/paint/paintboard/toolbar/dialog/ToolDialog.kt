package woowacourse.paint.paintboard.toolbar.dialog

import android.app.Dialog
import android.content.Context
import android.os.Build
import android.view.Gravity
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import androidx.databinding.ViewDataBinding
import woowacourse.paint.R
import woowacourse.paint.paintboard.common.Brush

abstract class ToolDialog(context: Context) {
    private val dialog = Dialog(context)
    abstract val binding: ViewDataBinding
    abstract val brush: Brush

    protected fun initView() {
        setDialogSize()
        dialog.window?.setBackgroundDrawableResource(R.drawable.rect_radius_12)
        dialog.setContentView(binding.root)
    }

    private fun setDialogSize() {
        val windowManager: WindowManager =
            dialog.context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val width: Int = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            (windowManager.currentWindowMetrics.bounds.width() * 0.9).toInt()
        } else {
            (windowManager.defaultDisplay.width * 0.9).toInt()
        }
        dialog.window?.setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    fun show() {
        dialog.show()
    }

    fun setCoordinateY(posY: Int) {
        val window: Window = requireNotNull(dialog.window) { "PenToolDialog의 window가 null입니다" }
        window.setGravity(Gravity.TOP)
        val params: WindowManager.LayoutParams = window.attributes.apply { y = posY }
        window.attributes = params
    }
}
