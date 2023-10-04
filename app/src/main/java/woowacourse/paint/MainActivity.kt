package woowacourse.paint

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import woowacourse.paint.customView.colorSelection.ColorSelectionView
import woowacourse.paint.customView.widthSelection.WidthSelection
import woowacourse.paint.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val viewModel by lazy { ViewModelProvider(this)[MainViewModel::class.java] }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setupBinding()

        binding.btnColorSelection.setOnClickListener {
            binding.layoutMenu.removeAllViews()
            binding.layoutMenu.addView(
                ColorSelectionView(
                    context = this,
                    click = viewModel::changeColor,
                ),
            )
        }

        binding.btnPenWidth.setOnClickListener {
            binding.layoutMenu.removeAllViews()
            binding.layoutMenu.addView(
                WidthSelection(
                    context = this,
                    listener = viewModel::changeWidth,
                    initialValue = viewModel.width.value ?: 0f,
                ),
            )
        }
    }

    private fun setupBinding() {
        binding.lifecycleOwner = this
        binding.mainViewModel = viewModel
    }
}
