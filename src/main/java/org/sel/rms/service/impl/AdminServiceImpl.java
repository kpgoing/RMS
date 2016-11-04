package org.sel.rms.service.impl;
import org.sel.rms.entity.AdminEntity;
import org.sel.rms.exception.AdminException;
import org.sel.rms.repository.AdminRepository;
import org.sel.rms.service.AdminService;
import org.sel.rms.status.AdminStatus;
import org.sel.rms.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
* 生成于2016/10/29
*/
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    AdminRepository adminRepository;

    @Value("config.admin.key")
    String adminKey;

    @Override
    public AdminStatus adminAuth(AdminEntity adminEntity) {
        AdminStatus status = AdminStatus.ERROR;
        AdminEntity adminEntityAuth;
        try {
            adminEntityAuth = adminRepository.findByAdminName(adminEntity.getAccount());
            if(null == adminEntityAuth) {
                throw new AdminException("username error or username not exist", AdminStatus.USERNAME_ERROR_OR_EXIST);
            } else {
                if(adminEntityAuth.getPassword().equals(MD5Util.calc(adminEntity.getPassword()))) {
                    status = AdminStatus.SUCCESS;
                } else {
                    throw new AdminException("password error", AdminStatus.PASSWORD_ERROR);
                }
            }
        } catch (Exception e) {
            throw new AdminException("authorize user error", e, AdminStatus.ERROR);
        }
        return status;
    }

    public List getAdmin(AdminEntity adminEntity) {
        List list = new ArrayList<>();
        AdminEntity adminResult = new AdminEntity();
        try {
            adminResult = adminRepository.findByAdminName(adminEntity.getAccount());
        } catch (Exception e) {
            throw new AdminException("find admin by account error", e, AdminStatus.ERROR);
        }
        list.add(adminEntity.getIdAdmin());
        return list;
    }
}