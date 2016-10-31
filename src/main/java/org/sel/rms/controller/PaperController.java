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
import org.springframework.web.bind.annotation.*;

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
     *   @api {post} /paper/publish 发表论文
     *   @apiName publishPaper
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
     *   @apiUse  NomalSuccessResponse
     *   @apiUse  NomalErrorResponse
     *   @apiUse  ArgumentsErrorResponse
     *   @apiUse  DataBaseErrorResponse
     */
    @RequestMapping(value = "/publish",method = RequestMethod.POST)
    public ResponseMessage publish(@Validated(PaperGroup.publish.class) @RequestBody PaperEntity paperEntity, BindingResult bindingResult) {
        PaperStatus paperStatus;
        if (bindingResult.hasErrors()) {
            logger.error("paperPublish argument error: " + paperEntity);
            paperStatus = PaperStatus.ARGUMENTS_ERROR;
        } else {
            paperService.publishPaper(paperEntity);
            paperStatus = PaperStatus.SUCCESS;
        }
        return new ResponseMessage(paperStatus);
    }



    /**
     *
     *   @api {post} /paper/modify 修改论文
     *   @apiName modifyPaper
     *   @apiGroup Paper
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
     */
    @RequestMapping(value = "/modify",method = RequestMethod.POST)
    public ResponseMessage modify(@Validated(PaperGroup.modify.class) @RequestBody PaperEntity paperEntity, BindingResult bindingResult) {
        PaperStatus paperStatus;
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
        return new ResponseMessage(paperStatus);
    }

    /**
     *
     *   @api {get} /paper/delete/:id 删除论文
     *   @apiName deletePaper
     *   @apiGroup Paper
     *   @apiVersion 0.1.0
     *   @apiParam {Number} id 论文id
     *
     *   @apiUse  NomalSuccessResponse
     *   @apiUse  NomalErrorResponse
     *   @apiUse  NotFoundErrorResponse
     */
    @RequestMapping(value = "/delete/{id}",method = RequestMethod.GET)
    public ResponseMessage delete(@PathVariable("id") int id) {
        paperService.deletePaper(id);
        return new ResponseMessage(PaperStatus.SUCCESS);
    }
}
