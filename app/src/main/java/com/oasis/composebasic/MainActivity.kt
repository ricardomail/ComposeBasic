package com.oasis.composebasic

import android.graphics.drawable.PaintDrawable
import android.os.Bundle
import android.provider.MediaStore.Images
import android.view.Display.Mode
import android.widget.Space
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.oasis.composebasic.component.IconSample
import com.oasis.composebasic.component.SwitchSample
import com.oasis.composebasic.component.TextFieldSample
import com.oasis.composebasic.component.TextSample
import com.oasis.composebasic.component.TextSample2
import com.oasis.composebasic.samples.CompositionSample1
import com.oasis.composebasic.samples.CompositionSample3
import com.oasis.composebasic.ui.theme.ComposeBasicTheme

/**
 * 声明式UI
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeBasicTheme(dynamicColor = false) {
//                MessageCard(Message("android", "body"))
//                LayoutStudy()
//                SimpleColumn()
//                ScrollingList()
//                TextWithPaddingToBaseline()
//                MyOwnColumn(Modifier.padding(100.dp)){
//                    Text(text = "1231231232")
//                    Text(text = "1231231232")
//                    Text(text = "1231231232")
//                    Text(text = "1231231232")}
//                ConstraintLayoutContent3()
//                CompositionSample3()
//                IconSample()
                TextFieldSample()
            }
        }
    }

    @Composable
    fun MessageCard(message: Message) {
        Row(
            modifier = Modifier
                .padding(all = 8.dp)
                .background(MaterialTheme.colorScheme.background)

        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = "background", modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape),

                alignment = Alignment.CenterStart
            )
            Spacer(modifier = Modifier.width(10.dp))

            var isExpanded by remember { mutableStateOf(false) }
            Column(modifier = Modifier.clickable { isExpanded = !isExpanded }) { // 点击事件
                Text(text = message.author, color = MaterialTheme.colorScheme.primary)
                Spacer(modifier = Modifier.height(4.dp))
                Text(message.body)
            }
        }

    }

    @Preview
    @Composable
    fun PreviewCard() {
        ComposeBasicTheme {
            MessageCard(Message("android", "body"))
        }
    }
}


data class Message(val author: String, val body: String)