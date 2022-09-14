package com.github.zxhtom.demo.transactional.controller;

import com.github.zxhtom.demo.transactional.model.Transmodel;
import com.github.zxhtom.demo.transactional.service.TansService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * TODO
 *
 * @author zxhtom
 * 2022/1/23
 */
@RestController
@RequestMapping(value = "/trans")
public class TranController {
    @Autowired
    TansService tansService;

    @RequestMapping(value = "/startTransaction", method = RequestMethod.POST)
    public Map<String, Object> startTransaction(@RequestBody Transmodel transmodel) {
        return tansService.startTransaction(transmodel);
    }

}
