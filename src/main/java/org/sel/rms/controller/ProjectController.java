package org.sel.rms.controller;

import org.apache.log4j.Logger;
import org.sel.rms.common.ResponseMessage;
import org.sel.rms.entity.ProjectEntity;
import org.sel.rms.entity.ValidGroup.ProjectGroup;
import org.sel.rms.service.ProjectService;
import org.sel.rms.status.ProjectStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Created by xubowei on 01/11/2016.
 */
@RestController
public class ProjectController {
    private final static Logger logger = Logger.getLogger(ProjectController.class);

    @Autowired
    ProjectService projectService;

    @Value("${config.teacher.key}")
    String teacherKey;


    @RequestMapping(value = "/addkey")
    public void addTeacherKey(HttpSession httpSession) {
        httpSession.setAttribute(teacherKey, "1");
    }



    /**
     * @api {post} /teacher/project/publish 发表论文
     * @apiName publishProject
     * @apiGroup Project
     * @apiPermission teacher
     * @apiVersion 0.1.0
     * @apiParam {json} projectEntity 论文信息
     * @apiParamExample {json} Request-Example:
     * {
     *      "idTeacher":1,
     *      "name":"ds",
     *      "source":"计算机学院",
     *      "projectTime":"2016-10-30",
     *      "master":"哈哈",
     *      "funds":"1231412314.11",
     *      "publishTime":"2016-10-30",
     *      "introduction":"123"
     * }
     *
     * @apiUse NormalSuccessResponse
     * @apiUse NormalErrorResponse
     * @apiUse ArgumentsErrorResponse
     * @apiUse DataBaseErrorResponse
     * @apiUse UnLoginErrorResponse
     */
    @RequestMapping(value = "/teacher/project/publish", method = RequestMethod.POST)
    public ResponseMessage publish(@Validated(ProjectGroup.publish.class) @RequestBody ProjectEntity projectEntity, BindingResult bindingResult, HttpSession httpSession) {

        ProjectStatus projectStatus;
        String idTeacher = (String) httpSession.getAttribute(teacherKey);
        if (idTeacher == null) {
            logger.error("teacher is offline!");
            projectStatus = ProjectStatus.UN_LOGIN;
        } else {
            if (bindingResult.hasErrors()) {
                logger.error("projectPublish argument error: " + projectEntity);
                projectStatus = ProjectStatus.ARGUMENTS_ERROR;
            } else {
                System.out.println(projectEntity);
                projectService.publishProject(projectEntity);
                projectStatus = ProjectStatus.SUCCESS;
            }
        }
        return new ResponseMessage(projectStatus);
    }


    /**
     * @api {post} /teacher/project/modify 修改论文
     * @apiName modifyProject
     * @apiGroup Project
     * @apiPermission teacher
     * @apiVersion 0.1.0
     * @apiParam {json} projectEntity 论文信息
     * @apiParamExample {json} Request-Example:
     * {
     *      "idProject":4,
     *      "idTeacher":1,
     *      "name":"ds",
     *      "source":"国外",
     *      "projectTime":"2016-10-30",
     *      "master":"哈哈",
     *      "funds":"1231412314.11",
     *      "publishTime":"2016-10-30",
     *      "introduction":"123"
     * }
     *
     * @apiUse NormalSuccessResponse
     * @apiUse NormalErrorResponse
     * @apiUse ArgumentsErrorResponse
     * @apiUse DataBaseErrorResponse
     * @apiUse NotFoundErrorResponse
     * @apiUse UnLoginErrorResponse
     */
    @RequestMapping(value = "/teacher/project/modify", method = RequestMethod.POST)
    public ResponseMessage modify(@Validated(ProjectGroup.modify.class) @RequestBody ProjectEntity projectEntity, BindingResult bindingResult, HttpServletRequest request) {
        ProjectStatus projectStatus;
        String idTeacher = (String) request.getSession().getAttribute(teacherKey);
        if (idTeacher == null) {
            logger.error("teacher is offline!");
            projectStatus = ProjectStatus.UN_LOGIN;
        } else {
            if (bindingResult.hasErrors()) {
//            StringBuilder errors = new StringBuilder("error:");
//            bindingResult.getAllErrors().forEach(n -> errors.append(n.getDefaultMessage() + "     "));
//            logger.error(errors.toString());
                logger.error("projectPublish argument error: " + projectEntity);
                projectStatus = ProjectStatus.ARGUMENTS_ERROR;
            } else {
                projectService.modifyProject(projectEntity);
                projectStatus = ProjectStatus.SUCCESS;
            }
        }
        return new ResponseMessage(projectStatus);
    }

    /**
     * @api {get} /teacher/project/delete/:id 删除论文
     * @apiName deleteProject
     * @apiGroup Project
     * @apiPermission teacher
     * @apiVersion 0.1.0
     * @apiParam {Number} id 论文id
     * @apiUse NormalSuccessResponse
     * @apiUse NormalErrorResponse
     * @apiUse NotFoundErrorResponse
     * @apiUse UnLoginErrorResponse
     * @apiUse PermissionDenyErrorResponse
     */
    @RequestMapping(value = "/teacher/project/delete/{id}", method = RequestMethod.GET)
    public ResponseMessage delete(@PathVariable("id") int id, HttpSession httpSession) {
        ProjectStatus projectStatus;
        String idTeacher = (String) httpSession.getAttribute(teacherKey);
        if (idTeacher == null) {
            logger.error("teacher is offline!");
            projectStatus = ProjectStatus.UN_LOGIN;
        } else {
            projectService.deleteProject(id, Integer.parseInt(idTeacher));
            projectStatus = ProjectStatus.SUCCESS;
        }
        return new ResponseMessage(projectStatus);
    }

    /**
     * @api {post} /teacher/project/myprojects 查询自己发表的论文
     * @apiName getProjectsByTeacher
     * @apiGroup Project
     * @apiPermission teacher
     * @apiVersion 0.1.0
     * @apiParam {json} map 分页信息
     * @apiParamExample {json} Request-Example:
     * {
     * "page":0,
     * "size":3
     * }
     * @apiSuccessExample {json} Success-Response:
     * HTTP/1.1 200 OK
     *   {
     *   "code": 0,
     *   "msg": "SUCCESS",
     *   "body": {
     *   "content": [
     *   {
     *   "idProject": 5,
     *   "idTeacher": 1,
     *   "name": "ds",
     *   "source": "计算机学院",
     *   "projectTime": "2016-10-30",
     *   "master": "哈哈",
     *   "funds": 1231412314.11,
     *   "publishTime": "2016-10-30",
     *   "introduction": "123",
     *   "param1": null,
     *   "param2": null
     *   },
     *   {
     *   "idProject": 4,
     *   "idTeacher": 1,
     *   "name": "ds",
     *   "source": "十大",
     *   "projectTime": "2016-10-30",
     *   "master": "阿萨德撒",
     *   "funds": 123123,
     *   "publishTime": "2016-10-30",
     *   "introduction": "123",
     *   "param1": null,
     *   "param2": null
     *   }
     *   ],
     *   "last": true,
     *   "totalElements": 2,
     *   "totalPages": 1,
     *   "size": 3,
     *   "number": 0,
     *   "sort": [
     *   {
     *   "direction": "DESC",
     *   "property": "idProject",
     *   "ignoreCase": false,
     *   "nullHandling": "NATIVE",
     *   "ascending": false
     *   }
     *   ],
     *   "first": true,
     *   "numberOfElements": 2
     *   }
     *   }
     * @apiUse NormalErrorResponse
     * @apiUse ArgumentsErrorResponse
     * @apiUse DataBaseErrorResponse
     * @apiUse UnLoginErrorResponse
     */
    @RequestMapping(value = "/teacher/project/myprojects", method = RequestMethod.POST)
    public ResponseMessage getProjectsByTeacher(@RequestBody Map map, HttpSession httpSession) {
        Page<ProjectEntity> projectEntities = null;
        String idTeacher = (String) httpSession.getAttribute(teacherKey);
        int page = (int) map.get("page");
        int size = (int) map.get("size");
        ProjectStatus projectStatus;
        if (idTeacher == null) {
            logger.error("teacher is offline!");
            projectStatus = ProjectStatus.UN_LOGIN;
        } else {
            Pageable pageable = new PageRequest(page, size, Sort.Direction.DESC, "idProject");
            projectEntities = projectService.getProjectEntitiesByIdOfTeacher(Integer.parseInt(idTeacher), pageable);
            projectStatus = ProjectStatus.SUCCESS;
        }
        return new ResponseMessage(projectStatus, projectEntities);
    }



}
