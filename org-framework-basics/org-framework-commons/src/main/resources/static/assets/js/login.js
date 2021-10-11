var token = "x-token";
var flag=true;
$(document).ready(function() {
    $('#username').focus().blur(checkName);
    $('#password').blur(checkPassword);
	$("#shiroServiceUrl").attr('href',$("#shiroServiceUrl").attr('href')+"http://"+window.location.host+"/framework_core/login/oauth2");
});


function checkName(){
	var name = $('#username').val();
	if(name == null || name == ""){
		//提示错误
		$('#count-msg').html("用户名不能为空");
		flag=false;
		return false;
	}
	var reg = /^\w{3,10}$/;
	if(!reg.test(name)){
		$('#count-msg').html("输入3-10个字母或数字或下划线");
		flag=false;
		return false;
	}
	$('#count-msg').empty();
	flag=true;
	return true;
}

function checkPassword(){
	var password = $('#password').val();
	if(password == null || password == ""){
		//提示错误
		$('#password-msg').html("密码不能为空");
		flag=false;
		return false;
	}
	var reg = /^\w{3,10}$/;
	if(!reg.test(password)){
		$('#password-msg').html("输入3-10个字母或数字或下划线");
		flag=false;
		return false;
	}
	$('#password-msg').empty();
	flag=true;
	return true;
}

/*登录方法*/
function login(){
	var userName = $("#userName").val();
	var passWord = $("#password").val();
	 $.ajax({
		url:"/framework_core/login/startLogin",
		beforeSend : function(request) {
			request.setRequestHeader('Content-Type', 'application/json');
		},
		type:"POST",
		data:JSON.stringify({
			'userName':userName,
			'password':passWord
		}),
		dataType:'json',
		success:function(data){
			if(data.code==0){
				$.cookie(token, data.data[''+token+'']);
				console.log($.cookie(token));
				window.location.href="/index.html";
			}else if(data.code==1){
				alert(data.message);
			}else if(data.code==-1){
				alert("@"+data.message);
			}
		},
		error:function(data){
			alert("@@"+JSON.stringify(data))
		}
	 });
}

var vm = new Vue({
    el:'#zxhtom',
    data:{
		userName: 'admin',
		checkCode: 'aaaa',
		password: '123456',
		captcha: '',
		error: false,
		errorMsg: '',
		src: '/framework_core/login/captcha.jpg'
    },
    beforeCreate: function(){
        if(self != top){
            top.location.href = self.location.href;
        }
    },
    methods: {
        refreshCode: function(){
            this.src = "/framework_core/login/captcha.jpg?t=" + $.now();
        },
        login: function (event) {
            if (!flag) {
            	return;
            }
            $.ajax({
                url:"/framework_core/login/startLogin",
                beforeSend : function(request) {
                    request.setRequestHeader('Content-Type', 'application/json');
                },
                type:"POST",
                data:JSON.stringify({
                    'userName':this.userName,
                    'password':this.password,
					'checkCode':this.checkCode
                }),
                dataType:'json',
                success:function(data){
                    if(data.code==0){
                        $.cookie(token, data.data[''+token+'']);
                        console.log($.cookie(token));
                        if (data.data.backUrl != undefined) {
                            window.location.href=data.data.backUrl;
                        } else {

                            window.location.href="/system.html";
                        }
                    }else if(data.code==1){
                        alert(data.message);
                    }else if(data.code==-1){
                        alert("@"+data.message);
                    }
                },
                error:function(data){
                    alert("@@"+JSON.stringify(data))
                }
            });
        },
        register: function (event) {
            if (!flag) {
                return;
            }
            $.ajax({
                url:"/framework_core/login/register",
                beforeSend : function(request) {
                    request.setRequestHeader('Content-Type', 'application/json');
                },
                type:"POST",
                data:JSON.stringify({
                    'userName':this.userName,
                    'password':this.password
                }),
                dataType:'json',
                success:function(data){
                    if(data.code==0){
                        $.cookie(token, data.data[''+token+'']);
                        console.log($.cookie(token));
                        if (data.data.backUrl != undefined) {
                            window.location.href=data.data.backUrl;
                        } else {

                            window.location.href="/framework_core/login/login";
                        }
                    }else if(data.code==1){
                        alert(data.message);
                    }else if(data.code==-1){
                        alert("@"+data.message);
                    }
                },
                error:function(data){
                    alert("@@"+JSON.stringify(data))
                }
            });
        }
    }
});
