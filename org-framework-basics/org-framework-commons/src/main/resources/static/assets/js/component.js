

/*注册组件*/
/*tree组件*/
// define the item component
Vue.component('tree-item', {
    template: '#item-template',
    props: {
        'treekey':{
            type:String,
            default:''
        },
        'vistreekey':{
            type:String,
            default:''
        },
        'ids':{
            type:Array,
            default:''
        },
        'names':{
            type:String,
            default:''
        },
        'treename':{
            type:String,
            default:''
        },
        'vistreename':{
            type:String,
            default:''
        },
        'treechildren':{
            type:String,
            default:''
        },
        model: {
            type:Object,
            default:()=>{}
        }
    },
    data: function () {
        return {
            class: 'button level1 switch center_close',
            selectClass:'',
            open: false,
            modelName:'',
            treekeys:{},
            treenames:{},
            treeshowids:'',
            treeshownames:'',
            opt:{'key':{},'name':{}},
            checkStatus:false
        }
    },
    computed: {
        isFolder: function () {
            return this.model.children &&
                this.model.children.length
        },
        openStatus: function () {
            //button level1 switch center_close
            //button level0 switch root_open
            //button ico_docu
            if (this.isFolder) {
                if (this.open) {
                    return 'button level0 switch root_open';
                } else {
                    return'button level1 switch center_close';
                }
            }
        }
    },
    watch:{
        /*ids(newmodel){
            console.log(this.ids, this.newmodel)
        }*/
    },
    created:function(){
        //接收到数据在外面套一层
        if(this.model[this.treekey]==undefined){
            this.treekey=this.vistreekey;
        }
        if(this.model[this.treename]==undefined){
            this.treename=this.vistreename;
        }
        if (this.model.disabled == true) {
            this.model.disabled = 'disabled';
        }
        console.log('组件注册了吗');
        if ((','+this.ids+',').indexOf(','+this.model[this.treekey]+',') == -1) {
            this.checkStatus = false;
            this.model.checkStatus=this.checkStatus;
        } else {
            this.checkStatus=true;
            this.model.checkStatus=this.checkStatus;
            this.treekeys[this.model[this.treekey]]= this.checkStatus;
            this.treenames[this.model[this.treename]]= this.checkStatus;
            this.opt.key=this.treekeys;
            this.opt['name']=this.treenames;
        }
        if(this.ids!=''){
            var idarr = this.ids;
            for(var index in idarr){
                this.treekeys[idarr[index]]=true;
            }
            if (this.names.indexOf(",") == -1&&this.names!='') {
                this.treenames[this.names]=true;
            }else{
                var namearr = this.names.split(",");
                for(var index in namearr){
                    this.treenames[namearr[index]]=true;
                }
            }
        }
    },
    methods: {
        keyname:function(opt){
            var newOpt ={'key':{},'name':{}};
            newOpt.key = Object.assign(this.opt.key, opt.key);
            newOpt.name = Object.assign(this.opt.name, opt.name);
            var flag=false;
            for(var index in this.model[this.treechildren]){
                if(newOpt.key[this.model[this.treechildren][index][this.treekey]]!=true){
                    flag=true;
                }
            }
            if(!flag){
                newOpt.key[this.model[this.treekey]]=true;
                newOpt.name[this.model[this.treename]]=true;
                this.checkStatus=true;
                this.model.checkStatus=true;
            }
            for(var key in newOpt){
                this.filterRealCheck(newOpt[key]);
            }
            this.opt=newOpt;
            this.$emit('keyname', newOpt);
        },
        filterRealCheck:function(obj){
            //obj {1:true,2:true,3:false}
            for(var key in obj){
                if(obj[key]==false){
                    //delete(obj[key]);
                }
            }
            //obj {1:true,2:true}
        },
        selectedObj:function(selected){
            if(selected instanceof MouseEvent){
                this.checkStatus=!this.checkStatus;
            }else{
                this.checkStatus=selected;
            }

            this.model.checkStatus=this.checkStatus;
            if (this.model.expected != true) {
                this.treekeys[this.model[this.treekey]]= this.checkStatus;
                this.treenames[this.model[this.treename]]= this.checkStatus;
                this.opt.key=this.treekeys;
                this.opt['name']=this.treenames;
            }
            for(var index in this.$refs.child){
                this.$refs.child[index].selectedObj(this.checkStatus);
            }

            this.$emit('keyname', this.opt);
        },
        focus:function(){
            this.selectClass = 'level0 curSelectedNode';
        },
        blur: function(){
            this.selectClass = '';
        },
        toggle: function () {
            //button level1 switch center_close
            //button level0 switch root_open
            if (this.isFolder) {
                this.open = !this.open;
            }
        },
        changeType: function () {
            if (!this.isFolder) {
                Vue.set(this.model, 'children', [])
                this.addChild()
                this.open = true
            }
        },
        addChild: function () {
            this.model.children.push({
                name: 'new stuff'
            })
        }
    }
})

Vue.component('zxhtree', {
    template: '#tree-template',
    props: {
        'treekey':{
            type:String,
            default:''
        },
        'vistreekey':{
            type:String,
            default:''
        },
        'ids':{
            type:String,
            default:''
        },
        'names':{
            type:String,
            default:''
        },
        'treename':{
            type:String,
            default:''
        },
        'vistreename':{
            type:String,
            default:''
        },
        'treechildren':{
            type:String,
            default:''
        },
        model: {
            type:Object,
            default:()=>{}
        }
    },
    data: function () {
        return {
            class: 'button level1 switch center_close',
            selectClass:'',
            open: false,
            modelName:'',
            treekeys:{},
            treenames:{},
            treeshowids:'',
            treeshownames:'',
            opt:{'key':{},'name':{}},
            checkStatus:false
        }
    },
    computed: {

    },
    created:function(){
    },
    methods: {
        selectKeyName:function(opt){
            for(var index in opt){
                if (opt[index].checkStatus == true) {
                    this.treekeys[opt[index][this.treekey]]= opt[index].checkStatus;
                    this.treenames[opt[index][this.treename]]= opt[index].checkStatus;
                    this.opt.key=this.treekeys;
                    this.opt['name']=this.treenames;
                }
                this.selectKeyName(opt[index][this.treechildren]);
            }
        },
        filterRealCheck:function(obj){
            //obj {1:true,2:true,3:false}
            for(var key in obj){
                if(obj[key]==false){
                    delete(obj[key]);
                }
            }
            //obj {1:true,2:true}
        },
        selectedObj:function (opt) {
            var newOpt ={'key':{},'name':{}};
            newOpt.key = Object.assign(this.opt.key, opt.key);
            newOpt.name = Object.assign(this.opt.name, opt.name);
            for(var key in newOpt){
                this.filterRealCheck(newOpt[key]);
            }
            this.opt=newOpt;
            this.$emit('keyname', newOpt);
        },
        synchdata:function (opt) {
            this.opt={'key':{},'name':{}};
            this.selectKeyName(opt);
            this.$emit('keyname', this.opt);
        }
    }
})

//折线颜色
var colors = ['#cc0033', '#ff5722', '#2196f3', '#4caf50','#AF7C7C'];
//点
var white = null;
//背景色
var background_color = '#21202D';

var my_tooltip = {
    trigger: 'axis',
    axisPointer: {
        animation: false,
        type: 'cross',
        lineStyle: {
            color: '#376df4',
            width: 2,
            opacity: 1
        }
    }
};
var my_toolbox ={
    left: 'center',
    feature: {
        dataZoom: {
            yAxisIndex: 'none'
        },
        restore: {},
        saveAsImage: {},
        magicType: {
            type:  ['line', 'bar']
        }
    }
};
/*echarts组件*/
Vue.component('tomchart',{
    template: '#chart-template',
    props:{
        domid:'ec',
        chartname:'demo',
        dataobj:[],
        xa:'data',//横坐标key值
        series:[],
        legendata:[],
        options:{}
    },
    data: function(){
        return {
            xadataobj:[],
            startValue:'',
            seriresobj:[],
            mytooltip:my_tooltip,
            mytoolbox:my_toolbox,
        }
    },
    computed:{
        option(){
            if(this.options!=undefined){
                return this.options;
            }
            //清除先
            this.xadataobj=[];
            this.dataobj.map((item)=>{
                this.xadataobj.push(item[this.xa]);
            });
            for(var serItem in this.series){
                var lineStyle={};
                lineStyle.color=colors[serItem];
                this.series[serItem].lineStyle = lineStyle;
                this.series[serItem].data = this.dataobj.map((item)=> {
                    return item[this.series[serItem].key];
                })
            }
            this.legendata = this.series.map(item=>{
                return item.name;
            })
            return {
                backgroundColor: background_color,
                title: {
                    subtext: '单位/MB',
                    textStyle: {
                        color: '#fff'
                    }
                },
                legend:{
                    left:'right',
                    textStyle:{
                        color: '#ffffff'//字体颜色
                    },
                    data:this.legendata
                },
                xAxis: {
                    axisLine: { lineStyle: { color: '#8392A5' } },
                    data: this.xadataobj,
                    nameTextStyle: {
                        color: '#fff'
                    }
                },
                yAxis: {
                    scale: true,
                    axisLine: { lineStyle: { color: '#8392A5' } },
                    splitLine: { show: false }
                },
                tooltip: this.mytooltip,
                toolbox: this.mytoolbox,
                dataZoom: [
                    {
                        startValue: this.xadataobj[0][this.xa]
                    },
                    {
                        type: 'inside'
                    }
                ],
                visualMap: [{
                    show: false,
                    inRange: {
                        color: white
                    }
                }],
                series: this.series
            };
        }
    },
    created: function(){
        //在下次 DOM 更新循环结束之后执行延迟回调。
        //在修改数据之后立即使用这个方法，获取更新后的 DOM。
        this.$nextTick(function(){
            //获取报表信息
            this.drawchat();
        })
    },
    watch:{
        dataobj(newmodel){
            console.log("重新加载")
            this.drawchat();
        }
    },
    methods:{
        drawchat() {
            //初始化echarts实例
            this.myChart = echarts.init(document.getElementById(this.domid));
            this.myChart.setOption(this.option);
        }
    }
})