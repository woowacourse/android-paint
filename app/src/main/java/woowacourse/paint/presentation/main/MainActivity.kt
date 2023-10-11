package woowacourse.paint.presentation.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import woowacourse.paint.R
import woowacourse.paint.databinding.ActivityMainBinding
import woowacourse.paint.mapper.toBrushColorUiModel
import woowacourse.paint.mapper.toBrushTypeUiModel
import woowacourse.paint.presentation.main.recyclerview.ItemAdapter
import woowacourse.paint.presentation.uimodel.BrushColorUiModel
import woowacourse.paint.presentation.uimodel.BrushTypeUiModel

class MainActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        initRecyclerView()
        subscribe()
        setListener()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_activity_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_undo -> {}
            R.id.menu_redo -> {}
            R.id.menu_remove -> {}
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initRecyclerView() {
        val colors = BrushColorUiModel.values().toList()
        val types = BrushTypeUiModel.values().toList()
        viewBinding.rvType.adapter = ItemAdapter(types) { viewModel.changeBrushType(it) }
        viewBinding.rvType.setHasFixedSize(true)
        viewBinding.rvColor.adapter = ItemAdapter(colors) { viewModel.changeBrushColor(it) }
        viewBinding.rvColor.setHasFixedSize(true)
    }

    private fun subscribe() {
        viewModel.brush.observe(this) {
            viewBinding.customCanvas.changeColor(it.brushColor.toBrushColorUiModel())
            viewBinding.customCanvas.changeStrokeWidth(it.brushWidth)
            viewBinding.customCanvas.changeType(it.brushType.toBrushTypeUiModel())
        }
    }

    private fun setListener() {
        viewBinding.rangeSlider.addOnChangeListener { _, value, _ ->
            viewModel.changeBrushWidth(value)
        }
    }
}
