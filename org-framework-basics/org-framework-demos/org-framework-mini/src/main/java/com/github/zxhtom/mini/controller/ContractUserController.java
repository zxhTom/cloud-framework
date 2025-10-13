package com.github.zxhtom.mini.controller;

import com.github.zxhtom.core.datasouce.PagedResult;
import com.github.zxhtom.core.utils.PagegationUtils;
import com.github.zxhtom.login.core.model.User;
import com.github.zxhtom.mini.dto.ContractUser;
import com.github.zxhtom.mini.model.Contract;
import com.github.zxhtom.mini.pages.ContractPageRequest;
import com.github.zxhtom.mini.pages.ContractUserPageRequest;
import com.github.zxhtom.mini.service.ContractService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.PermitAll;

/**
 * @author zxhtom
 * 10/12/25
 */
@Api(value = "mini app login authorization interfaces")
@RestController
@RequestMapping(value = "/contract/user")
@Slf4j
public class ContractUserController {

    @Autowired
    ContractService contractService;

    @RequestMapping(value = "/completeProfile",method = RequestMethod.POST)
    public ResponseEntity<?> completeProfile(@RequestBody ContractUser user) {
        return ResponseEntity.ok(contractService.completeProfile(user));
    }
}
