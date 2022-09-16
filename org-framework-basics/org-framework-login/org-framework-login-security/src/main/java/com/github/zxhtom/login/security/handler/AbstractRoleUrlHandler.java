package com.github.zxhtom.login.security.handler;

import org.springframework.beans.factory.InitializingBean;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.login.security.handler
 * @date 2021/10/9 13:25
 */
public abstract class AbstractRoleUrlHandler<T,R> implements RoleUrlHandler<T,R>, InitializingBean {
    /**
     * 前执行节点
     */
    private RoleUrlHandler preFlow;
    /**
     * 后执行节点
     */
    private RoleUrlHandler nextFlow;

    /**
     * 结果收集器
     */
    private List<Map<Object, Object>> resultCollector = new ArrayList<>();

    @Override
    public RoleUrlHandler getPreFlow() {
        return preFlow;
    }

    @Override
    public RoleUrlHandler setPreFlow(RoleUrlHandler preFlow) {
        this.preFlow = preFlow;
        return preFlow;
    }

    @Override
    public RoleUrlHandler getNextFlow() {
        return nextFlow;
    }

    @Override
    public RoleUrlHandler setNextFlow(RoleUrlHandler nextFlow) {
        this.nextFlow = nextFlow;
        return nextFlow;
    }

    @Override
    public boolean hasNext() {
        return nextFlow!=null;
    }

    public List<Map<Object, Object>> getResultCollector() {
        return resultCollector;
    }

    public void setResultCollector(List<Map<Object, Object>> resultCollector) {
        this.resultCollector = resultCollector;
    }


    /**
    * @author zxhtom
    * @Description 子类实现具体解析步骤
    * @Date 13:38 2021/10/9
    */
    public abstract R run(T t);

    /**
    * @author zxhtom
    * @Description 
    * @Date 13:37 2021/10/9
     * @param t
    */
    @Override
    public R execute(T t) {
        R run = run(t);
        if (run ==null&&this.hasNext()) {
            return (R) this.getNextFlow().execute(t);
        }
        return run;
    }

    public Class getInstanceClass() {
        Type type = this.getClass().getGenericSuperclass();
        String typeName = type.getTypeName();
        Type[] actualTypeArguments = ((ParameterizedType) type).getActualTypeArguments();
        if (null==actualTypeArguments||actualTypeArguments.length==0) {
            return null;
        }
        String fanxingName = actualTypeArguments[0].getTypeName();
        try {
            return Class.forName(fanxingName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        HandlerFactory.getInstance().registerHandler(this);
    }
}
