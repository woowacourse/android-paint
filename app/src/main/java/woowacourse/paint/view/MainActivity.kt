package woowacourse.paint.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import woowacourse.paint.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val viewModel: MainViewModel by viewModels()
    private val colorSelectionAdapter: ColorSelectionAdapter by lazy {
        ColorSelectionAdapter(viewModel.colors, viewModel)
    }
    private val brushControllerAdapter: BrushControllerAdapter by lazy {
        BrushControllerAdapter(viewModel.brushes, viewModel)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.colorSelectionAdapter = colorSelectionAdapter
        binding.brushControllerAdapter = brushControllerAdapter
    }
}
