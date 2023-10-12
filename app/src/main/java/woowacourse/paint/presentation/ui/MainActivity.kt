package woowacourse.paint.presentation.ui

import android.os.Bundle
import androidx.activity.addCallback
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import woowacourse.paint.data.model.SettingMode
import woowacourse.paint.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setUpDataBinding()
        setUpBackPressedDispatcher()
    }

    private fun setUpDataBinding() {
        binding.vm = viewModel
        binding.lifecycleOwner = this
        binding.onColorChangeButtonClick = ::onColorChangeButtonClick
        binding.onThicknessChangeButtonClick = ::onThicknessChangeButtonClick
        binding.onBrushChangeButtonClick = ::onBrushChangeButtonClick
    }

    private fun onColorChangeButtonClick() {
        if (viewModel.uiState.value!!.settingMode == SettingMode.COLOR) {
            viewModel.finishSetting()
        } else {
            viewModel.startColorSelection()
        }
    }

    private fun onThicknessChangeButtonClick() {
        if (viewModel.uiState.value!!.settingMode == SettingMode.THICKNESS) {
            viewModel.finishSetting()
        } else {
            viewModel.startThicknessSelection()
        }
    }

    private fun onBrushChangeButtonClick() {
        if (viewModel.uiState.value!!.settingMode == SettingMode.BRUSH) {
            viewModel.finishSetting()
        } else {
            viewModel.startBrushSelection()
        }
    }

    private fun setUpBackPressedDispatcher() {
        onBackPressedDispatcher.addCallback {
            if (viewModel.uiState.value!!.settingMode == SettingMode.NONE) {
                finish()
            } else {
                viewModel.finishSetting()
            }
        }
    }
}
