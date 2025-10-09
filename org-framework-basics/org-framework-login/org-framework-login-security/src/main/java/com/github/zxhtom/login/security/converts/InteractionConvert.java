package com.github.zxhtom.login.security.converts;

import com.github.zxhtom.login.security.model.InteractionEnum;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
@ConfigurationPropertiesBinding
public class InteractionConvert implements Converter<String, InteractionEnum> {
    @Override
    public InteractionEnum convert(String source) {
        return InteractionEnum.valueOf(source.toUpperCase(Locale.ROOT));
    }
}
