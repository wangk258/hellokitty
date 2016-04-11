package common.rdbms.base;

import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import common.bo.ResultFlag;
import net.sf.json.JSONObject;


public class BaseController {

	protected static ResultFlag resultFlag=new ResultFlag();
	
	public ResultFlag setRightFlag(Object o){
		resultFlag.setFlag(true);
		resultFlag.setData(o);
		return resultFlag;
	}
	
	public ResultFlag setErrorFlag(String msg){
		resultFlag.setFlag(false);
		resultFlag.setMsg(msg);
		return resultFlag;
	}
	
	public void write(HttpServletResponse response) throws Exception{
		OutputStream out=response.getOutputStream();
		out.write(JSONObject.fromObject(resultFlag).toString().getBytes());
	}
}
