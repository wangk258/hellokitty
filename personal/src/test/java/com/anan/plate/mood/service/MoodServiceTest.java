package com.anan.plate.mood.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.anan.plate.core.TestBase;

public class MoodServiceTest extends TestBase {

	@Autowired
	private MoodService moodService;

	@Test
	public void getMP3() {
		try {
			String key=URLEncoder.encode("%");
			URL url = new URL("http://cloud.kugou.com/app/getSearchResult.php?key={"+key+"}&pageNo={1}&pageSize={-1}");
			InputStreamReader isr = new InputStreamReader(url.openStream());
			BufferedReader br = new BufferedReader(isr);
			String s;
			StringBuilder sb=new StringBuilder();
			while((s=br.readLine())!=null){
				System.out.println(s);
				sb.append(s);
			}
			br.close();
			isr.close();
			s=sb.toString();
			s.replace("\"", "'");
			JSONObject jo=JSONObject.fromObject(s);
			if(jo.getJSONArray("data").size()>1){
				JSONArray datas=jo.getJSONArray("data");
				for (int i = 0; i < datas.size(); i++) {
					System.out.println(datas.getJSONObject(i).get("FileName")+"-----"+datas.getJSONObject(i).get("SingerName")+"-----"+datas.getJSONObject(i).get("Hash"));
				}
				String link="http://cloud.kugou.com/singlePlayer/4/362/1/3/"+datas.getJSONObject(0).get("Hash")+".swf";
				System.out.println(link);
				return;
			}
			this.getMP3();
//			for(int i=0;i<ja.size();i++){
//				JSONObject
//			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
