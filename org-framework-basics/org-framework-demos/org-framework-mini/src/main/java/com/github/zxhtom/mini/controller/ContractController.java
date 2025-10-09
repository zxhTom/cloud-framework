package com.github.zxhtom.mini.controller;

import com.github.zxhtom.core.datasouce.PagedResult;
import com.github.zxhtom.core.utils.PagegationUtils;
import com.github.zxhtom.mini.model.Contract;
import com.github.zxhtom.mini.pages.ContractPageRequest;
import com.github.zxhtom.mini.service.ContractService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO
 *
 * @author zxhtom
 * 10/6/25
 */
@Api(value = "contract list interface")
@RestController
@RequestMapping(value = "/contract")
@Slf4j
public class ContractController {
    @Autowired
    ContractService contractService;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public PagedResult<Contract> queryList(ContractPageRequest pageRequest) {
        PagegationUtils.getInstance().pageStart(pageRequest.getPageNumber(), pageRequest.getPageSize());
        return null;
    }
    @RequestMapping(value = "/list1",method = RequestMethod.GET)
    public Integer queryList1(ContractPageRequest pageRequest) {
        return 100;
    }
    @RequestMapping(value = "/list2",method = RequestMethod.GET)
    public List<Contract> queryList2(ContractPageRequest pageRequest) {
        List<Contract> list = new ArrayList<>();
        Contract contract = new Contract();
        contract.setCompanyName("tttttttttt");
        list.add(contract);
        return list;
    }
}
