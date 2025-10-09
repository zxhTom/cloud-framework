package com.github.zxhtom.flowable.controller;

import com.github.zxhtom.flowable.model.FlowableIdmAppUserModel;
import org.apache.commons.lang3.StringUtils;
import org.flowable.idm.api.Group;
import org.flowable.idm.api.IdmIdentityService;
import org.flowable.idm.api.User;
import org.flowable.ui.common.model.ResultListDataRepresentation;
import org.flowable.ui.common.model.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO
 *
 * @author zxhtom
 * 2023/9/12
 */
@RestController
//@RequestMapping(value = "/idm-app")
public class IDMAppController {
    @Autowired
    IdmIdentityService identityService;
    @RequestMapping(value = "/rest/admin/users" , method = RequestMethod.GET)
    public FlowableIdmAppUserModel userList (){
        return new FlowableIdmAppUserModel();
    }

    @RequestMapping(value = "idm-app/rest/admin/users" , method = RequestMethod.GET)
    public FlowableIdmAppUserModel userList2 (){
        return new FlowableIdmAppUserModel();
    }

    @RequestMapping(value = "app/rest/admin/users" , method = RequestMethod.GET)
    public FlowableIdmAppUserModel userList3 () {
        return new FlowableIdmAppUserModel();
    }
    @RequestMapping(value = "/modeler-app/rest/editor-users" ,method = RequestMethod.GET)
    public ResultListDataRepresentation getUsers(@RequestParam(value = "filter" , required = false) String filter){
        if (StringUtils.isNotEmpty(filter)) {
            filter = filter.trim();
            String sql = "select * from ACT_ID_USER where ID_ like #{id} or LAST_ like #{name}";
            filter = String.format("%%%s%%", filter);
            List<User> list = identityService.createNativeUserQuery().sql(sql).parameter("id", filter).parameter("name", filter).listPage(0, 10);
            List<UserRepresentation> userRepresentations = new ArrayList<>(list.size());
            for (User user : list) {
                userRepresentations.add(new UserRepresentation(user));
                userRepresentations.add(new UserRepresentation(user));
            }

            return new ResultListDataRepresentation(userRepresentations);
        }
        return new ResultListDataRepresentation();

    }
}
