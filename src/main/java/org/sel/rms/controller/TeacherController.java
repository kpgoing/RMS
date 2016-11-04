package org.sel.rms.controller;

import org.apache.log4j.Logger;
import org.sel.rms.common.ResponseMessage;
import org.sel.rms.entity.TeacherEntity;
import org.sel.rms.entity.ValidGroup.TeacherGroup;
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
            logger.error("admin login arguments error");
            teacherStatus = TeacherStatus.ARGUMENTS_ERROR;
        } else {
            teacherStatus = teacherService.teacherAuth(teacherEntity);
            if(teacherStatus.equals(TeacherStatus.SUCCESS)) {
                TeacherEntity anotherEntity = new TeacherEntity();
                anotherEntity.setAccount(teacherEntity.getAccount());
                anotherEntity.setPassword(teacherEntity.getPassword());
                List list = teacherService.getTeacher(anotherEntity);
                int tid = (int)list.get(1);
                httpSession.setAttribute("tid", tid);
            }
        }
        return new ResponseMessage(teacherStatus);
    }
}
