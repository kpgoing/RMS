package org.sel.rms.service;
import org.sel.rms.entity.AdminEntity;
import org.sel.rms.entity.TeacherEntity;
import org.sel.rms.status.AdminStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


/**
* 生成于2016/10/29
*/
public interface AdminService {
    AdminStatus adminAuth(AdminEntity adminEntity);

    List getAdmin(AdminEntity adminEntity);

    AdminStatus checkTeacher(int teacherId);

    AdminStatus unpassTeacher(int teacherId);

    Map getUncheck();

    List<TeacherEntity> getAllTeachers();

    TeacherEntity getTeacher(int idTeacher);
}