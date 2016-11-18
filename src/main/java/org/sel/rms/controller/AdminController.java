package org.sel.rms.controller;

import org.apache.log4j.Logger;
import org.sel.rms.common.ResponseMessage;
import org.sel.rms.entity.AdminEntity;
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
                List list = adminService.getAdmin(anotherAdminEntity);
                int aid = (int) list.get(1);
                httpSession.setAttribute(adminKey, aid);
            }
        }
        return new ResponseMessage(adminStatus);
    }

    /**
     * @api {post} /admin/checkTeacher 审核通过教师
     * @apiName checkTeacher
     * @apigroup admin
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
        int teacherId = (int)map.get("teacherId");
        String teacherMail = (String)map.get("teacherMail");
        if(0 == teacherId) {
            logger.error("check teacher arguments error");
            throw new AdminException("check teacher arguments error", AdminStatus.ARGUMENTS_ERROR);
        } else {
            adminStatus = adminService.checkTeacher(teacherId);
            if(AdminStatus.SUCCESS.equals(adminStatus)) {
                SimpleMailMessage message = new SimpleMailMessage();
                message.setFrom("15528359737@163.com");
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
                message.setFrom("15528359737@163.com");
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
}
