package com.github.zxhtom.oss.beanpostprocess;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.boot.env.YamlPropertySourceLoader;
import org.springframework.core.Ordered;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.List;

public class CoreConfigEnvironmentPostProcessor implements EnvironmentPostProcessor, Ordered {

    private final YamlPropertySourceLoader loader = new YamlPropertySourceLoader();

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        try {
            // 关键：使用 ClassLoader.getResources() 而不是 ClassPathResource
            Enumeration<URL> configUrls = getClass().getClassLoader().getResources("application.yml");

            while (configUrls.hasMoreElements()) {
                URL url = configUrls.nextElement();
                System.out.println("找到配置文件: " + url);

                // 检查是否来自核心模块
                if (isCoreConfig(url)) {
                    loadCoreConfig(environment, url);
                }
            }
        } catch (IOException e) {
            System.err.println("加载核心配置失败: " + e.getMessage());
        }
    }

    private boolean isCoreConfig(URL url) {
        String path = url.toString();
        // 通过包路径判断是否核心模块
        return path.contains("org-framework-oss-core");
    }

    private void loadCoreConfig(ConfigurableEnvironment environment, URL configUrl) throws IOException {
        Resource resource = new UrlResource(configUrl);
        List<PropertySource<?>> propertySources = loader.load("coreModuleConfig", resource);

        if (!propertySources.isEmpty()) {
            // 添加到最前面，作为默认配置
            environment.getPropertySources().addLast(propertySources.get(0));
            System.out.println("✓ 核心模块配置加载成功");
        }
    }
}
