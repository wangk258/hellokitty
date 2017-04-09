package com.xiaoxuanfeng.plate.common.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

import org.apache.commons.lang.StringUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

public class AutoCreateCode {

	static String project = "E:\\MyProject\\MyWorkSpace\\WEBSITE\\";

	// 必须 templates.length==paths.length
	static String[] templates = { "Dao.java", "DaoImpl.java",
			"Controller.java", "Service.java", "ServiceImpl.java",
			"QueryObject.java", "DaoTest.java", "ServiceTest.java",
			"Domain.java" };
	static String packageName = "src/main/java/com/xiaoxuanfeng/plate/";
	static String testPackageName = "src/test/java/com/xiaoxuanfeng/plate/";
	static String[] paths = { "/dao/", "/dao/impl/", "/controller/",
			"/service/", "/service/impl/", "/bo/", "/dao/", "/service/",
			"/domain/" };
	static StringBuilder xmlPaths = new StringBuilder();
	// 必须entity.lengths==chineseEntityName
	// 实体的名称
	static String[] entitys = {"Banner"};
	// 功能名称
	static String[] functionName = {"banner"};
	// 额外包名称
	static String[] extPackage ={};

	/**
	 * 把多个单词组成的实体类的字符串的首字母变成小写：例如一个实体名为OaAssetTypePar：变成oaAssetTypePar
	 * 
	 * @param str
	 * @return
	 */
	public static String toLowerInitialWord(String str) {
		return str.substring(0, 1).toLowerCase() + str.substring(1);
	}

	public static void main(String[] args) throws Exception {
		VelocityContext context = new VelocityContext();
		for (int i = 0; i < entitys.length; i++) {
			context.put("Entity", entitys[i]);
			String initialWord = toLowerInitialWord(entitys[i]);
			context.put("entity", initialWord);
			context.put("functionName", functionName[i]);
			if (extPackage.length>0&&StringUtils.isNotBlank(extPackage[i])) {
				context.put("extPackage", "." + extPackage[i]);
			} else {
				context.put("extPackage", "");
			}
			for (int j = 0; j < templates.length; j++) {
				Template template = Velocity.getTemplate("template/"
						+ templates[j], "UTF-8");
				String filePathName = null;

				if ("Domain.java".equals(templates[j])) {
					if (extPackage.length>0&&StringUtils.isNotBlank(extPackage[i])) {
							filePathName = project + packageName + functionName[i]
									+ "/" + extPackage[i] + paths[j] + entitys[i]
									+ ".java";
					} else {
							filePathName = project + packageName + functionName[i]
									+ paths[j] + entitys[i] + ".java";
					}
				} else if ("DaoTest.java".equals(templates[j])
						|| "ServiceTest.java".equals(templates[j])) {
					if (extPackage.length>0&&StringUtils.isNotBlank(extPackage[i])) {
						filePathName = project + testPackageName + functionName[i]
								+ "/" + extPackage[i] + paths[j] + entitys[i]
								+ templates[j];
					} else {
						filePathName = project + testPackageName + functionName[i]
								+ paths[j] + entitys[i] + templates[j];
					}
				}
				else{
					if (extPackage.length>0&&StringUtils.isNotBlank(extPackage[i])) {
						filePathName = project + packageName + functionName[i]
								+ "/" + extPackage[i] + paths[j] + entitys[i]
								+ templates[j];
					}
					else{
						filePathName = project + packageName + functionName[i]
								+ paths[j] + entitys[i] + templates[j];
					}
				}
				System.out.println(filePathName);
				File savefile = new File(filePathName);
				// 如果当前文件的父路径没有,就创建
				if (!savefile.getParentFile().exists())
					savefile.getParentFile().mkdirs();
				// 如果代码已经存在,不要覆盖
				if (!savefile.exists()) {
					FileOutputStream outstream = new FileOutputStream(savefile);
					OutputStreamWriter writer = new OutputStreamWriter(
							outstream, "UTF-8");// 必须设置编码格式
					BufferedWriter bufferWriter = new BufferedWriter(writer);
					template.merge(context, bufferWriter);
					bufferWriter.flush();
					outstream.close();
					bufferWriter.close();
				}
			}
		}
		System.out.println("代码已经ok,注意清空entitys");
	}
}
