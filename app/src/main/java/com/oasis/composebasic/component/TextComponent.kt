package com.oasis.composebasic.component

import android.util.Log
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.oasis.composebasic.R

/**
 * 基础功能
 */
@Composable
fun TextSample(modifier: Modifier = Modifier) {
//    Text(text = "hello oasis")
    Text(
        text = stringResource(id = R.string.text),// 通过values中获取文本
        color = colorResource(R.color.teal_200), // 设置文本颜色
        fontSize = TextUnit(
            dimensionResource(R.dimen.font_size).value,
            TextUnitType.Sp
        ), // 设置文本的字体大小
        fontWeight = FontWeight.Bold, // 设置风格
        fontFamily = FontFamily.Monospace, // 设置字体
        letterSpacing = TextUnit(10f, TextUnitType.Sp), // 文字间距
//        textDecoration = TextDecoration.Underline, // 下划线&删除线
//        textDecoration = TextDecoration.combine(
//            listOf(
//                TextDecoration.Underline,
//                TextDecoration.LineThrough
//            )
//        ), // 下划线加上删除线
        textAlign = TextAlign.Start, // 文本基于父容器位置
        lineHeight = 30.sp, // 设置行高
        maxLines = 1, //设置最大行数
        overflow = TextOverflow.Ellipsis, // 显示不下时的处理方式
        style = TextStyle() // 通过style也可以设置上面的那些属性
    )
}

@Composable
fun TextSample2(modifier: Modifier = Modifier) {
//    SelectionContainer { // 文字选择
//        Text(
//            text = stringResource(id = R.string.text),// 通过values中获取文本
//            color = colorResource(R.color.teal_200), // 设置文本颜色
//        )
//    }
    // 富文本点击
    val annotatedString = buildAnnotatedString {
        append("点击登录即代表已悉知和同意")
        // 向字符串中添加注解, tag为标识
        pushStringAnnotation("protocol", "https://protocol")
        withStyle(style = SpanStyle(Color.Blue, textDecoration = TextDecoration.Underline)) {
            append("用户协议")
        }
        pop()

        append("和")

        pushStringAnnotation("privacy", "https://privacy")
        withStyle(style = SpanStyle(Color.Blue, textDecoration = TextDecoration.Underline)) {
            append("隐私政策")
        }
        pop()
    }

    ClickableText(text = annotatedString, onClick = { offset ->
        // 从字符串中根据tag查找注解
        annotatedString.getStringAnnotations("protocol", start = offset, end = offset).firstOrNull()
            ?.let {
                Log.i("====", "TextSample2: ${it.tag} ${it.item}")
            }

        annotatedString.getStringAnnotations("privacy", start = offset, end = offset).firstOrNull()
            ?.let {
                Log.i("====", "TextSample2: ${it.tag} ${it.item}")
            }
    })
}

@Preview(widthDp = 100)
@Composable
private fun previewSample() {
    TextSample()
}