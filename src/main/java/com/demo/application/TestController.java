package com.demo.application;


import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.ObjectMetadata;
import com.demo.com.ali.oss.util.OssFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Ivan on 2016/6/23.
 * controller需要和application.java在一个包内，这样才能找到controller类
 */
@RestController
public class TestController {
    @RequestMapping(value="/", produces = "text/html")
    public ModelAndView index(){
        ModelAndView model = new ModelAndView("/pages/index");
        return model;
    }
    @RequestMapping("/hello")
    String home() {
        return "Hello World!";
    }

    @RequestMapping("/getName/{name}")
    public String getName(@PathVariable String name){
        System.out.println("name:"+name);
        return name;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public
    @ResponseBody
    Response save(HttpServletRequest request) {
        String content = request.getParameter("content");
        String name = request.getParameter("name");
        StringBuffer p = new StringBuffer();
        p.append("<!DOCTYPE html> \n" +
                "<html>\n" +
                "<head>\n" +
                "<meta charset=\"utf-8\">\n" +
                "<meta name=\"viewport\" content=\"width=device-width,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no\"/>" +
                "<title>jQuery Mobile Web 应用程序</title>\n" +
                "<link rel=\"stylesheet\" href=\"http://code.jquery.com/mobile/1.3.2/jquery.mobile-1.3.2.min.css\">" +
                "<script src=\"http://code.jquery.com/jquery-1.8.3.min.js\"></script>" +
                "<script src=\"http://code.jquery.com/mobile/1.3.2/jquery.mobile-1.3.2.min.js\"></script>" +
                "</head> \n" +
                "<body><!--<div data-role=\"header\"></div>--> ");
        p.append(content);
        p.append("<!--<div data-role=\"footer\"> </div>-->"+
                "</body>\n" +
                "</html>");
        p.append("\r\n");

        OSSClient client = OssFactory.getClient();
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType("text/html");
        client.putObject("test-xiaoxin", "htmls/" + name + ".html", new ByteArrayInputStream(p.toString().getBytes()), metadata);
        String url = "http://test-xiaoxin.oss-cn-shanghai.aliyuncs.com/htmls/" + name + ".html";
        return new Response(1, "success", url);
    }

    /**
     * 上传Froala Editor 图片
     *
     * @throws Exception
     */
    @RequestMapping("/uploadImgEditor")
    @ResponseBody
    Map<String, String> uploadImgEditor(MultipartHttpServletRequest request, Model model) throws Exception {
        MultipartFile orginalFile = request.getFile("file");
        Map<String, String> map = new HashMap<String, String>();
        System.out.println("size:" + orginalFile.getSize());
        OSSClient client = OssFactory.getClient();
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(orginalFile.getContentType());
        client.putObject("test-xiaoxin", "test/" + orginalFile.getOriginalFilename(), orginalFile.getInputStream(), metadata);
        String url = "http://test-xiaoxin.oss-cn-shanghai.aliyuncs.com/test/" + orginalFile.getOriginalFilename();
        System.out.println("url:" + url);
        map.put("link", url);
        return map;
    }
}
