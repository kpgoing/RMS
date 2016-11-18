define({ "api": [
  {
    "type": "get",
    "url": "/admin/new/:page/:size",
    "title": "拉取动态信息",
    "name": "getDynamicState",
    "group": "DynamicState",
    "permission": [
      {
        "name": "admin"
      }
    ],
    "version": "0.1.0",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Number",
            "optional": false,
            "field": "page",
            "description": "<p>页码（从0开始）</p>"
          },
          {
            "group": "Parameter",
            "type": "Number",
            "optional": false,
            "field": "size",
            "description": "<p>每页数据数量</p>"
          }
        ]
      }
    },
    "success": {
      "examples": [
        {
          "title": "Success-Response:",
          "content": "HTTP/1.1 200 OK\n{\n  \"code\": 0,\n  \"msg\": \"SUCCESS\",\n  \"body\": {\n    \"content\": [\n      {\n        \"idDynamicState\": 24,\n        \"idTeacher\": 1,\n        \"idContent\": 46,\n        \"kind\": \"paper\",\n        \"name\": \"1111111111111111111111\",\n        \"teacherName\": \"xbw\",\n        \"publishDate\": 1479194911000\n      },\n      {\n        \"idDynamicState\": 22,\n        \"idTeacher\": 1,\n        \"idContent\": 44,\n        \"kind\": \"paper\",\n        \"name\": \"1111111111111111111111\",\n        \"teacherName\": \"xbw\",\n        \"publishDate\": 1479194910000\n      },\n      {\n        \"idDynamicState\": 23,\n        \"idTeacher\": 1,\n        \"idContent\": 45,\n        \"kind\": \"paper\",\n        \"name\": \"1111111111111111111111\",\n        \"teacherName\": \"xbw\",\n        \"publishDate\": 1479194910000\n      },\n      {\n        \"idDynamicState\": 20,\n        \"idTeacher\": 1,\n        \"idContent\": 42,\n        \"kind\": \"paper\",\n        \"name\": \"1111111111111111111111\",\n        \"teacherName\": \"xbw\",\n        \"publishDate\": 1479194909000\n      }\n    ],\n    \"totalPages\": 4,\n    \"last\": false,\n    \"totalElements\": 13,\n    \"size\": 4,\n    \"number\": 0,\n    \"sort\": [\n      {\n        \"direction\": \"DESC\",\n        \"property\": \"publishDate\",\n        \"ignoreCase\": false,\n        \"nullHandling\": \"NATIVE\",\n        \"ascending\": false\n      }\n    ],\n    \"first\": true,\n    \"numberOfElements\": 4\n  }\n}",
          "type": "json"
        }
      ]
    },
    "filename": "./src/main/java/org/sel/rms/controller/DynamicStateContorller.java",
    "groupTitle": "DynamicState",
    "error": {
      "examples": [
        {
          "title": "NormalErrorResponse:",
          "content": "\n{\n  \"code\": 1,\n  \"msg\": \"ERROR(:...some err info)\",\n  \"body\": null\n}",
          "type": "json"
        },
        {
          "title": "DataBaseErrorResponse:",
          "content": "\n{\n  \"code\": 3,\n  \"msg\": \"DATABASE_ERROR\",\n  \"body\": null\n}",
          "type": "json"
        },
        {
          "title": "UnLoginErrorResponse:",
          "content": "\n{\n  \"code\": 5,\n  \"msg\": \"UN_LOGIN\",\n  \"body\": null\n}",
          "type": "json"
        }
      ]
    }
  },
  {
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "optional": false,
            "field": "varname1",
            "description": "<p>No type.</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "varname2",
            "description": "<p>With type.</p>"
          }
        ]
      }
    },
    "type": "",
    "url": "",
    "version": "0.0.0",
    "filename": "./doc/main.js",
    "group": "E__Workbench_RMS_doc_main_js",
    "groupTitle": "E__Workbench_RMS_doc_main_js",
    "name": ""
  },
  {
    "type": "get",
    "url": "/teacher/paper/delete/:id",
    "title": "删除论文",
    "name": "deletePaper",
    "group": "Paper",
    "permission": [
      {
        "name": "teacher"
      }
    ],
    "version": "0.1.0",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Number",
            "optional": false,
            "field": "id",
            "description": "<p>论文id</p>"
          }
        ]
      }
    },
    "filename": "./src/main/java/org/sel/rms/controller/PaperController.java",
    "groupTitle": "Paper",
    "success": {
      "examples": [
        {
          "title": "Success-Response:",
          "content": "HTTP/1.1 200 OK\n{\n  \"code\": 0,\n  \"msg\": \"SUCCESS\",\n  \"body\": null\n}",
          "type": "json"
        }
      ]
    },
    "error": {
      "examples": [
        {
          "title": "NormalErrorResponse:",
          "content": "\n{\n  \"code\": 1,\n  \"msg\": \"ERROR(:...some err info)\",\n  \"body\": null\n}",
          "type": "json"
        },
        {
          "title": "NotFoundErrorResponse:",
          "content": "\n{\n  \"code\": 4,\n  \"msg\": \"NOT_FOUND\",\n  \"body\": null\n}",
          "type": "json"
        },
        {
          "title": "UnLoginErrorResponse:",
          "content": "\n{\n  \"code\": 5,\n  \"msg\": \"UN_LOGIN\",\n  \"body\": null\n}",
          "type": "json"
        },
        {
          "title": "PermissionDenyErrorResponse:",
          "content": "\n{\n  \"code\": 6,\n  \"msg\": \"PERMISSIOM_DENY\",\n  \"body\": null\n}",
          "type": "json"
        }
      ]
    }
  },
  {
    "type": "get",
    "url": "/project/teacher/:id/:page/:size",
    "title": "查询某个老师的论文",
    "name": "getPapersByTeacher",
    "group": "Paper",
    "version": "0.1.0",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Number",
            "optional": false,
            "field": "id",
            "description": "<p>老师id</p>"
          },
          {
            "group": "Parameter",
            "type": "Number",
            "optional": false,
            "field": "page",
            "description": "<p>页码（从0开始）</p>"
          },
          {
            "group": "Parameter",
            "type": "Number",
            "optional": false,
            "field": "size",
            "description": "<p>每页数据数量</p>"
          }
        ]
      }
    },
    "success": {
      "examples": [
        {
          "title": "Success-Response:",
          "content": "HTTP/1.1 200 OK\n{\n\"code\": 0,\n\"msg\": \"SUCCESS\",\n\"body\": {\n\"content\": [\n{\n\"idPaper\": 15,\n\"idTeacher\": 1,\n\"title\": \"aaaaaaaaaaaaaaa\",\n\"releaseDate\": \"2016-10-30\",\n\"writer\": \"xbw\",\n\"publishDate\": \"2016-10-30\",\n\"publishPlace\": \"aaa\",\n\"keyWord\": \"123\",\n\"abstractContent\": \"123\",\n\"content\": \"123\",\n\"param1\": null,\n\"param2\": null\n},\n{\n\"idPaper\": 14,\n\"idTeacher\": 1,\n\"title\": \"aaaaaaaaaaaaaaa\",\n\"releaseDate\": \"2016-10-30\",\n\"writer\": \"xbw\",\n\"publishDate\": \"2016-10-30\",\n\"publishPlace\": \"aaa\",\n\"keyWord\": \"123\",\n\"abstractContent\": \"123\",\n\"content\": \"123\",\n\"param1\": null,\n\"param2\": null\n},\n{\n\"idPaper\": 13,\n\"idTeacher\": 1,\n\"title\": \"aaaaaaaaaaaaaaa\",\n\"releaseDate\": \"2016-10-30\",\n\"writer\": \"xbw\",\n\"publishDate\": \"2016-10-30\",\n\"publishPlace\": \"aaa\",\n\"keyWord\": \"123\",\n\"abstractContent\": \"123\",\n\"content\": \"123\",\n\"param1\": null,\n\"param2\": null\n}\n],\n\"last\": false,\n\"totalElements\": 14,\n\"totalPages\": 5,\n\"size\": 3,\n\"number\": 0,\n\"sort\": [\n{\n\"direction\": \"DESC\",\n\"property\": \"idPaper\",\n\"ignoreCase\": false,\n\"nullHandling\": \"NATIVE\",\n\"ascending\": false\n}\n],\n\"first\": true,\n\"numberOfElements\": 3\n}\n}",
          "type": "json"
        }
      ]
    },
    "filename": "./src/main/java/org/sel/rms/controller/PaperController.java",
    "groupTitle": "Paper",
    "error": {
      "examples": [
        {
          "title": "NormalErrorResponse:",
          "content": "\n{\n  \"code\": 1,\n  \"msg\": \"ERROR(:...some err info)\",\n  \"body\": null\n}",
          "type": "json"
        },
        {
          "title": "ArgumentsErrorResponse",
          "content": "\n{\n  \"code\": 2,\n  \"msg\": \"ARGUMENTS_ERROR\",\n  \"body\": null\n}",
          "type": "json"
        },
        {
          "title": "DataBaseErrorResponse:",
          "content": "\n{\n  \"code\": 3,\n  \"msg\": \"DATABASE_ERROR\",\n  \"body\": null\n}",
          "type": "json"
        },
        {
          "title": "UnLoginErrorResponse:",
          "content": "\n{\n  \"code\": 5,\n  \"msg\": \"UN_LOGIN\",\n  \"body\": null\n}",
          "type": "json"
        }
      ]
    }
  },
  {
    "type": "post",
    "url": "/teacher/paper/modify",
    "title": "修改论文",
    "name": "modifyPaper",
    "group": "Paper",
    "permission": [
      {
        "name": "teacher"
      }
    ],
    "version": "0.1.0",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "json",
            "optional": false,
            "field": "paperEntity",
            "description": "<p>论文信息</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Request-Example:",
          "content": "{\n\"idPaper\":1,\n\"idTeacher\":1,\n\"title\":\"ds\",\n\"releaseDate\":\"2016-10-30\",\n\"writer\":\"xbw\",\n\"publishPlace\":\"aaa\",\n\"keyWord\":\"123\",\n\"abstractContent\":\"123\",\n\"content\":\"123\"\n}",
          "type": "json"
        }
      ]
    },
    "filename": "./src/main/java/org/sel/rms/controller/PaperController.java",
    "groupTitle": "Paper",
    "success": {
      "examples": [
        {
          "title": "Success-Response:",
          "content": "HTTP/1.1 200 OK\n{\n  \"code\": 0,\n  \"msg\": \"SUCCESS\",\n  \"body\": null\n}",
          "type": "json"
        }
      ]
    },
    "error": {
      "examples": [
        {
          "title": "NormalErrorResponse:",
          "content": "\n{\n  \"code\": 1,\n  \"msg\": \"ERROR(:...some err info)\",\n  \"body\": null\n}",
          "type": "json"
        },
        {
          "title": "ArgumentsErrorResponse",
          "content": "\n{\n  \"code\": 2,\n  \"msg\": \"ARGUMENTS_ERROR\",\n  \"body\": null\n}",
          "type": "json"
        },
        {
          "title": "DataBaseErrorResponse:",
          "content": "\n{\n  \"code\": 3,\n  \"msg\": \"DATABASE_ERROR\",\n  \"body\": null\n}",
          "type": "json"
        },
        {
          "title": "NotFoundErrorResponse:",
          "content": "\n{\n  \"code\": 4,\n  \"msg\": \"NOT_FOUND\",\n  \"body\": null\n}",
          "type": "json"
        },
        {
          "title": "UnLoginErrorResponse:",
          "content": "\n{\n  \"code\": 5,\n  \"msg\": \"UN_LOGIN\",\n  \"body\": null\n}",
          "type": "json"
        },
        {
          "title": "DeleteFileErrorResponse:",
          "content": "\n{\n  \"code\": 7,\n  \"msg\": \"DELETE_OLD_FILE_ERROR\",\n  \"body\": null\n}",
          "type": "json"
        }
      ]
    }
  },
  {
    "type": "post",
    "url": "/teacher/paper/publish",
    "title": "发表论文",
    "name": "publishPaper",
    "group": "Paper",
    "permission": [
      {
        "name": "teacher"
      }
    ],
    "version": "0.1.0",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "json",
            "optional": false,
            "field": "paperEntity",
            "description": "<p>论文信息</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Request-Example:",
          "content": "{\n\"idTeacher\":1,\n\"title\":\"ds\",\n\"releaseDate\":\"2016-10-30\",\n\"writer\":\"xbw\",\n\"publishPlace\":\"aaa\",\n\"keyWord\":\"123\",\n\"abstractContent\":\"123\",\n\"content\":\"123\"\n}",
          "type": "json"
        }
      ]
    },
    "filename": "./src/main/java/org/sel/rms/controller/PaperController.java",
    "groupTitle": "Paper",
    "success": {
      "examples": [
        {
          "title": "Success-Response:",
          "content": "HTTP/1.1 200 OK\n{\n  \"code\": 0,\n  \"msg\": \"SUCCESS\",\n  \"body\": null\n}",
          "type": "json"
        }
      ]
    },
    "error": {
      "examples": [
        {
          "title": "NormalErrorResponse:",
          "content": "\n{\n  \"code\": 1,\n  \"msg\": \"ERROR(:...some err info)\",\n  \"body\": null\n}",
          "type": "json"
        },
        {
          "title": "ArgumentsErrorResponse",
          "content": "\n{\n  \"code\": 2,\n  \"msg\": \"ARGUMENTS_ERROR\",\n  \"body\": null\n}",
          "type": "json"
        },
        {
          "title": "DataBaseErrorResponse:",
          "content": "\n{\n  \"code\": 3,\n  \"msg\": \"DATABASE_ERROR\",\n  \"body\": null\n}",
          "type": "json"
        },
        {
          "title": "UnLoginErrorResponse:",
          "content": "\n{\n  \"code\": 5,\n  \"msg\": \"UN_LOGIN\",\n  \"body\": null\n}",
          "type": "json"
        }
      ]
    }
  },
  {
    "type": "get",
    "url": "/project/teacher/:keyword/:page/:size",
    "title": "查询论文信息（匹配标题或摘要或关键字，用空格分隔）",
    "name": "searchPaper",
    "group": "Paper",
    "version": "0.1.0",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "keyword",
            "description": "<p>搜素关键字</p>"
          },
          {
            "group": "Parameter",
            "type": "Number",
            "optional": false,
            "field": "page",
            "description": "<p>页码（从0开始）</p>"
          },
          {
            "group": "Parameter",
            "type": "Number",
            "optional": false,
            "field": "size",
            "description": "<p>每页数据数量</p>"
          }
        ]
      }
    },
    "success": {
      "examples": [
        {
          "title": "Success-Response:",
          "content": "HTTP/1.1 200 OK\n{\n  \"code\": 0,\n  \"msg\": \"SUCCESS\",\n  \"body\": {\n    \"content\": [\n      {\n        \"idPaper\": 15,\n        \"idTeacher\": 1,\n        \"title\": \"aaaaaaaaaaaaaaa\",\n        \"releaseDate\": \"2016-10-30\",\n        \"writer\": \"xbw\",\n        \"publishDate\": \"2016-10-30\",\n        \"publishPlace\": \"aaa\",\n        \"keyWord\": \"123\",\n        \"abstractContent\": \"123\",\n        \"content\": \"123\",\n        \"param1\": null,\n        \"param2\": null\n      },\n      {\n        \"idPaper\": 14,\n        \"idTeacher\": 1,\n        \"title\": \"aaaaaaaaaaaaaaa\",\n        \"releaseDate\": \"2016-10-30\",\n        \"writer\": \"xbw\",\n        \"publishDate\": \"2016-10-30\",\n        \"publishPlace\": \"aaa\",\n        \"keyWord\": \"123\",\n        \"abstractContent\": \"123\",\n        \"content\": \"123\",\n        \"param1\": null,\n        \"param2\": null\n      },\n      {\n        \"idPaper\": 13,\n        \"idTeacher\": 1,\n        \"title\": \"aaaaaaaaaaaaaaa\",\n        \"releaseDate\": \"2016-10-30\",\n        \"writer\": \"xbw\",\n        \"publishDate\": \"2016-10-30\",\n        \"publishPlace\": \"aaa\",\n        \"keyWord\": \"123\",\n        \"abstractContent\": \"123\",\n        \"content\": \"123\",\n        \"param1\": null,\n        \"param2\": null\n      },\n      {\n        \"idPaper\": 12,\n        \"idTeacher\": 1,\n        \"title\": \"aaaaaaaaaaaaaaa\",\n        \"releaseDate\": \"2016-10-30\",\n        \"writer\": \"xbw\",\n        \"publishDate\": \"2016-10-30\",\n        \"publishPlace\": \"aaa\",\n        \"keyWord\": \"123\",\n        \"abstractContent\": \"123\",\n        \"content\": \"123\",\n        \"param1\": null,\n        \"param2\": null\n      },\n      {\n        \"idPaper\": 11,\n        \"idTeacher\": 1,\n        \"title\": \"aaaaaaaaaaaaaaa\",\n        \"releaseDate\": \"2016-10-30\",\n        \"writer\": \"xbw\",\n        \"publishDate\": \"2016-10-30\",\n        \"publishPlace\": \"aaa\",\n        \"keyWord\": \"123\",\n        \"abstractContent\": \"123\",\n        \"content\": \"123\",\n        \"param1\": null,\n        \"param2\": null\n      }\n    ],\n    \"last\": false,\n    \"totalPages\": 3,\n    \"totalElements\": 12,\n    \"size\": 5,\n    \"number\": 0,\n    \"sort\": [\n      {\n        \"direction\": \"DESC\",\n        \"property\": \"idPaper\",\n        \"ignoreCase\": false,\n        \"nullHandling\": \"NATIVE\",\n        \"ascending\": false\n      }\n    ],\n    \"first\": true,\n    \"numberOfElements\": 5\n  }\n}",
          "type": "json"
        }
      ]
    },
    "filename": "./src/main/java/org/sel/rms/controller/PaperController.java",
    "groupTitle": "Paper",
    "error": {
      "examples": [
        {
          "title": "NormalErrorResponse:",
          "content": "\n{\n  \"code\": 1,\n  \"msg\": \"ERROR(:...some err info)\",\n  \"body\": null\n}",
          "type": "json"
        },
        {
          "title": "DataBaseErrorResponse:",
          "content": "\n{\n  \"code\": 3,\n  \"msg\": \"DATABASE_ERROR\",\n  \"body\": null\n}",
          "type": "json"
        }
      ]
    }
  },
  {
    "type": "post",
    "url": "/teacher/paper/uploadfile",
    "title": "上传论文",
    "name": "uploadFile",
    "group": "Paper",
    "permission": [
      {
        "name": "teacher"
      }
    ],
    "version": "0.1.0",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "File",
            "optional": false,
            "field": "paper",
            "description": "<p>论文pdf</p>"
          }
        ]
      }
    },
    "filename": "./src/main/java/org/sel/rms/controller/PaperController.java",
    "groupTitle": "Paper",
    "success": {
      "examples": [
        {
          "title": "Success-Response:",
          "content": "HTTP/1.1 200 OK\n{\n  \"code\": 0,\n  \"msg\": \"SUCCESS\",\n  \"body\": null\n}",
          "type": "json"
        }
      ]
    },
    "error": {
      "examples": [
        {
          "title": "NormalErrorResponse:",
          "content": "\n{\n  \"code\": 1,\n  \"msg\": \"ERROR(:...some err info)\",\n  \"body\": null\n}",
          "type": "json"
        },
        {
          "title": "ArgumentsErrorResponse",
          "content": "\n{\n  \"code\": 2,\n  \"msg\": \"ARGUMENTS_ERROR\",\n  \"body\": null\n}",
          "type": "json"
        },
        {
          "title": "NotFoundErrorResponse:",
          "content": "\n{\n  \"code\": 4,\n  \"msg\": \"NOT_FOUND\",\n  \"body\": null\n}",
          "type": "json"
        },
        {
          "title": "UnLoginErrorResponse:",
          "content": "\n{\n  \"code\": 5,\n  \"msg\": \"UN_LOGIN\",\n  \"body\": null\n}",
          "type": "json"
        },
        {
          "title": "UploadFileErrorResponse:",
          "content": "\n{\n  \"code\": 7,\n  \"msg\": \"UPLOAD_FILE_ERROR\",\n  \"body\": null\n}",
          "type": "json"
        }
      ]
    }
  },
  {
    "type": "get",
    "url": "/teacher/project/delete/:id",
    "title": "删除项目",
    "name": "deleteProject",
    "group": "Project",
    "permission": [
      {
        "name": "teacher"
      }
    ],
    "version": "0.1.0",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Number",
            "optional": false,
            "field": "id",
            "description": "<p>论文id</p>"
          }
        ]
      }
    },
    "filename": "./src/main/java/org/sel/rms/controller/ProjectController.java",
    "groupTitle": "Project",
    "success": {
      "examples": [
        {
          "title": "Success-Response:",
          "content": "HTTP/1.1 200 OK\n{\n  \"code\": 0,\n  \"msg\": \"SUCCESS\",\n  \"body\": null\n}",
          "type": "json"
        }
      ]
    },
    "error": {
      "examples": [
        {
          "title": "NormalErrorResponse:",
          "content": "\n{\n  \"code\": 1,\n  \"msg\": \"ERROR(:...some err info)\",\n  \"body\": null\n}",
          "type": "json"
        },
        {
          "title": "NotFoundErrorResponse:",
          "content": "\n{\n  \"code\": 4,\n  \"msg\": \"NOT_FOUND\",\n  \"body\": null\n}",
          "type": "json"
        },
        {
          "title": "UnLoginErrorResponse:",
          "content": "\n{\n  \"code\": 5,\n  \"msg\": \"UN_LOGIN\",\n  \"body\": null\n}",
          "type": "json"
        },
        {
          "title": "PermissionDenyErrorResponse:",
          "content": "\n{\n  \"code\": 6,\n  \"msg\": \"PERMISSIOM_DENY\",\n  \"body\": null\n}",
          "type": "json"
        }
      ]
    }
  },
  {
    "type": "get",
    "url": "/project/teacher/:id/:page/:size",
    "title": "查询某个老师发表的项目",
    "name": "getProjectsByTeacher",
    "group": "Project",
    "version": "0.1.0",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Number",
            "optional": false,
            "field": "id",
            "description": "<p>老师id</p>"
          },
          {
            "group": "Parameter",
            "type": "Number",
            "optional": false,
            "field": "page",
            "description": "<p>页码（从0开始）</p>"
          },
          {
            "group": "Parameter",
            "type": "Number",
            "optional": false,
            "field": "size",
            "description": "<p>每页数据数量</p>"
          }
        ]
      }
    },
    "success": {
      "examples": [
        {
          "title": "Success-Response:",
          "content": "HTTP/1.1 200 OK\n  {\n  \"code\": 0,\n  \"msg\": \"SUCCESS\",\n  \"body\": {\n  \"content\": [\n  {\n  \"idProject\": 5,\n  \"idTeacher\": 1,\n  \"name\": \"ds\",\n  \"source\": \"计算机学院\",\n  \"projectTime\": \"2016-10-30\",\n  \"master\": \"哈哈\",\n  \"funds\": 1231412314.11,\n  \"publishTime\": \"2016-10-30\",\n  \"introduction\": \"123\",\n  \"param1\": null,\n  \"param2\": null\n  },\n  {\n  \"idProject\": 4,\n  \"idTeacher\": 1,\n  \"name\": \"ds\",\n  \"source\": \"十大\",\n  \"projectTime\": \"2016-10-30\",\n  \"master\": \"阿萨德撒\",\n  \"funds\": 123123,\n  \"publishTime\": \"2016-10-30\",\n  \"introduction\": \"123\",\n  \"param1\": null,\n  \"param2\": null\n  }\n  ],\n  \"last\": true,\n  \"totalElements\": 2,\n  \"totalPages\": 1,\n  \"size\": 3,\n  \"number\": 0,\n  \"sort\": [\n  {\n  \"direction\": \"DESC\",\n  \"property\": \"idProject\",\n  \"ignoreCase\": false,\n  \"nullHandling\": \"NATIVE\",\n  \"ascending\": false\n  }\n  ],\n  \"first\": true,\n  \"numberOfElements\": 2\n  }\n  }",
          "type": "json"
        }
      ]
    },
    "filename": "./src/main/java/org/sel/rms/controller/ProjectController.java",
    "groupTitle": "Project",
    "error": {
      "examples": [
        {
          "title": "NormalErrorResponse:",
          "content": "\n{\n  \"code\": 1,\n  \"msg\": \"ERROR(:...some err info)\",\n  \"body\": null\n}",
          "type": "json"
        },
        {
          "title": "DataBaseErrorResponse:",
          "content": "\n{\n  \"code\": 3,\n  \"msg\": \"DATABASE_ERROR\",\n  \"body\": null\n}",
          "type": "json"
        }
      ]
    }
  },
  {
    "type": "post",
    "url": "/teacher/project/modify",
    "title": "修改项目",
    "name": "modifyProject",
    "group": "Project",
    "permission": [
      {
        "name": "teacher"
      }
    ],
    "version": "0.1.0",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "json",
            "optional": false,
            "field": "projectEntity",
            "description": "<p>论文信息</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Request-Example:",
          "content": "{\n     \"idProject\":4,\n     \"idTeacher\":1,\n     \"name\":\"ds\",\n     \"source\":\"国外\",\n     \"projectTime\":\"2016-10-30\",\n     \"master\":\"哈哈\",\n     \"funds\":\"1231412314.11\",\n     \"introduction\":\"123\"\n}",
          "type": "json"
        }
      ]
    },
    "filename": "./src/main/java/org/sel/rms/controller/ProjectController.java",
    "groupTitle": "Project",
    "success": {
      "examples": [
        {
          "title": "Success-Response:",
          "content": "HTTP/1.1 200 OK\n{\n  \"code\": 0,\n  \"msg\": \"SUCCESS\",\n  \"body\": null\n}",
          "type": "json"
        }
      ]
    },
    "error": {
      "examples": [
        {
          "title": "NormalErrorResponse:",
          "content": "\n{\n  \"code\": 1,\n  \"msg\": \"ERROR(:...some err info)\",\n  \"body\": null\n}",
          "type": "json"
        },
        {
          "title": "ArgumentsErrorResponse",
          "content": "\n{\n  \"code\": 2,\n  \"msg\": \"ARGUMENTS_ERROR\",\n  \"body\": null\n}",
          "type": "json"
        },
        {
          "title": "DataBaseErrorResponse:",
          "content": "\n{\n  \"code\": 3,\n  \"msg\": \"DATABASE_ERROR\",\n  \"body\": null\n}",
          "type": "json"
        },
        {
          "title": "NotFoundErrorResponse:",
          "content": "\n{\n  \"code\": 4,\n  \"msg\": \"NOT_FOUND\",\n  \"body\": null\n}",
          "type": "json"
        },
        {
          "title": "UnLoginErrorResponse:",
          "content": "\n{\n  \"code\": 5,\n  \"msg\": \"UN_LOGIN\",\n  \"body\": null\n}",
          "type": "json"
        }
      ]
    }
  },
  {
    "type": "post",
    "url": "/teacher/project/publish",
    "title": "发表项目",
    "name": "publishProject",
    "group": "Project",
    "permission": [
      {
        "name": "teacher"
      }
    ],
    "version": "0.1.0",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "json",
            "optional": false,
            "field": "projectEntity",
            "description": "<p>论文信息</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Request-Example:",
          "content": "{\n     \"idTeacher\":1,\n     \"name\":\"ds\",\n     \"source\":\"计算机学院\",\n     \"projectTime\":\"2016-10-30\",\n     \"master\":\"哈哈\",\n     \"funds\":\"1231412314.11\",\n     \"introduction\":\"123\"\n}",
          "type": "json"
        }
      ]
    },
    "filename": "./src/main/java/org/sel/rms/controller/ProjectController.java",
    "groupTitle": "Project",
    "success": {
      "examples": [
        {
          "title": "Success-Response:",
          "content": "HTTP/1.1 200 OK\n{\n  \"code\": 0,\n  \"msg\": \"SUCCESS\",\n  \"body\": null\n}",
          "type": "json"
        }
      ]
    },
    "error": {
      "examples": [
        {
          "title": "NormalErrorResponse:",
          "content": "\n{\n  \"code\": 1,\n  \"msg\": \"ERROR(:...some err info)\",\n  \"body\": null\n}",
          "type": "json"
        },
        {
          "title": "ArgumentsErrorResponse",
          "content": "\n{\n  \"code\": 2,\n  \"msg\": \"ARGUMENTS_ERROR\",\n  \"body\": null\n}",
          "type": "json"
        },
        {
          "title": "DataBaseErrorResponse:",
          "content": "\n{\n  \"code\": 3,\n  \"msg\": \"DATABASE_ERROR\",\n  \"body\": null\n}",
          "type": "json"
        },
        {
          "title": "UnLoginErrorResponse:",
          "content": "\n{\n  \"code\": 5,\n  \"msg\": \"UN_LOGIN\",\n  \"body\": null\n}",
          "type": "json"
        }
      ]
    }
  },
  {
    "type": "get",
    "url": "/project/search/:keyword/:page/:size",
    "title": "查询项目（匹配项目名称或项目介绍或项目责任人或项目来源，用空格分隔）",
    "name": "searchProjects",
    "group": "Project",
    "version": "0.1.0",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "keyword",
            "description": "<p>关键词</p>"
          },
          {
            "group": "Parameter",
            "type": "Number",
            "optional": false,
            "field": "page",
            "description": "<p>页码（从0开始）</p>"
          },
          {
            "group": "Parameter",
            "type": "Number",
            "optional": false,
            "field": "size",
            "description": "<p>每页数据数量</p>"
          }
        ]
      }
    },
    "success": {
      "examples": [
        {
          "title": "Success-Response:",
          "content": "HTTP/1.1 200 OK\n  {\n    \"code\": 0,\n    \"msg\": \"SUCCESS\",\n    \"body\": {\n      \"content\": [\n        {\n          \"idProject\": 5,\n          \"idTeacher\": 1,\n          \"name\": \"ds\",\n          \"source\": \"计算机学院\",\n          \"projectTime\": \"2016-10-30\",\n          \"master\": \"哈哈\",\n          \"funds\": 1231412314.11,\n          \"publishTime\": \"2016-10-30\",\n          \"introduction\": \"123\",\n          \"param1\": null,\n          \"param2\": null\n        }\n      ],\n      \"last\": true,\n      \"totalPages\": 1,\n      \"totalElements\": 1,\n      \"size\": 5,\n      \"number\": 0,\n      \"sort\": [\n        {\n          \"direction\": \"DESC\",\n          \"property\": \"idProject\",\n          \"ignoreCase\": false,\n          \"nullHandling\": \"NATIVE\",\n          \"ascending\": false\n        }\n      ],\n      \"first\": true,\n      \"numberOfElements\": 1\n    }\n  }",
          "type": "json"
        }
      ]
    },
    "filename": "./src/main/java/org/sel/rms/controller/ProjectController.java",
    "groupTitle": "Project",
    "error": {
      "examples": [
        {
          "title": "NormalErrorResponse:",
          "content": "\n{\n  \"code\": 1,\n  \"msg\": \"ERROR(:...some err info)\",\n  \"body\": null\n}",
          "type": "json"
        },
        {
          "title": "DataBaseErrorResponse:",
          "content": "\n{\n  \"code\": 3,\n  \"msg\": \"DATABASE_ERROR\",\n  \"body\": null\n}",
          "type": "json"
        }
      ]
    }
  },
  {
    "type": "post",
    "url": "/admin/login",
    "title": "管理员登录",
    "name": "adminLogin",
    "group": "admin",
    "permission": [
      {
        "name": "admin"
      }
    ],
    "version": "0.1.0",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "json",
            "optional": false,
            "field": "AdminEntity",
            "description": "<p>管理员信息</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Request-Example:",
          "content": "{\n    \"account\":\"abc\",\n    \"password\":\"123\"\n}",
          "type": "json"
        }
      ]
    },
    "filename": "./src/main/java/org/sel/rms/controller/AdminController.java",
    "groupTitle": "admin",
    "success": {
      "examples": [
        {
          "title": "Success-Response:",
          "content": "HTTP/1.1 200 OK\n{\n  \"code\": 0,\n  \"msg\": \"SUCCESS\",\n  \"body\": null\n}",
          "type": "json"
        }
      ]
    },
    "error": {
      "examples": [
        {
          "title": "NormalErrorResponse:",
          "content": "\n{\n  \"code\": 1,\n  \"msg\": \"ERROR(:...some err info)\",\n  \"body\": null\n}",
          "type": "json"
        },
        {
          "title": "ArgumentsErrorResponse",
          "content": "\n{\n  \"code\": 2,\n  \"msg\": \"ARGUMENTS_ERROR\",\n  \"body\": null\n}",
          "type": "json"
        },
        {
          "title": "DataBaseErrorResponse:",
          "content": "\n{\n  \"code\": 3,\n  \"msg\": \"DATABASE_ERROR\",\n  \"body\": null\n}",
          "type": "json"
        },
        {
          "title": "UnLoginErrorResponse:",
          "content": "\n{\n  \"code\": 5,\n  \"msg\": \"UN_LOGIN\",\n  \"body\": null\n}",
          "type": "json"
        }
      ]
    }
  },
  {
    "type": "post",
    "url": "/admin/checkTeacher",
    "title": "审核通过教师",
    "name": "checkTeacher",
    "group": "admin",
    "permission": [
      {
        "name": "admin"
      }
    ],
    "version": "0.1.0",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Number",
            "optional": false,
            "field": "teacherId",
            "description": "<p>教师id</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "teacherMail",
            "description": "<p>教师邮箱</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Request-Example:",
          "content": "{\n    \"teacherId\":1,\n    \"teacherMail\":\"123@123.com\"\n}",
          "type": "json"
        }
      ]
    },
    "filename": "./src/main/java/org/sel/rms/controller/AdminController.java",
    "groupTitle": "admin",
    "success": {
      "examples": [
        {
          "title": "Success-Response:",
          "content": "HTTP/1.1 200 OK\n{\n  \"code\": 0,\n  \"msg\": \"SUCCESS\",\n  \"body\": null\n}",
          "type": "json"
        }
      ]
    },
    "error": {
      "examples": [
        {
          "title": "NormalErrorResponse:",
          "content": "\n{\n  \"code\": 1,\n  \"msg\": \"ERROR(:...some err info)\",\n  \"body\": null\n}",
          "type": "json"
        },
        {
          "title": "ArgumentsErrorResponse",
          "content": "\n{\n  \"code\": 2,\n  \"msg\": \"ARGUMENTS_ERROR\",\n  \"body\": null\n}",
          "type": "json"
        },
        {
          "title": "DataBaseErrorResponse:",
          "content": "\n{\n  \"code\": 3,\n  \"msg\": \"DATABASE_ERROR\",\n  \"body\": null\n}",
          "type": "json"
        },
        {
          "title": "UnLoginErrorResponse:",
          "content": "\n{\n  \"code\": 5,\n  \"msg\": \"UN_LOGIN\",\n  \"body\": null\n}",
          "type": "json"
        }
      ]
    }
  },
  {
    "type": "get",
    "url": "/admin/deleteTeacher/:id",
    "title": "删除教师信息",
    "name": "deleteTeacher",
    "group": "admin",
    "permission": [
      {
        "name": "admin"
      }
    ],
    "version": "0.1.0",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Number",
            "optional": false,
            "field": "id",
            "description": "<p>教师id</p>"
          }
        ]
      }
    },
    "filename": "./src/main/java/org/sel/rms/controller/AdminController.java",
    "groupTitle": "admin",
    "success": {
      "examples": [
        {
          "title": "Success-Response:",
          "content": "HTTP/1.1 200 OK\n{\n  \"code\": 0,\n  \"msg\": \"SUCCESS\",\n  \"body\": null\n}",
          "type": "json"
        }
      ]
    },
    "error": {
      "examples": [
        {
          "title": "NormalErrorResponse:",
          "content": "\n{\n  \"code\": 1,\n  \"msg\": \"ERROR(:...some err info)\",\n  \"body\": null\n}",
          "type": "json"
        },
        {
          "title": "NotFoundErrorResponse:",
          "content": "\n{\n  \"code\": 4,\n  \"msg\": \"NOT_FOUND\",\n  \"body\": null\n}",
          "type": "json"
        },
        {
          "title": "UnLoginErrorResponse:",
          "content": "\n{\n  \"code\": 5,\n  \"msg\": \"UN_LOGIN\",\n  \"body\": null\n}",
          "type": "json"
        },
        {
          "title": "PermissionDenyErrorResponse:",
          "content": "\n{\n  \"code\": 6,\n  \"msg\": \"PERMISSIOM_DENY\",\n  \"body\": null\n}",
          "type": "json"
        }
      ]
    }
  },
  {
    "type": "post",
    "url": "/admin/getAllTeachers",
    "title": "获取所有教师信息",
    "name": "getAllTeachers",
    "group": "admin",
    "permission": [
      {
        "name": "admin"
      }
    ],
    "version": "0.1.0",
    "success": {
      "examples": [
        {
          "title": "Success-Response:",
          "content": "HTTP/1.1 200 OK\n{\n    \"code\":0,\n    \"msg\":\"SUCCESS\",\n    \"body\":[\n    {\n    \"idTeacher\":1,\n    \"account\":\"teaccher\",\n    \"password\":null,\n    \"birthday\":\"1980-10-01\",\n    \"educationBackground\":\"doctor\",\n    \"college\":\"uestc\",\n    \"name\":\"jack\",\n    \"id\":\"2\",\n    \"email\":\"123@123.com\",\n    \"phoneNumber\":\"12345678901\",\n    \"gender\":0,\n    \"workPlace\":\"uestc\",\n    \"title\":\"123\",\n    \"avatarUrl\":null\n    ,\"param1\":null,\n    \"param2\":null},\n    {\n    \"idTeacher\":2,\n    \"account\":\"ttt\",\n    \"password\":\"111111\",\n    \"birthday\":\"1970-07-01\",\n    \"educationBackground\":\"11\",\n    \"college\":\"11\",\n    \"name\":\"a\",\n    \"id\":\"1\",\n    \"email\":\"123@123.com\",\n    \"phoneNumber\":\"12345678901\",\n    \"gender\":1,\n    \"workPlace\":\"uestc\",\n    \"title\":\"1\",\n    \"avatarUrl\":null,\n    \"param1\":null,\n    \"param2\":null\n    }]\n}",
          "type": "json"
        }
      ]
    },
    "filename": "./src/main/java/org/sel/rms/controller/AdminController.java",
    "groupTitle": "admin",
    "error": {
      "examples": [
        {
          "title": "NormalErrorResponse:",
          "content": "\n{\n  \"code\": 1,\n  \"msg\": \"ERROR(:...some err info)\",\n  \"body\": null\n}",
          "type": "json"
        },
        {
          "title": "DataBaseErrorResponse:",
          "content": "\n{\n  \"code\": 3,\n  \"msg\": \"DATABASE_ERROR\",\n  \"body\": null\n}",
          "type": "json"
        },
        {
          "title": "UnLoginErrorResponse:",
          "content": "\n{\n  \"code\": 5,\n  \"msg\": \"UN_LOGIN\",\n  \"body\": null\n}",
          "type": "json"
        }
      ]
    }
  },
  {
    "type": "post",
    "url": "/admin/getUncheck",
    "title": "获取未审核教师信息及数目",
    "name": "getUncheck",
    "group": "admin",
    "permission": [
      {
        "name": "admin"
      }
    ],
    "version": "0.1.0",
    "success": {
      "examples": [
        {
          "title": "Success-Response:",
          "content": "HTTP/1.1 200 OK\n{\n    \"code\":0,\n    \"msg\":\"SUCCESS\",\n    \"body\":\n    {\n    \"teacher\":\n    {\n    \"idTeacher\":1,\n    \"account\":\"teaccher\",\n    \"password\":null,\n    \"birthday\":\"1980-10-01\",\n    \"educationBackground\":\"doctor\",\n    \"college\":\"uestc\",\n    \"name\":\"jack\",\n    \"id\":\"2\",\n    \"email\":\"123@123.com\",\n    \"phoneNumber\":\"12345678901\",\n    \"gender\":0,\n    \"workPlace\":\"uestc\",\n    \"title\":\"123\",\n    \"avatarUrl\":null,\n    \"param1\":null,\n    \"param2\":null\n    },\n    \"uncheckNum\":1\n    }\n}",
          "type": "json"
        }
      ]
    },
    "filename": "./src/main/java/org/sel/rms/controller/AdminController.java",
    "groupTitle": "admin",
    "error": {
      "examples": [
        {
          "title": "NormalErrorResponse:",
          "content": "\n{\n  \"code\": 1,\n  \"msg\": \"ERROR(:...some err info)\",\n  \"body\": null\n}",
          "type": "json"
        },
        {
          "title": "DataBaseErrorResponse:",
          "content": "\n{\n  \"code\": 3,\n  \"msg\": \"DATABASE_ERROR\",\n  \"body\": null\n}",
          "type": "json"
        },
        {
          "title": "UnLoginErrorResponse:",
          "content": "\n{\n  \"code\": 5,\n  \"msg\": \"UN_LOGIN\",\n  \"body\": null\n}",
          "type": "json"
        }
      ]
    }
  },
  {
    "type": "post",
    "url": "/admin/teacherUnpass",
    "title": "审核不通过教师",
    "name": "unpassTeacher",
    "group": "admin",
    "permission": [
      {
        "name": "admin"
      }
    ],
    "version": "0.1.0",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Number",
            "optional": false,
            "field": "teacherId",
            "description": "<p>教师id</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "teacherMail",
            "description": "<p>教师邮箱</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Request-Example:",
          "content": "{\n    \"teacherId\":1,\n    \"teacherMail\":\"123@123.com\"\n}",
          "type": "json"
        }
      ]
    },
    "filename": "./src/main/java/org/sel/rms/controller/AdminController.java",
    "groupTitle": "admin",
    "success": {
      "examples": [
        {
          "title": "Success-Response:",
          "content": "HTTP/1.1 200 OK\n{\n  \"code\": 0,\n  \"msg\": \"SUCCESS\",\n  \"body\": null\n}",
          "type": "json"
        }
      ]
    },
    "error": {
      "examples": [
        {
          "title": "NormalErrorResponse:",
          "content": "\n{\n  \"code\": 1,\n  \"msg\": \"ERROR(:...some err info)\",\n  \"body\": null\n}",
          "type": "json"
        },
        {
          "title": "ArgumentsErrorResponse",
          "content": "\n{\n  \"code\": 2,\n  \"msg\": \"ARGUMENTS_ERROR\",\n  \"body\": null\n}",
          "type": "json"
        },
        {
          "title": "DataBaseErrorResponse:",
          "content": "\n{\n  \"code\": 3,\n  \"msg\": \"DATABASE_ERROR\",\n  \"body\": null\n}",
          "type": "json"
        },
        {
          "title": "UnLoginErrorResponse:",
          "content": "\n{\n  \"code\": 5,\n  \"msg\": \"UN_LOGIN\",\n  \"body\": null\n}",
          "type": "json"
        }
      ]
    }
  },
  {
    "type": "json",
    "url": "/teacher/modifyPassword",
    "title": "教师修改密码",
    "name": "modifyPassword",
    "group": "teacher",
    "permission": [
      {
        "name": "teacher"
      }
    ],
    "version": "0.1.0",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Number",
            "optional": false,
            "field": "teacherId",
            "description": "<p>教师ID</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "newPassword",
            "description": "<p>教师新密码</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Request-Example",
          "content": "{\n    \"teacherId\":1,\n    \"newPassword\":\"123\"\n}",
          "type": "json"
        }
      ]
    },
    "filename": "./src/main/java/org/sel/rms/controller/TeacherController.java",
    "groupTitle": "teacher",
    "success": {
      "examples": [
        {
          "title": "Success-Response:",
          "content": "HTTP/1.1 200 OK\n{\n  \"code\": 0,\n  \"msg\": \"SUCCESS\",\n  \"body\": null\n}",
          "type": "json"
        }
      ]
    },
    "error": {
      "examples": [
        {
          "title": "NormalErrorResponse:",
          "content": "\n{\n  \"code\": 1,\n  \"msg\": \"ERROR(:...some err info)\",\n  \"body\": null\n}",
          "type": "json"
        },
        {
          "title": "ArgumentsErrorResponse",
          "content": "\n{\n  \"code\": 2,\n  \"msg\": \"ARGUMENTS_ERROR\",\n  \"body\": null\n}",
          "type": "json"
        },
        {
          "title": "DataBaseErrorResponse:",
          "content": "\n{\n  \"code\": 3,\n  \"msg\": \"DATABASE_ERROR\",\n  \"body\": null\n}",
          "type": "json"
        },
        {
          "title": "UnLoginErrorResponse:",
          "content": "\n{\n  \"code\": 5,\n  \"msg\": \"UN_LOGIN\",\n  \"body\": null\n}",
          "type": "json"
        }
      ]
    }
  },
  {
    "type": "get",
    "url": "/teacher/search/:keyword/:page/:size",
    "title": "查询某个老师",
    "name": "searchTeacher",
    "group": "teacher",
    "version": "0.1.0",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "keyword",
            "description": "<p>搜素关键字</p>"
          },
          {
            "group": "Parameter",
            "type": "Number",
            "optional": false,
            "field": "page",
            "description": "<p>页码（从0开始）</p>"
          },
          {
            "group": "Parameter",
            "type": "Number",
            "optional": false,
            "field": "size",
            "description": "<p>每页数据数量</p>"
          }
        ]
      }
    },
    "success": {
      "examples": [
        {
          "title": "Success-Response:",
          "content": "{\n    \"code\":0,\n    \"msg\":\"SUCCESS\",\n    \"body\":\n    {\n    \"content\":[\n    {\n    \"idTeacher\":1,\n    \"account\":\"teaccher\",\n    \"password\":null,\n    \"birthday\":\"1980-10-01\",\n    \"educationBackground\":\"doctor\",\n    \"college\":\"uestc\",\n    \"name\":\"jack\",\n    \"id\":\"2\",\n    \"email\":\"123@123.com\",\n    \"phoneNumber\":\"12345678901\",\n    \"gender\":0,\n    \"workPlace\":\"uestc\",\n    \"title\":\"123\",\n    \"avatarUrl\":null,\n    \"param1\":null,\n    \"param2\":null}],\n    \"totalElements\":1,\n    \"totalPages\":1,\n    \"last\":true,\n    \"size\":5,\n    \"number\":0,\n    \"sort\":[\n    {\n    \"direction\":\"DESC\",\n    \"property\":\"idTeacher\",\n    \"ignoreCase\":false,\n    \"nullHandling\":\"NATIVE\",\n    \"ascending\":false}],\n    \"first\":true,\n    \"numberOfElements\":1\n    }\n}",
          "type": "json"
        }
      ]
    },
    "filename": "./src/main/java/org/sel/rms/controller/TeacherController.java",
    "groupTitle": "teacher",
    "error": {
      "examples": [
        {
          "title": "NormalErrorResponse:",
          "content": "\n{\n  \"code\": 1,\n  \"msg\": \"ERROR(:...some err info)\",\n  \"body\": null\n}",
          "type": "json"
        },
        {
          "title": "ArgumentsErrorResponse",
          "content": "\n{\n  \"code\": 2,\n  \"msg\": \"ARGUMENTS_ERROR\",\n  \"body\": null\n}",
          "type": "json"
        },
        {
          "title": "NotFoundErrorResponse:",
          "content": "\n{\n  \"code\": 4,\n  \"msg\": \"NOT_FOUND\",\n  \"body\": null\n}",
          "type": "json"
        },
        {
          "title": "UnLoginErrorResponse:",
          "content": "\n{\n  \"code\": 5,\n  \"msg\": \"UN_LOGIN\",\n  \"body\": null\n}",
          "type": "json"
        },
        {
          "title": "UploadFileErrorResponse:",
          "content": "\n{\n  \"code\": 7,\n  \"msg\": \"UPLOAD_FILE_ERROR\",\n  \"body\": null\n}",
          "type": "json"
        }
      ]
    }
  },
  {
    "type": "post",
    "url": "/teacher/login",
    "title": "教师登录",
    "name": "teacherLogin",
    "group": "teacher",
    "version": "0.1.0",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "json",
            "optional": false,
            "field": "teacherEntity",
            "description": "<p>教师账户信息</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Request-Example:",
          "content": "{\n    \"account\"：\"abc\",\n    \"password\":\"123\"\n}",
          "type": "json"
        }
      ]
    },
    "filename": "./src/main/java/org/sel/rms/controller/TeacherController.java",
    "groupTitle": "teacher",
    "success": {
      "examples": [
        {
          "title": "Success-Response:",
          "content": "HTTP/1.1 200 OK\n{\n  \"code\": 0,\n  \"msg\": \"SUCCESS\",\n  \"body\": null\n}",
          "type": "json"
        }
      ]
    },
    "error": {
      "examples": [
        {
          "title": "NormalErrorResponse:",
          "content": "\n{\n  \"code\": 1,\n  \"msg\": \"ERROR(:...some err info)\",\n  \"body\": null\n}",
          "type": "json"
        },
        {
          "title": "ArgumentsErrorResponse",
          "content": "\n{\n  \"code\": 2,\n  \"msg\": \"ARGUMENTS_ERROR\",\n  \"body\": null\n}",
          "type": "json"
        },
        {
          "title": "DataBaseErrorResponse:",
          "content": "\n{\n  \"code\": 3,\n  \"msg\": \"DATABASE_ERROR\",\n  \"body\": null\n}",
          "type": "json"
        },
        {
          "title": "UnLoginErrorResponse:",
          "content": "\n{\n  \"code\": 5,\n  \"msg\": \"UN_LOGIN\",\n  \"body\": null\n}",
          "type": "json"
        }
      ]
    }
  },
  {
    "type": "post",
    "url": "/teacher/register",
    "title": "教师注册",
    "name": "teacherRegister",
    "group": "teacher",
    "permission": [
      {
        "name": "teacher"
      }
    ],
    "version": "0.1.0",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "json",
            "optional": false,
            "field": "teacherEntity",
            "description": "<p>教师信息</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "birthday",
            "description": "<p>教师生日（选填）</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "educationBackground",
            "description": "<p>教师教育背景（选填）</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "title",
            "description": "<p>教师职称（选填）</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Request-Example:",
          "content": "{\n    \"account\":\"abc\",\n    \"password\":\"123\",\n    \"birthday\":\"yyyy-mm-dd\",\n    \"educationBackground\":\"abc\",\n    \"college\":\"abc\",\n    \"name\":\"abc\",\n    \"id\":\"123\",\n    \"email\":\"123@123.com\"\n    \"phoneNumber\":\"13000000000\",\n    \"byte\":0(male)/1(female),\n    \"workPlace\":\"abc\",\n    \"title\":\"abc\"\n}",
          "type": "json"
        }
      ]
    },
    "filename": "./src/main/java/org/sel/rms/controller/TeacherController.java",
    "groupTitle": "teacher",
    "success": {
      "examples": [
        {
          "title": "Success-Response:",
          "content": "HTTP/1.1 200 OK\n{\n  \"code\": 0,\n  \"msg\": \"SUCCESS\",\n  \"body\": null\n}",
          "type": "json"
        }
      ]
    },
    "error": {
      "examples": [
        {
          "title": "NormalErrorResponse:",
          "content": "\n{\n  \"code\": 1,\n  \"msg\": \"ERROR(:...some err info)\",\n  \"body\": null\n}",
          "type": "json"
        },
        {
          "title": "ArgumentsErrorResponse",
          "content": "\n{\n  \"code\": 2,\n  \"msg\": \"ARGUMENTS_ERROR\",\n  \"body\": null\n}",
          "type": "json"
        },
        {
          "title": "DataBaseErrorResponse:",
          "content": "\n{\n  \"code\": 3,\n  \"msg\": \"DATABASE_ERROR\",\n  \"body\": null\n}",
          "type": "json"
        },
        {
          "title": "UnLoginErrorResponse:",
          "content": "\n{\n  \"code\": 5,\n  \"msg\": \"UN_LOGIN\",\n  \"body\": null\n}",
          "type": "json"
        }
      ]
    }
  },
  {
    "type": "post",
    "url": "/teacher/uploadAvatar/:id",
    "title": "教师上传头像",
    "name": "uploadAvatar",
    "group": "teacher",
    "version": "0.1.0",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "File",
            "optional": false,
            "field": "avatar",
            "description": "<p>教师头像</p>"
          }
        ]
      }
    },
    "filename": "./src/main/java/org/sel/rms/controller/TeacherController.java",
    "groupTitle": "teacher",
    "success": {
      "examples": [
        {
          "title": "Success-Response:",
          "content": "HTTP/1.1 200 OK\n{\n  \"code\": 0,\n  \"msg\": \"SUCCESS\",\n  \"body\": null\n}",
          "type": "json"
        }
      ]
    },
    "error": {
      "examples": [
        {
          "title": "NormalErrorResponse:",
          "content": "\n{\n  \"code\": 1,\n  \"msg\": \"ERROR(:...some err info)\",\n  \"body\": null\n}",
          "type": "json"
        },
        {
          "title": "ArgumentsErrorResponse",
          "content": "\n{\n  \"code\": 2,\n  \"msg\": \"ARGUMENTS_ERROR\",\n  \"body\": null\n}",
          "type": "json"
        },
        {
          "title": "NotFoundErrorResponse:",
          "content": "\n{\n  \"code\": 4,\n  \"msg\": \"NOT_FOUND\",\n  \"body\": null\n}",
          "type": "json"
        },
        {
          "title": "UnLoginErrorResponse:",
          "content": "\n{\n  \"code\": 5,\n  \"msg\": \"UN_LOGIN\",\n  \"body\": null\n}",
          "type": "json"
        },
        {
          "title": "UploadFileErrorResponse:",
          "content": "\n{\n  \"code\": 7,\n  \"msg\": \"UPLOAD_FILE_ERROR\",\n  \"body\": null\n}",
          "type": "json"
        }
      ]
    }
  }
] });
