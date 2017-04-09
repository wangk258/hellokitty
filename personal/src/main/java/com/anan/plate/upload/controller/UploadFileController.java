package com.anan.plate.upload.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import common.base.BaseController;
import common.bo.ResultFlag;
import common.util.FileUploadUtil;

@SuppressWarnings("rawtypes")
@Controller
public class UploadFileController extends BaseController {

	@Autowired
	private FileUploadUtil fileService;
	
	/**
	 * 
	 * @param req
	 * @param response
	 * @param type 上传的文件夹名称
	 */
	@RequestMapping("/upload")
	@ResponseBody
	public void upload(MultipartRequest req,HttpServletResponse response,String type) {
		try {
			MultipartFile file = req.getFile("filedata");
			resultFlag =  this.fileService.upload(req, file,type);
		} catch (Exception e) {
			e.printStackTrace();
			resultFlag.setMsg(e.getMessage());
		}
		try {
			response.getWriter().print(resultFlag.getData());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
