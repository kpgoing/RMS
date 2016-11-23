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
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
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

    @Autowired
    JavaMailSender mailSender;

    @Value("config.teacher.key")
    String teacherKey;

    /**
     * @api {post} /teacher/login 教师登录
     * @apiName teacherLogin
     * @apiGroup teacher
     * @apiVersion 0.1.0
     * @apiParam {json} teacherEntity 教师账户信息
     * @apiParamExample {json} Request-Example:
     * {
     *     "account"："abc",
     *     "password":"123"
     * }
     * @apiSuccessExample {json} Success_Response:
     * {
     *     "code": 0,
     *     "msg": "SUCCESS",
     *     "body": {
     *     "IdTeacher": 2
     *     }
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
        Map map = new HashMap<>();
        if(bindingResult.hasErrors()) {
            logger.error("teacher login arguments error");
            teacherStatus = TeacherStatus.ARGUMENTS_ERROR;
        } else {
            teacherStatus = teacherService.teacherAuth(teacherEntity);
            if(teacherStatus.equals(TeacherStatus.SUCCESS)) {
                TeacherEntity anotherEntity = new TeacherEntity();
                anotherEntity.setAccount(teacherEntity.getAccount());
                anotherEntity.setPassword(teacherEntity.getPassword());
                int tid = teacherService.getTeacherId(anotherEntity);
                httpSession.setAttribute(teacherKey, tid);
                map.put("IdTeacher", tid);
            }
        }
        return new ResponseMessage(teacherStatus, map);
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
     *     "gender":0(male)/1(female),
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
     * @api {post} /teacher/modifyPassword 教师修改密码
     * @apiName modifyPassword
     * @apiGroup teacher
     * @apiPermission teacher
     * @apiVersion 0.1.0
     * @apiParam {Number} teacherId 教师ID
     * @apiParam {String} oldPassword 教师旧密码
     * @apiParam {String} newPassword 教师新密码
     * @apiParamExample {json} Request-Example
     * {
     *     "teacherId":1,
     *     "oldPassword":"111",
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
        String oldPassword = (String)map.get("oldPassword");
        String newPassword = (String)map.get("newPassword");
        if(0 == teacherId || null == newPassword) {
            logger.error("teacher modify password arguments error");
            throw new TeacherException("teacher modify password arguments error", TeacherStatus.ARGUMENTS_ERROR);
        } else {
            teacherStatus = teacherService.modifyPassword(teacherId, oldPassword, newPassword);
        }
        return new ResponseMessage(teacherStatus);
    }

    /**
     * @api {get} /teacher/search/:keyword/:page/:size 搜索老师
     * @apiName searchTeacher
     * @apiGroup teacher
     * @apiVersion 0.1.0
     * @apiParam {String} keyword 搜素关键字
     * @apiParam {Number} page 页码（从0开始）
     * @apiParam {Number} size 每页数据数量
     * @apiSuccessExample {json} Success-Response:
     * {
     *     "code":0,
     *     "msg":"SUCCESS",
     *     "body":
     *     {
     *     "content":[
     *     {
     *     "idTeacher":1,
     *     "account":"teaccher",
     *     "password":null,
     *     "birthday":"1980-10-01",
     *     "educationBackground":"doctor",
     *     "college":"uestc",
     *     "name":"jack",
     *     "id":"2",
     *     "email":"123@123.com",
     *     "phoneNumber":"12345678901",
     *     "gender":0,
     *     "workPlace":"uestc",
     *     "title":"123",
     *     "avatarUrl":null,
     *     "param1":null,
     *     "param2":null}],
     *     "totalElements":1,
     *     "totalPages":1,
     *     "last":true,
     *     "size":5,
     *     "number":0,
     *     "sort":[
     *     {
     *     "direction":"DESC",
     *     "property":"idTeacher",
     *     "ignoreCase":false,
     *     "nullHandling":"NATIVE",
     *     "ascending":false}],
     *     "first":true,
     *     "numberOfElements":1
     *     }
     * }
     * @apiUse NormalErrorResponse
     * @apiUse ArgumentsErrorResponse
     * @apiUse NotFoundErrorResponse
     * @apiUse UnLoginErrorResponse
     * @apiUse UploadFileErrorResponse
     */
    @RequestMapping(value = "/teacher/search/{keyword}/{page}/{size}", method = RequestMethod.GET)
    public ResponseMessage searchTeacher(@PathVariable("keyword") String keyword, @PathVariable("page") int page, @PathVariable("size") int size) {
        Pageable pageable = new PageRequest(page, size, Sort.Direction.DESC, "idTeacher");
        Page<TeacherEntity> teacherEntities = teacherService.searchTeacher(keyword, pageable);
        return new ResponseMessage(TeacherStatus.SUCCESS, teacherEntities);
    }

    /**
     * @api {post} /teacher/uploadAvatar/:id 教师上传头像
     * @apiName uploadAvatar
     * @apiGroup teacher
     * @apiPermssion teacher
     * @apiVersion 0.1.0
     * @apiParam {File} avatar 教师头像
     * @apiSuccessExample {json} Success-Response:
     * {
     *     "code":0,
     *     "msg":"SUCCESS",
     *     "body":"\\upload\\1\\1231479614806368.jpg"
     * }
     * @apiUse NormalSuccessResponse
     * @apiUse NormalErrorResponse
     * @apiUse ArgumentsErrorResponse
     * @apiUse NotFoundErrorResponse
     * @apiUse UnLoginErrorResponse
     * @apiUse UploadFileErrorResponse
     */
    @RequestMapping(value = "/teacher/uploadAvatar/{id}", method = RequestMethod.POST)
    public ResponseMessage uploadAvatar(@PathVariable("id") int id, HttpServletRequest request, @RequestParam("avatar") MultipartFile avatar) {
        String avatarPath;
        avatarPath = teacherService.uploadAvatar(request, avatar, id);
        return new ResponseMessage(TeacherStatus.SUCCESS, avatarPath);
    }

    /**
     * @api {post} /teacher/modifyInfo 修改教师个人信息
     * @apiName modifyInfo
     * @apiGroup teacher
     * @apiVersion 0.1.0
     * @apiParam {json} TeacherEntity 教师信息
     * @apiParamExample {json} Request-Example
     * {
     *     "IdTeacher":1,
     *     "birthday":"yyyy-mm-dd",
     *     "educationBackground":"abc",
     *     "college":"abc",
     *     "email":"123@123.com"
     *     "phoneNumber":"13000000000",
     *     "workPlace":"abc",
     *     "title":"abc"
     * }
     * @apiUse NormalSuccessResponse
     * @apiUse NormalErrorResponse
     * @apiUse NotFoundErrorResponse
     * @apiUse UnLoginErrorResponse
     */
    @RequestMapping(value = "/teacher/modifyInfo", method = RequestMethod.POST)
    public ResponseMessage modifyInfo(@RequestBody TeacherEntity teacherEntity) {
        TeacherStatus teacherStatus;
        teacherStatus = teacherService.modifyTeacherInfo(teacherEntity);
        return new ResponseMessage(teacherStatus);
    }

    /**
     * @api {post} /teacher/forgetPassword 忘记密码
     * @apiName forgetPassword
     * @apiGroup teacher
     * @apiVersion 0.1.0
     * @apiParam {json} teacherEmail 教师邮箱
     * @apiParamExample {json} Request-Example
     * {
     *     "teacherEmail":"123@123.com"
     * }
     * @apiUse NormalSuccessResponse
     * @apiUse NormalErrorResponse
     * @apiUse NotFoundErrorResponse
     * @apiUse UnLoginErrorResponse
     */
    @RequestMapping(value = "/teacher/forgetPassword", method = RequestMethod.POST)
    public ResponseMessage forgetPassword(@RequestBody Map map, HttpServletRequest request) {
        int teacherId;
        String email = (String)map.get("teacherEmail");
        teacherId = teacherService.forgetPassword(email);
        if(0 == teacherId) {
            throw new TeacherException("find teacher by email error", TeacherStatus.FORGET_PASSWORD_ERROR);
        } else {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("15528359737@163.com");
            message.setTo(email);
            message.setSubject("重置密码");
            message.setText("请点击此连接重置密码：" + request.getContextPath() + "/html/reset_passwd.html?id=" + teacherId);

            mailSender.send(message);
        }
        return new ResponseMessage(TeacherStatus.SUCCESS);
    }

    /**
     * @api {post} /teacher/resetPassword 教师重置密码
     * @apiName resetPassword
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
    @RequestMapping(value = "/teacher/resetPassword", method = RequestMethod.POST)
    public ResponseMessage resetPassword(@RequestBody Map map) {
        TeacherStatus teacherStatus;
        int teacherId = (int)map.get("teacherId");
        String newPassword = (String)map.get("newPassword");
        if(0 == teacherId || null == newPassword) {
            logger.error("teacher modify password arguments error");
            throw new TeacherException("teacher modify password arguments error", TeacherStatus.ARGUMENTS_ERROR);
        } else {
            teacherStatus = teacherService.resetPassword(teacherId, newPassword);
        }
        return new ResponseMessage(teacherStatus);
    }


}
