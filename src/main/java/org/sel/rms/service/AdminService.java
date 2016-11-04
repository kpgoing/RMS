package org.sel.rms.service;
import org.sel.rms.entity.AdminEntity;
import org.sel.rms.status.AdminStatus;
import org.springframework.stereotype.Service;

import java.util.List;


/**
* 生成于2016/10/29
*/
public interface AdminService {
    AdminStatus adminAuth(AdminEntity adminEntity);

    List getAdmin(AdminEntity adminEntity);
}