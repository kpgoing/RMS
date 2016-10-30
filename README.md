# RMS
## Research Management System
###目录结构
1. OtherDocs : 数据库信息及设计信息。
2. doc : 后台API文档。
3. src : 后台源码。
4. frontend ：前台源码。

### backend tips

1. 自定义异常实现RuntimeException。
2. 自定义状态枚举实现Statusable接口。
3. 使用apidoc生成文档。
4. 数据交互使用json, 参数装箱需要使用@RequestBody注解。
5. 返回体使用ResponseMessage。
6. 使用log4j日志。