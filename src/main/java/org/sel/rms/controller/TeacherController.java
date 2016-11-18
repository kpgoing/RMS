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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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

    /**
     * @api {post} /teacher/login 教师登录
     * @apiName teacherLogin
     * @apiGroup teacher
     * @apiversion 0.1.0
     * @apiParam {json} teacherEntity 教师账户信息
     * @apiParamExample {json} Request-Example:
     * {
     *     "account"："abc",
     *     "password":"123"
     * }
     * @apiUse NormalSuccessResponse
     * @apiUse NormalErrorResponse
     * @apiUse ArgumentsErrorResponse
     * @apiUse DataBaseErrorResponse
     * @apiUse UnLoginErrorResponse
     */
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

    /**
     * @api {post} /teacher/register 教师注册
     * @apiName teacherRegister
     * @apiGroup teacher
     * @apiPermission teacher
     * @apiVersion 0.1.0
     * @apiParam {json} teacherEntity 教师信息
     * @apiParam {String} birthday 教师生日（选填）
     * @apiParam {String} educationBackground 教师教育背景（选填）
     * @apiParam {String} title 教师职称（选填）
     * @apiParamExample {json} Request-Example:
     * {
     *     "account":"abc",
     *     "password":"123",
     *     "birthday":"yyyy-mm-dd",
     *     "educationBackground":"abc",
     *     "college":"abc",
     *     "name":"abc",
     *     "id":"123",
     *     "email":"123@123.com"
     *     "phoneNumber":"13000000000",
     *     "byte":0(male)/1(female),
     *     "workPlace":"abc",
     *     "title":"abc"
     * }
     * @apiUse NormalSuccessResponse
     * @apiUse NormalErrorResponse
     * @apiUse ArgumentsErrorResponse
     * @apiUse DataBaseErrorResponse
     * @apiUse UnLoginErrorResponse
     */
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

    /**
     * @api {json} /teacher/modifyPassword 教师修改密码
     * @apiName modifyPassword
     * @apiGroup teacher
     * @apiPermission teacher
     * @apiVersion 0.1.0
     * @apiParam {Number} teacherId 教师ID
     * @apiParam {String} newPassword 教师新密码
     * @apiParamExample {json} Request-Example
     * {
     *     "teacherId":1,
     *     "newPassword":"123"
     * }
     * @apiUse NormalSuccessResponse
     * @apiUse NormalErrorResponse
     * @apiUse ArgumentsErrorResponse
     * @apiUse DataBaseErrorResponse
     * @apiUse UnLoginErrorResponse
     */
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

    @RequestMapping(value = "/teacher/search/{keyWord}/{page}/{size}", method = RequestMethod.POST)
    public ResponseMessage searchTeacher(@PathVariable("keyWord") String keyWord, @PathVariable("page") int page, @PathVariable("size") int size) {
        Pageable pageable = new PageRequest(page, size, Sort.Direction.DESC, "idTeacher");
        Page<TeacherEntity> teacherEntities = teacherService.searchTeacher(keyWord, pageable);
        return new ResponseMessage(TeacherStatus.SUCCESS, teacherEntities);
    }

}
