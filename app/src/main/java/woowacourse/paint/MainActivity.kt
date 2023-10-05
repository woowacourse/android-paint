package woowacourse.paint

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.ViewModelProvider
import woowacourse.paint.databinding.ActivityMainBinding
import woowacourse.paint.drawingMenu.colorSelection.ColorSelectionView
import woowacourse.paint.drawingMenu.widthSelection.WidthSelection

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val viewModel by lazy { ViewModelProvider(this)[MainViewModel::class.java] }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setupBinding()
        setupObserver()
    }

    private fun setupBinding() {
        binding.lifecycleOwner = this
        binding.mainViewModel = viewModel
    }

    private fun setupObserver() {
        initColorSelectionObserveEvent()
        initWidthSelectionObserveEvent()
    }

    private fun initColorSelectionObserveEvent() {
        viewModel.colorSelectionEvent.observe(
            this,
            this::setupColorSelection,
        )
    }

    private fun setupColorSelection(isClicked: Boolean) {
        if (isClicked) {
            addMenu(
                ColorSelectionView(
                    context = this,
                    click = viewModel::changeColor,
                ),
            )
        }
    }

    private fun initWidthSelectionObserveEvent() {
        viewModel.widthSelectionEvent.observe(
            this,
            this::setupWidthSelection,
        )
    }

    private fun setupWidthSelection(isClicked: Boolean) {
        if (isClicked) {
            addMenu(
                WidthSelection(
                    context = this,
                    onClickWidthListener = viewModel::changeWidth,
                    initialValue = viewModel.width.value ?: DEFAULT_WIDTH_VALUE,
                ),
            )
        }
    }

    private fun addMenu(menu: ConstraintLayout) {
        binding.layoutMenu.removeAllViews()
        binding.layoutMenu.addView(menu)
    }

    companion object {
        private const val DEFAULT_WIDTH_VALUE = 0f
    }
}
