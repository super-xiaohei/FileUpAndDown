package net.suncaper.fileprocess.controller;

import net.suncaper.fileprocess.common.JsonResult;
import net.suncaper.fileprocess.entity.FileEntity;
import net.suncaper.fileprocess.mapper.FileEntityMapper;
import net.suncaper.fileprocess.service.FileEntityService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author zyq
 * @date 2021/1/10 - 13:36
 */
@RequestMapping("/file")
@Controller
public class FileController {
    private static String realPath = "C:\\Users\\Administrator\\IdeaProjects\\fileprocess\\src\\main\\resources\\static\\file\\";

    @Autowired
    private FileEntityService fileEntityService;

    /**
     * 文件上传
     * @param request
     * @param multipartFile
     * @param model
     * @return
     */
    @RequestMapping("/upload")
    public String upload(HttpServletRequest request, MultipartFile[] multipartFile, Model model){
        String realPath2 = request.getServletContext().getRealPath("/static/file");
        System.out.println(realPath2);
        File file = new File(FileController.realPath);
        if(!file.exists()){
            file.mkdirs();
        }
        if(multipartFile == null){
            model.addAttribute("msg","请点击" + "\"文件上传\"" + "按钮选择文件");
            return "index";
        }
        for (MultipartFile multipartFile1 : multipartFile) {
            if(multipartFile1.getOriginalFilename() == null || "".equals(multipartFile1.getOriginalFilename())){
                model.addAttribute("msg","请至少选择一个文件");
                return "index";
            }
        }

        List<FileEntity> fileEntityList = Stream.of(multipartFile).filter(x -> x.getOriginalFilename() != null && !"".equals(x.getOriginalFilename())).map(x -> {
            String filePath = UUID.randomUUID().toString().substring(0, 6) + x.getOriginalFilename(); //bcd7e1jdk-8u271-linux-x64.tar.gz
            File fileUpload = new File(FileController.realPath + "/" + filePath); //C:\Users\Administrator\IdeaProjects\fileprocess\src\main\resources\static\file\bcd7e1jdk-8u271-linux-x64.tar.gz
            System.out.println(fileUpload);
            if (!fileUpload.exists()) {
                try {
                    fileUpload.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                    x.transferTo(fileUpload);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                FileEntity fileEntity = new FileEntity();
                fileEntity.setFileName(x.getOriginalFilename());
                fileEntity.setFilePath(filePath);
            return fileEntity;
        }).collect(Collectors.toList());
        model.addAttribute("fileEntityList",fileEntityList);
        fileEntityService.upload(fileEntityList);
        return "upLoadSuccess";
    }

    @RequestMapping("/selectAll")
    public String selectAll(Model model){
        List<FileEntity> fileEntitys = fileEntityService.selectAll();
        model.addAttribute("fileEntitys",fileEntitys);
        return "index";
    }

    /**
     * 文件下载
     * @param id
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/downLoadById")
    public JsonResult downLoadById(int id, HttpServletRequest request, HttpServletResponse response) {
        FileEntity fileEntity = fileEntityService.selectById(id);
        String downloadFilePath = realPath;//被下载的文件在服务器中的路径,
        String fileName = fileEntity.getFileName();//被下载文件的名称
        System.out.println(fileName);

        File file = new File(downloadFilePath);
        if (file.exists()) {
            response.setContentType("application/force-download");// 设置强制下载不打开
            response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);
            byte[] buffer = new byte[1024];
            FileInputStream fis = null;
            BufferedInputStream bis = null;
            try {
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                OutputStream outputStream = response.getOutputStream();
                int i = bis.read(buffer);
                while (i != -1) {
                    outputStream.write(buffer, 0, i);
                    i = bis.read(buffer);
                }

                return JsonResult.ok(true);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (bis != null) {
                    try {
                        bis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return JsonResult.ok(false);
    }
    }
