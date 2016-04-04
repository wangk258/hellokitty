package com.anan.plate.upload.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import common.bo.ResultFlag;
import common.util.FileUploadUtil;

@Controller
public class UploadFileController {

	@Autowired
	private FileUploadUtil fileService;
	
	/**
	 * 
	 * @param req
	 * @param response
	 * @param type 上传的文件夹名称
	 */
	@RequestMapping("/upload")
	public void upload(MultipartRequest req,HttpServletResponse response,String type) {
		try {
			MultipartFile file = req.getFile("filedata");
			ResultFlag resultFlag = this.fileService.upload(req, file,type);
			if (resultFlag.getFlag()) {
				response.getWriter().print(resultFlag.getData());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
