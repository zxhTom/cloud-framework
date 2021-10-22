package com.github.zxhtom.common.aspect;

import com.github.zxhtom.common.expressin.RootChoiceExpression;
import com.github.zxhtom.common.model.MethodInfo;
import com.github.zxhtom.core.annotaion.SuperDirection;
import com.github.zxhtom.core.annotaion.SuperDirectionHandler;
import com.github.zxhtom.core.constant.MaltcloudConstant;
import com.github.zxhtom.web.auths.OnlineSecurity;
import com.github.zxhtom.web.context.ApplicationContextUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.common.aspect
 * @date 2021/10/13 13:52
 * @description
 *
 * 该切面旨在减少业务条件分支，该切面配合表达式可以实现条件分支细化到多类上
 * 比如我们查询菜单需要根据权限，但是系统有内置超级管理员又不想在权限菜单关联中配置，作为系统内置功能的话
 * 正常开发我们需要在业务中if判断下如何操作
 *         if (SecurityUtils.getSubject().hasRole(RoleList.SUPERADMIN)) {
 *             listemp = customMapper.selectRootMenusByRoleIdList(null,null, null, null);
 *         } else {
 *             listemp = customMapper.selectRootMenusByRoleIdList(roleList, oauthClientId,null, moduleCodes);
 *         }
 *  而通过本切面我们业务只需要专注正常根据角色查询菜单的需求了，这种超级管理员查询业务的特殊需求我们可以单独新建一个业务类实现，这样两种需求两种实现无需耦合
 */
@Aspect
@Slf4j
public class MoreChoiceAspect {

    @Autowired
    RootChoiceExpression expression;
    @Autowired
    OnlineSecurity onlineSecurity;

    /**定义一个切点; 拦截所有带有SuperDirection注解的类和方法*/
    @Pointcut("@annotation(com.github.zxhtom.core.annotaion.SuperDirection) || @within(com.github.zxhtom.core.annotaion.SuperDirection)")
    public void direction() {

    }

    @Around("direction()")
    public Object aroud(ProceedingJoinPoint pjp) throws Throwable {
        Class<?>[] inters = pjp.getTarget().getClass().getInterfaces();
        for (Class<?> inter : inters) {
            Map<String, ?> beansOfType = ApplicationContextUtil.getApplicationContext().getBeansOfType(inter);
            Map<String, Object> beansWithAnnotation = ApplicationContextUtil.getApplicationContext().getBeansWithAnnotation(SuperDirectionHandler.class);
            for (Map.Entry<String, ?> entry : beansOfType.entrySet()) {
                if (beansWithAnnotation.containsKey(entry.getKey())) {
                    try {
                        return doOthersHandler(entry.getValue(), pjp);
                    } catch (Exception e) {
                        log.error("分支执行失败，系统判定执行原有分支....");
                    }
                }
            }
        }
        return pjp.proceed();
    }

    private Object doOthersHandler(Object value, ProceedingJoinPoint pjp) throws Throwable {
        // 获取当前拦截方法的对象
        MethodSignature msig = (MethodSignature) pjp.getSignature();
        Method targetMethod = value.getClass().getDeclaredMethod(msig.getName(),msig.getParameterTypes());
        SuperDirection superDirection = null;
        superDirection = targetMethod.getAnnotation(SuperDirection.class);
        if (superDirection == null) {
            superDirection = pjp.getTarget().getClass().getAnnotation(SuperDirection.class);
        }
        if(selectAnnotationChoiceDo(superDirection)){
            return targetMethod.invoke(value,pjp.getArgs());
        }
        return pjp.proceed();
    }

    private boolean selectAnnotationChoiceDo(SuperDirection superDirection) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String value = superDirection.value();
        if (StringUtils.isEmpty(value)) {
            return onlineSecurity.getRoleNames().contains(MaltcloudConstant.SUPERADMIN);
        }
        MethodInfo info = selectInfoFromExpression(value);
        Method declaredMethod = expression.getClass().getDeclaredMethod(info.getMethodName(), String.class);
        Object invoke = declaredMethod.invoke(expression, info.getArgs());
        if (invoke != null && invoke.toString().equals("true")) {
            return true;
        }
        return false;
    }

    public MethodInfo selectInfoFromExpression(String value) {
        MethodInfo info = new MethodInfo();
        String regex = "(?<methodName>\\w+)\\((?<args>\\w+,{0,1})+\\)";
        Pattern compile = Pattern.compile(regex);
        Matcher matcher = compile.matcher(value);
        if (matcher.find()) {
            String methodName = matcher.group("methodName");
            String patternArgs = matcher.group("args");
            info.setMethodName(methodName);
            info.setArgs(patternArgs.split(","));
        }
        return info;
    }

}
