package org.sel.rms.service.impl;
import org.sel.rms.entity.AdminEntity;
import org.sel.rms.entity.CheckStatusOfTeacherEntity;
import org.sel.rms.entity.TeacherEntity;
import org.sel.rms.exception.AdminException;
import org.sel.rms.repository.AdminRepository;
import org.sel.rms.repository.CheckStatusOfTeacherRepository;
import org.sel.rms.repository.TeacherRepository;
import org.sel.rms.service.AdminService;
import org.sel.rms.status.AdminStatus;
import org.sel.rms.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
* 生成于2016/10/29
*/
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    AdminRepository adminRepository;

    @Autowired
    CheckStatusOfTeacherRepository checkStatusOfTeacherRepository;

    @Autowired
    TeacherRepository teacherRepository;

    @Value("config.admin.key")
    String adminKey;

    @Override
    public AdminStatus adminAuth(AdminEntity adminEntity) {
        AdminStatus status = AdminStatus.ERROR;
        AdminEntity adminEntityAuth;
        try {
            adminEntityAuth = adminRepository.findByAccount(adminEntity.getAccount());
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
        AdminEntity adminResult;
        try {
            adminResult = adminRepository.findByAccount(adminEntity.getAccount());
        } catch (Exception e) {
            throw new AdminException("find admin by account error", e, AdminStatus.ERROR);
        }
        list.add(adminResult.getIdAdmin());
        return list;
    }

    public AdminStatus checkTeacher(int teacherId) {
        AdminStatus adminStatus;
        CheckStatusOfTeacherEntity checkStatusOfTeacherEntity;
        try {
            checkStatusOfTeacherEntity = checkStatusOfTeacherRepository.findByidTeacher(teacherId);
            checkStatusOfTeacherEntity.setCheckStatus((byte)1);
            checkStatusOfTeacherRepository.save(checkStatusOfTeacherEntity);
            adminStatus = AdminStatus.SUCCESS;
        } catch (Exception e) {
            throw new AdminException("check teacher error", e, AdminStatus.CHECK_TEACHER_ERROR);
        }
        return adminStatus;
    }

    public AdminStatus unpassTeacher(int teacherId) {
        AdminStatus adminStatus;
        CheckStatusOfTeacherEntity checkStatusOfTeacherEntity;
        try {
            checkStatusOfTeacherEntity = checkStatusOfTeacherRepository.findByidTeacher(teacherId);
            checkStatusOfTeacherRepository.delete(checkStatusOfTeacherEntity);
            adminStatus = AdminStatus.SUCCESS;
        } catch (Exception e) {
            throw new AdminException("delete unpass teacher information error", e, AdminStatus.CHECK_TEACHER_ERROR);
        }
        return adminStatus;
    }

    public Map getUncheck() {
        Map map = new HashMap<>();
        TeacherEntity teacherEntity;
        List<CheckStatusOfTeacherEntity> checkStatusOfTeacherEntities;
        try {
            checkStatusOfTeacherEntities = checkStatusOfTeacherRepository.uncheckList();
        } catch (Exception e) {
            throw new AdminException("get uncheck list error", e, AdminStatus.GET_UNCHECK_ERROR);
        }
        int id = checkStatusOfTeacherEntities.get(0).getIdTeacher();
        teacherEntity = teacherRepository.findOne(id);
        teacherEntity.setPassword(null);
        int uncheckNum = checkStatusOfTeacherEntities.size() - 1;
        map.put("uncheckNum", uncheckNum);
        map.put("teacher", teacherEntity);
        return map;
    }

    public List<TeacherEntity> getAllTeachers() {
        List<TeacherEntity> teacherEntities;
        try {
            teacherEntities = teacherRepository.findAll();
            for(TeacherEntity t : teacherEntities) {
                t.setPassword(null);
            }
        } catch (Exception e) {
            throw new AdminException("get all teachers error", e, AdminStatus.GET_ALL_TEACHERS_ERROR);
        }
        return teacherEntities;
    }
}