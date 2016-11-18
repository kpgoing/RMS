define({ "api": [
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
            "type": "json",
            "optional": false,
            "field": "map",
            "description": "<p>教师id与邮箱</p>"
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
            "type": "json",
            "optional": false,
            "field": "map",
            "description": "<p>教师id与邮箱</p>"
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
  }
] });
