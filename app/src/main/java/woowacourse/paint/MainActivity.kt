package woowacourse.paint

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import woowacourse.paint.databinding.ActivityMainBinding
import woowacourse.paint.utils.setVisible
import woowacourse.paint.view.BrushType
import woowacourse.paint.view.Paint
import woowacourse.paint.view.PaletteAdapter
import woowacourse.paint.view.PanelType

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val adapter by lazy {
        PaletteAdapter {
            binding.drawingPaper.changeColor(it.color)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initDrawingPaper()
        initSlider()
        initAdapter()
        initListeners()
    }

    private fun initDrawingPaper() {
        val sliderPos = binding.mySlider.sliderPosition
        binding.drawingPaper.changeStrokeWidth(sliderPos)
        updatePanelVisibility()
    }

    private fun initSlider() {
        binding.mySlider.setOnSliderChangeListener {
            binding.drawingPaper.changeStrokeWidth(it)
        }
    }

    private fun initAdapter() {
        binding.rvPalette.adapter = adapter
//        binding.rvPalette.setHasFixedSize(true)
        adapter.submitList(Paint.defaults)
    }

    private fun initListeners() {
        // Clear, Undo, Redo 버튼
        binding.btnUndo.setOnClickListener {
            binding.drawingPaper.undo()
        }
        binding.btnRedo.setOnClickListener {
            binding.drawingPaper.redo()
        }
        binding.btnClearAll.setOnClickListener {
            binding.drawingPaper.clearAll()
        }
        // brush Tool 버튼
        binding.btnPen.setOnClickListener {
            binding.drawingPaper.changeBrushType(BrushType.PEN)
        }

        binding.btnRect.setOnClickListener {
            binding.drawingPaper.changeBrushType(BrushType.RECTANGLE)
        }

        binding.btnCircle.setOnClickListener {
            binding.drawingPaper.changeBrushType(BrushType.CIRCLE)
        }

        binding.btnEraser.setOnClickListener {
            binding.drawingPaper.changeBrushType(BrushType.ERASER)
        }
        // brush Tool 변경 버튼
        binding.btnBrushColor.setOnClickListener {
            binding.drawingPaper.changePanelType(PanelType.BRUSH_COLOR)
            updatePanelVisibility()
        }
        binding.btnBrushStroke.setOnClickListener {
            binding.drawingPaper.changePanelType(PanelType.BRUSH_STROKE)
            updatePanelVisibility()
        }
        binding.btnBrushChange.setOnClickListener {
            binding.drawingPaper.changePanelType(PanelType.BRUSH_TOOL)
            updatePanelVisibility()
        }
    }

    private fun updatePanelVisibility() {
        val panelType = binding.drawingPaper.panelType
        binding.rvPalette.setVisible(panelType == PanelType.BRUSH_COLOR)
        binding.mySlider.setVisible(panelType == PanelType.BRUSH_STROKE)
        binding.toolGroup.setVisible(panelType == PanelType.BRUSH_TOOL)
    }
}
