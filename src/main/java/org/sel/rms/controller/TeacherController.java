package org.sel.rms.controller;

import org.apache.log4j.Logger;
import org.sel.rms.common.ResponseMessage;
import org.sel.rms.entity.TeacherEntity;
import org.sel.rms.entity.ValidGroup.TeacherGroup;
import org.sel.rms.exception.TeacherException;
import org.sel.rms.service.TeacherService;
import org.sel.rms.status.TeacherStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * Created by Huxh on 2016/11/3.
 */
@RestController
public class TeacherController {

    private final static Logger logger = Logger.getLogger(TeacherController.class);

    @Autowired
    TeacherService teacherService;

    @Value("config.teacher.key")
    String teacherKey;

    @RequestMapping(value = "/teacher/login", method = RequestMethod.POST)
    public ResponseMessage teacherLogin(@Validated(TeacherGroup.login.class) @RequestBody TeacherEntity teacherEntity, HttpSession httpSession, BindingResult bindingResult) {
        TeacherStatus teacherStatus;
        if(bindingResult.hasErrors()) {
            logger.error("teacher login arguments error");
            teacherStatus = TeacherStatus.ARGUMENTS_ERROR;
        } else {
            teacherStatus = teacherService.teacherAuth(teacherEntity);
            if(teacherStatus.equals(TeacherStatus.SUCCESS)) {
                TeacherEntity anotherEntity = new TeacherEntity();
                anotherEntity.setAccount(teacherEntity.getAccount());
                anotherEntity.setPassword(teacherEntity.getPassword());
                List list = teacherService.getTeacher(anotherEntity);
                int tid = (int)list.get(1);
                httpSession.setAttribute(teacherKey, tid);
            }
        }
        return new ResponseMessage(teacherStatus);
    }

    @RequestMapping(value = "/teacher/register", method = RequestMethod.POST)
    public ResponseMessage teacherRegister(@Validated(TeacherGroup.register.class) @RequestBody TeacherEntity teacherEntity, BindingResult bindingResult) {
        TeacherStatus teacherStatus;
        if(bindingResult.hasErrors()) {
            logger.error("teacher register arguments error");
            teacherStatus = TeacherStatus.ARGUMENTS_ERROR;
        } else {
            teacherStatus = teacherService.teacherRegister(teacherEntity);
            teacherService.saveCheckStatus(teacherEntity);
        }
        return new ResponseMessage(teacherStatus);
    }

    @RequestMapping(value = "/teacher/modifyPassword", method = RequestMethod.POST)
    public ResponseMessage modifyPassword(@RequestBody Map map) {
        TeacherStatus teacherStatus;
        int teacherId = (int)map.get("teacherId");
        String newPassword = (String)map.get("newPassword");
        if(0 == teacherId || null == newPassword) {
            logger.error("teacher modify password arguments error");
            throw new TeacherException("teacher modify password arguments error", TeacherStatus.ARGUMENTS_ERROR);
        } else {
            teacherStatus = teacherService.modifyPassword(teacherId, newPassword);
        }
        return new ResponseMessage(teacherStatus);
    }

}
