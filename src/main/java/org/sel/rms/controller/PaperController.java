package org.sel.rms.controller;

import ch.qos.logback.core.status.Status;
import org.apache.log4j.Logger;
import org.sel.rms.common.ResponseMessage;
import org.sel.rms.entity.PaperEntity;
import org.sel.rms.entity.ValidGroup.PaperGroup;
import org.sel.rms.exception.PaperException;
import org.sel.rms.service.PaperService;
import org.sel.rms.status.PaperStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

/**
 * Created by xubowei on 30/10/2016.
 */
@RestController
public class PaperController {
    private final static Logger logger = Logger.getLogger(PaperController.class);

    @Autowired
    PaperService paperService;


    /**
     * @api {post} /teacher/paper/publish 发表论文
     * @apiName publishPaper
     * @apiGroup Paper
     * @apiPermission teacher
     * @apiVersion 0.1.0
     * @apiParam {json} paperEntity 论文信息
     * @apiParamExample {json} Request-Example:
     * {
     * "idTeacher":1,
     * "title":"ds",
     * "releaseDate":"2016-10-30",
     * "writer":"xbw",
     * "publishDate":"2016-10-30",
     * "publishPlace":"aaa",
     * "keyWord":"123",
     * "abstractContent":"123",
     * "content":"123"
     * }
     * @apiUse NomalSuccessResponse
     * @apiUse NomalErrorResponse
     * @apiUse ArgumentsErrorResponse
     * @apiUse DataBaseErrorResponse
     * @apiUse  UnLoginErrorResponse
     */
    @RequestMapping(value = "/teacher/paper/publish", method = RequestMethod.POST)
    public ResponseMessage publish(@Validated(PaperGroup.publish.class) @RequestBody PaperEntity paperEntity, BindingResult bindingResult, HttpSession httpSession) {

        PaperStatus paperStatus;
        String idTeacher = (String) httpSession.getAttribute("idTeacher");
        if (idTeacher == null) {
            logger.error("teacher is offline!");
            paperStatus = PaperStatus.UN_LOGIN;
        } else {
            if (bindingResult.hasErrors()) {
                logger.error("paperPublish argument error: " + paperEntity);
                paperStatus = PaperStatus.ARGUMENTS_ERROR;
            } else {
                paperService.publishPaper(paperEntity);
                paperStatus = PaperStatus.SUCCESS;
            }
        }
        return new ResponseMessage(paperStatus);
    }



    /**
     *
     *   @api {post} /teacher/paper/modify 修改论文
     *   @apiName modifyPaper
     *   @apiGroup Paper
     *   @apiPermission teacher
     *   @apiVersion 0.1.0
     *   @apiParam {json} paperEntity 论文信息
     *   @apiParamExample {json} Request-Example:
     *     {
     *       "idPaper":1,
     *       "idTeacher":1,
     *       "title":"ds",
     *       "releaseDate":"2016-10-30",
     *       "writer":"xbw",
     *       "publishDate":"2016-10-30",
     *       "publishPlace":"aaa",
     *       "keyWord":"123",
     *       "abstractContent":"123",
     *       "content":"123"
     *     }
     *
     *   @apiUse  NomalSuccessResponse
     *   @apiUse  NomalErrorResponse
     *   @apiUse  ArgumentsErrorResponse
     *   @apiUse  DataBaseErrorResponse
     *   @apiUse  NotFoundErrorResponse
     *   @apiUse  UnLoginErrorResponse
     */
    @RequestMapping(value = "/teacher/paper/modify",method = RequestMethod.POST)
    public ResponseMessage modify(@Validated(PaperGroup.modify.class) @RequestBody PaperEntity paperEntity, BindingResult bindingResult, HttpSession httpSession) {
        PaperStatus paperStatus;
        String idTeacher = (String) httpSession.getAttribute("idTeacher");
        if (idTeacher == null) {
            logger.error("teacher is offline!");
            paperStatus = PaperStatus.UN_LOGIN;
        } else {
            if (bindingResult.hasErrors()) {
//            StringBuilder errors = new StringBuilder("error:");
//            bindingResult.getAllErrors().forEach(n -> errors.append(n.getDefaultMessage() + "     "));
//            logger.error(errors.toString());
                logger.error("paperPublish argument error: " + paperEntity);
                paperStatus = PaperStatus.ARGUMENTS_ERROR;
            } else {
                paperService.modifyPaper(paperEntity);
                paperStatus = PaperStatus.SUCCESS;
            }
        }
        return new ResponseMessage(paperStatus);
    }

    /**
     * @api {get} /teacher/paper/delete/:id 删除论文
     * @apiName deletePaper
     * @apiGroup Paper
     * @apiPermission teacher
     * @apiVersion 0.1.0
     * @apiParam {Number} id 论文id
     * @apiUse NomalSuccessResponse
     * @apiUse NomalErrorResponse
     * @apiUse NotFoundErrorResponse
     * @apiUse UnLoginErrorResponse
     * @apiUse PermissionDenyErrorResponse
     */
    @RequestMapping(value = "/teacher/paper/delete/{id}", method = RequestMethod.GET)
    public ResponseMessage delete(@PathVariable("id") int id, HttpSession httpSession) {
        PaperStatus paperStatus;
        String idTeacher = (String) httpSession.getAttribute("idTeacher");
        if (idTeacher == null) {
            logger.error("teacher is offline!");
            paperStatus = PaperStatus.UN_LOGIN;
        } else {
            paperService.deletePaper(id, Integer.parseInt(idTeacher));
            paperStatus = PaperStatus.SUCCESS;
        }
        return new ResponseMessage(paperStatus);
    }

    /**
     *
     *   @api {post} /teacher/paper/mypapers 查询自己发表的论文
     *   @apiName getPapersByTeacher
     *   @apiGroup Paper
     *   @apiPermission teacher
     *   @apiVersion 0.1.0
     *   @apiParam {json} map 分页信息
     *   @apiParamExample {json} Request-Example:
     *      {
     *          "page":0,
     *          "size":3
     *      }
     *
     *   @apiSuccessExample {json} Success-Response:
     *     HTTP/1.1 200 OK
     *     {
     *       "code": 0,
     *       "msg": "SUCCESS",
     *       "body": {
     *           "content": [
     *           {
     *           "idPaper": 15,
     *           "idTeacher": 1,
     *           "title": "aaaaaaaaaaaaaaa",
     *           "releaseDate": "2016-10-30",
     *           "writer": "xbw",
     *           "publishDate": "2016-10-30",
     *           "publishPlace": "aaa",
     *           "keyWord": "123",
     *           "abstractContent": "123",
     *           "content": "123",
     *           "param1": null,
     *           "param2": null
     *           },
     *           {
     *           "idPaper": 14,
     *           "idTeacher": 1,
     *           "title": "aaaaaaaaaaaaaaa",
     *           "releaseDate": "2016-10-30",
     *           "writer": "xbw",
     *           "publishDate": "2016-10-30",
     *           "publishPlace": "aaa",
     *           "keyWord": "123",
     *           "abstractContent": "123",
     *           "content": "123",
     *           "param1": null,
     *           "param2": null
     *           },
     *           {
     *           "idPaper": 13,
     *           "idTeacher": 1,
     *           "title": "aaaaaaaaaaaaaaa",
     *           "releaseDate": "2016-10-30",
     *           "writer": "xbw",
     *           "publishDate": "2016-10-30",
     *           "publishPlace": "aaa",
     *           "keyWord": "123",
     *           "abstractContent": "123",
     *           "content": "123",
     *           "param1": null,
     *           "param2": null
     *           }
     *           ],
     *           "last": false,
     *           "totalElements": 14,
     *           "totalPages": 5,
     *           "size": 3,
     *           "number": 0,
     *           "sort": [
     *           {
     *           "direction": "DESC",
     *           "property": "idPaper",
     *           "ignoreCase": false,
     *           "nullHandling": "NATIVE",
     *           "ascending": false
     *           }
     *           ],
     *           "first": true,
     *           "numberOfElements": 3
     *           }
     *     }
     *
     *   @apiUse  NomalErrorResponse
     *   @apiUse  ArgumentsErrorResponse
     *   @apiUse  DataBaseErrorResponse
     *   @apiUse  UnLoginErrorResponse
     */
    @RequestMapping(value = "/teacher/paper/mypapers",method = RequestMethod.POST)
    public ResponseMessage getPapersByTeacher(@RequestBody Map map, HttpSession httpSession) {
        Page<PaperEntity> paperEntities = null;
        String idTeacher = (String) httpSession.getAttribute("idTeacher");
        int page = (int)map.get("page");
        int size = (int)map.get("size");
        PaperStatus paperStatus;
        if (idTeacher == null) {
            logger.error("teacher is offline!");
            paperStatus = PaperStatus.UN_LOGIN;
        } else {
            Pageable pageable = new PageRequest(page, size, Sort.Direction.DESC, "idPaper");
            paperEntities = paperService.getPageEntitiesByIdOfTeacher(Integer.parseInt(idTeacher), pageable);
            paperStatus = PaperStatus.SUCCESS;
        }
        return new ResponseMessage(paperStatus, paperEntities);
    }
}
