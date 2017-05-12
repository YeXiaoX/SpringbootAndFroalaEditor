package com.demo.com.ali.oss.util;

import com.aliyun.oss.OSSClient;

/**
 * Created by Ivan on 2016/6/21.
 */
public class OssFactory {
    final static String  endpoint = "oss-cn-shanghai.aliyuncs.com";
    // accessKey请登录https://ak-console.aliyun.com/#/查看
    final static String accessKeyId = "";
    final static String accessKeySecret = "";
//
//    final static String  endpoint = "oss-cn-qingdao.aliyuncs.com";
//    final static String accessKeyId = "";
//    final static String accessKeySecret = "";
    // 创建OSSClient实例
    final static OSSClient client = new OSSClient(endpoint, accessKeyId, accessKeySecret);
// 使用访问OSS
// 关闭client

    public static OSSClient getClient(){
        return client;
    }
}
