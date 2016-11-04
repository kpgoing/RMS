package org.sel.rms.controller;

import org.apache.log4j.Logger;
import org.sel.rms.common.ResponseMessage;
import org.sel.rms.entity.AdminEntity;
import org.sel.rms.service.AdminService;
import org.sel.rms.status.AdminStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by Huxh on 2016/11/1.
 */
@RestController
public class AdminController {
    private final static Logger logger = Logger.getLogger(AdminController.class);

    @Autowired
    AdminService adminService;

    @Value("config.admin.key")
    String adminKey;

    @RequestMapping(value = "/admin/login", method = RequestMethod.POST)
    public ResponseMessage adminLogin(@RequestBody AdminEntity adminEntity, HttpSession httpSession) {
        AdminStatus adminStatus;
        adminStatus = adminService.adminAuth(adminEntity);
        if(adminStatus.equals(AdminStatus.SUCCESS)) {
            AdminEntity anotherAdminEntity = new AdminEntity();
            anotherAdminEntity.setAccount(adminEntity.getAccount());
            anotherAdminEntity.setPassword(adminEntity.getPassword());
            List list = adminService.getAdmin(anotherAdminEntity);
            int aid = (int)list.get(1);
            httpSession.setAttribute("aid",aid);
        }
        return new ResponseMessage(adminStatus);
    }
}
