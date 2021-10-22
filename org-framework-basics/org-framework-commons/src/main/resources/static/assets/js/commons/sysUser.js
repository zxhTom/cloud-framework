$(function () {
    var baseURL = "/";
    $("#jqGrid").jqGrid({
        url: baseURL + 'framework_common/sysUser/0',
        datatype: "json",
        beforeSend : function(request) {
            request.setRequestHeader('menuId', 'application/json');
        },
        colModel: [
            { label: 'userId',formatter:commonFormat,unformat:commonUnFormat, name: 'id', index: 'id', width: 50, key: true },
            { label: '用户编号',formatter:commonFormat,unformat:commonUnFormat, name: 'userCode', index: 'USER_CODE', width: 80 },
            { label: '用户名称',formatter:commonFormat,unformat:commonUnFormat, name: 'userName', index: 'USER_NAME', width: 80 },
            { label: '密码',formatter:commonFormat,unformat:commonUnFormat, name: 'password', index: 'PASSWORD', width: 80 },
            { label: '性别',formatter:commonFormat,unformat:commonUnFormat, name: 'sex', index: 'SEX', width: 80 },
            { label: '用户头像',formatter:imageFormat,unformat:commonUnFormat, name: 'headImg', index: 'HEAD_IMG', width: 20 }
        ],
        viewrecords: true,
        height: 385,
        rowNum: 10,
        rowList : [10,30,50],
        rownumbers: true,
        rownumWidth: 25,
        autowidth:true,
        multiselect: true,
        pager: "#jqGridPager",
        jsonReader : {
            root: "datas",
            page: "pageNumber",
            total: "totalPages",
            records: "totalRecords"
        },
        prmNames : {
            page:"pageNumber",
            rows:"pageSize",
            order: "order"
        },
        gridComplete:function(){
            //隐藏grid底部滚动条
            $("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" });
        }
    });
});


var vm = new Vue({
    el:'#rrapp',
    data:{
        showList: true,
        title: "testuser",
        sysUser: {},
        sysUserModalId:false,
        loading:true,
        newPassword:false
    },
    methods: {
        query: function () {
            vm.reload();
        },
        add: function(){
            vm.sysUserModalId = true;
            vm.newPassword=false;
            vm.title = "新增";
            vm.sysUser = {};
        },
        update: function (event) {
            vm.newPassword=true;
            var userId = getSelectedRow();
            if(userId == null){
                return ;
            }
            vm.sysUserModalId = true;
            vm.title = "修改";

            vm.getInfo(userId)
        },
        saveOrUpdate: function (event) {
            var url = "framework_common/sysUser/insertUpdate";
            var ajaxType = "POST";
            var userList=new Array()
            userList.push(vm.sysUser);
            $.ajax({
                type: ajaxType,
                url: baseURL + url,
                contentType: "application/json",
                data: JSON.stringify(userList),
                success: function(r){
                    if(r > 0){
                        alert('操作成功', function(index){
                            vm.reload();
                            vm.sysUserModalId = false;
                        });
                    }else{
                        alert(r.message);
                        vm.loading = false;
                        setTimeout(() =>{
                            vm.$nextTick(() => {
                                vm.loading = true
                            })},20);
                    }
                }
            });
        },
        del: function (event) {
            var userIds = getSelectedRows();
            if(userIds == null){
                return ;
            }

            confirm('确定要删除选中的记录？', function(){
                $.ajax({
                    type: "DELETE",
                    url: baseURL + "framework_common/sysUser/deleteBatch",
                    contentType: "application/json",
                    data: JSON.stringify(userIds),
                    success: function(r){
                        if(r > 0){
                            alert('操作成功', function(index){
                                $("#jqGrid").trigger("reloadGrid");
                            });
                        }else{
                            alert(r.msg);
                        }
                    }
                });
            });
        },
        getInfo: function(userId){
            $.get(baseURL + "framework_common/sysUser/"+userId,{"pageNumber":1,  "pageSize":1}, function(r){
                vm.sysUser = r.datas[0];
            });
        },
        reload: function (event) {
            vm.showList = true;
            var page = $("#jqGrid").jqGrid('getGridParam','page');
            $("#jqGrid").jqGrid('setGridParam',{
                page:page
            }).trigger("reloadGrid");
        }
    }
});