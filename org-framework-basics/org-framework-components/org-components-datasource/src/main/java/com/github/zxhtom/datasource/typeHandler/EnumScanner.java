package com.github.zxhtom.datasource.typeHandler;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.core.type.filter.AssignableTypeFilter;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;

import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.Set;

/**
 * 枚举类扫描器
 */
@Component
public class EnumScanner {

    /**
     * 扫描指定包下的所有枚举类
     */
    public Set<Class<?>> scanEnums(String... basePackages) {
        Set<Class<?>> enumClasses = new HashSet<>();

        for (String basePackage : basePackages) {
            enumClasses.addAll(doScan(basePackage));
        }

        return enumClasses;
    }

    /**
     * 扫描指定包下带有特定注解的枚举类
     */
    public Set<Class<?>> scanEnumsWithAnnotation(Class<? extends Annotation> annotation,
                                                 String... basePackages) {
        Set<Class<?>> enumClasses = new HashSet<>();

        for (String basePackage : basePackages) {
            enumClasses.addAll(doScanWithAnnotation(basePackage, annotation));
        }

        return enumClasses;
    }

    private Set<Class<?>> doScan(String basePackage) {
        Set<Class<?>> enumClasses = new HashSet<>();
        ClassPathScanningCandidateComponentProvider scanner =
                new ClassPathScanningCandidateComponentProvider(false);

        scanner.addIncludeFilter(new AssignableTypeFilter(Enum.class));

        for (BeanDefinition beanDefinition : scanner.findCandidateComponents(basePackage)) {
            try {
                Class<?> clazz = ClassUtils.forName(beanDefinition.getBeanClassName(),
                        getClass().getClassLoader());
                if (clazz.isEnum()) {
                    enumClasses.add(clazz);
                }
            } catch (ClassNotFoundException e) {
                // 忽略无法加载的类
            }
        }

        return enumClasses;
    }

    private Set<Class<?>> doScanWithAnnotation(String basePackage,
                                               Class<? extends Annotation> annotation) {
        Set<Class<?>> enumClasses = new HashSet<>();
        ClassPathScanningCandidateComponentProvider scanner =
                new ClassPathScanningCandidateComponentProvider(false);

        scanner.addIncludeFilter(new AssignableTypeFilter(Enum.class));
        scanner.addIncludeFilter(new AnnotationTypeFilter(annotation));

        for (BeanDefinition beanDefinition : scanner.findCandidateComponents(basePackage)) {
            try {
                Class<?> clazz = ClassUtils.forName(beanDefinition.getBeanClassName(),
                        getClass().getClassLoader());
                if (clazz.isEnum() && clazz.isAnnotationPresent(annotation)) {
                    enumClasses.add(clazz);
                }
            } catch (ClassNotFoundException e) {
                // 忽略无法加载的类
            }
        }

        return enumClasses;
    }
}
