package com.github.zxhtom.flowable.utils;

import com.github.zxhtom.flowable.FlowableApplication;
import com.github.zxhtom.flowable.annotation.CoverRoute;
import liquibase.util.StringUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * TODO
 *
 * @author zxhtom
 * 2023/9/12
 */

public class ConverRouteUtil {
    private static HashMap<String, String> mappingRegist = new HashMap<>();

    public static void initRoute(Class runtimeClass, List<String> extraPackageNameList) {
        List<Class<?>> scanClassList = new ArrayList<>();
        if (!runtimeClass.getPackage().getName().equals(FlowableApplication.class.getPackage().getName())) {
            scanClassList.addAll(ScanUtil.getAllClassByPackageName_Annotation(runtimeClass.getPackage().getName(), CoverRoute.class));
        }
        for (String packageName : extraPackageNameList) {
            scanClassList.addAll(ScanUtil.getAllClassByPackageName_Annotation(packageName, CoverRoute.class));
        }
        for (Class clazz : scanClassList) {
            CoverRoute coverRoute = (CoverRoute) clazz.getAnnotation(CoverRoute.class);
            if (StringUtil.isEmpty(coverRoute.value())) {
                continue;
            }
            RequestMapping requestMapping = (RequestMapping) clazz.getAnnotation(RequestMapping.class);
            String classRoute = "";
            if (requestMapping != null) {
                classRoute = requestMapping.value()[0];
            } else {
                continue;
            }
            List<Method> methodList = Arrays.asList(clazz.getDeclaredMethods());
            for (Method method : methodList) {
                PostMapping postMapping = method.getAnnotation(PostMapping.class);
                String methodRoute = "";
                if (postMapping != null) {
                    methodRoute = postMapping.value()[0];
                } else {
                    GetMapping getMapping = method.getAnnotation(GetMapping.class);
                    if (getMapping != null) {
                        methodRoute = getMapping.value()[0];
                    }
                }
                if (!StringUtil.isEmpty(classRoute) && !StringUtil.isEmpty(methodRoute)) {
                    String orginalRoute = coverRoute.value() + methodRoute;
                    String redirectRoute = classRoute + methodRoute;
                    mappingRegist.put(orginalRoute, redirectRoute);
                }
            }
        }
        if (mappingRegist.size() > 0) {
            System.out.println("扫描路由方法覆盖：" + mappingRegist.size() + "个");
        }
    }

    public static boolean checkExistCover(String orginalRoute) {
        return mappingRegist.containsKey(orginalRoute);
    }

    public static String getRedirectRoute(String orginalRoute) {
        return mappingRegist.get(orginalRoute);
    }
}
