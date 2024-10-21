package com.oasis.composebasic.note

/**
 * 参数传递
 * 1. 显式传参
 *  按层级层层传递，繁琐，但数据隔离
 * 2. 隐式传参
 *  定义全局变量，一旦修改，接下来全改，但方便
 *  provider("color", content: () -> Unit)
 *
 *  compose中对于广泛使用的常用数据(比如颜色或类型样式)，使用CompositionLocal
 *  可以让你创建以树为作用域的具名对象，作为隐式传参
 *
 *  可以通过MaterialTheme的colors，shapes，typography属性访问的LocalColors,LocalShapes,
 *  LocalTypography属性
 *
 *
 *  compositionLocalOf
 *  如果更改提供的值，会使读取其current值的组件发生重组
 *  staticCompositionLocalOf
 *  更改值会导致整个Content Lambda被重组，而不仅仅使读取current值的组件，如果为CompositionLocal提供的值发生
 *  更改的可能性微乎其微，使用这种方式可提高性能
 *
 *
 */