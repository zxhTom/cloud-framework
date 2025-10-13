package com.github.zxhtom.mini.service;

import com.github.zxhtom.mini.dto.ContractUser;
import org.springframework.http.ResponseEntity;

/**
 * TODO
 *
 * @author zxhtom
 * 10/6/25
 */
public interface ContractService {
    Integer completeProfile(ContractUser user);
}
