package com.github.zxhtom.knife4j.demo.service.impl;

import com.github.zxhtom.knife4j.demo.model.Test;
import com.github.zxhtom.knife4j.demo.service.KnifeService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.knife4j.demo.service.impl
 * @date 2021/9/14 15:53
 */
@Service
public class KnifeServiceImpl implements KnifeService {
    @Override
    public List<Test> selectList() {
        List<Test> resultList = new ArrayList<>();
        Test test = new Test();
        test.setId(1);
        test.setName("zxhtom");
        test.setBirthDate(new Date());
        resultList.add(test);
        return resultList;
    }
}
