//生成菜单
var menuItem = Vue.extend({
    name: 'menu-item',
    props:{item:{}},
    template:[
        '<li>',
        '	<a v-if="item.menuType === 1" href="javascript:;">',
        '		<i v-if="item.icon != null" :class="item.icon"></i>',
        '		<span>{{item.name}}</span>',
        '		<i class="fa fa-angle-left pull-right"></i>',
        '	</a>',
        '	<ul v-if="item.menuType === 1" class="treeview-menu">',
        '		<menu-item :item="item" v-for="item in item.menuList"></menu-item>',
        '	</ul>',

        '	<a v-if="item.menuType === 0 && item.parentId === -1" :href="\'#\'+item.url">',
        '		<i v-if="item.icon != null" :class="item.icon"></i>',
        '		<span>{{item.name}}</span>',
        '	</a>',

        '	<a v-if="item.menuType === 0 && item.parentId != -1" :href="\'#\'+item.url"><i v-if="item.icon != null" :class="item.icon"></i><i v-else class="fa fa-circle-o"></i> {{item.name}}</a>',
        '</li>'
    ].join('')
});

//iframe自适应
$(window).on('resize', function() {
	var $content = $('.content');
	$content.height($(this).height() - 120);
	$content.find('iframe').each(function() {
		$(this).height($content.height());
	});
}).resize();

//注册菜单组件
Vue.component('menuItem',menuItem);
var vm = new Vue({
	el:'#rrapp',
	data:{
		user:{},
		sysUser:{},
		menuList:{},
		main:"main.html",
		password:'',
		newPassword:'',
        navTitle:"控制台"
	},
	methods: {
		getMenuList: function (event) {
			$.getJSON("/framework_common/menu_init/nav?moduleId=1&_"+$.now(), function(r){
				vm.menuList = r;
			});
		},
		getUser: function(){
			$.getJSON("/framework_common/user/info?_"+$.now(), function(r){
				vm.user = r;
			});
		},
		updatePassword: function(){
			layer.open({
				type: 1,
				skin: 'layui-layer-molv',
				title: "修改密码",
				area: ['550px', '270px'],
				shadeClose: false,
				content: jQuery("#passwordLayer"),
				btn: ['修改','取消'],
				btn1: function (index) {
					$.ajax({
						type: "PUT",
					    url: "/framework_common/sysUser/updatePassword",
					    data: JSON.stringify(vm.sysUser),
                        contentType: "application/json",
					    success: function(result){
							if(result.code == 0){
								layer.close(index);
								layer.alert('修改成功', function(index){
									location.reload();
								});
							}else{
                                var msg=result.message.replace('\n','</br>')
								layer.alert('<font color="red">'+msg+'</font>');
							}
						}
					});
	            }
			});
		},
        donate: function () {
            layer.open({
                type: 2,
                title: false,
                area: ['846px', '478px'],
                closeBtn: 1,
                shadeClose: false,
                content: ['https://zxhtom.gitee.io/img/aliwechat.png', 'no']
            });
        }
	},
	created: function(){
		this.getMenuList();
		this.getUser();
	},
	updated: function(){
		//路由
		var router = new Router();
		routerList(router, vm.menuList);
		router.start();
	}
});



function routerList(router, menuList){
	for(var key in menuList){
		var menu = menuList[key];
		if(menu.menuType == '1'){
			routerList(router, menu.menuList);
		}else if(menu.menuType == '0'){
			router.add('#'+menu.url, function() {
				var url = window.location.hash;
				
				//替换iframe的url
			    vm.main = url.replace('#', '');

			    //导航菜单展开
			    $(".treeview-menu li").removeClass("active");
			    $("a[href='"+url+"']").parents("li").addClass("active");
			    
			    vm.navTitle = $("a[href='"+url+"']").text();
			});
		}
	}
}
