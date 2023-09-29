package woowacourse.paint.paintboard

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import woowacourse.paint.R
import woowacourse.paint.databinding.DialogFileNameBinding

class FileNameDialog(
    context: Context,
    clickPositive: (name: String) -> Unit,
) {
    private val dialog = Dialog(context)
    private val binding = DialogFileNameBinding.inflate(LayoutInflater.from(context))

    init {
        dialog.window?.setBackgroundDrawableResource(R.drawable.rect_radius_12)
        dialog.setContentView(binding.root)
        binding.btnFileNamePositive.setOnClickListener {
            clickPositive(binding.etFileNameContent.text.toString())
            dialog.dismiss()
        }
        binding.btnFileNameNegative.setOnClickListener { dialog.dismiss() }
    }

    fun show() {
        dialog.show()
    }
}
