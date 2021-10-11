var lableClazz=['label-primary','label-success','label-info','label-warning','label-danger'];
$(function() {
    $.ajaxSetup({
        beforeSend:function(xhr) {
            xhr.setRequestHeader('Authorization','Token 123')
        }
    });
});
/**系统全局变量*/
var sysUser={userId:'-3'};
/**系统全局变量*/
/**
 * 获取当前登录用户
 * @returns {*}
 */
function getCurrentUser() {
    if (sysUser != null&&sysUser.userId!=-3) {
        return sysUser;
    }
    $.ajax({
        url: '/framework_core/user/info',
        type: 'get',
        async: false,
        dataType: 'json',
        success:function (data) {
            if(data.data!=null){
                sysUser=data.data.userInfo;
            }
        },
        error:function (data) {
            console.log('error');
        }
    });
    return sysUser;
}

function getHost(){
    return 'http://'+window.location.host;
}

function contain(str, char) {
    if (str.indexOf(char) == -1) {
        return false;
    } else {
        return true;
    }
}

function array_remove_repeat(a) { // 去重
    var r = [];
    for(var i = 0; i < a.length; i ++) {
        var flag = true;
        var temp = a[i];
        for(var j = 0; j < r.length; j ++) {
            if(temp === r[j]) {
                flag = false;
                break;
            }
        }
        if(flag) {
            r.push(temp);
        }
    }
    return r;
}

function array_intersection(a, b) { // 交集
    var result = [];
    for(var i = 0; i < b.length; i ++) {
        var temp = b[i];
        for(var j = 0; j < a.length; j ++) {
            if(temp === a[j]) {
                result.push(temp);
                break;
            }
        }
    }
    return array_remove_repeat(result);
}

function array_union(a, b) { // 并集
    return array_remove_repeat(a.concat(b));
}

function array_difference(a, b) { // 差集 a - b
    //clone = a
    var clone = a.slice(0);
    for(var i = 0; i < b.length; i ++) {
        var temp = b[i];
        for(var j = 0; j < clone.length; j ++) {
            if(temp === clone[j]) {
                //remove clone[j]
                clone.splice(j,1);
            }
        }
    }
    return array_remove_repeat(clone);
}

function addCellAttr(rowId, val, rawObject, cm, rdata) {
    return "class='label-info'";
}
/**
 * jqrid单元格随机样式生成器
 * @param cellvalue
 * @param options
 * @param rowObject
 * @returns {string}
 */
function commonFormat(cellValue, options, rowObject){
    var result = "";
    if (cellValue == undefined) {
        return '';
    }
    var cellvalue=String(cellValue);
    var values=[];
    if(cellvalue.indexOf(",")){
        values = cellvalue.split(",");
    }else{
        values.push(cellvalue);
    }
    for(var index in values){
        if (values[index] == undefined) {
            continue;
        }
        var labelIndex = parseInt(Math.random()*(lableClazz.length),lableClazz.length);
        result+='<span class="label '+lableClazz[labelIndex%lableClazz.length]+'">'+values[index]+'.</span>';
    }
    return result;
}
function commonUnFormat(cellValue, options, rowObject){
    var text = cellValue.replace('.',',');
    return text.substring(0,text.length-1);
}
function imageFormat(cellValue, options, rowObject){
    var img = "";
    img = "<img class='table_actor' src='"+cellValue+"'/>";
    return img;
}
