package org.sel.rms.controller;

import org.apache.log4j.Logger;
import org.sel.rms.common.ResponseMessage;
import org.sel.rms.entity.PaperEntity;
import org.sel.rms.entity.ProjectEntity;
import org.sel.rms.entity.ValidGroup.ProjectGroup;
import org.sel.rms.service.ProjectService;
import org.sel.rms.status.PaperStatus;
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
     * @api {post} /teacher/project/publish 发表项目
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
        Integer idTeacher = (Integer) httpSession.getAttribute(teacherKey);
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
     * @api {post} /teacher/project/modify 修改项目
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
        Integer idTeacher = (Integer) request.getSession().getAttribute(teacherKey);
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
     * @api {get} /teacher/project/delete/:id 删除项目
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
        Integer idTeacher = (Integer) httpSession.getAttribute(teacherKey);
        if (idTeacher == null) {
            logger.error("teacher is offline!");
            projectStatus = ProjectStatus.UN_LOGIN;
        } else {
            projectService.deleteProject(id, idTeacher);
            projectStatus = ProjectStatus.SUCCESS;
        }
        return new ResponseMessage(projectStatus);
    }

    /**
     * @api {get} /project/teacher/:id/:page/:size 查询某个老师发表的项目
     * @apiName getProjectsByTeacher
     * @apiGroup Project
     * @apiVersion 0.1.0
     * @apiParam {Number} id 老师id
     * @apiParam {Number} page 页码（从0开始）
     * @apiParam {Number} size 每页数据数量
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
     * @apiUse DataBaseErrorResponse
     */
    @RequestMapping(value = "/project/teacher/{id}/{page}/{size}", method = RequestMethod.GET)
    public ResponseMessage getProjectsByTeacher(@PathVariable("id") int id, @PathVariable("page") int page, @PathVariable("size") int size) {
        Page<ProjectEntity> projectEntities = null;
        ProjectStatus projectStatus;
        Pageable pageable = new PageRequest(page, size, Sort.Direction.DESC, "idProject");
        projectEntities = projectService.getProjectEntitiesByIdOfTeacher(id, pageable);
        projectStatus = ProjectStatus.SUCCESS;
        return new ResponseMessage(projectStatus, projectEntities);
    }


    /**
     * @api {get} /project/search/:keyword/:page/:size 查询项目（匹配项目名称或项目介绍或项目责任人或项目来源，用空格分隔）
     * @apiName searchProjects
     * @apiGroup Project
     * @apiVersion 0.1.0
     * @apiParam {String} keyword 关键词
     * @apiParam {Number} page 页码（从0开始）
     * @apiParam {Number} size 每页数据数量
     * @apiSuccessExample {json} Success-Response:
     * HTTP/1.1 200 OK
     *   {
     *     "code": 0,
     *     "msg": "SUCCESS",
     *     "body": {
     *       "content": [
     *         {
     *           "idProject": 5,
     *           "idTeacher": 1,
     *           "name": "ds",
     *           "source": "计算机学院",
     *           "projectTime": "2016-10-30",
     *           "master": "哈哈",
     *           "funds": 1231412314.11,
     *           "publishTime": "2016-10-30",
     *           "introduction": "123",
     *           "param1": null,
     *           "param2": null
     *         }
     *       ],
     *       "last": true,
     *       "totalPages": 1,
     *       "totalElements": 1,
     *       "size": 5,
     *       "number": 0,
     *       "sort": [
     *         {
     *           "direction": "DESC",
     *           "property": "idProject",
     *           "ignoreCase": false,
     *           "nullHandling": "NATIVE",
     *           "ascending": false
     *         }
     *       ],
     *       "first": true,
     *       "numberOfElements": 1
     *     }
     *   }
     *
     *
     * @apiUse NormalErrorResponse
     * @apiUse DataBaseErrorResponse
     */
    @RequestMapping(value = "/project/search/{keyWord}/{page}/{size}", method = RequestMethod.GET)
    public ResponseMessage search(@PathVariable("keyWord") String keyWord, @PathVariable("page") int page, @PathVariable("size") int size) {
        Pageable pageable = new PageRequest(page, size, Sort.Direction.DESC, "idProject");
        Page<ProjectEntity> projectEntities = projectService.searchProjects(keyWord, pageable);
        return new ResponseMessage(PaperStatus.SUCCESS, projectEntities);
    }

    /**
     * @api {get} /project/:id 获取单条项目信息
     * @apiName findProjectById
     * @apiGroup Project
     * @apiVersion 0.1.0
     * @apiParam {Number} id 项目id
     * @apiSuccessExample {json} Success-Response:
     * HTTP/1.1 200 OK
     * {
     *   "code": 0,
     *   "msg": "SUCCESS",
     *   "body": {
     *     "idProject": 7,
     *     "idTeacher": 1,
     *     "name": "ds",
     *     "source": "计算机学院",
     *     "projectTime": "2016-10-30",
     *     "master": "哈哈",
     *     "funds": 1231412314.11,
     *     "publishTime": "2016-11-15",
     *     "introduction": "123",
     *     "param1": null,
     *     "param2": null
     *   }
     * }
     *
     * @apiUse NormalErrorResponse
     * @apiUse NotFoundErrorResponse
     */
    @RequestMapping(value = "/project/{id}", method = RequestMethod.GET)
    public ResponseMessage findOne(@PathVariable("id") int id){
        ProjectEntity projectById = projectService.getProjectById(id);
        return new ResponseMessage(ProjectStatus.SUCCESS, projectById);
    }

}
