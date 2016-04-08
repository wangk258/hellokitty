package common.util;

import java.io.File;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

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
	
	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}
	
	public void setAccessKeySecret(String accessKeySecret) {
		this.accessKeySecret = accessKeySecret;
	}
	
	public void setAccessKeyId(String accessKeyId) {
		this.accessKeyId = accessKeyId;
	}
	
	
	public ResultFlag upload(MultipartRequest request, MultipartFile files,String filePath) throws Exception {
		String originalFileName=files.getOriginalFilename();
		String extName=originalFileName.substring(originalFileName.lastIndexOf(".")+1);
		String newFileName=UUID.randomUUID().toString()+"."+extName;
		HttpServletRequest req=(HttpServletRequest)request;
		String realPath=req.getSession().getServletContext().getRealPath(filePath);
		File file=new File(realPath+File.separator+newFileName);
		if(!file.exists()){
			file.getParentFile().mkdirs();
		}
		files.transferTo(file);
		ResultFlag flag=new ResultFlag();
		flag.setFlag(true);
		flag.setData("{'url':'/"+filePath+"/"+newFileName+"','fileType':'"+extName+"','state':'SUCCESS','original':'"+originalFileName+"'}");
		return flag;
	}
}
