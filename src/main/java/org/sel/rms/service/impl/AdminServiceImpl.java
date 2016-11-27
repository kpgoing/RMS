package org.sel.rms.service.impl;
import org.sel.rms.entity.AdminEntity;
import org.sel.rms.entity.CheckStatusOfTeacherEntity;
import org.sel.rms.entity.TeacherEntity;
import org.sel.rms.exception.AdminException;
import org.sel.rms.exception.TeacherException;
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

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;


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
    public AdminStatus  adminAuth(AdminEntity adminEntity) {
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

    public int getAdmin(AdminEntity adminEntity) {
        AdminEntity adminResult;
        try {
            adminResult = adminRepository.findByAccount(adminEntity.getAccount());
        } catch (Exception e) {
            throw new AdminException("find admin by account error", e, AdminStatus.ERROR);
        }
        return adminEntity.getIdAdmin();
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
        int id;
        int uncheckNum;
        TeacherEntity teacherEntity;
        List<CheckStatusOfTeacherEntity> checkStatusOfTeacherEntities = null;
        try {
            checkStatusOfTeacherEntities = checkStatusOfTeacherRepository.uncheckList();
        } catch (Exception e) {
            throw new AdminException("get uncheck list error", e, AdminStatus.GET_UNCHECK_ERROR);
        }
        if (checkStatusOfTeacherEntities.size() == 0) {
            teacherEntity = null;
            uncheckNum = 0;
        } else {
            id = checkStatusOfTeacherEntities.get(0).getIdTeacher();
            teacherEntity = teacherRepository.findOne(id);
            teacherEntity.setPassword(null);
            uncheckNum = checkStatusOfTeacherEntities.size();
        }
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

    public TeacherEntity getTeacher(int idTeacher) {
        TeacherEntity teacherEntity;
        try {
            teacherEntity = teacherRepository.findOne(idTeacher);
            teacherEntity.setPassword(null);
        } catch (Exception e) {
            throw new AdminException("get a teacher error", e, AdminStatus.GET_A_TEACHER_ERROR);
        }
        return teacherEntity;
    }

    public AdminStatus modifyPassword(int adminId, String oldPassword, String newPassword){
        AdminStatus adminStatus;
        AdminEntity adminEntity;
        try {
            adminEntity = adminRepository.findOne(adminId);
            if(!(oldPassword.equals(MD5Util.calc(adminEntity.getPassword())))) {
                adminStatus = AdminStatus.OLDPASSWORD_ERROR;
                return adminStatus;
            }
            adminEntity.setPassword(newPassword);
            adminRepository.save(adminEntity);
            adminStatus = AdminStatus.SUCCESS;
        } catch (Exception e) {
            throw new AdminException("modify password error", e, AdminStatus.MODIFY_PASSWORD_ERROR);
        }
        return adminStatus;
    }
}