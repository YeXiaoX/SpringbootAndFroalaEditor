package com.demo.com.ali.oss.util;

import com.aliyun.oss.OSSClient;

/**
 * Created by Ivan on 2016/6/21.
 */
public class OssFactory {
//    final static String  endpoint = "oss-cn-shanghai.aliyuncs.com";
//    // accessKey请登录https://ak-console.aliyun.com/#/查看
//    final static String accessKeyId = "q7vHi6atC8cA6wns";
//    final static String accessKeySecret = "uWp9X9tPN4fgFNdBybLB4JTDWpSofm";

    final static String  endpoint = "oss-cn-qingdao.aliyuncs.com";
    final static String accessKeyId = "zTT0TobirfTd4noZ";
    final static String accessKeySecret = "SPnXY8RYxNI9MvxyyF24cn4qjIqqd3";
    // 创建OSSClient实例
    final static OSSClient client = new OSSClient(endpoint, accessKeyId, accessKeySecret);
// 使用访问OSS
// 关闭client

    public static OSSClient getClient(){
        return client;
    }
}
