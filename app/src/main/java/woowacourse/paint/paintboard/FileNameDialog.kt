package woowacourse.paint.paintboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import woowacourse.paint.R
import woowacourse.paint.databinding.DialogFileNameBinding

class FileNameDialog(
    private val clickPositive: (name: String) -> Unit,
) : DialogFragment() {
    private lateinit var binding: DialogFileNameBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = DialogFileNameBinding.inflate(LayoutInflater.from(context))
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog?.window?.setBackgroundDrawableResource(R.drawable.rect_radius_12)
        setClickListeners()
    }

    private fun setClickListeners() {
        binding.btnFileNamePositive.setOnClickListener {
            clickPositive(binding.etFileNameContent.text.toString())
            dialog?.dismiss()
        }
        binding.btnFileNameNegative.setOnClickListener { dialog?.dismiss() }
    }
}
