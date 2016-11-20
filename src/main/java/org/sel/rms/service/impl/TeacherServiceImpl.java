package org.sel.rms.service.impl;
import org.apache.commons.lang3.StringUtils;
import org.sel.rms.entity.CheckStatusOfTeacherEntity;
import org.sel.rms.entity.TeacherEntity;
import org.sel.rms.exception.PaperException;
import org.sel.rms.exception.TeacherException;
import org.sel.rms.repository.CheckStatusOfTeacherRepository;
import org.sel.rms.repository.TeacherRepository;
import org.sel.rms.service.TeacherService;
import org.sel.rms.status.PaperStatus;
import org.sel.rms.status.TeacherStatus;
import org.sel.rms.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
* 生成于2016/10/29
*/
@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    TeacherRepository teacherRepository;

    @Autowired
    CheckStatusOfTeacherRepository checkStatusOfTeacherRepository;

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

    public int getTeacherId(TeacherEntity teacherEntity) {
        TeacherEntity teacherResult;
        try {
            teacherResult = teacherRepository.findByAccount(teacherEntity.getAccount());
        } catch (Exception e) {
            throw new TeacherException("find teacher by account error", TeacherStatus.ERROR);
        }
        return teacherResult.getIdTeacher();
    }

    public TeacherStatus teacherRegister(TeacherEntity teacherEntity) {
        TeacherStatus teacherStatus;
        try {
            teacherRepository.save(teacherEntity);
            teacherStatus = TeacherStatus.SUCCESS;
        } catch (Exception e) {
            throw new TeacherException("save teacher information error", TeacherStatus.SAVE_INFO_ERROR);
        }
        return teacherStatus;
    }

    public void saveCheckStatus(TeacherEntity teacherEntity) {
        TeacherEntity checkTeacher = new TeacherEntity();
        CheckStatusOfTeacherEntity checkStatusOfTeacherEntity = new CheckStatusOfTeacherEntity();
        try {
            checkTeacher = teacherRepository.findByAccount(teacherEntity.getAccount());
            checkStatusOfTeacherEntity.setIdTeacher(checkTeacher.getIdTeacher());
            checkStatusOfTeacherEntity.setCheckStatus((byte)0);
            checkStatusOfTeacherRepository.save(checkStatusOfTeacherEntity);
        } catch (Exception e) {
            teacherRepository.delete(checkTeacher.getIdTeacher());
            throw new TeacherException("save teacher check status error", e, TeacherStatus.SAVE_CHECK_STATUS_ERROR);
        }
    }

    public TeacherStatus modifyPassword(int teacherId, String oldPassword, String newPassword) {
        TeacherStatus teacherStatus;
        TeacherEntity teacherEntity;
        try {
            teacherEntity = teacherRepository.findOne(teacherId);
            if(!(oldPassword.equals(MD5Util.calc(teacherEntity.getPassword())))) {
                teacherStatus = TeacherStatus.OLDPASSWORD_ERROR;
                return teacherStatus;
            }
            teacherEntity.setPassword(newPassword);
            teacherRepository.save(teacherEntity);
            teacherStatus = TeacherStatus.SUCCESS;
        } catch (Exception e) {
            throw new TeacherException("modify password error", e, TeacherStatus.MODIFY_PASSWORD_ERROR);
        }
        return teacherStatus;
    }

    public TeacherStatus deleteTeacher(int teacherId) {
        TeacherStatus teacherStatus;
        TeacherEntity teacherEntity;
        try {
            teacherEntity = teacherRepository.findOne(teacherId);
            teacherRepository.delete(teacherEntity);
            teacherStatus = TeacherStatus.SUCCESS;
        } catch (Exception e) {
            throw new TeacherException("delete teacher error", e, TeacherStatus.DELETE_ERROR);
        }
        return teacherStatus;
    }

    public Page<TeacherEntity> searchTeacher(String keyWord, Pageable page) {
        Page<TeacherEntity> teacherEntities;
        String[] keyWords = keyWord.split(" ");
        keyWord = StringUtils.join(keyWords, "%");
        keyWord = "%" + keyWord + "%";
        teacherEntities = teacherRepository.search(keyWord, page);
        for(TeacherEntity t : teacherEntities) {
            t.setPassword(null);
        }
        return teacherEntities;
    }

    public TeacherStatus uploadAvatar(HttpServletRequest request, MultipartFile file, int id) {
        TeacherEntity teacherEntity;
        TeacherStatus teacherStatus;
        String sqPath;
        String fileName = file.getOriginalFilename();
        String extensionName = fileName.substring(fileName.indexOf("."));
        fileName = fileName.substring(0,fileName.indexOf("."));
        fileName = fileName + new java.util.Date().getTime() + extensionName;
        String path = request.getServletContext().getRealPath(File.separator + "upload") + File.separator + id ;

        File dir = new File(path);
        if (!dir.exists())
            dir.mkdirs();
        try {
            path = path + File.separator + fileName;
            File realFile = new File(path);
            realFile.createNewFile();
            file.transferTo(realFile);
            sqPath = path.substring(path.indexOf(File.separator + "upload"));
            teacherEntity = teacherRepository.findOne(id);
            teacherEntity.setAvatarUrl(sqPath);
            teacherRepository.save(teacherEntity);
            teacherStatus = TeacherStatus.SUCCESS;
        } catch (IOException e) {
            throw new TeacherException("upload file error!", e, TeacherStatus.UPLOAD_AVATAR_ERROR);
        }
        return teacherStatus;
    }

    public TeacherStatus modifyTeacherInfo(TeacherEntity teacherEntity) {
        TeacherStatus teacherStatus;
        try {
            teacherRepository.save(teacherEntity);
            teacherStatus = TeacherStatus.SUCCESS;
        } catch (Exception e) {
            throw new TeacherException("modify teacher information error", e, TeacherStatus.MODIFY_TEACHER_INFO_ERROR);
        }
        return teacherStatus;
    }
}