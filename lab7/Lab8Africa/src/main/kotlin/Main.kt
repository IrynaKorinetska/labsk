import androidx.compose.ui.graphics.toComposeImageBitmap
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import org.jetbrains.skia.Image
import ui.MainScreen
import java.net.URL
import androidx.compose.ui.graphics.ImageBitmap

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Країни Африки"
    ) {
        MainScreen()
    }
}

// Допоміжна функція для завантаження прапорів
fun loadPicture(url: String): ImageBitmap {
    return try {
        val bytes = URL(url).readBytes()
        Image.makeFromEncoded(bytes).toComposeImageBitmap()
    } catch (e: Exception) {
        ImageBitmap(1, 1) // Заглушка, якщо немає картинки
    }
}