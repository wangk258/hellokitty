package common.util;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import com.aliyun.oss.OSSClient;

import common.bo.ResultFlag;

@Service
public class FileUploadUtil {
	/**
	 * 阿里云oss存储 AccessKeyId
	 */
	private String accessKeyId;
	
	/**
	 * 阿里云oss存储AccessKeySecret
	 */
	private String accessKeySecret;
	
	/**
	 * 阿里云oss远程存储地址
	 */
	private String endpoint;
	
	/**
	 * 阿里云oss存储包名称
	 */
	private String bucketName;
	/**
	 * 阿里云oss资源读取地址
	 */
	private String readBaseUrl;
	
	public void setReadBaseUrl(String readBaseUrl) {
		this.readBaseUrl = readBaseUrl;
	}

	public void setBucketName(String bucketName) {
		this.bucketName = bucketName;
	}

	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}
	
	public void setAccessKeySecret(String accessKeySecret) {
		this.accessKeySecret = accessKeySecret;
	}
	
	public void setAccessKeyId(String accessKeyId) {
		this.accessKeyId = accessKeyId;
	}
	
	
	public ResultFlag upload(MultipartRequest request, MultipartFile files,String type) throws Exception {
		ResultFlag flag=new ResultFlag();
		try{
			String originalFileName=files.getOriginalFilename();
			String extName=originalFileName.substring(originalFileName.lastIndexOf(".")+1);
			String newFileName=UUID.randomUUID().toString()+"."+extName;
			OSSClient client = new OSSClient(endpoint,accessKeyId,accessKeySecret);
			String  key = type+"/"+newFileName;
			client.putObject(bucketName, key, files.getInputStream());
			flag.setError(false);
			flag.setData("{'url':'"+readBaseUrl+"/"+key+"','fileType':'"+extName+"','state':'SUCCESS','original':'"+originalFileName+"'}");
		}catch(Exception e){
			flag.setError(true);
			flag.setData("{'url':'','fileType':'','state':'SUCCESS','original':''}");
			throw new RuntimeException(e.getMessage());
		}
		return flag;
	}
}
