## Y4 Tomcat

`Y4 Tomcat` 项目是基于 `Apache Tomcat 8.5.100` 的分支

源码链接：https://github.com/apache/tomcat/releases/tag/8.5.100

开发目的：改写一个自用的、安全的、难以被漏洞利用的 `Apache Tomcat` 分支

注意：没有进行完善的测试，不保证生产可用，仅供安全研究和学习

混淆:

![](img/001.png)

404页面特殊处理:

![](img/002.png)

不允许内存马:

![](img/003.png)

阻止反序列化打入内存马:

![](img/004.png)

### 主要功能

主要新增功能如下，具体的功能以及实现请参考 `CHANGELOG.MD`

- 不允许通过 `JSP` 动态添加 `Filter/Servlet/Listener` 等内存马
- 不允许通过 `JSP` 执行命令（针对 `webshell` 脚本）
- 不允许通过反序列化漏洞添加 `Filter/Servlet/Listener` 等内存马
- 定制 `SecurityManager` 禁止 `TemplatesImpl` 反序列化
- 不允许修改 `AccessLog` 的 `suffix` 为 `JSP/JSPX` 等恶意后缀
- 删除 `WEBAPPS` 中不必要的内容（仅保留一个首页）
- 禁止动态 `attach` 类型的 `Java Agent` 以阻止某些内存马
- 修改响应头和错误页面等配置内容将 `Tomcat` 模拟为一个 `PHP` 服务

### 更新日志

[CHANGELOG.MD](CHANGELOG.MD)