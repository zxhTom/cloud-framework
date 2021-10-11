var vm = new Vue({
    el:"#modules",
    data(){
        return {
            cardchange:false,
            moduleList:[]
        }
    },
    created:function(){
        this.getModules();
        this.$refs.bodyHeight.height = document.body.clientHeight
    },
    methods: {
        jumpToModule:function(url){
            window.location=url;
        },
        getModules:function () {
            $.ajax({
                type: 'get',
                url: '/framework_core/menu_init/selectModuleByCurrentUser',
                contentType: "application/json",
                success: function(r){
                    if(r.code === 0){
                        vm.cardchange=true;
                        vm.moduleList=r.data;
                    }
                }
            });
        }
    }
});