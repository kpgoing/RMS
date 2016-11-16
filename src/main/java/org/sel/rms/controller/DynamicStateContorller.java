package org.sel.rms.controller;

import org.sel.rms.common.ResponseMessage;
import org.sel.rms.entity.DynamicStateEntity;
import org.sel.rms.service.DynamicStateService;
import org.sel.rms.status.PaperStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by xubowei on 15/11/2016.
 */
@RestController
public class DynamicStateContorller {

    @Autowired
    DynamicStateService dynamicStateService;

    /**
     * @api {get} /admin/new/:page/:size 拉取动态信息
     * @apiName getDynamicState
     * @apiGroup DynamicState
     * @apiPermission admin
     * @apiVersion 0.1.0
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
     *         "idDynamicState": 24,
     *         "idTeacher": 1,
     *         "idContent": 46,
     *         "kind": "paper",
     *         "name": "1111111111111111111111",
     *         "teacherName": "xbw",
     *         "publishDate": 1479194911000
     *       },
     *       {
     *         "idDynamicState": 22,
     *         "idTeacher": 1,
     *         "idContent": 44,
     *         "kind": "paper",
     *         "name": "1111111111111111111111",
     *         "teacherName": "xbw",
     *         "publishDate": 1479194910000
     *       },
     *       {
     *         "idDynamicState": 23,
     *         "idTeacher": 1,
     *         "idContent": 45,
     *         "kind": "paper",
     *         "name": "1111111111111111111111",
     *         "teacherName": "xbw",
     *         "publishDate": 1479194910000
     *       },
     *       {
     *         "idDynamicState": 20,
     *         "idTeacher": 1,
     *         "idContent": 42,
     *         "kind": "paper",
     *         "name": "1111111111111111111111",
     *         "teacherName": "xbw",
     *         "publishDate": 1479194909000
     *       }
     *     ],
     *     "totalPages": 4,
     *     "last": false,
     *     "totalElements": 13,
     *     "size": 4,
     *     "number": 0,
     *     "sort": [
     *       {
     *         "direction": "DESC",
     *         "property": "publishDate",
     *         "ignoreCase": false,
     *         "nullHandling": "NATIVE",
     *         "ascending": false
     *       }
     *     ],
     *     "first": true,
     *     "numberOfElements": 4
     *   }
     * }
     *
     * @apiUse NormalErrorResponse
     * @apiUse DataBaseErrorResponse
     * @apiUse UnLoginErrorResponse
     */
    @RequestMapping(value = "/admin/new/{page}/{size}", method = RequestMethod.GET)
    public ResponseMessage search(@PathVariable("page") int page, @PathVariable("size") int size) {
        Pageable pageable = new PageRequest(page, size, Sort.Direction.DESC, "publishDate");
        Page<DynamicStateEntity> dynamicStateEntities = dynamicStateService.getByPage(pageable);
        return new ResponseMessage(PaperStatus.SUCCESS, dynamicStateEntities);
    }
}
