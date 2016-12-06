package org.sel.rms.controller;

import org.apache.log4j.Logger;
import org.sel.rms.common.ResponseMessage;
import org.sel.rms.entity.AdminEntity;
import org.sel.rms.entity.TeacherEntity;
import org.sel.rms.entity.ValidGroup.AdminGroup;
import org.sel.rms.exception.AdminException;
import org.sel.rms.service.AdminService;
import org.sel.rms.service.TeacherService;
import org.sel.rms.status.AdminStatus;
import org.sel.rms.status.TeacherStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Huxh on 2016/11/1.
 */
@RestController
public class AdminController {
    private final static Logger logger = Logger.getLogger(AdminController.class);

    @Autowired
    AdminService adminService;

    @Autowired
    TeacherService teacherService;

    @Autowired
    JavaMailSender mailSender;

    @Value("config.admin.key")
    String adminKey;

    /**
     * @api {post} /admin/login 管理员登录
     * @apiName adminLogin
     * @apiGroup admin
     * @apiPermission admin
     * @apiVersion 0.1.0
     * @apiParam {json} AdminEntity 管理员信息
     * @apiParamExample {json} Request-Example:
     *{
     *     "account":"abc",
     *     "password":"123"
     *}
     * @apiUse NormalSuccessResponse
     * @apiUse NormalErrorResponse
     * @apiUse ArgumentsErrorResponse
     * @apiUse DataBaseErrorResponse
     * @apiUse UnLoginErrorResponse
     */
    @RequestMapping(value = "/admin/login", method = RequestMethod.POST)
    public ResponseMessage adminLogin(@Validated(AdminGroup.class) @RequestBody AdminEntity adminEntity, HttpSession httpSession, BindingResult bindingResult) {
        AdminStatus adminStatus;
        if(bindingResult.hasErrors()) {
            logger.error("admin login arguments error");
            adminStatus = AdminStatus.ARGUMENTS_ERROR;
        } else {
            adminStatus = adminService.adminAuth(adminEntity);
            if (adminStatus.equals(AdminStatus.SUCCESS)) {
                AdminEntity anotherAdminEntity = new AdminEntity();
                anotherAdminEntity.setAccount(adminEntity.getAccount());
                anotherAdminEntity.setPassword(adminEntity.getPassword());
                int aid = adminService.getAdmin(anotherAdminEntity);
                httpSession.setAttribute(adminKey, aid);
            }
        }
        return new ResponseMessage(adminStatus);
    }

    /**
     * @api {get} /admin/logout 管理员登出
     * @apiName adminLogout
     * @apiGroup admin
     * @apiPermission admin
     * @apiVersion 0.1.0
     * @apiUse NormalSuccessResponse
     * @apiUse NormalErrorResponse
     * @apiUse UnLoginErrorResponse
     */
    @RequestMapping(value = "/admin/logout", method = RequestMethod.GET)
    public ResponseMessage adminLogout(HttpSession httpSession) {
        httpSession.removeAttribute(adminKey);
        return new ResponseMessage(AdminStatus.SUCCESS);
    }

    /**
     * @api {post} /admin/checkTeacher 审核通过教师
     * @apiName checkTeacher
     * @apiGroup admin
     * @apiPermission admin
     * @apiVersion 0.1.0
     * @apiParam {Number} teacherId 教师id
     * @apiParam {String} teacherMail 教师邮箱
     * @apiParamExample {json} Request-Example:
     * {
     *     "teacherId":1,
     *     "teacherMail":"123@123.com"
     * }
     * @apiUse NormalSuccessResponse
     * @apiUse NormalErrorResponse
     * @apiUse ArgumentsErrorResponse
     * @apiUse DataBaseErrorResponse
     * @apiUse UnLoginErrorResponse
     */
    @RequestMapping(value = "/admin/checkTeacher", method = RequestMethod.POST)
    public ResponseMessage checkTeacher(@RequestBody Map map) {
        AdminStatus adminStatus;
        int teacherId = Integer.parseInt((String)map.get("teacherId"));
        String teacherMail = (String)map.get("teacherMail");
        if(0 == teacherId) {
            logger.error("check teacher arguments error");
            throw new AdminException("check teacher arguments error", AdminStatus.ARGUMENTS_ERROR);
        } else {
            adminStatus = adminService.checkTeacher(teacherId);
            if(AdminStatus.SUCCESS.equals(adminStatus)) {
                SimpleMailMessage message = new SimpleMailMessage();
                message.setFrom("科研管理系统");
                message.setTo(teacherMail);
                message.setSubject("审核结果");
                message.setText("审核通过");

                mailSender.send(message);
            }
        }
        return new ResponseMessage(adminStatus);
    }

    /**
     * @api {post} /admin/teacherUnpass 审核不通过教师
     * @apiName unpassTeacher
     * @apiGroup admin
     * @apiPermission admin
     * @apiVersion 0.1.0
     * @apiParam {Number} teacherId 教师id
     * @apiParam {String} teacherMail 教师邮箱
     * @apiParamExample {json} Request-Example:
     * {
     *     "teacherId":1,
     *     "teacherMail":"123@123.com"
     * }
     * @apiUse NormalSuccessResponse
     * @apiUse NormalErrorResponse
     * @apiUse ArgumentsErrorResponse
     * @apiUse DataBaseErrorResponse
     * @apiUse UnLoginErrorResponse
     */
    @RequestMapping(value = "/admin/teacherUnpass", method = RequestMethod.POST)
    public ResponseMessage teacherUnpass(@RequestBody Map map) {
        AdminStatus adminStatus;
        int teacherId = (int)map.get("teacherId");
        String teacherMail = (String)map.get("teacherMail");
        if(0 == teacherId) {
            logger.error("check teacher arguments error");
            throw new AdminException("check teacher arguments error", AdminStatus.ARGUMENTS_ERROR);
        } else {
            adminStatus = adminService.unpassTeacher(teacherId);
            if(AdminStatus.SUCCESS.equals(adminStatus)) {
                SimpleMailMessage message = new SimpleMailMessage();
                message.setFrom("科研管理系统");
                message.setTo(teacherMail);
                message.setSubject("审核结果");
                message.setText("审未通过,请重新注册");

                mailSender.send(message);
            }
        }
        return new ResponseMessage(adminStatus);
    }

    /**
     * @api {get} /admin/deleteTeacher/:id 删除教师信息
     * @apiName deleteTeacher
     * @apiGroup admin
     * @apiPermission admin
     * @apiVersion 0.1.0
     * @apiParam {Number} id 教师id
     * @apiUse NormalSuccessResponse
     * @apiUse NormalErrorResponse
     * @apiUse NotFoundErrorResponse
     * @apiUse UnLoginErrorResponse
     * @apiUse PermissionDenyErrorResponse
     */
    @RequestMapping(value = "/admin/deleteTeacher/{id}", method = RequestMethod.GET)
    public ResponseMessage deleteTeacher(@PathVariable("id") int id) {
        TeacherStatus teacherStatus;
        int teacherId = id;
        if(0 == teacherId) {
            logger.error("check teacher arguments error");
            throw new AdminException("check teacher arguments error", AdminStatus.ARGUMENTS_ERROR);
        } else {
            teacherStatus = teacherService.deleteTeacher(teacherId);
        }
        return new ResponseMessage(teacherStatus);
    }

    /**
     * @api {post} /admin/getUncheck 获取未审核教师信息及数目
     * @apiName getUncheck
     * @apiGroup admin
     * @apiPermission admin
     * @apiVersion 0.1.0
     * @apiSuccessExample {json} Success-Response:
     * HTTP/1.1 200 OK
     * {
     *     "code":0,
     *     "msg":"SUCCESS",
     *     "body":
     *     {
     *     "teacher":
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
     *     "param2":null
     *     },
     *     "uncheckNum":1
     *     }
     * }
     * @apiUse NormalErrorResponse
     * @apiUse DataBaseErrorResponse
     * @apiUse UnLoginErrorResponse
     */
    @RequestMapping(value = "/admin/getUncheck", method = RequestMethod.POST)
    public ResponseMessage getUncheck() {
        Map map = new HashMap<>();
        map = adminService.getUncheck();
        return new ResponseMessage(AdminStatus.SUCCESS, map);
    }

    /**
     * @api {post} /admin/getAllTeachers 获取所有教师信息
     * @apiName getAllTeachers
     * @apiGroup admin
     * @apiPermission admin
     * @apiVersion 0.1.0
     * @apiSuccessExample {json} Success-Response:
     * HTTP/1.1 200 OK
     * {
     *     "code":0,
     *     "msg":"SUCCESS",
     *     "body":[
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
     *     "avatarUrl":null
     *     ,"param1":null,
     *     "param2":null},
     *     {
     *     "idTeacher":2,
     *     "account":"ttt",
     *     "password":"111111",
     *     "birthday":"1970-07-01",
     *     "educationBackground":"11",
     *     "college":"11",
     *     "name":"a",
     *     "id":"1",
     *     "email":"123@123.com",
     *     "phoneNumber":"12345678901",
     *     "gender":1,
     *     "workPlace":"uestc",
     *     "title":"1",
     *     "avatarUrl":null,
     *     "param1":null,
     *     "param2":null
     *     }]
     * }
     * @apiUse NormalErrorResponse
     * @apiUse DataBaseErrorResponse
     * @apiUse UnLoginErrorResponse
     */
    @RequestMapping(value = "/admin/getAllTeachers", method = RequestMethod.POST)
    public ResponseMessage getAllTeachers() {
        List<TeacherEntity> teacherEntities;
        teacherEntities = adminService.getAllTeachers();
        return new ResponseMessage(AdminStatus.SUCCESS, teacherEntities);
    }

    /**
     * @api {get} /admin/getTeacher/:id 获取单个教师信息
     * @apiName getTeacher
     * @apiGroup admin
     * @apiPermission admin
     * @apiVersion 0.1.0
     * @apiParam {Number} id 教师id
     * @apiSuccessExample {json} Success-Response:
     * HTTP/1.1 200 OK
     * {
     *     "code":0,
     *     "msg":"SUCCESS",
     *     "body":
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
     *     "param2":null
     *     }
     * }
     *
     * @apiUse NormalSuccessResponse
     * @apiUse NormalErrorResponse
     * @apiUse ArgumentsErrorResponse
     * @apiUse DataBaseErrorResponse
     * @apiUse UnLoginErrorResponse
     */
    @RequestMapping(value = "/admin/getTeacher/{id}", method = RequestMethod.GET)
    public ResponseMessage getTeacher(@PathVariable("id") int id) {
        TeacherEntity teacherEntity;
        if(0 == id) {
            logger.error("check teacher arguments error");
            throw new AdminException("check teacher arguments error", AdminStatus.ARGUMENTS_ERROR);
        } else {
            teacherEntity = adminService.getTeacher(id);
        }
        return new ResponseMessage(AdminStatus.SUCCESS, teacherEntity);
    }

    /**
     * @api {post} /admin/modifyPassword 管理员修改密码
     * @apiName modifyPassword
     * @apiGroup admin
     * @apiPermission admin
     * @apiVersion 0.1.0
     * @apiParam {Number} adminId 管理员ID
     * @apiParam {String} oldPassword 管理员旧密码
     * @apiParam {String} newPassword 管理员新密码
     * @apiParamExample {json} Request-Example
     * {
     *     "adminId":1,
     *     "oldPassword":"111",
     *     "newPassword":"123"
     * }
     * @apiUse NormalSuccessResponse
     * @apiUse NormalErrorResponse
     * @apiUse ArgumentsErrorResponse
     * @apiUse DataBaseErrorResponse
     * @apiUse UnLoginErrorResponse
     */
    @RequestMapping(value = "/admin/modifyPassword", method = RequestMethod.POST)
    public ResponseMessage modifyPassword(@RequestBody Map map) {
        AdminStatus adminStatus;
        int adminId = (int)map.get("adminId");
        String oldPassword = (String)map.get("oldPassword");
        String newPassword = (String)map.get("newPassword");
        if(0 == adminId || null == newPassword) {
            logger.error("teacher modify password arguments error");
            throw new AdminException("admin modify password arguments error", AdminStatus.ARGUMENTS_ERROR);
        } else {
            adminStatus = adminService.modifyPassword(adminId, oldPassword, newPassword);
        }
        return new ResponseMessage(adminStatus);
    }
}
