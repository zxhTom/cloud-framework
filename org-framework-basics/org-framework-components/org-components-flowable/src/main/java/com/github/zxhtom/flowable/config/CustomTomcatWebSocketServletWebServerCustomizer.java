package com.github.zxhtom.flowable.config;

import org.apache.tomcat.websocket.server.WsContextListener;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ContextLoaderListener;

/**
 * TODO
 *
 * @author zxhtom
 * 2023/9/14
 */
@Component("websocketServletWebServerCustomizer")
public class CustomTomcatWebSocketServletWebServerCustomizer implements WebServerFactoryCustomizer<TomcatServletWebServerFactory>, Ordered {
    @Override
    public void customize(TomcatServletWebServerFactory factory) {
        factory.addContextCustomizers((context) -> context.addApplicationListener(WsContextListener.class.getName()));

//        factory.addContextCustomizers((context) -> context.addApplicationListener(ContextLoaderListener.class.getName()));

    }

    @Override
    public int getOrder() {
        return 0;
    }
}
