package tw.edu.pu.csim.s1141254.canvas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.PaintingStyle.Companion.Stroke
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.delay
import tw.edu.pu.csim.s1141254.canvas.ui.theme.CanvasTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CanvasTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CanvasScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun CanvasScreen(modifier: Modifier = Modifier) {

    var CanvasWidth by remember { mutableStateOf(0f) }
    var CanvaHeight by remember { mutableStateOf(0f) }

    var minionsX by remember { mutableStateOf(0f) }
    var minionsY by remember { mutableStateOf(0f) }

    var gameOver by remember { mutableStateOf(false) }

    val minionsImage: ImageBitmap =
        ImageBitmap.imageResource(R.drawable.minions)

    LaunchedEffect(gameOver) {
        while (!gameOver) {
            delay(100L)
            minionsX += 5
            minionsY += 10
            if (minionsX > CanvasWidth || minionsY > CanvaHeight) {
                minionsX = 0f
                minionsY = 0f
            }
        }
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Canvas(
            modifier = Modifier.fillMaxSize()
        ) {
            drawCircle(Color.Magenta,
                100f,
                Offset(100f, 100f))

            drawRect(
                Color.Blue,
                Offset(300f, 200f),
                Size(200f, 200f),
                style = Stroke(width = 5f)
            )

            CanvasWidth = size.width
            CanvaHeight = size.height

            drawLine(
                Color.Red,
                Offset(200f, 800f),
                Offset(size.width, size.height),
                strokeWidth = 10f
            )

            translate(minionsX, minionsY) {
                drawImage(minionsImage)
            }
        }


        Column {
            Text("畫布寬度 $CanvasWidth,長度 $CanvaHeight")

            Button(
                onClick = {gameOver = !gameOver}
            ) {
                if (gameOver){ Text("繼續遊戲") }
                else { Text("停止遊戲") }
            }

        }


    }
}
