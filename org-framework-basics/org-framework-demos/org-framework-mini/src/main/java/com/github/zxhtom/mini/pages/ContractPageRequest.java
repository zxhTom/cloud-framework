package com.github.zxhtom.mini.pages;

import com.github.zxhtom.core.datasouce.PagedRequest;
import com.github.zxhtom.core.datasouce.PagedResult;
import com.github.zxhtom.mini.model.Contract;
import com.github.zxhtom.mini.service.ContractService;
import lombok.Data;

/**
 * @author zxhtom
 * 10/6/25
 */
@Data
public class ContractPageRequest extends PagedRequest<Contract> {
}
