package com.github.zxhtom.core.date;

import com.github.zxhtom.core.constant.MaltcloudConstant;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.InitializingBean;

import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.core.date
 * @date 2021/9/6 10:33
 */
@Data
@Slf4j
public class MultiDateFormat extends DateFormat implements InitializingBean {

    List<String> patterns=new ArrayList<>();

    private DateFormat dateFormat ;

    public MultiDateFormat() {
        this.dateFormat = new SimpleDateFormat(MaltcloudConstant.COMMON_DATE_FORMAT_PATTERN);
    }

    public MultiDateFormat(DateFormat dateFormat) {
        this.dateFormat = dateFormat;
    }
    @Override
    public StringBuffer format(Date date, StringBuffer toAppendTo, FieldPosition fieldPosition) {
        for (String pattern : patterns) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat(pattern);
                StringBuffer format = sdf.format(date, toAppendTo, fieldPosition);
                if (pattern.length() != format.length()) {
                    continue;
                }
                if (format != null) {
                    return format;
                }
            } catch (Exception e) {

            }
        }

        return dateFormat.format(date, toAppendTo, fieldPosition);
    }

    @Override
    public Date parse(String source, ParsePosition pos) {
        for (String pattern : patterns) {
            try {
                if (pattern.length() != source.length()) {
                    continue;
                }
                SimpleDateFormat sdf = new SimpleDateFormat(pattern);
                Date parse = sdf.parse(source, pos);
                if (null != parse) {
                    return parse;
                }
            } catch (Exception e) {

            }
        }
        return dateFormat.parse(source, pos);
    }

    @Override
    public Object clone() {
        Object format = dateFormat.clone();
        MultiDateFormat multiDateFormat = new MultiDateFormat((DateFormat) format);
        multiDateFormat.setPatterns(patterns);
        return multiDateFormat;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (CollectionUtils.isEmpty(patterns)) {
            log.warn("检测到未配置时间格式，maltcloud将采用默认时间格式："+MaltcloudConstant.COMMON_DATE_FORMAT_PATTERN);
        }
    }
}
