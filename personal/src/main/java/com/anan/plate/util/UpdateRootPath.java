package com.anan.plate.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class UpdateRootPath {

	private static final String[] ladygaga={"ueditor","jquery","page","jPaginate","date"};
	
	private static BufferedReader reader=null;
	
	private static BufferedWriter writer=null;
	
	private static final String orgContextPath="/ss";
	
	private static final String contextPath="/"; 
	
	
	public static void main(String[] args) throws Exception {
		File file=new File("E:/personal/src/main/webapp");
		findCssFile(file);
	}
	
	private static void findCssFile(File file) throws Exception{
		if(file.isDirectory()){
			for (File  file1 :file.listFiles() ) {
				String absolutePath=file.getAbsolutePath();
				boolean flag=false;
				for (String gaga : ladygaga) {
					if(absolutePath.contains(gaga)){
						flag=true;
						break;
					}
				}
				if(flag){
					continue;
				}
				findCssFile(file1);
			}
		}
		else{
			String fish=file.getAbsolutePath();
			if(fish.contains(".jsp")&&!fish.contains("jquery")){
				updateCssFile(new File(fish));
			}
		}
	}
	
	private static void updateCssFile(File file) throws Exception{
		reader=new BufferedReader(new FileReader(file));
		String s="";
		StringBuilder text=new StringBuilder();
		Integer lineNumber=0;
		while((s=reader.readLine())!=null){
			lineNumber++;
			if(s.contains("<script")){
				if("/".equals(contextPath)){
					s=s.replace(orgContextPath, "");
				}
				else{
					if(!s.contains(contextPath)){
						if("/".equals(orgContextPath)||!s.contains(orgContextPath)){
							int start=s.indexOf("src=\"");
							int end=s.indexOf(".js\"");
							if(start>=0 && end >=0){
								String temp=s.substring(start+5,end+3);
								s=s.replace(temp, contextPath+temp);
							}
						}
						else{
							s=s.replace(orgContextPath, contextPath);
						}
					}
					else{
						s=s.replace(orgContextPath, contextPath);
					}
				}
			}
			else if(s.contains("<link")){
				if("/".equals(contextPath)){
					s=s.replace(orgContextPath, "");
				}
				else{
					if(!s.contains(contextPath)){
						if("/".equals(orgContextPath)||!s.contains(orgContextPath)){
							int start=s.indexOf("href=\"");
							int end=s.indexOf(".css\"");
							if(start>=0 && end >=0){
								String temp=s.substring(start+6,end+4);
								s=s.replace(temp, contextPath+temp);
							}
						}
						else{
							s=s.replace(orgContextPath, contextPath);
						}
					}
					else{
						s=s.replace(orgContextPath, contextPath);
					}
				}
			}
			System.out.println(s);
			text.append(s+"\r\n");
		}
		reader.close();
		writer = new BufferedWriter(new FileWriter(file));
		writer.write(text.toString());
		writer.flush();
		writer.close();
	}
}
