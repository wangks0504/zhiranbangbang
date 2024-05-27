package org.zhiran.utils;
import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.common.auth.*;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;
import org.zhiran.pojo.Category;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.InputStream;

public class AliOssUtil extends Category {
   private static final String ENDPOINT = "https://oss-cn-hangzhou.aliyuncs.com";
    // 从环境变量中获取访问凭证。运行本代码示例之前，请确保已设置环境变量OSS_ACCESS_KEY_ID和OSS_ACCESS_KEY_SECRET。
//        EnvironmentVariableCredentialsProvider credentialsProvider = CredentialsProviderFactory.newEnvironmentVariableCredentialsProvider();
    private static final String ACCESSKEY_ID = "LTAI5t8rg5NZGWYat2q9VnNo";
    private static final String ACCESSKEY_SECRET = "VqcL2IP33k5G1gjUlC3RjLSqzBGNao";
    // 填写Bucket名称，例如examplebucket。
    private static final String BUCKETNAME = "zhiranbangbang";
    // 填写Object完整路径，完整路径中不能包含Bucket名称，例如exampledir/exampleobject.txt。

    public static String uploadFile(String objectName, InputStream in) throws Exception {
        // Endpoint以华东1（杭州）为例，其它Region请按实际情况填写。
        String objectNameEnd = "zhiran/source/picture/" + objectName;

        // 创建OSSClient实例。//build中填写accesskey密钥
        OSS ossClient = new OSSClientBuilder().build(ENDPOINT, ACCESSKEY_ID,ACCESSKEY_SECRET);
        String URL = "" ;
        try {
            // 填写字符串。
            String content = "Hello OSS，你好世界";

            // 创建PutObjectRequest对象。//输入文件流
            PutObjectRequest putObjectRequest = new PutObjectRequest(BUCKETNAME, objectNameEnd, in);
//https://zhiranbangbang.oss-cn-hangzhou.aliyuncs.com/zhiran/source/picture/836fe633-facd-4c53-ac64-7f157f6fc145addtestUpload.tpng
            URL = "https://" + BUCKETNAME + "." +  ENDPOINT.substring(ENDPOINT.lastIndexOf("/")+1) +"/" + objectNameEnd;
            // 如果需要上传时设置存储类型和访问权限，请参考以下示例代码。
            // ObjectMetadata metadata = new ObjectMetadata();
            // metadata.setHeader(OSSHeaders.OSS_STORAGE_CLASS, StorageClass.Standard.toString());
            // metadata.setObjectAcl(CannedAccessControlList.Private);
            // putObjectRequest.setMetadata(metadata);

            // 上传字符串。
            PutObjectResult result = ossClient.putObject(putObjectRequest);
            //自定义拼接URL地址
        } catch (OSSException oe) {
            System.out.println("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            System.out.println("Error Message:" + oe.getErrorMessage());
            System.out.println("Error Code:" + oe.getErrorCode());
            System.out.println("Request ID:" + oe.getRequestId());
            System.out.println("Host ID:" + oe.getHostId());
        } catch (ClientException ce) {
            System.out.println("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message:" + ce.getMessage());
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
        return URL;
    }
}

