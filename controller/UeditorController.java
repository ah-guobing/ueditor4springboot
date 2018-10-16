package com.webapp123.cms.controller.admin;

import com.webapp123.cms.util.CommUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;


/**
 * @author GuoBingbing（QQ：46926125  505050820）
 * @version 1.0
 * @license http://blog.webapp123.com
 * @datetime 2018/10/15 15:32
 * @Description UE编辑器业务
 * 具体请求/响应规范请参见：http://fex.baidu.com/ueditor/#dev-request_specification
 */
@Controller
@RequestMapping("/admin/ueditor")
public class UeditorController {

    @Value("${web.upload.dir}")
    private String webUploadDir;
    @Value("${web.upload.domain}")
    private String webUploadDomain;


    /**
     * 此处UE配置是默认的，实际上的上传配置项不在此处配置，此处只是给UE编辑器前台调用避免报初始化错误
     *
     * @return
     */
    @RequestMapping(value = "/controller", params = "action=config", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String config() {
        return "{\n" +
                "    \"imageActionName\": \"uploadimage\",\n" +
                "    \"imageFieldName\": \"upfile\",\n" +
                "    \"imageMaxSize\": 2048000,\n" +
                "    \"imageAllowFiles\": [\".png\", \".jpg\", \".jpeg\", \".gif\", \".bmp\"],\n" +
                "    \"imageCompressEnable\": true,\n" +
                "    \"imageCompressBorder\": 1600,\n" +
                "    \"imageInsertAlign\": \"none\",\n" +
                "    \"imageUrlPrefix\": \"\",\n" +
                "    \"imagePathFormat\": \"/upload/image/{yyyy}{mm}{dd}/{time}{rand:6}\",\n" +
                "\n" +
                "\n" +
                "    \"scrawlActionName\": \"uploadscrawl\",\n" +
                "    \"scrawlFieldName\": \"upfile\",\n" +
                "    \"scrawlPathFormat\": \"/upload/image/{yyyy}{mm}{dd}/{time}{rand:6}\",\n" +
                "    \"scrawlMaxSize\": 2048000,\n" +
                "    \"scrawlUrlPrefix\": \"\",\n" +
                "    \"scrawlInsertAlign\": \"none\",\n" +
                "\n" +
                "\n" +
                "    \"snapscreenActionName\": \"uploadimage\",\n" +
                "    \"snapscreenPathFormat\": \"/upload/image/{yyyy}{mm}{dd}/{time}{rand:6}\",\n" +
                "    \"snapscreenUrlPrefix\": \"\",\n" +
                "    \"snapscreenInsertAlign\": \"none\",\n" +
                "\n" +
                "\n" +
                "    \"catcherLocalDomain\": [\"127.0.0.1\", \"localhost\", \"img.baidu.com\"],\n" +
                "    \"catcherActionName\": \"catchimage\",\n" +
                "    \"catcherFieldName\": \"source\",\n" +
                "    \"catcherPathFormat\": \"/upload/image/{yyyy}{mm}{dd}/{time}{rand:6}\",\n" +
                "    \"catcherUrlPrefix\": \"\",\n" +
                "    \"catcherMaxSize\": 2048000,\n" +
                "    \"catcherAllowFiles\": [\".png\", \".jpg\", \".jpeg\", \".gif\", \".bmp\"],\n" +
                "\n" +
                "\n" +
                "    \"videoActionName\": \"uploadvideo\",\n" +
                "    \"videoFieldName\": \"upfile\",\n" +
                "    \"videoPathFormat\": \"/upload/video/{yyyy}{mm}{dd}/{time}{rand:6}\",\n" +
                "    \"videoUrlPrefix\": \"\",\n" +
                "    \"videoMaxSize\": 102400000,\n" +
                "    \"videoAllowFiles\": [\n" +
                "        \".flv\", \".swf\", \".mkv\", \".avi\", \".rm\", \".rmvb\", \".mpeg\", \".mpg\",\n" +
                "        \".ogg\", \".ogv\", \".mov\", \".wmv\", \".mp4\", \".webm\", \".mp3\", \".wav\", \".mid\"],\n" +
                "\n" +
                "\n" +
                "    \"fileActionName\": \"uploadfile\",\n" +
                "    \"fileFieldName\": \"upfile\",\n" +
                "    \"filePathFormat\": \"/upload/file/{yyyy}{mm}{dd}/{time}{rand:6}\",\n" +
                "    \"fileUrlPrefix\": \"\",\n" +
                "    \"fileMaxSize\": 51200000,\n" +
                "    \"fileAllowFiles\": [\n" +
                "        \".png\", \".jpg\", \".jpeg\", \".gif\", \".bmp\",\n" +
                "        \".flv\", \".swf\", \".mkv\", \".avi\", \".rm\", \".rmvb\", \".mpeg\", \".mpg\",\n" +
                "        \".ogg\", \".ogv\", \".mov\", \".wmv\", \".mp4\", \".webm\", \".mp3\", \".wav\", \".mid\",\n" +
                "        \".rar\", \".zip\", \".tar\", \".gz\", \".7z\", \".bz2\", \".cab\", \".iso\",\n" +
                "        \".doc\", \".docx\", \".xls\", \".xlsx\", \".ppt\", \".pptx\", \".pdf\", \".txt\", \".md\", \".xml\"\n" +
                "    ],\n" +
                "\n" +
                "\n" +
                "    \"imageManagerActionName\": \"listimage\",\n" +
                "    \"imageManagerListPath\": \"/upload/\",\n" +
                "    \"imageManagerListSize\": 20,\n" +
                "    \"imageManagerUrlPrefix\": \"\",\n" +
                "    \"imageManagerInsertAlign\": \"none\",\n" +
                "    \"imageManagerAllowFiles\": [\".png\", \".jpg\", \".jpeg\", \".gif\", \".bmp\"],\n" +
                "\n" +
                "\n" +
                "    \"fileManagerActionName\": \"listfile\",\n" +
                "    \"fileManagerListPath\": \"/upload/file/\",\n" +
                "    \"fileManagerUrlPrefix\": \"\",\n" +
                "    \"fileManagerListSize\": 20,\n" +
                "    \"fileManagerAllowFiles\": [\n" +
                "        \".png\", \".jpg\", \".jpeg\", \".gif\", \".bmp\",\n" +
                "        \".flv\", \".swf\", \".mkv\", \".avi\", \".rm\", \".rmvb\", \".mpeg\", \".mpg\",\n" +
                "        \".ogg\", \".ogv\", \".mov\", \".wmv\", \".mp4\", \".webm\", \".mp3\", \".wav\", \".mid\",\n" +
                "        \".rar\", \".zip\", \".tar\", \".gz\", \".7z\", \".bz2\", \".cab\", \".iso\",\n" +
                "        \".doc\", \".docx\", \".xls\", \".xlsx\", \".ppt\", \".pptx\", \".pdf\", \".txt\", \".md\", \".xml\"\n" +
                "    ]\n" +
                "\n" +
                "}";
    }

    /**
     * 上传图片
     *
     * @param file
     * @param callback
     * @return
     */
    @RequestMapping(value = "/controller", params = "action=uploadimage", method = RequestMethod.POST)
    @ResponseBody
    public String upImage(@RequestParam("upfile") MultipartFile file, @RequestParam(value = "callback", required = false) String callback) {
        return CommUtil.uploadFile("image", webUploadDir, webUploadDomain, file, callback);
    }

    /**
     * 上传附件
     *
     * @param file
     * @param callback
     * @return
     */
    @RequestMapping(value = "/controller", params = "action=uploadfile", method = RequestMethod.POST)
    @ResponseBody
    public String upFile(@RequestParam("upfile") MultipartFile file, @RequestParam(value = "callback", required = false) String callback) {
        return CommUtil.uploadFile("file", webUploadDir, webUploadDomain, file, callback);
    }


    /**
     * 图片管理
     *
     * @param callback
     * @return
     */
//    @RequestMapping(value = "/controller", params = "action=listimage")
//    @ResponseBody
//    public String listImage(@RequestParam(value = "callback", required = false) String callback) {
//        return "{\n" +
//                "    \"state\": \"SUCCESS\",\n" +
//                "    \"list\": [{\n" +
//                "        \"url\": \"upload/1.jpg\"\n" +
//                "    }, {\n" +
//                "        \"url\": \"upload/2.jpg\"\n" +
//                "    }, ],\n" +
//                "    \"start\": 20,\n" +
//                "    \"total\": 100\n" +
//                "}";
//    }

}
