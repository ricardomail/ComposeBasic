package com.oasis.composebasic.samples

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.oasis.composebasic.R

@Composable
fun CompositionSample1() {
    MaterialTheme {
        CustomTextLabel(labelText = "lakjdflkajd klj lakjdflajd lkjljljdl")
    }
}

@Composable
fun CustomTextLabel(labelText: String) {
    Column {
        Text(text = labelText)
        CompositionLocalProvider(LocalContentColor provides Color.Green) {
            Text(text = labelText)
        }
        Text(text = labelText, color = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f))
        Text(text = labelText)
    }

}

@Composable
fun FruitText() {
    // 获取上下文并拿到resource
    val resource = LocalContext.current.resources
    val text = resource.getText(R.string.app_name)
}


/**
 * 自定义Local
 */
@Composable
fun CompositionSample3() {
    Column {
        CompositionLocalProvider(LocalElevations provides CardElevations.high) {
            MyCard(backgroundColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.05f)) { }
        }
        MyCard(backgroundColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.05f)) { }
    }
}
object CardElevations{
    val high: Elevations
        get() = Elevations(10.dp)
    val medium: Elevations
        get() = Elevations(5.dp)
    val none: Elevations
        get() = Elevations(0.dp)
}


data class Elevations(val card: Dp = 0.dp)
val LocalElevations = compositionLocalOf { Elevations() }



@Composable
fun MyCard(
    elevation: CardElevation = CardDefaults.cardElevation(defaultElevation = LocalElevations.current.card),
    backgroundColor: Color,
    content: @Composable ColumnScope.() -> Unit
) {

    Card(
        elevation = elevation,
        modifier = Modifier.size(200.dp),
        colors = CardDefaults.cardColors(containerColor = backgroundColor),
        content = content
    )
}
