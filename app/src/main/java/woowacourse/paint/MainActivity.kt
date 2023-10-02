package woowacourse.paint

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import woowacourse.paint.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding()
        setContentView(binding.root)

        initPenTool()
    }

    private fun initBinding() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        binding.lifecycleOwner = this
        binding.mainViewModel = viewModel
    }

    private fun initPenTool() {
        val selectedPen = binding.penToolView.selectedPen
        binding.penToolView.setPenChangeClickEvent {
            binding.penToolView.visibility = View.GONE
            binding.paintView.pen = selectedPen
        }
    }
}
