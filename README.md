# ueditor4springboot
SpringBoot整合ueditor


### 项目简介
UEditor是由百度推出的一款开源富文本编辑器，但是也有2年多没有更新了，目前Java最新版为2016-02-25发布的 v1.4.3.3，由于年代较久远，在SpringBoot项目中整合也遇到一些坑，特建此项目记录SpringBoot整合UEditor步聚。<br />
UEditor [1.4.3.3 Jsp 版本] 下载地址：https://ueditor.baidu.com/website/download.html

### 准备工作
1、下载UEditor JSP版本后解压，将所有文件放入项目资源目录中，如我的项目结构为：<br />
resources/<br />
-&nbsp;&nbsp;mapper/<br />
-&nbsp;&nbsp;static/<br />
&nbsp;&nbsp;&nbsp;&nbsp;-&nbsp;&nbsp;js/<br />
&nbsp;&nbsp;&nbsp;&nbsp;-&nbsp;&nbsp;...<br />
&nbsp;&nbsp;&nbsp;&nbsp;-&nbsp;&nbsp;ueditor/<br />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-&nbsp;&nbsp;dialogs/<br />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-&nbsp;&nbsp;lang/<br />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-&nbsp;&nbsp;themes/<br />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-&nbsp;&nbsp;...<br />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-&nbsp;&nbsp;ueditor.config.js<br />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-&nbsp;&nbsp;...<br />

<br />

2、当前GitHub项目中的 controller/ 为控制器、util/ 为工具类、resources/application.properties 为项目配置参数，请将这3个文件合理放入你的项目中，确保正常。

<br />

3、在第1步基础上，修改你的ueditor配置文件（ueditor.config.js），主要修改：serverUrl 配置项即可，此配置项的值请填入第2步中控制器的访问路径，如我的配置：<br />
, serverUrl:  "/admin/ueditor/controller"

<br />
