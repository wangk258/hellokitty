package org.util;

import java.io.File;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.bo.ResultFlag;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

@Service
public class FileUploadUtil {
	
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
