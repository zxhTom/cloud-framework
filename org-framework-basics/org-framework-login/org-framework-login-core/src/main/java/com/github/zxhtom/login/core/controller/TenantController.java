package com.github.zxhtom.login.core.controller;

import com.github.zxhtom.login.core.model.Tenant;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.PermitAll;

@RestController
@RequestMapping(value = "/system/tenant")
@PermitAll
public class TenantController {
    @RequestMapping(value = "/getByWebsite",method = RequestMethod.GET)
    public Tenant getByWebsite() {
        Tenant tenant = new Tenant();
        tenant.setId(-1L);
        tenant.setName("demo");
        return tenant;
    }
}
