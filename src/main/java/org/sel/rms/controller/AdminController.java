package org.sel.rms.controller;

import org.apache.log4j.Logger;
import org.sel.rms.common.ResponseMessage;
import org.sel.rms.entity.AdminEntity;
import org.sel.rms.entity.ValidGroup.AdminGroup;
import org.sel.rms.exception.AdminException;
import org.sel.rms.service.AdminService;
import org.sel.rms.status.AdminStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

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
    public ResponseMessage adminLogin(@Validated(AdminGroup.class) @RequestBody AdminEntity adminEntity, HttpSession httpSession, BindingResult bindingResult) {
        AdminStatus adminStatus;
        if(bindingResult.hasErrors()) {
            logger.error("admin login arguments error");
            adminStatus = AdminStatus.ARGUMENTS_ERROR;
        } else {
            adminStatus = adminService.adminAuth(adminEntity);
            if (adminStatus.equals(AdminStatus.SUCCESS)) {
                AdminEntity anotherAdminEntity = new AdminEntity();
                anotherAdminEntity.setAccount(adminEntity.getAccount());
                anotherAdminEntity.setPassword(adminEntity.getPassword());
                List list = adminService.getAdmin(anotherAdminEntity);
                int aid = (int) list.get(1);
                httpSession.setAttribute(adminKey, aid);
            }
        }
        return new ResponseMessage(adminStatus);
    }

    @RequestMapping(value = "/admin/checkTeacher", method = RequestMethod.POST)
    public ResponseMessage checkTeacher(@RequestBody Map map) {
        AdminStatus adminStatus;
        int teacherId = (int)map.get("teacherId");
        if(0 == teacherId) {
            logger.error("check teacher arguments error");
            throw new AdminException("check teacher arguments error", AdminStatus.ARGUMENTS_ERROR);
        } else {
            adminStatus = adminService.checkTeacher(teacherId);
        }
        return new ResponseMessage(adminStatus);
    }
}
