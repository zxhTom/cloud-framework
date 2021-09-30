package com.github.zxhtom.datasource.intercepter;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.datasource.intercepter
 * @date 2021/9/29 16:02
 */
@Component
@Data
public class InterceptorFillParameterRegister {

    private List<AbstractFillHandler> abHandlerList = new ArrayList<>();

    public Integer register(AbstractFillHandler... handlers) {
        Integer result = abHandlerList.size();
        if (handlers != null && handlers.length > 0) {
            for (AbstractFillHandler handler : handlers) {
                abHandlerList.add(handler);
            }
        }
        return abHandlerList.size()-result;
    }

    public Integer registerAt(AbstractFillHandler handler,Integer index) {
        abHandlerList.set(index,handler);
        return 1;
    }
}
