package com.oasis.composebasic.note

/**
 * 布局
 */

/// 修饰符的作用类似于基于视图的布局参数借助修饰符，可以修饰或扩充可组合项
/// 1. 更改组合项的大小，布局，行为和外观
//  2. 添加信息，如无障碍标签
//  3. 处理用户输入
//  4. 使元素可点击，可滚动，可拖动，可缩放


/// 槽位API 坑位已经留好了
//      只需要按照位置添加子元素即可
//      Scaffold 最常见的顶级Material组件(为 TopAppBar BottomAppBar FloatingActionButton Drawer 提供槽位)


/// 已知用例的个数，并且不需要滚动，可以使用简单的Column/Row。
// 如果需要显示大量列表想(长度未知的列表项)，可以使用LazyColumn/LazyRow。
// 事件导致State发生改变，然后触发UI的重绘，进行界面刷新


/// 自定义布局
// 使用布局修饰符 来修改元素的测量和布局方式
