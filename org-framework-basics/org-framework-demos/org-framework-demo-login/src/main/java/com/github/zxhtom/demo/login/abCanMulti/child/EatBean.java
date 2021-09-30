package com.github.zxhtom.demo.login.abCanMulti.child;

import com.github.zxhtom.demo.login.abCanMulti.AbStractSoutBean;
import org.springframework.stereotype.Component;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.demo.login.abCanMulti.child
 * @date 2021/9/30 13:13
 */
@Component
public class EatBean extends AbStractSoutBean {
    @Override
    public void sayHello() {
        System.out.println("i want eat");
    }
}
