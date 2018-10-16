package com.webapp123.cms.util;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * @author GuoBingbing（QQ：46926125  505050820）
 * @version 1.0
 * @license http://cms.webapp123.com
 * @datetime 2018/9/18 23:15
 * @Description 通用函数库
 */
public class CommUtil {

    /**
     * 获取当前时间戳
     *
     * @return 10位长度的时间戳
     */
    public static long getTime() {
        //String time = String.valueOf(System.currentTimeMillis());
        //return Integer.parseInt(time.substring(0, 10));
        return System.currentTimeMillis();
    }


    public static String uploadFile(String type, String webUploadDir, String webUploadDomain, MultipartFile file, String callback) {
        if (!webUploadDir.endsWith("/")) {
            webUploadDir += "/";
        }
        if (!webUploadDomain.endsWith("/")) {
            webUploadDomain += "/";
        }

        String[] fileAllowType = {".gif", ".png", ".jpg", ".jpeg", ".bmp"};
        String fileOldName = file.getOriginalFilename();
        String fileExt = fileOldName.substring(fileOldName.lastIndexOf("."));

        if (!Arrays.asList(fileAllowType).contains(fileExt)) {
            return "不允许的文件类型！";
        }

        Long time = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String ymd = sdf.format(time);
        sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String ymdhms = sdf.format(time);
        int rnd = 1000 + (int) (Math.random() * 8999);
        String fileNewName = type + "/" + ymd + "/" + ymdhms + rnd + fileExt;
        String saveFileName = webUploadDir + fileNewName;


        File saveFile = new File(saveFileName);
        if (!saveFile.getParentFile().exists()) {
            saveFile.getParentFile().mkdirs();
        }
        BufferedOutputStream out = null;
        try {
            out = new BufferedOutputStream(new FileOutputStream(saveFile));
            out.write(file.getBytes());
            out.flush();
            out.close();
        } catch (Exception e) {
            if (out != null) {
                try {
                    out.close();
                } catch (Exception e1) {
                }
            }
        }


        String result = "{\"name\":\"" + saveFileName + "\", \"originalName\": \"" + saveFileName + "\", \"size\": " + file.getSize() + ", \"state\": \"" + "SUCCESS" + "\", \"type\": \"" + fileExt + "\", \"url\": \"" + webUploadDomain + fileNewName + "\"}";
        result = result.replaceAll("\\\\", "\\\\");


        if (callback != null && callback.equals("fileName")) {
            return webUploadDomain + fileNewName;
        } else if (callback == null) {
            return result;
        } else {
            return "<script>" + callback + "(" + result + ")</script>";
        }
    }


    public static String httpGet(String url) {
        try {
            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .connectTimeout(3, TimeUnit.SECONDS)
                    .readTimeout(3, TimeUnit.SECONDS)
                    .writeTimeout(3, TimeUnit.SECONDS)
                    .retryOnConnectionFailure(false)
                    .build();
            Request request = new Request.Builder().url(url).build();
            Response response = null;
            response = okHttpClient.newCall(request).execute();
            if (response.isSuccessful()) {
                return response.body().string();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
