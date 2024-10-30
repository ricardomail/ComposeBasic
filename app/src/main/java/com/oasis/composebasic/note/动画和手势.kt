package com.oasis.composebasic.note

/**
 * 动画
 *
 * 1. 简单值动画
 * val backgroundColor by animateColorAsState(value)
 * 2. 可见性动画
 * AnimatedVisibility(value) { view }
 * 3. 内容大小动画
 * Modifier.AnimateContentSize
 * 4. 多值动画
 *  transition可以添加子动画
 *  val transition = updateTransition(tabPage, lab = "")
 *  val indicatorLeft by transition.animateDp(label = "", transitionSpec = {
 *      if (TabPage.Home is TransitioningTo TabPage.Work) {
 *          从左到右
 *          spring(stiffness = Spring.StiffnessVeryLow)
 *      } else {
 *          spring(stiffness = Spring.StiffnessMedium)
 *      }
 *  }) {
 *      tabPage ->
 *      .....
 *  }
 * 5. 重复动画
        val infiniteTransition = rememberInfiniteTransition(label = "")
        val alpha by infiniteTransition.animateFloat(
                initialValue = 0f,
                targetValue = 1f,
                animationSpec = infiniteRepeatable(animation = keyframes {
                durationMillis = 1000
                1f at 500 // 500ms时为1f
                }, repeatMode = RepeatMode.Reverse), label = ""
        )
 *
 *
 *
 * 6. 手势动画
 *
private fun Modifier.swipeToDismiss(): Modifier = this.composed {
val offsetX = remember { Animatable(0f) }

pointerInput(Unit) {
// 衰减器，通常在投掷姿势后使用，用于计算投掷动画最后的位置
val decay = splineBasedDecay<Float>(this)
coroutineScope {
while (true) {
// 等待触摸按下事件
val pointerId = awaitPointerEventScope { awaitFirstDown().id }

val velocityTracker = VelocityTracker()
awaitPointerEventScope {
horizontalDrag(pointerId) { change ->
val horizontalDrag = offsetX.value + change.positionChange().x
launch {
offsetX.snapTo(horizontalDrag)
}
velocityTracker.addPosition(change.uptimeMillis, change.position)
// 消费事件，不再传递给外部
change.consume()
}
}
val velocity = velocityTracker.calculateVelocity().x
val targetOffsetX = decay.calculateTargetValue(offsetX.value, velocity)
offsetX.updateBounds(
lowerBound = -size.width.toFloat(),
upperBound = size.width.toFloat()
)
launch {
if (targetOffsetX.absoluteValue <= size.width) {
// 划回原来的位置
offsetX.animateTo(targetValue = 0f, initialVelocity = velocity)
} else {
offsetX.animateDecay(velocity, decay)
// 执行删除
}
}
}
}


}.offset { IntOffset(offsetX.value.roundToInt(), 0) }
}
 *
 *
 */


/**
 * 手势
 *
 * 点击
 * 滚动
 * 拖动
 * 滑动
 * 多点触控
 */
