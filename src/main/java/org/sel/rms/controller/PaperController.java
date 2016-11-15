package org.sel.rms.controller;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.sel.rms.common.ResponseMessage;
import org.sel.rms.entity.PaperEntity;
import org.sel.rms.entity.ValidGroup.PaperGroup;
import org.sel.rms.service.PaperService;
import org.sel.rms.status.PaperStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;


/**
 * Created by xubowei on 30/10/2016.
 */
@RestController
public class PaperController {
    private final static Logger logger = Logger.getLogger(PaperController.class);

    @Autowired
    PaperService paperService;

    //    @Autowired
//    SessionFactory sessionFactory;
//
    @Value("${config.teacher.key}")
    String teacherKey;

    @RequestMapping(value = "/test")
    public void addSessionKey(HttpSession httpSession) {
        httpSession.setAttribute(teacherKey,1);
    }



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
     * "publishPlace":"aaa",
     * "keyWord":"123",
     * "abstractContent":"123",
     * "content":"123"
     * }
     * @apiUse NormalSuccessResponse
     * @apiUse NormalErrorResponse
     * @apiUse ArgumentsErrorResponse
     * @apiUse DataBaseErrorResponse
     * @apiUse UnLoginErrorResponse
     */
    @RequestMapping(value = "/teacher/paper/publish", method = RequestMethod.POST)
    public ResponseMessage publish(@Validated(PaperGroup.publish.class) @RequestBody PaperEntity paperEntity, BindingResult bindingResult, HttpSession httpSession) {

        PaperStatus paperStatus;
        Integer idTeacher = (Integer) httpSession.getAttribute(teacherKey);
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
     * @api {post} /teacher/paper/modify 修改论文
     * @apiName modifyPaper
     * @apiGroup Paper
     * @apiPermission teacher
     * @apiVersion 0.1.0
     * @apiParam {json} paperEntity 论文信息
     * @apiParamExample {json} Request-Example:
     * {
     * "idPaper":1,
     * "idTeacher":1,
     * "title":"ds",
     * "releaseDate":"2016-10-30",
     * "writer":"xbw",
     * "publishPlace":"aaa",
     * "keyWord":"123",
     * "abstractContent":"123",
     * "content":"123"
     * }
     * @apiUse NormalSuccessResponse
     * @apiUse NormalErrorResponse
     * @apiUse ArgumentsErrorResponse
     * @apiUse DataBaseErrorResponse
     * @apiUse NotFoundErrorResponse
     * @apiUse UnLoginErrorResponse
     * @apiUse DeleteFileErrorResponse
     */
    @RequestMapping(value = "/teacher/paper/modify", method = RequestMethod.POST)
    public ResponseMessage modify(@Validated(PaperGroup.modify.class) @RequestBody PaperEntity paperEntity, BindingResult bindingResult, HttpServletRequest request) {
        PaperStatus paperStatus;
        Integer idTeacher = (Integer) request.getSession().getAttribute(teacherKey);
        if (idTeacher == null) {
            logger.error("teacher is offline!");
            paperStatus = PaperStatus.UN_LOGIN;
        } else {
            if (bindingResult.hasErrors()) {
                logger.error("paperPublish argument error: " + paperEntity);
                paperStatus = PaperStatus.ARGUMENTS_ERROR;
            } else {
                paperService.modifyPaper(paperEntity, request);
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
     * @apiUse NormalSuccessResponse
     * @apiUse NormalErrorResponse
     * @apiUse NotFoundErrorResponse
     * @apiUse UnLoginErrorResponse
     * @apiUse PermissionDenyErrorResponse
     */
    @RequestMapping(value = "/teacher/paper/delete/{id}", method = RequestMethod.GET)
    public ResponseMessage delete(@PathVariable("id") int id, HttpServletRequest request) {
        PaperStatus paperStatus;
        Integer idTeacher = (Integer) request.getSession().getAttribute(teacherKey);
        if (idTeacher == null) {
            logger.error("teacher is offline!");
            paperStatus = PaperStatus.UN_LOGIN;
        } else {
            paperService.deletePaper(id, idTeacher, request);
            paperStatus = PaperStatus.SUCCESS;
        }
        return new ResponseMessage(paperStatus);
    }

    /**
     * @api {get} /project/teacher/:id/:page/:size 查询某个老师的论文
     * @apiName getPapersByTeacher
     * @apiGroup Paper
     * @apiVersion 0.1.0
     * @apiParam {Number} id 老师id
     * @apiParam {Number} page 页码（从0开始）
     * @apiParam {Number} size 每页数据数量
     * @apiSuccessExample {json} Success-Response:
     * HTTP/1.1 200 OK
     * {
     * "code": 0,
     * "msg": "SUCCESS",
     * "body": {
     * "content": [
     * {
     * "idPaper": 15,
     * "idTeacher": 1,
     * "title": "aaaaaaaaaaaaaaa",
     * "releaseDate": "2016-10-30",
     * "writer": "xbw",
     * "publishDate": "2016-10-30",
     * "publishPlace": "aaa",
     * "keyWord": "123",
     * "abstractContent": "123",
     * "content": "123",
     * "param1": null,
     * "param2": null
     * },
     * {
     * "idPaper": 14,
     * "idTeacher": 1,
     * "title": "aaaaaaaaaaaaaaa",
     * "releaseDate": "2016-10-30",
     * "writer": "xbw",
     * "publishDate": "2016-10-30",
     * "publishPlace": "aaa",
     * "keyWord": "123",
     * "abstractContent": "123",
     * "content": "123",
     * "param1": null,
     * "param2": null
     * },
     * {
     * "idPaper": 13,
     * "idTeacher": 1,
     * "title": "aaaaaaaaaaaaaaa",
     * "releaseDate": "2016-10-30",
     * "writer": "xbw",
     * "publishDate": "2016-10-30",
     * "publishPlace": "aaa",
     * "keyWord": "123",
     * "abstractContent": "123",
     * "content": "123",
     * "param1": null,
     * "param2": null
     * }
     * ],
     * "last": false,
     * "totalElements": 14,
     * "totalPages": 5,
     * "size": 3,
     * "number": 0,
     * "sort": [
     * {
     * "direction": "DESC",
     * "property": "idPaper",
     * "ignoreCase": false,
     * "nullHandling": "NATIVE",
     * "ascending": false
     * }
     * ],
     * "first": true,
     * "numberOfElements": 3
     * }
     * }
     * @apiUse NormalErrorResponse
     * @apiUse ArgumentsErrorResponse
     * @apiUse DataBaseErrorResponse
     * @apiUse UnLoginErrorResponse
     */


    @RequestMapping(value = "/paper/teacher/{id}/{page}/{size}", method = RequestMethod.GET)
    public ResponseMessage getPapersByTeacher(@PathVariable("id") int id, @PathVariable("page") int page, @PathVariable("size") int size) {
        Page<PaperEntity> paperEntities = null;
        PaperStatus paperStatus;
        Pageable pageable = new PageRequest(page, size, Sort.Direction.DESC, "idPaper");
        paperEntities = paperService.getPageEntitiesByIdOfTeacher(id, pageable);
        paperStatus = PaperStatus.SUCCESS;
        return new ResponseMessage(paperStatus, paperEntities);
    }

//    @RequestMapping(value = "/teacher/paper/mypapers", method = RequestMethod.POST)
//    public ResponseMessage getPapersByTeacher(@RequestBody Map map) {
//        Page<PaperEntity> paperEntities = null;
//        int idTeacher = (int) map.get("idTeacher");
//        int page = (int) map.get("page");
//        int size = (int) map.get("size");
//        PaperStatus paperStatus;
//        Pageable pageable = new PageRequest(page, size, Sort.Direction.DESC, "idPaper");
//        paperEntities = paperService.getPageEntitiesByIdOfTeacher(idTeacher, pageable);
//        paperStatus = PaperStatus.SUCCESS;
//        return new ResponseMessage(paperStatus, paperEntities);
//    }


    /**
     * @api {post} /teacher/paper/uploadfile 上传论文
     * @apiName uploadFile
     * @apiGroup Paper
     * @apiPermission teacher
     * @apiVersion 0.1.0
     * @apiParam {File} paper 论文pdf
     * @apiUse NormalSuccessResponse
     * @apiUse NormalErrorResponse
     * @apiUse ArgumentsErrorResponse
     * @apiUse NotFoundErrorResponse
     * @apiUse UnLoginErrorResponse
     * @apiUse UploadFileErrorResponse
     */
    @RequestMapping(value = "/teacher/paper/uploadfile", method = RequestMethod.POST)
    public ResponseMessage uploadFile(HttpServletRequest request, @RequestParam("paper") MultipartFile paper) {
        String url = paperService.uploadFile(request, paper);
        return new ResponseMessage(PaperStatus.SUCCESS, url);
    }


    /**
     * @api {get} /project/teacher/:keyword/:page/:size 查询论文信息（匹配标题或摘要或关键字，用空格分隔）
     * @apiName searchPaper
     * @apiGroup Paper
     * @apiVersion 0.1.0
     * @apiParam {String} keyword 搜素关键字
     * @apiParam {Number} page 页码（从0开始）
     * @apiParam {Number} size 每页数据数量
     * @apiSuccessExample {json} Success-Response:
     * HTTP/1.1 200 OK
     * {
     *   "code": 0,
     *   "msg": "SUCCESS",
     *   "body": {
     *     "content": [
     *       {
     *         "idPaper": 15,
     *         "idTeacher": 1,
     *         "title": "aaaaaaaaaaaaaaa",
     *         "releaseDate": "2016-10-30",
     *         "writer": "xbw",
     *         "publishDate": "2016-10-30",
     *         "publishPlace": "aaa",
     *         "keyWord": "123",
     *         "abstractContent": "123",
     *         "content": "123",
     *         "param1": null,
     *         "param2": null
     *       },
     *       {
     *         "idPaper": 14,
     *         "idTeacher": 1,
     *         "title": "aaaaaaaaaaaaaaa",
     *         "releaseDate": "2016-10-30",
     *         "writer": "xbw",
     *         "publishDate": "2016-10-30",
     *         "publishPlace": "aaa",
     *         "keyWord": "123",
     *         "abstractContent": "123",
     *         "content": "123",
     *         "param1": null,
     *         "param2": null
     *       },
     *       {
     *         "idPaper": 13,
     *         "idTeacher": 1,
     *         "title": "aaaaaaaaaaaaaaa",
     *         "releaseDate": "2016-10-30",
     *         "writer": "xbw",
     *         "publishDate": "2016-10-30",
     *         "publishPlace": "aaa",
     *         "keyWord": "123",
     *         "abstractContent": "123",
     *         "content": "123",
     *         "param1": null,
     *         "param2": null
     *       },
     *       {
     *         "idPaper": 12,
     *         "idTeacher": 1,
     *         "title": "aaaaaaaaaaaaaaa",
     *         "releaseDate": "2016-10-30",
     *         "writer": "xbw",
     *         "publishDate": "2016-10-30",
     *         "publishPlace": "aaa",
     *         "keyWord": "123",
     *         "abstractContent": "123",
     *         "content": "123",
     *         "param1": null,
     *         "param2": null
     *       },
     *       {
     *         "idPaper": 11,
     *         "idTeacher": 1,
     *         "title": "aaaaaaaaaaaaaaa",
     *         "releaseDate": "2016-10-30",
     *         "writer": "xbw",
     *         "publishDate": "2016-10-30",
     *         "publishPlace": "aaa",
     *         "keyWord": "123",
     *         "abstractContent": "123",
     *         "content": "123",
     *         "param1": null,
     *         "param2": null
     *       }
     *     ],
     *     "last": false,
     *     "totalPages": 3,
     *     "totalElements": 12,
     *     "size": 5,
     *     "number": 0,
     *     "sort": [
     *       {
     *         "direction": "DESC",
     *         "property": "idPaper",
     *         "ignoreCase": false,
     *         "nullHandling": "NATIVE",
     *         "ascending": false
     *       }
     *     ],
     *     "first": true,
     *     "numberOfElements": 5
     *   }
     * }
     *
     *
     * @apiUse NormalErrorResponse
     * @apiUse DataBaseErrorResponse
     */
    @RequestMapping(value = "/paper/search/{keyWord}/{page}/{size}", method = RequestMethod.GET)
    public ResponseMessage search(@PathVariable("keyWord") String keyWord, @PathVariable("page") int page, @PathVariable("size") int size) {
        Pageable pageable = new PageRequest(page, size, Sort.Direction.DESC, "idPaper");
        Page<PaperEntity> paperEntities = paperService.searchPaper(keyWord, pageable);
        return new ResponseMessage(PaperStatus.SUCCESS, paperEntities);
    }


//    @RequestMapping(value = "/paper/search", method = RequestMethod.POST)
//    public ResponseMessage search(@RequestBody Map map) {
//        String keyWord = (String)map.get("keyWord");
//        int page = (int) map.get("page");
//        int size = (int) map.get("size");
//        Pageable pageable = new PageRequest(page, size, Sort.Direction.DESC, "idPaper");
//        Page<PaperEntity> paperEntities = paperService.searchPaper(keyWord, pageable);
//        return new ResponseMessage(PaperStatus.SUCCESS, paperEntities);
//    }



    @RequestMapping(value = "/paper/new/{page}/{size}", method = RequestMethod.GET)
    public ResponseMessage search(@PathVariable("page") int page, @PathVariable("size") int size) {
        Pageable pageable = new PageRequest(page, size, Sort.Direction.DESC, "publishDate");
        Page<PaperEntity> paperEntities = paperService.getNewPapers(pageable);
        return new ResponseMessage(PaperStatus.SUCCESS, paperEntities);
    }

//    @RequestMapping("/paper/search/{q}")
//    public ResponseMessage search(@PathVariable("q") String q) {
//        List searchResults = null;
//        try {
//            searchResults = paperSearch.search(q);
//        }
//        catch (Exception ex) {
//            // here you should handle unexpected errors
//            // ...
//            // throw ex;
//        }
//        return new ResponseMessage(PaperStatus.SUCCESS, searchResults);
//        int pageNum = 1;
//        int pageSize = 2;
//        FullTextSession fts = Search.getFullTextSession(sessionFactory.getCurrentSession());
//        QueryBuilder qb = fts.getSearchFactory().buildQueryBuilder().forEntity(PaperEntity.class).get();
//        Query luceneQuery = qb.keyword().onFields("title", "releaseDate", "writer","publishDate", "publishPlace", "keyWord", "abstractContent", "content").matching(q).createQuery();
//        FullTextQuery query = fts.createFullTextQuery(luceneQuery, PaperEntity.class);
//        query.setFirstResult((pageNum - 1) * pageSize);
//        query.setMaxResults(pageSize);
//        List<PaperEntity> data = query.list();
//        //将数据高亮
////        model.setData(SearchUtils.hightLight(luceneQuery, data, "title", "content", "description"));
//        return new ResponseMessage(PaperStatus.SUCCESS, data);
//    }

}
