package woowacourse.paint

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import woowacourse.paint.ui.PaintScreen

class MainActivity : ComponentActivity() {
    private val drawingViewModel: DrawingViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            PaintTheme {
                val drawingState by drawingViewModel.state.collectAsStateWithLifecycle()
                PaintScreen(
                    state = drawingState,
                    onColorSelected = drawingViewModel::selectColor,
                    onBrushWidthChanged = drawingViewModel::changeBrushWidth,
                    onStrokeStarted = drawingViewModel::startStroke,
                    onStrokePointsAdded = drawingViewModel::addStrokePoints,
                    onStrokeFinished = drawingViewModel::finishStroke,
                )
            }
        }
    }
}

@Composable
@Suppress("ktlint:standard:function-naming")
private fun PaintTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = if (isSystemInDarkTheme()) darkColorScheme() else lightColorScheme(),
        content = content,
    )
}
