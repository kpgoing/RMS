package org.sel.rms.service;
import org.sel.rms.entity.TeacherEntity;
import org.sel.rms.status.TeacherStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
* 生成于2016/10/29
*/
@Transactional
public interface TeacherService {
    TeacherStatus teacherAuth(TeacherEntity teacherEntity);

    List getTeacher(TeacherEntity teacherEntity);

    TeacherStatus teacherRegister(TeacherEntity teacherEntity);

    void saveCheckStatus(TeacherEntity teacherEntity);

    TeacherStatus modifyPassword(int teacherId, String newPassword);
}