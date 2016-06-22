package com.demo.application;

import com.aliyun.oss.HttpMethod;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.common.utils.DateUtil;
import com.aliyun.oss.model.GeneratePresignedUrlRequest;
import com.aliyun.oss.model.ObjectMetadata;
import com.demo.com.ali.oss.util.OssFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Ivan on 2016/6/21.
 */
@RestController
@Configuration
@EnableAutoConfiguration
@ComponentScan
public class Application {
    @RequestMapping("/")
    String home() {
        return "Hello World!";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public
    @ResponseBody
    Response save(HttpServletRequest request) {
        String content = request.getParameter("content");
        String name = request.getParameter("name");
//        File f = new File( name + ".html");
//        FileOutputStream fs = null;
//        PrintStream p = null;
//        try {
//            fs = new FileOutputStream(f);
//            p = new PrintStream(fs);
        StringBuffer  p =new StringBuffer();
            p.append("<!DOCTYPE html> \n" +
                    "<html>\n" +
                    "<head>\n" +
                    "<meta charset=\"utf-8\">\n" +
                    "<meta name=\"viewport\" content=\"width=device-width,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no\"/>" +
                    "<title>jQuery Mobile Web 应用程序</title>\n" +
                    "<link href=\"jquery.mobile-1.0.min.css\" rel=\"stylesheet\" type=\"text/css\"/>\n" +
                    "<script src=\"jquery-1.6.4.min.js\" type=\"text/javascript\"></script>\n" +
                    "</head> \n" +
                    "<body><div data-role=\"header\"></div> ");
            p.append(content);
            p.append("</body>\n" +
                    "</html>");
            p.append("\r\n");
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            p.close();
//            try {
//                fs.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
       // }

        OSSClient client = OssFactory.getClient();
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType("text/html");
        client.putObject("test-xiaoxin1", "htmls/" + name+".html", new ByteArrayInputStream(p.toString().getBytes()), metadata);

        System.out.println(content);
        return new Response(1, "success");
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
//        DateFormat gmtFormat = new SimpleDateFormat("EEE, dd MMM yyyy hh:mm:ss z", Locale.ENGLISH);
//        TimeZone gmtTime = TimeZone.getTimeZone("GMT");
//        gmtFormat.setTimeZone(gmtTime);
//        Date date = new Date();
//        System.out.println("date:" + gmtFormat.format(date));
//        //Date expiration = DateUtil.parseRfc822Date(gmtFormat.format(date));
//        Date expiration = new Date(new Date().getTime() + 3600 * 1000);

//        GeneratePresignedUrlRequest request1 = new GeneratePresignedUrlRequest("test-xiaoxin", orginalFile.getOriginalFilename()+".png", HttpMethod.GET);
////设置过期时间
//        request1.setExpiration(expiration);
// 生成URL签名(HTTP GET请求)
        // URL url = client.generatePresignedUrl("test-xiaoxin", "test/"+orginalFile.getOriginalFilename(),expiration);

        client.shutdown();
        String url = "http://test-xiaoxin.oss-cn-shanghai.aliyuncs.com/test/" + orginalFile.getOriginalFilename();
        System.out.println("url:" + url);
        map.put("link", url);
        return map;

    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }
}
