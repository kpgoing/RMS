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
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by xubowei on 30/10/2016.
 */
@RestController
@RequestMapping(value = "/paper")
public class PaperController {
    private final static Logger logger = Logger.getLogger(PaperController.class);

    @Autowired
    PaperService paperService;

    /**
     * @apiDefine NomalErrorResponse
     *
     *
     * @apiErrorExample NomalErrorResponse:
     *
     *     {
     *       "code": 1,
     *       "msg": "ERROR",
     *       "body": null
     *     }
     */

    /**
     * @apiDefine DataBaseErrorResponse
     *
     *
     * @apiErrorExample DataBaseErrorResponse:
     *
     *     {
     *       "code": 2,
     *       "msg": "DATABASE_ERROR",
     *       "body": null
     *     }
     */


    /**
     *   @api {post} /paper/publish 发表论文
     *   @apiName 发表论文
     *   @apiGroup Paper
     *   @apiVersion 0.1.0
     *   @apiParam {json} paperEntity 论文信息
     *   @apiParamExample {json} Request-Example:
     *     {
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
     *   @apiSuccessExample {json} Success-Response:
     *     HTTP/1.1 200 OK
     *     {
     *       "code": 0,
     *       "msg": "SUCCESS",
     *       "body": null
     *     }
     *
     *   @apiUse  NomalErrorResponse
     *   @apiUse  DataBaseErrorResponse
     */
    @RequestMapping(value = "/publish",method = RequestMethod.POST)
    public ResponseMessage publish(@Validated(PaperGroup.publish.class) @RequestBody PaperEntity paperEntity, BindingResult bindingResult) {
        PaperStatus paperStatus;
        System.out.println(paperEntity);
        if (bindingResult.hasErrors()) {
            StringBuilder errors = new StringBuilder("error:");
            bindingResult.getAllErrors().forEach(n -> errors.append(n.getCode() + "  "  + n.getDefaultMessage() + "     "));
            logger.error(errors.toString());
            paperStatus = PaperStatus.ERROR;
        } else {
            paperService.publishPaper(paperEntity);
            paperStatus = PaperStatus.SUCCESS;
        }
        return new ResponseMessage(paperStatus);
    }
}
