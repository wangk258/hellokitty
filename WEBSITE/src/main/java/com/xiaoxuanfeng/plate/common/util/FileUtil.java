package com.xiaoxuanfeng.plate.common.util;

import java.io.File;
import java.net.FileNameMap;
import java.net.URLConnection;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import com.xiaoxuanfeng.plate.common.constants.Constant;

/**
 * @desc <h3>文件工具类</h3>
 * @date 2014-8-6 下午03:19:11
 * @author tangk
 */
public class FileUtil {

	/**
	 * 
	 * @desc 获取文件后缀
	 * @param fileName
	 *            待获取的文件名
	 * @return 文件后缀名
	 * @throws Exception
	 *             空指针
	 * @exception 异常说明
	 * @date 2014-8-6 下午03:37:17
	 * @author tangk
	 */
	public static String getFilePrefix(String fileName) throws Exception {

		return fileName.substring(fileName.lastIndexOf(".") + 1);
	}

	public static String saveFile(MultipartFile applyFile, String floder) throws Exception {

		String saveFilePath =  Constant.UPLOADFILEDIR + "/" + floder+"/";
		if (applyFile != null) {
			String applyFileName = applyFile.getOriginalFilename();
			if (StringUtils.isNotEmpty(applyFileName)) {
				;
				// 创建文件
				File dirPath = new File(Constant.CTXDIR+"/"+saveFilePath);
				if (!dirPath.exists()) {
					dirPath.mkdirs();
				}
				saveFilePath += UUIDGenerator.generate() + "." + getFilePrefix(applyFileName);
				File uploadFile = new File(Constant.CTXDIR+"/"+saveFilePath);
				FileCopyUtils.copy(applyFile.getBytes(), uploadFile);
			}
		}
		return saveFilePath;
	}

	/**
	 * 
	 * @desc 获取路径
	 * @param fileUrl
	 * @return
	 * @throws java.io.IOException
	 * @throws Exception
	 * @exception 异常说明
	 * @date 2014-9-23 下午06:01:28
	 * @author tangk
	 */
	public final static String getMimeType(String fileUrl) throws java.io.IOException {
		FileNameMap fileNameMap = URLConnection.getFileNameMap();
		String type = fileNameMap.getContentTypeFor(fileUrl);
		return type;
	}

	public static void main(String args[]) throws Exception {
		System.out.println(FileUtil.getMimeType("file://c:/temp/test.TXT"));
		// output : text/plain
	}
}
