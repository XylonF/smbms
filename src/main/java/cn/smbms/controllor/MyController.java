package cn.smbms.controllor;


import org.springframework.stereotype.Controller;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.swing.plaf.multi.MultiFileChooserUI;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
public class MyController {

    public String upload (MultipartFile upload) throws IOException {
        upload.getContentType();//文件类型
        upload.getSize();//文件大小
        String filename=upload.getOriginalFilename();//原文件名
        //获取当前Spring容器
        ServletContext application = ContextLoader.getCurrentWebApplicationContext().getServletContext() ;

        String path=application.getRealPath("statics/upload")+"\\"+ UUID.randomUUID().toString().replaceAll("-","")+filename.substring(filename.lastIndexOf('.'));
        upload.transferTo(new File(path));
        return "success";
    }
}
