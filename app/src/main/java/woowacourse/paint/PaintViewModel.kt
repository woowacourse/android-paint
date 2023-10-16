package woowacourse.paint

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import woowacourse.paint.entity.bixxdrawable.BixxDrawable
import woowacourse.paint.entity.brush.BixxBrush
import woowacourse.paint.entity.brush.BrushMode
import woowacourse.paint.entity.brush.FigureBixxBrush
import woowacourse.paint.entity.brush.LineBixxBrush
import woowacourse.paint.repository.PaintBoardRepository

class PaintViewModel(private val repository: PaintBoardRepository) : ViewModel() {
    private val _bixxDrawables = mutableListOf<BixxDrawable>()
    val bixxDrawables: List<BixxDrawable> get() = _bixxDrawables.toList()

    private lateinit var lineBrush: LineBixxBrush
    private lateinit var ovalBrush: FigureBixxBrush
    private lateinit var rectBrush: FigureBixxBrush

    private val _error = MutableLiveData<Throwable>()
    val error: LiveData<Throwable> = _error

    val brushMode = MutableLiveData<BrushMode>(BrushMode.LINE)
    val currentBixxBrush: LiveData<BixxBrush> = brushMode.map {
        when (it) {
            BrushMode.LINE -> lineBrush
            BrushMode.FIGURE -> rectBrush
        }
    }

    fun updateLineBrush(lineBrush: LineBixxBrush) {
        this.lineBrush = lineBrush
    }

    fun updateRectBrush(figureBrush: FigureBixxBrush) {
        rectBrush = figureBrush
    }

    fun updateOvalBrush(figureBrush: FigureBixxBrush) {
        ovalBrush = figureBrush
    }

    suspend fun save() {
        viewModelScope.launch {
            repository.save(bixxDrawables).getOrElse {
                println("[ERROR] ${it.message}")
                _error.value = Throwable("저장에 실패했습니다")
            }
        }
    }

    suspend fun load() {
        viewModelScope.launch {
            repository.load()
                .onSuccess {
                    _bixxDrawables.clear()
                    _bixxDrawables.addAll(it)
                }
                .onFailure {
                    println("[ERROR] ${it.message}")
                    _error.value = Throwable("그림을 불러오는데 실패했습니다")
                }
        }
    }
}
