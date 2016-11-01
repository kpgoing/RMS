/**
 * @apiDefine  NormalSuccessResponse
 *
 *
 *   @apiSuccessExample {json} Success-Response:
 *     HTTP/1.1 200 OK
 *     {
 *       "code": 0,
 *       "msg": "SUCCESS",
 *       "body": null
 *     }

 */


/**
 * @apiDefine NormalErrorResponse
 *
 *
 * @apiErrorExample NormalErrorResponse:
 *
 *     {
 *       "code": 1,
 *       "msg": "ERROR(:...some err info)",
 *       "body": null
 *     }
 */

/**
 * @apiDefine ArgumentsErrorResponse
 *
 *
 * @apiErrorExample ArgumentsErrorResponse
 *
 *     {
 *       "code": 2,
 *       "msg": "ARGUMENTS_ERROR",
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
 *       "code": 3,
 *       "msg": "DATABASE_ERROR",
 *       "body": null
 *     }
 */

/**
 * @apiDefine NotFoundErrorResponse
 *
 *
 * @apiErrorExample NotFoundErrorResponse:
 *
 *     {
 *       "code": 4,
 *       "msg": "NOT_FOUND",
 *       "body": null
 *     }
 */

/**
 * @apiDefine UnLoginErrorResponse
 *
 *
 * @apiErrorExample UnLoginErrorResponse:
 *
 *     {
 *       "code": 5,
 *       "msg": "UN_LOGIN",
 *       "body": null
 *     }
 */

/**
 * @apiDefine PermissionDenyErrorResponse
 *
 *
 * @apiErrorExample PermissionDenyErrorResponse:
 *
 *     {
 *       "code": 6,
 *       "msg": "PERMISSIOM_DENY",
 *       "body": null
 *     }
 */

/**
 * @apiDefine UploadFileErrorResponse
 *
 *
 * @apiErrorExample UploadFileErrorResponse:
 *
 *     {
 *       "code": 7,
 *       "msg": "UPLOAD_FILE_ERROR",
 *       "body": null
 *     }
 */

/**
 * @apiDefine DeleteFileErrorResponse
 *
 *
 * @apiErrorExample DeleteFileErrorResponse:
 *
 *     {
 *       "code": 7,
 *       "msg": "DELETE_OLD_FILE_ERROR",
 *       "body": null
 *     }
 */
