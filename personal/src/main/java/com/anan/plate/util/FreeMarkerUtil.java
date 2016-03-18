package com.anan.plate.util;

import java.io.File;
import java.io.FileWriter;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class FreeMarkerUtil {

	private static FreeMarkerUtil util=new FreeMarkerUtil();
	
	private FreeMarkerUtil(){
		
	}
	
	public static FreeMarkerUtil getInstance(){
		return  util;
	}
	
	private Template getTemplate(String name) throws Exception{
		Configuration cfg=new Configuration();
		cfg.setDirectoryForTemplateLoading(new File(System.getProperty("user.dir")+"/src/main/java/ftl"));
		return cfg.getTemplate(name);
	}
	
	public static void writeToFile(String name,Map<String,Object> root,String filePath) throws Exception{
		Template template=FreeMarkerUtil.getInstance().getTemplate(name);
		FileWriter out=new FileWriter(new File(filePath));
		template.process(root, out);
	}
}
