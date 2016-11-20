package org.sel.rms.service;
import org.sel.rms.entity.TeacherEntity;
import org.sel.rms.status.TeacherStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;


/**
* 生成于2016/10/29
*/
@Transactional
public interface TeacherService {
    TeacherStatus teacherAuth(TeacherEntity teacherEntity);

    int getTeacherId(TeacherEntity teacherEntity);

    TeacherStatus teacherRegister(TeacherEntity teacherEntity);

    void saveCheckStatus(TeacherEntity teacherEntity);

    TeacherStatus modifyPassword(int teacherId, String oldPassword, String newPassword);

    TeacherStatus deleteTeacher(int teacherId);

    Page<TeacherEntity> searchTeacher(String keyWord, Pageable page);

    String uploadAvatar(HttpServletRequest request, MultipartFile file, int id);

    TeacherStatus modifyTeacherInfo(TeacherEntity teacherEntity);
}