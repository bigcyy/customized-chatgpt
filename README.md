## customized chat

### 简介

基于ChatGpt，Java，SpringBoot，Vue，Milvus向量数据库的定制化聊天Web demo

- 可开发成个人知识库

- 针对某品牌的智能客服
- 私人助理
- more

### 原理

上传pdf，让ChtGpt基于pdf的内容回答问题，原理很简单：将内容分割然后embedding存入向量数据库，当用户询问时将问题embedding，拿embedding结果去向量数据库查询相似度最高的几段话丢给ChatGpt让他组织语言并结合自己丰富的知识进行润色。
### todo

- 支持更多的文件格式
- token计数
- 优化文本的分割
- 抽离embedding模型，使其能更优雅的切换为自己训练的模型或者其他公开的模型

### 参考资料

https://twitter.com/chuangbo/status/1631461656151887873 作者详细的描述了该方案的运行流程，本项目也是参考自该文章

### 相关项目

- https://github.com/GanymedeNil/document.ai
- https://github.com/mckaywrigley/paul-graham-gpt