package com.swsadWeb.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FileUploadUtil {
    // 使用日志工厂获取日志对象
    private static Log log = LogFactory.getLog(FileUploadUtil.class);

    private static String imgPath = "/Users/wangjing/Desktop/swsadPicture";
    //private static String imgPath = "C:/Users/Administrator/Desktop/image";

    //private static String imgURL = "image";
    private static String imgURL = "swsadPicture";

    private static String iconFileName = "icon";

    /**
     * 批量上传文件
     *
     * @param request
     * 用户名; 用于区分用户上传的图片
     * 模块名称; 用于区分该图片是位于那个模块进行上传
     * @return
     * @throws FileNotFoundException
     */
    public static List<String> uploadFile(HttpServletRequest request, String userUUID) throws FileNotFoundException {

        // 创建list集合，用于接收上传文件的路径
        List<String> filePathList = new ArrayList<String>();

        // 拼接文件上传位置，这里使用Tomcat服务器，将文件上传到webapps中，和项目同目录，files将用于保存上传的文件，将上传的文件于项目分开
        //String strPath = ",webapps,files," + moduleName + "," + username;

        // 解析出文件存放路径位置
        //String filepath = System.getProperty("catalina.base") + strPath.replace(',', File.separatorChar);

        String filepath = imgPath + File.separatorChar + userUUID;

        log.debug("文件上传路劲位置-------->>>>>>>>>>>>" + filepath);

        // 转换request，解析出request中的文件
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;

        // 获取文件map集合
        Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();

        String fileName = null;

        // 循环遍历，取出单个文件
        for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {

            // 获取单个文件
            MultipartFile mf = entity.getValue();

            // 获得原始文件名
            fileName = mf.getOriginalFilename();

            // 截取文件类型; 这里可以根据文件类型进行判断
            String fileType = fileName.substring(fileName.lastIndexOf('.'));

            try {
                // 截取上传的文件名称
                String newFileName = fileName.substring(0, fileName.lastIndexOf('.'));

                log.debug("上传来的文件名称------->>>>>>>>>" + newFileName);

                // 拼接上传文件位置
                String newfilePath = filepath + File.separatorChar + newFileName + fileType;

                log.debug("拼接好的文件路径地址------------->>>>>>>>" + newfilePath);

                // 重新组装文件路径，用于保存在list集合中
                String filepathUrl = imgURL + File.separatorChar + userUUID + File.separatorChar + newFileName + fileType;

                log.debug("文件位置---------------->>>>>>>>>>" + filepathUrl);

                // 创建文件存放路径实例
                File dest = new File(filepath);

                // 判断文件夹不存在就创建
                if (!dest.exists()) {
                    dest.mkdirs();
                }

                // 创建文件实例
                File uploadFile = new File(newfilePath);

                // 判断文件已经存在，则删除该文件
                if (uploadFile.exists()) {
                    uploadFile.delete();
                }

                log.debug("start upload file-------------->>>>>>> " + fileName);

                // 利于spring中的FileCopyUtils.copy()将文件复制
               // FileCopyUtils.copy(mf.getBytes(), uploadFile);

                mf.transferTo(uploadFile);

                // 将文件路径存入list集合中
                filePathList.add(filepathUrl);

            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();

                log.error("upload failed. filename: " + fileName+"---->>>error message ----->>>>> "+ e.getMessage());

                return null;
            }
        }

        return filePathList;
    }




    public static String uploadIcon(HttpServletRequest request, String userUUID) throws FileNotFoundException {

        // 创建list集合，用于接收上传文件的路径
        String filePathURL = "";

        // 拼接文件上传位置，这里使用Tomcat服务器，将文件上传到webapps中，和项目同目录，files将用于保存上传的文件，将上传的文件于项目分开
        //String strPath = ",webapps,files," + moduleName + "," + username;

        // 解析出文件存放路径位置
        //String filepath = System.getProperty("catalina.base") + strPath.replace(',', File.separatorChar);

        String filepath = imgPath + File.separatorChar + userUUID;

        log.debug("文件上传路劲位置-------->>>>>>>>>>>>" + filepath);

        // 转换request，解析出request中的文件
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;

        // 获取文件map集合
        Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();

        String fileName = null;

        // 循环遍历，取出单个文件
        for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {

            // 获取单个文件
            MultipartFile mf = entity.getValue();

            // 获得原始文件名
            fileName = mf.getOriginalFilename();

            // 截取文件类型; 这里可以根据文件类型进行判断
            String fileType = fileName.substring(fileName.lastIndexOf('.'));

            try {
                // 截取上传的文件名称
                //String newFileName = fileName.substring(0, fileName.lastIndexOf('.'));

                String newFileName = iconFileName;

                log.debug("上传来的文件名称------->>>>>>>>>" + newFileName);

                // 拼接上传文件位置
                String newfilePath = filepath + File.separatorChar + newFileName + fileType;

                log.debug("拼接好的文件路径地址------------->>>>>>>>" + newfilePath);

                // 重新组装文件路径，用于保存在list集合中
                String filepathUrl = imgURL + File.separatorChar + userUUID + File.separatorChar + newFileName + fileType;

                log.debug("文件位置---------------->>>>>>>>>>" + filepathUrl);

                // 创建文件存放路径实例
                File dest = new File(filepath);

                // 判断文件夹不存在就创建
                if (!dest.exists()) {
                    dest.mkdirs();
                }

                // 创建文件实例
                File uploadFile = new File(newfilePath);

                // 判断文件已经存在，则删除该文件
                if (uploadFile.exists()) {
                    uploadFile.delete();
                }

                log.debug("start upload file-------------->>>>>>> " + fileName);

                // 利于spring中的FileCopyUtils.copy()将文件复制
                // FileCopyUtils.copy(mf.getBytes(), uploadFile);

                mf.transferTo(uploadFile);

                // 将文件路径存入list集合中
                filePathURL = filepathUrl;

            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();

                log.error("upload failed. filename: " + fileName+"---->>>error message ----->>>>> "+ e.getMessage());

                return null;
            }
        }

        return filePathURL;
    }

    public static String uploadGroupIcon(HttpServletRequest request, Long groupId) throws FileNotFoundException {

        // 创建list集合，用于接收上传文件的路径
        String filePathURL = "";


        String filepath = imgPath + File.separatorChar + "group" + File.separatorChar + groupId;

        log.debug("文件上传路劲位置-------->>>>>>>>>>>>" + filepath);

        // 转换request，解析出request中的文件
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;

        // 获取文件map集合
        Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();

        String fileName = null;

        // 循环遍历，取出单个文件
        for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {

            // 获取单个文件
            MultipartFile mf = entity.getValue();

            // 获得原始文件名
            fileName = mf.getOriginalFilename();

            // 截取文件类型; 这里可以根据文件类型进行判断
            String fileType = fileName.substring(fileName.lastIndexOf('.'));

            try {
                // 截取上传的文件名称
                //String newFileName = fileName.substring(0, fileName.lastIndexOf('.'));

                String newFileName = iconFileName;

                log.debug("上传来的文件名称------->>>>>>>>>" + newFileName);

                // 拼接上传文件位置
                String newfilePath = filepath + File.separatorChar + newFileName + fileType;

                log.debug("拼接好的文件路径地址------------->>>>>>>>" + newfilePath);

                // 重新组装文件路径，用于保存在list集合中
                String filepathUrl = imgURL + File.separatorChar + "group" + File.separatorChar + groupId + File.separatorChar + newFileName + fileType;

                log.debug("文件位置---------------->>>>>>>>>>" + filepathUrl);

                // 创建文件存放路径实例
                File dest = new File(filepath);

                // 判断文件夹不存在就创建
                if (!dest.exists()) {
                    dest.mkdirs();
                }

                // 创建文件实例
                File uploadFile = new File(newfilePath);

                // 判断文件已经存在，则删除该文件
                if (uploadFile.exists()) {
                    uploadFile.delete();
                }

                log.debug("start upload file-------------->>>>>>> " + fileName);

                // 利于spring中的FileCopyUtils.copy()将文件复制
                // FileCopyUtils.copy(mf.getBytes(), uploadFile);

                mf.transferTo(uploadFile);

                // 将文件路径存入list集合中
                filePathURL = filepathUrl;

            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();

                log.error("upload failed. filename: " + fileName+"---->>>error message ----->>>>> "+ e.getMessage());

                return null;
            }
        }

        return filePathURL;
    }
}