// ******************* 初始化 *****************************
// 默认绑定菜单事件
$(function () {

    // 绑定所有的菜单链接点击事件，根据不同的URL加载页面
    $("#menu li a[url]").click(function () {
        // 加载页面
        $initContainer.load($(this).attr("url"));
    });

    // 头部导航栏选中切换事件
    var $menu = $('#menu > li');
    $menu.click(function () {
        $menu.removeClass('active');
        $(this).addClass('active');
    });

    // 显示主页
    backIndexPage();
});