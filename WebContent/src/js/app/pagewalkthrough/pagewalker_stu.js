/**
 * 学生引导
 */
	$(function() {
    // 设置参数
    $('body').pagewalkthrough({
        name: 'introduction',
        steps: [{
            popup: { //定义弹出提示引导层
                content: '欢迎亲光临计算机学院实训系统，由小疯我为大家做指引，来了解系统使用^_^',
                type: 'modal'
            }
        },
        {
            wrapper: '#js-shixun',
            //当前引导对应的元素位置
            popup: {
                content: '相应的周总结信息和实训文档信息',
                //关联的内容元素
                type: 'tooltip',
                //弹出方式（tooltip和modal以及nohighlight）
                position: 'right', //弹出层位置（top,left, right or bottom）
            }
        },
        {
            wrapper: '#js-password',
            popup: {
                content: '在这里修改密码',
                type: 'tooltip',
                position: 'bottom'
            }
        },
        {
            wrapper: '.js-weeknum',
            popup: {
                content: '显示是否开始实训，开始实训时显示当前周数例如(第一周)',
                type: 'tooltip',
                position: 'right'
            }
        },
        {
            wrapper: '#js-outschool',
            popup: {
                content: '在这里申请校外实训',
                type: 'tooltip',
                position: 'right'
            }
        }]
    });
 
    // 一步一步显示引导页
    $('body').pagewalkthrough('show');	
});