package org.sel.rms.service.impl;
import org.sel.rms.entity.TeacherEntity;
import org.sel.rms.exception.TeacherException;
import org.sel.rms.repository.TeacherRepository;
import org.sel.rms.service.TeacherService;
import org.sel.rms.status.TeacherStatus;
import org.sel.rms.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
* 生成于2016/10/29
*/
@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    TeacherRepository teacherRepository;

    public TeacherStatus teacherAuth(TeacherEntity teacherEntity) {
        TeacherStatus teacherStatus;
        TeacherEntity teacherEntityAuth;
        try {
            teacherEntityAuth = teacherRepository.findByAccount(teacherEntity.getAccount());
            if(null == teacherEntityAuth) {
                throw new TeacherException("teacher username error or exist", TeacherStatus.USERNAME_ERROR_OR_EXIST);
            } else {
                if(teacherEntityAuth.getPassword().equals(MD5Util.calc(teacherEntity.getPassword()))) {
                    teacherStatus = TeacherStatus.SUCCESS;
                } else {
                    throw new TeacherException("password error", TeacherStatus.PASSWORD_ERROR);
                }
            }
        } catch (Exception e) {
            throw new TeacherException("authorize teacher error", TeacherStatus.ERROR);
        }
        return teacherStatus;
    }

    public List getTeacher(TeacherEntity teacherEntity) {
        List list = new ArrayList<>();
        TeacherEntity teacherResult;
        try {
            teacherResult = teacherRepository.findByAccount(teacherEntity.getAccount());
        } catch (Exception e) {
            throw new TeacherException("find teacher by account error", TeacherStatus.ERROR);
        }
        list.add(teacherResult.getIdTeacher());
        return list;
    }
}