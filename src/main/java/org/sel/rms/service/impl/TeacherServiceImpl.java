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
import java.sql.Date;
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
            throw new TeacherException("authorize teacher error", e, TeacherStatus.ERROR);
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
            String password = teacherEntity.getPassword();
            teacherEntity.setPassword(MD5Util.calc(password));
            teacherRepository.save(teacherEntity);
            teacherStatus = TeacherStatus.SUCCESS;
        } catch (Exception e) {
            throw new TeacherException("save teacher information error", e, TeacherStatus.SAVE_INFO_ERROR);
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

    public String uploadAvatar(HttpServletRequest request, MultipartFile file, int id) {
        TeacherEntity teacherEntity;
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
        } catch (IOException e) {
            throw new TeacherException("upload file error!", e, TeacherStatus.UPLOAD_AVATAR_ERROR);
        }
        return sqPath;
    }

    public TeacherStatus modifyTeacherInfo(TeacherEntity teacherEntity) {
        TeacherStatus teacherStatus;
        try {
            TeacherEntity one = teacherRepository.findOne(teacherEntity.getIdTeacher());
            System.out.println(teacherEntity);
            Date date = teacherEntity.getBirthday();
            one.setBirthday(teacherEntity.getBirthday());
            one.setCollege(teacherEntity.getCollege());
            one.setEducationBackground(teacherEntity.getEducationBackground());
            one.setEmail(teacherEntity.getEmail());
            one.setWorkPlace(teacherEntity.getWorkPlace());
            one.setPhoneNumber(teacherEntity.getPhoneNumber());
            one.setTitle(one.getTitle());
            teacherRepository.save(one);
            teacherStatus = TeacherStatus.SUCCESS;
        } catch (Exception e) {
            throw new TeacherException("modify teacher information error", e, TeacherStatus.MODIFY_TEACHER_INFO_ERROR);
        }
        return teacherStatus;
    }

    public TeacherStatus resetPassword(int id, String newPassword) {
        TeacherStatus teacherStatus;
        TeacherEntity teacherEntity;
        try {
            teacherEntity = teacherRepository.findOne(id);
            teacherEntity.setPassword(MD5Util.calc(newPassword));
            teacherRepository.save(teacherEntity);
            teacherStatus = TeacherStatus.SUCCESS;
        } catch (Exception e) {
            throw new TeacherException("reset new password error", e, TeacherStatus.RESET_PASSWORD_ERROR);
        }
        return teacherStatus;
    }

    public int forgetPassword(String email) {
        int teacherId;
        TeacherEntity teacherEntity;
        try {
            teacherEntity = teacherRepository.findByEmail(email);
            teacherId = teacherEntity.getIdTeacher();
        } catch (Exception e) {
            throw new TeacherException("forget password error", e, TeacherStatus.FORGET_PASSWORD_ERROR);
        }
        return teacherId;
    }

    public CheckStatusOfTeacherEntity uncheckDenied(int teacherId) {
        CheckStatusOfTeacherEntity checkStatusOfTeacherEntity;
        try {
            checkStatusOfTeacherEntity = checkStatusOfTeacherRepository.findByidTeacher(teacherId);
        } catch (Exception e) {
            throw new TeacherException("uncheck deny error", e, TeacherStatus.UNCHECK_DENY);
        }
        return checkStatusOfTeacherEntity;
    }
}